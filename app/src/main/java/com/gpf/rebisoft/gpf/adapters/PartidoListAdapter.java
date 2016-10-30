package com.gpf.rebisoft.gpf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.clasesbase.Jugador;
import com.gpf.rebisoft.gpf.clasesbase.Partido;
import com.gpf.rebisoft.gpf.utils.Utilidades;

import java.util.ArrayList;

/**
 * Adapter para rellenar un listview con los partidos jugados y creados para la temporada
 * que haya seleccionada en la aplicación
 */
public class PartidoListAdapter extends BaseAdapter{
    private Context context;

    /**
     * Lista para almacenar los partidos para nutrir al listview que use este adapter
     */
    private ArrayList<Partido> partidos;

    /**
     * Clase auxiliar para gestionar los elementos de la vista
     */
    private HolderPartidoList holderPartidoList;

    /**
     * Constructor de la clase
     * @param context contexto de la aplicación
     * @param partidos lista de partidos que contendrá el adapter para nutrir el listview que lo use
     */
    public PartidoListAdapter(Context context, ArrayList<Partido> partidos) {
        this.context = context;
        this.partidos = partidos;
    }

    /**
     * Devuelve la cantidad de elementos cargados en el adapter
     * @return cantidad de elementos cargados en el adapter
     */
    @Override
    public int getCount() {
        return partidos.size();
    }

    /**
     * Obtiene un elemento del adapter a partir de una posición
     * @param position posición del elemnto seleccionado
     * @return devuelve el elemento requerido
     */
    @Override
    public Object getItem(int position) {
        return partidos.get(position);
    }

    /**
     * Obtiene un elemento del adapter a partir de una posición
     * @param position posición del elemnto seleccionado
     * @return devuelve el elemento requerido
     */
    @Override
    public long getItemId(int position) {
        return partidos.get(position).getId();
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
            holderPartidoList = new HolderPartidoList();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview_partidos, parent, false);

            holderPartidoList.setFecha((TextView) convertView.findViewById(R.id.textViewFechaPartidoList));
            holderPartidoList.setGolesEquipo1((TextView) convertView.findViewById(R.id.textViewGoles1PartidoList));
            holderPartidoList.setGolesEquipo2((TextView) convertView.findViewById(R.id.textViewGoles2PartidoList));
            holderPartidoList.setJornada((TextView) convertView.findViewById(R.id.textViewJornadaPartidoList));

            convertView.setTag(holderPartidoList);
        } else {
            holderPartidoList = (HolderPartidoList) convertView.getTag();
        }
        holderPartidoList.getFecha().setText(Utilidades.pasarDateString(this.partidos.get(position).getFecha()));
        holderPartidoList.getJornada().setText(String.valueOf(this.partidos.get(position).getJornada()));

        // Establecemos los datos en el elemento visual correspondiente
        if(this.partidos.get(position).getDisputado() == 1) {
            holderPartidoList.getGolesEquipo1().setText(String.valueOf(this.partidos.get(position).getGolesEquipoA()));
            holderPartidoList.getGolesEquipo2().setText(String.valueOf(this.partidos.get(position).getGolesEquipoB()));
        } else {
            holderPartidoList.getGolesEquipo1().setText("");
            holderPartidoList.getGolesEquipo2().setText("");
        }


        return convertView;
    }
}
