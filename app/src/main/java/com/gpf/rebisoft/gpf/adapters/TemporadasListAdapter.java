package com.gpf.rebisoft.gpf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.clasesbase.Temporada;
import com.gpf.rebisoft.gpf.utils.Utilidades;

import java.util.ArrayList;

/**
 * Adapter para rellenar un listview con las temporadas que hay creadas en la aplicación
 */
public class TemporadasListAdapter extends BaseAdapter {

    private Context context;
    /**
     * Lista para almacenar las propiedades que tiene actualmente creadas la aplicación
     */
    private ArrayList<Temporada> temporadas;
    private HolderTemporada holderTemporada;

    /**
     * Constructor de la clase
     * @param context contexto de la aplicación
     * @param temporadas lista de temporadas
     */
    public TemporadasListAdapter(Context context, ArrayList<Temporada> temporadas) {
        this.context = context;
        this.temporadas = temporadas;
    }

    /**
     * Devuelve la cantidad de elementos cargados en el adapter
     * @return cantidad de elementos cargados en el adapter
     */
    @Override
    public int getCount() {
        return temporadas.size();
    }

    /**
     * Obtiene un elemento del adapter a partir de una posición
     * @param position posición del elemnto seleccionado
     * @return devuelve el elemento requerido
     */
    @Override
    public Object getItem(int position) {
        return temporadas.get(position);
    }

    /**
     * Devuelve el ID del elemnto situado en una posición del adapter
     * @param position posición solicitada
     * @return ID del elemnto solicitado
     */
    @Override
    public long getItemId(int position) {
        return temporadas.get(position).getId();
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
            holderTemporada = new HolderTemporada();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview_temporadas, parent, false);

            holderTemporada.setAnnoInicio((TextView) convertView.findViewById(R.id.textViewAnnoInicioTemporada));
            holderTemporada.setAnnoFin((TextView) convertView.findViewById(R.id.textViewAnnoFinTemporada));

            convertView.setTag(holderTemporada);
        } else {
            holderTemporada = (HolderTemporada) convertView.getTag();
        }

        holderTemporada.getAnnoInicio().setText(String.valueOf(temporadas.get(position).getAnnoInicio()));
        holderTemporada.getAnnoFin().setText(String.valueOf(temporadas.get(position).getAnnoFin()));


        return convertView;
    }
}
