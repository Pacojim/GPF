package com.gpf.rebisoft.gpf.adapters;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.clasesbase.Cuota;
import com.gpf.rebisoft.gpf.database.GPFDataSource;
import com.gpf.rebisoft.gpf.utils.Utilidades;

import java.util.ArrayList;

/**
 * Adapter para rellenar un listview que gestionará la adición de cuotas a las temporadas y mostrará también las que tienen añadidas
 */
public class TemporadaCuotasListAdapter extends BaseAdapter {

    private Context context;

    /**
     * Lista con todas las plantillas de cuotas registradas en la aplicación
     */
    private ArrayList<Cuota> cuotas;
    private HolderCuotasTemporada holderCuotasTemporada;
    private GPFDataSource dataSource;
    private int idTemporada;

    /**
     * Constructor de la clase.
     * @param context contexto de la aplicación
     * @param cuotas lista de plantillas de cuotas en la aplicación
     * @param idTemporada id de la temporada sobre la que se van a consultar las cuotas que tiene añadidas o sobre
     * se quieren añadir
     */
    public TemporadaCuotasListAdapter(Context context, ArrayList<Cuota> cuotas, int idTemporada) {
        this.context = context;
        this.cuotas = cuotas;
        this.dataSource = new GPFDataSource(context);
        this.idTemporada = idTemporada;
    }

    /**
     * Devuelve la cantidad de elementos cargados en el adapter
     * @return cantidad de elementos cargados en el adapter
     */
    @Override
    public int getCount() {
        return cuotas.size();
    }

    /**
     * Obtiene un elemento del adapter a partir de una posición
     * @param position posición del elemnto seleccionado
     * @return devuelve el elemento requerido
     */
    @Override
    public Object getItem(int position) {
        return cuotas.get(position);
    }

    /**
     * Devuelve el ID del elemnto situado en una posición del adapter
     * @param position posición solicitada
     * @return ID del elemnto solicitado
     */
    @Override
    public long getItemId(int position) {
        return cuotas.get(position).getId();
    }

    /**
     * Crea las vistas que van a mostrar en listview que use este adapter
     * @param position posición del elemento que se muestra
     * @param convertView vista a mostrar
     * @param parent ViewGroup padre
     * @return
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holderCuotasTemporada = new HolderCuotasTemporada();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview_cuotas_temporada, parent, false);

            holderCuotasTemporada.setCodigo((TextView) convertView.findViewById(R.id.textViewCodCuotasTemporada));
            holderCuotasTemporada.setNombre((TextView) convertView.findViewById(R.id.textViewNombreCuotasTemporada));
            holderCuotasTemporada.setDescripcion((TextView) convertView.findViewById(R.id.textViewDescripcionCuotasTemporada));
            holderCuotasTemporada.setOrden((TextView) convertView.findViewById(R.id.textViewOrdenCuotasTemporada));
            holderCuotasTemporada.setCuantia((TextView) convertView.findViewById(R.id.textViewCuantiaCuotasTemporada));
            holderCuotasTemporada.setFechaLimite((TextView) convertView.findViewById(R.id.textViewFechaLimiteCuotasTemporada));
            holderCuotasTemporada.setAdd((ToggleButton) convertView.findViewById(R.id.togglePagarCuotasTemporada));
            convertView.setTag(holderCuotasTemporada);
        } else {
            holderCuotasTemporada = (HolderCuotasTemporada) convertView.getTag();
        }
        final View convert = convertView;
        holderCuotasTemporada.getCodigo().setText(this.cuotas.get(position).getCodigo());
        holderCuotasTemporada.getNombre().setText(this.cuotas.get(position).getNombre());
        holderCuotasTemporada.getDescripcion().setText(this.cuotas.get(position).getDescripcion());
        holderCuotasTemporada.getOrden().setText(String.valueOf(this.cuotas.get(position).getOrden()));
        holderCuotasTemporada.getCuantia().setText(String.valueOf(this.cuotas.get(position).getCuantia()));
        holderCuotasTemporada.getFechaLimite().setText(Utilidades.pasarDateString(this.cuotas.get(position).getFechaLimite()));
        if(this.cuotas.get(position).getIdTemporada() != 0){
            holderCuotasTemporada.getAdd().setChecked(true);
        } else {
            holderCuotasTemporada.getAdd().setChecked(false);
        }

        /**
         * Establecemos el comportamiento del botón añadir/quitar cuota de una temporada.
         * - Podemos añadir o quitar cuotas con normalidad, a no ser que en una temporada ya se hayan hecho
         * pagos de la cuota que se quiere borrar, entonces la aplicación no lo permitirá y lanzará un mensaje de alerta
         * al usuario.
         */
        holderCuotasTemporada.getAdd().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (((ToggleButton) v).isChecked()) {
                    cuotas.get(position).setIdTemporada(idTemporada);
                    dataSource.addCuotaTemporada(cuotas.get(position));
                } else if (!((ToggleButton) v).isChecked()) {
                    if (!dataSource.deleteCuotaTemporada(cuotas.get(position).getId(), cuotas.get(position).getIdTemporada())) {
                        ((ToggleButton) v).setChecked(true);
                        crearDialog().show();
                    } else {
                        cuotas.get(position).setIdTemporada(0);
                    }
                }
            }
        });


        return convertView;
    }

    /**
     * Mensaje de alerta para el usuario informando de que una cuota no se puede borrar de una temporada
     * porque ya se han hecho transacciones con ella.
     * @return AlerDialog
     */
    private AlertDialog crearDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("La cuota no se puede eliminar.")
                .setMessage("Se han realizado gestiones esta temporada con esta cuota. " +
                        "Puede editarla en Ajustes -> Cuotas, pero se editará en todas las temporadas que la tengan añadida.")
                .setPositiveButton("OK", null);

        return builder.create();
    }
}
