package com.gpf.rebisoft.gpf.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Adapter para el spinner que permite seleccionar la temporada de la que queremos ver información,
 * en el toolbar de la activity principal
 */

public class SpinnerToolbarAdapter<T> extends ArrayAdapter<T>
{
    /**
     * Constructor por defecto de la clase
     * @param context contexto de la aplicación
     * @param objects  lista con los objetos que contendrá el adapter
     */
    public SpinnerToolbarAdapter(Context context, ArrayList<T> objects)
    {
        super(context, android.R.layout.simple_spinner_dropdown_item, objects);
    }

    /**
     * Método usado para establecer el color del texto del spinner cuando está cerrado
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        View view = super.getView(position, convertView, parent);

        TextView text = (TextView)view.findViewById(android.R.id.text1);
        text.setTextColor(Color.BLACK);

        return view;

    }

    /**
     * Método usado para establecer el color de las opciones del spinner cuando está abierto
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        ((TextView) v).setTextSize(16);
        ((TextView) v).setTextColor(Color.WHITE);

        return v;
    }
}
