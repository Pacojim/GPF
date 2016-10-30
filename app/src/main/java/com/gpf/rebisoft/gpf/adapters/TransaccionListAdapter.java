package com.gpf.rebisoft.gpf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.clasesbase.Transaccion;
import com.gpf.rebisoft.gpf.utils.Utilidades;

import java.util.ArrayList;

/**
 * Adapter para rellenar un listview que gestionará las transacciones creadas y realizadas en la aplicación
 */
public class TransaccionListAdapter extends BaseAdapter {
    private Context context;
    /**
     * Lista con para almacenar las transacciones registradas en la aplicación
     */
    private ArrayList<Transaccion> transacciones;
    private HolderTransaccionList holderTransaccionList;

    /**
     * Constructor de la clase
     * @param context contexto de la aplicación
     * @param transacciones lista con tras transacciones registradas
     */
    public TransaccionListAdapter(Context context, ArrayList<Transaccion> transacciones) {
        this.context = context;
        this.transacciones = transacciones;
    }

    /**
     * Devuelve la cantidad de elementos cargados en el adapter
     * @return cantidad de elementos cargados en el adapter
     */
    @Override
    public int getCount() {
        return transacciones.size();
    }

    /**
     * Obtiene un elemento del adapter a partir de una posición
     * @param position posición del elemnto seleccionado
     * @return devuelve el elemento requerido
     */
    @Override
    public Object getItem(int position) {
        return transacciones.get(position);
    }

    /**
     * Devuelve el ID del elemnto situado en una posición del adapter
     * @param position posición solicitada
     * @return ID del elemnto solicitado
     */
    @Override
    public long getItemId(int position) {
        return transacciones.get(position).getId();
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
            holderTransaccionList = new HolderTransaccionList();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview_transacciones, parent, false);

            holderTransaccionList.setColorTransaccion((ImageView) convertView.findViewById(R.id.imageViewColorTransaccion));
            holderTransaccionList.setFechaLimiteTransaccion((TextView) convertView.findViewById(R.id.textViewFechaLimiteTransaccion));
            holderTransaccionList.setNombreTransaccion((TextView) convertView.findViewById(R.id.textViewNombreTransaccion));
            holderTransaccionList.setTipoTransaccion((TextView) convertView.findViewById(R.id.textViewTipoTransaccion));
            holderTransaccionList.setCuantiaTransaccion((TextView) convertView.findViewById(R.id.textViewCuantiaTransaccion));

            convertView.setTag(holderTransaccionList);
        } else {
            holderTransaccionList = (HolderTransaccionList) convertView.getTag();
        }

        // Establecemos los datos de las transacciones en los elementos visuales correspondientes

        holderTransaccionList.getColorTransaccion().setImageResource(this.transacciones.get(position).getColor());
        if(!Utilidades.pasarDateString(this.transacciones.get(position).getFechaLimite()).trim().equals("")) {
            holderTransaccionList.getFechaLimiteTransaccion().setText(Utilidades.pasarDateString(this.transacciones.get(position).getFechaLimite()));
        } else {
            holderTransaccionList.getFechaLimiteTransaccion().setText("Sin fecha límite");
        }

        holderTransaccionList.getNombreTransaccion().setText(this.transacciones.get(position).getNombre());
        holderTransaccionList.getTipoTransaccion().setText(String.valueOf(this.transacciones.get(position).getSignoTipo()));
        holderTransaccionList.getCuantiaTransaccion().setText(String.valueOf(Math.abs(this.transacciones.get(position).getCuantia())));

        return convertView;
    }
}
