package com.gpf.rebisoft.gpf.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.clasesbase.Cuota;
import com.gpf.rebisoft.gpf.database.GPFDataSource;
import com.gpf.rebisoft.gpf.utils.Utilidades;

import java.util.ArrayList;

/**
 * Adapter para rellenar un listview con los datos de las cuotas que ha de afrontar un jugador en una determinada
 * temporada
 */
public class CuotasJugadorListAdapter extends BaseAdapter {

    private Context context;

    /**
     * Lista donde se almacenan las cuotas a satisfacer por el jugador en una temporada
     */
    private ArrayList<Cuota> cuotas;
    // Clase auxiliar para gestionar la vista
    private HolderCuotasJugador holderCuotasJugador;
    // Constantes para establecer valores
    final int PAGAR_CUOTA_DESCARTADA = 1;
    final int ANULAR_CUOTA = 2;
    final int DESCARTAR_CUOTA = 3;
    final int ANULAR_DESCARTE_CUOTA = 4;
    //Instancia al objeto para hacer consultas a la base de datos
    private GPFDataSource dataSource;
//    private boolean load = false;

    /**
     * Constructor de la clase
     * @param context contexto de la aplicación
     * @param cuotas lista con las cuotas a satisfacer por el jugador
     */
    public CuotasJugadorListAdapter(Context context, ArrayList<Cuota> cuotas) {
        this.context = context;
        this.cuotas = cuotas;
        this.dataSource = new GPFDataSource(context);
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // Si la vista está vacía cargamos los elementos que va a contener
        if (convertView == null) {
            holderCuotasJugador = new HolderCuotasJugador();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview_cuotas_jugador, parent, false);

            holderCuotasJugador.setCodigo((TextView) convertView.findViewById(R.id.textViewCodCuotasJugador));
            holderCuotasJugador.setNombre((TextView) convertView.findViewById(R.id.textViewNombreCuotasJugador));
            holderCuotasJugador.setDescripcion((TextView) convertView.findViewById(R.id.textViewDescripcionCuotasJugador));
            holderCuotasJugador.setOrden((TextView) convertView.findViewById(R.id.textViewOrdenCuotasJugador));
            holderCuotasJugador.setCuantia((TextView) convertView.findViewById(R.id.textViewCuantiaCuotasJugador));
            holderCuotasJugador.setFechaLimite((TextView) convertView.findViewById(R.id.textViewFechaLimiteCuotasJugador));
            holderCuotasJugador.setFechaPago((TextView) convertView.findViewById(R.id.textViewFechaPagoCuotasJugador));
            holderCuotasJugador.setFechaDescarte((TextView) convertView.findViewById(R.id.textViewFechaDescarteCuotasJugador));
            holderCuotasJugador.setMotivoDescarte((TextView) convertView.findViewById(R.id.textViewMotivoDescarteCuotasJugador));
            holderCuotasJugador.setPagar((ToggleButton) convertView.findViewById(R.id.switchPagarCuotasJugador));
            holderCuotasJugador.setDescartar((ToggleButton) convertView.findViewById(R.id.switchDescartarCuotasJugador));
            holderCuotasJugador.setLabelMotivoDescarte((TextView) convertView.findViewById(R.id.textViewLabelMotivoDescarteCuotasJugador));

            convertView.setTag(holderCuotasJugador);
        } else {
            holderCuotasJugador = (HolderCuotasJugador) convertView.getTag();
        }
        // Rellenamos los elementos de la vista con la información adecuada
        final View convert = convertView;
        holderCuotasJugador.getCodigo().setText(this.cuotas.get(position).getCodigo());
        holderCuotasJugador.getNombre().setText(this.cuotas.get(position).getNombre());
        holderCuotasJugador.getDescripcion().setText(this.cuotas.get(position).getDescripcion());
        holderCuotasJugador.getOrden().setText(String.valueOf(this.cuotas.get(position).getOrden()));
        holderCuotasJugador.getCuantia().setText(String.valueOf(this.cuotas.get(position).getCuantia()));
        holderCuotasJugador.getFechaLimite().setText(Utilidades.pasarDateString(this.cuotas.get(position).getFechaLimite()));
//            holderCuotasJugador.getPagar().setChecked(false);
//            holderCuotasJugador.getDescartar().setChecked(false);
        if (this.cuotas.get(position).getPagada() == 1 && this.cuotas.get(position).getFechaRealizada() != null) {
            holderCuotasJugador.getPagar().setChecked(true);
            holderCuotasJugador.getFechaPago().setText(Utilidades.pasarDateString(this.cuotas.get(position).getFechaRealizada()));
            holderCuotasJugador.getDescartar().setChecked(false);
            holderCuotasJugador.getFechaDescarte().setText("");
            holderCuotasJugador.getLabelMotivoDescarte().setVisibility(View.INVISIBLE);
            holderCuotasJugador.getMotivoDescarte().setText("");
            holderCuotasJugador.getMotivoDescarte().setVisibility(View.INVISIBLE);
        } else if (this.cuotas.get(position).getDescartada() == 1) {
            holderCuotasJugador.getPagar().setChecked(false);
            holderCuotasJugador.getFechaPago().setText("");
            holderCuotasJugador.getDescartar().setChecked(true);
            holderCuotasJugador.getFechaDescarte().setText(Utilidades.pasarDateString(this.cuotas.get(position).getFechaRealizada()));
            holderCuotasJugador.getLabelMotivoDescarte().setVisibility(View.VISIBLE);
            holderCuotasJugador.getMotivoDescarte().setText(this.cuotas.get(position).getMotivoDescarte());
            holderCuotasJugador.getMotivoDescarte().setVisibility(View.VISIBLE);
        } else {
            holderCuotasJugador.getFechaPago().setText("");
            holderCuotasJugador.getFechaDescarte().setText("");
            holderCuotasJugador.getLabelMotivoDescarte().setVisibility(View.INVISIBLE);
            holderCuotasJugador.getMotivoDescarte().setText("");
            holderCuotasJugador.getMotivoDescarte().setVisibility(View.INVISIBLE);
            holderCuotasJugador.getDescartar().setChecked(false);
            holderCuotasJugador.getPagar().setChecked(false);
        }

