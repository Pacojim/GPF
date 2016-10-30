package com.gpf.rebisoft.gpf.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.clasesbase.Jugador;
import com.gpf.rebisoft.gpf.database.GPFDataSource;
import com.gpf.rebisoft.gpf.utils.Utilidades;

import java.util.ArrayList;

/**
 * Adapter para rellenar un listview con los jugadores activos y que hallan jugado algún partido
 * en la tempora que haya seleccionada en la aplicación
 */
public class JugadorListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Jugador> jugadores;
    private HolderJugadorList holderJugadorList;

    /**
     * Constructor de la clase
     * @param context contexto de la aplicación
     * @param jugadores lista con los jugadores para poblar el listview
     */
    public JugadorListAdapter(Context context, ArrayList<Jugador> jugadores) {
        this.context = context;
        this.jugadores = jugadores;
    }

    /**
     * Devuelve la cantidad de elementos cargados en el adapter
     * @return cantidad de elementos cargados en el adapter
     */
    @Override
    public int getCount() {
        return jugadores.size();
    }

    /**
     * Obtiene un elemento del adapter a partir de una posición
     * @param position posición del elemnto seleccionado
     * @return devuelve el elemento requerido
     */
    @Override
    public Object getItem(int position) {
        return jugadores.get(position);
    }

    /**
     * Obtiene un elemento del adapter a partir de una posición
     * @param position posición del elemnto seleccionado
     * @return devuelve el elemento requerido
     */
    @Override
    public long getItemId(int position) {
        return jugadores.get(position).getId();
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
            holderJugadorList = new HolderJugadorList();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview_jugadores, parent, false);

            holderJugadorList.setImage((ImageView) convertView.findViewById(R.id.imageViewJugadorList));
            holderJugadorList.setNombre((TextView) convertView.findViewById(R.id.textViewNombreJugadorList));
            holderJugadorList.setApellido((TextView) convertView.findViewById(R.id.textViewApellidoJugadorList));
            holderJugadorList.setPuntos((TextView) convertView.findViewById(R.id.textViewPuntosJugadorList));

            convertView.setTag(holderJugadorList);
        } else {
            holderJugadorList = (HolderJugadorList) convertView.getTag();
        }

        // Establecemos la información correspondiente en cada elemento

        holderJugadorList.getNombre().setText(this.jugadores.get(position).getNombreApodo());
        holderJugadorList.getApellido().setText(this.jugadores.get(position).getApellido1());
        holderJugadorList.getPuntos().setText(String.valueOf(this.jugadores.get(position).getPuntos()));

        // Si el jugador tiene una foto guardada se establece, si no, se pone el icono por defecto
        if(this.jugadores.get(position).getFoto() != null && !this.jugadores.get(position).getFoto().trim().equalsIgnoreCase("")){
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            holderJugadorList.getImage().setImageBitmap(Utilidades.getBitmapFromPath(extStorageDirectory + Utilidades.PATH_IMAGES_APP + this.jugadores.get(position).getFoto()));
        } else {
            holderJugadorList.getImage().setImageResource(R.drawable.jugador_default);
        }

        return convertView;
    }
}
