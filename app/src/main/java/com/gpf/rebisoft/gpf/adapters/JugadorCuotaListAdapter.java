package com.gpf.rebisoft.gpf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.clasesauxiliares.JugadorCuota;
import com.gpf.rebisoft.gpf.database.GPFDataSource;
import com.gpf.rebisoft.gpf.utils.Utilidades;

import java.util.ArrayList;

/**
 * Adapter para rellenar un listview con los datos los datos de un jugador, el numero de cuotas pagadas esta temporada y el número
 * total de cuotas a afrontar esta temporada
 */
public class JugadorCuotaListAdapter extends BaseAdapter {

    private Context context;

    /**
     * Lista donde se almacenan los registros que poblarán el listview
     */
    private ArrayList<JugadorCuota> jugadoresCuotas;
    //Clase auxiliar para gestionar la vista
    private HolderJugadorCuotaList holderJugadorCuotaList ;
    //Número de cuotas totales a afrontar esta temporada
    private int cuotasTotales;

    /**
     * Constructor de la clase
     * @param context contexto de la aplicación
     * @param jugadoresCuotas Lista de registros para poblar el listview que use este adapter
     * @param cuotasTotales Número de cuotas totales a afrontar esta temporada
     */
    public JugadorCuotaListAdapter(Context context, ArrayList<JugadorCuota> jugadoresCuotas, int cuotasTotales) {
        this.context = context;
        this.jugadoresCuotas = jugadoresCuotas;
        this.cuotasTotales = cuotasTotales;
    }

    /**
     * Devuelve la cantidad de elementos cargados en el adapter
     * @return cantidad de elementos cargados en el adapter
     */
    @Override
    public int getCount() {
        return jugadoresCuotas.size();
    }

    /**
     * Obtiene un elemento del adapter a partir de una posición
     * @param position posición del elemnto seleccionado
     * @return devuelve el elemento requerido
     */
    @Override
    public Object getItem(int position) {
        return jugadoresCuotas.get(position);
    }

    /**
     * Devuelve el ID del elemnto situado en una posición del adapter
     * @param position posición solicitada
     * @return ID del elemnto solicitado
     */
    @Override
    public long getItemId(int position) {
        return jugadoresCuotas.get(position).getId();
    }

    /**
     * Crea las vistas que van a mostrar en listview que use este adapter
     * @param position posición del elemento que se muestra
     * @param convertView vista a mostrar
     * @param parent ViewGroup padre
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            holderJugadorCuotaList = new HolderJugadorCuotaList();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview_jugadores_cuotas, parent, false);

            holderJugadorCuotaList.setNombreJugador((TextView) convertView.findViewById(R.id.textViewNombreJugadorCuota));
            holderJugadorCuotaList.setApellido1Jugador((TextView) convertView.findViewById(R.id.textViewApellido1JugadorCuota));
            holderJugadorCuotaList.setCuotasPagadas((TextView) convertView.findViewById(R.id.textViewCuotasPagadas));
            holderJugadorCuotaList.setCuotasTotales((TextView) convertView.findViewById(R.id.textViewCuotasTotales));

            convertView.setTag(holderJugadorCuotaList);
        } else {
            holderJugadorCuotaList = (HolderJugadorCuotaList) convertView.getTag();
        }
        holderJugadorCuotaList.getNombreJugador().setText(this.jugadoresCuotas.get(position).getNombre());
        holderJugadorCuotaList.getApellido1Jugador().setText(this.jugadoresCuotas.get(position).getApellido1());
        holderJugadorCuotaList.getCuotasPagadas().setText(String.valueOf(this.jugadoresCuotas.get(position).getCuotasPagadas()));
        holderJugadorCuotaList.getCuotasTotales().setText(String.valueOf(cuotasTotales));

        return convertView;
    }
}