        /**
         * Establecemos el comportamiento del botón para pagar una cuota. Puede pasar:
         * - Si la cuota no está pagada ni descartada, la paga.
         * - Si la cuota está pagada, alerta al usuario y se pasa a no pagada si el usuario confirma en el diálogo de alerta.
         * - Si la cuota está descartada, alerta al usuario y si este confirma anula el descarte y la establece en pagada.
         */
        holderCuotasJugador.getPagar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (((ToggleButton) v).isChecked()) {

                    if (cuotas.get(position).getDescartada() == 1) {
                        AlertDialog dialog = createSimpleDialog("Pagar Cuota", "Esta cuota está descartada, ¿seguro que quiere pagarla?",
                                PAGAR_CUOTA_DESCARTADA, cuotas.get(position), position, convert, parent);
                        dialog.show();
                    } else {
                        cuotas.set(position, dataSource.pagarCuota(cuotas.get(position)));
                        getView(position, convert, parent);
                    }
                } else if (!((ToggleButton) v).isChecked()) {

                    AlertDialog dialog = createSimpleDialog("Anular Cuota", "Esta cuota está pagada, ¿seguro que quiere anular el pago?",
                            ANULAR_CUOTA, cuotas.get(position), position, convert, parent);
                    dialog.show();
                }
            }
        });

        /**
         * Establecemos el comportamiento del botón para descartar una cuota
         * Puede pasar:
         * - Si la cuota no está pagada ni descartada, la descarta.
         * - Si la cuota está descartada, alerta al usuario y se pasa a no descartada si el usuario confirma en el diálogo de alerta.
         * - Si la cuota está pagada, alerta al usuario y si este confirma anula el pago y la establece en descartada.
         */
        holderCuotasJugador.getDescartar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                load = true;

                if (((ToggleButton) v).isChecked()) {

                    if (cuotas.get(position).getPagada() == 1) {
                        AlertDialog dialog = createSimpleDialog("Descartar Cuota", "Esta cuota está pagada, ¿seguro que quiere descartarla?",
                                DESCARTAR_CUOTA, cuotas.get(position), position, convert, parent);
                        dialog.show();
                    } else {
                        AlertDialog dialog = createSimpleDialog("Descartar Cuota", "¿Por que motivo quiere descartar esta cuota?",
                                DESCARTAR_CUOTA, cuotas.get(position), position, convert, parent);
                        dialog.show();
                    }
                } else if (!((ToggleButton) v).isChecked()) {
                    AlertDialog dialog = createSimpleDialog("Activar Cuota", "Esta cuota está descartada, ¿seguro que quiere activar esta cuota?",
                            ANULAR_DESCARTE_CUOTA, cuotas.get(position), position, convert, parent);
                    dialog.show();

                }


            }

        });

        return convertView;
    }

    /**
     * Crea un diálogo de alerta configurado para la acción realizada, ya sea pagar una cuota o descartarla. Avisa al usuario de las consecuencias
     * de la acción que pretende realizar.
     * @param title título a mostar en el diálogo de alerta
     * @param mesagge mensaje a mostrar en el diálogo de alerta
     * @param accion acción que lanza el diálogo y que determina el comportamiento del botón OK y Cancelar
     * @param cuota cuota a modificar con la acción realizada
     * @param position posición del adapter donde se ha presionado el botón de pagar o descartar que ha provocado que se muestre el diálogo de alerta
     * @param convertView vista que contiene los botones
     * @param parent ViewGroupo padre.
     * @return
     */
    private AlertDialog createSimpleDialog(String title, String mesagge, final int accion, final Cuota cuota, final int position, final View convertView, final ViewGroup parent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final EditText editTextMotivoDescarte = new EditText(context);
        if (accion == DESCARTAR_CUOTA) {
            editTextMotivoDescarte.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(editTextMotivoDescarte);
        }
        builder.setTitle(title)
                .setMessage(mesagge)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (accion) {
                                    case PAGAR_CUOTA_DESCARTADA:
                                        cuotas.set(position, dataSource.pagarCuota(cuota));
                                        getView(position, convertView, parent);
                                        break;
                                    case ANULAR_CUOTA:
                                        cuotas.set(position, dataSource.anularCuota(cuota));
                                        getView(position, convertView, parent);
                                        break;
                                    case DESCARTAR_CUOTA:
                                        Cuota cuotaAux;
                                        cuota.setMotivoDescarte(editTextMotivoDescarte.getText().toString());
                                        cuotaAux = dataSource.descartarCuota(cuota);
                                        cuotas.set(position, cuotaAux);
                                        getView(position, convertView, parent);
                                        break;
                                    case ANULAR_DESCARTE_CUOTA:
                                        cuotas.set(position, dataSource.anularDescarteCuota(cuota));
                                        getView(position, convertView, parent);
                                        break;
                                }


                            }
                        })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (accion) {
                                    case PAGAR_CUOTA_DESCARTADA:
                                        holderCuotasJugador.getPagar().setChecked(false);
                                        getView(position, convertView, parent);
                                        break;
                                    case ANULAR_CUOTA:
                                        holderCuotasJugador.getPagar().setChecked(true);
                                        getView(position, convertView, parent);
                                        break;
                                    case DESCARTAR_CUOTA:
                                        holderCuotasJugador.getDescartar().setChecked(false);
                                        getView(position, convertView, parent);
                                        break;
                                    case ANULAR_DESCARTE_CUOTA:
                                        holderCuotasJugador.getDescartar().setChecked(true);
                                        getView(position, convertView, parent);
                                        break;
                                }
                            }
                        });

        return builder.create();
    }
}
