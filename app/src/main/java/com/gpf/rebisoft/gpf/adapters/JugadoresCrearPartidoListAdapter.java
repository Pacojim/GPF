package com.gpf.rebisoft.gpf.adapters;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.clasesbase.Jugador;
import com.gpf.rebisoft.gpf.utils.Utilidades;

import java.util.ArrayList;

/**
 * Adapter para rellenar un listview con los jugadores en activo, y nos permitirá
 * seleccionar los que queramos para crear un partido
 */
public class JugadoresCrearPartidoListAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;

    /**
     * Lista donde se almacenan los registros que poblarán el listview
     */
    private ArrayList<Jugador> jugadores;

    //Clase auxiliar para gestionar la vista
    private HolderJugadoresCrearPartidoList holderJugadoresCrearPartidoList;
    private boolean[] jugadoresCheck;

    /**
     * Constructor de la clase
     * @param context contexto de la aplicación
     * @param jugadores lista de jugadores para poblar el listview
     */
    public JugadoresCrearPartidoListAdapter(Context context, ArrayList<Jugador> jugadores) {
        this.context = context;
        this.jugadores = jugadores;
        // Inicializamos un vector que guardará los elementos del adapter seleccionados en función de su posición.
        this.jugadoresCheck = new boolean[jugadores.size()];
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
            holderJugadoresCrearPartidoList = new HolderJugadoresCrearPartidoList();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview_jugadores_crear_partido, parent, false);

            holderJugadoresCrearPartidoList.setImagen((ImageView) convertView.findViewById(R.id.imageViewJugadoresCrearPartido));
            holderJugadoresCrearPartidoList.setNombre((TextView) convertView.findViewById(R.id.textViewNombreJugadoresCrearPartido));
            holderJugadoresCrearPartidoList.setApellido1((TextView) convertView.findViewById(R.id.textViewApellidoJugadoresCrearPartido));
            holderJugadoresCrearPartidoList.setSeleccionado((CheckBox) convertView.findViewById(R.id.checkBoxJugadoresCrearPartido));

            convertView.setTag(holderJugadoresCrearPartidoList);
        } else {
            holderJugadoresCrearPartidoList = (HolderJugadoresCrearPartidoList) convertView.getTag();
        }

        // Establecemos la información correspondiente en cada elemento

        holderJugadoresCrearPartidoList.getNombre().setText(this.jugadores.get(position).getNombreApodo());
        holderJugadoresCrearPartidoList.getApellido1().setText(this.jugadores.get(position).getApellido1());


        // Si el jugador tiene una foto guardada se establece, si no, se pone el icono por defecto
        if(this.jugadores.get(position).getFoto() != null && !this.jugadores.get(position).getFoto().trim().equalsIgnoreCase("")){
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            holderJugadoresCrearPartidoList.getImagen().setImageBitmap(Utilidades.getBitmapFromPath(extStorageDirectory + Utilidades.PATH_IMAGES_APP + this.jugadores.get(position).getFoto()));
        } else {
            holderJugadoresCrearPartidoList.getImagen().setImageResource(R.drawable.jugador_default);
        }
        holderJugadoresCrearPartidoList.getSeleccionado().setTag(position);
        holderJugadoresCrearPartidoList.getSeleccionado().setChecked(jugadoresCheck[position]);
        holderJugadoresCrearPartidoList.getSeleccionado().setOnClickListener(this);

        return convertView;
    }

    /**
     * Si pulsamos en el checkbox de un elemento de la lista, lo seleccionamos o
     * deseleccionamos y actualizamos el valor del vector auxiliar
     * @param v
     */
    @Override
    public void onClick(View v) {
        CheckBox checkBox = (CheckBox) v;
        int position = (Integer) v.getTag();
        jugadoresCheck[position] = checkBox.isChecked();
    }

    /**
     * Devuelve los un arraylist con los jugadores seleccionados de la lista
     * @return
     */
    public ArrayList<Jugador> getJugadoresSeleccionados () {
        ArrayList<Jugador> jugadoresAux = new ArrayList<>();
        for(int i=0; i< jugadoresCheck.length; i++) {
            if(jugadoresCheck[i] == true) {
                jugadoresAux.add(jugadores.get(i));
            }
        }
        return jugadoresAux;
    }
}
