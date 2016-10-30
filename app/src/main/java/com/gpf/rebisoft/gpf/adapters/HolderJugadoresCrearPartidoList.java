package com.gpf.rebisoft.gpf.adapters;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Clase auxiliar para gestionar los elementos de la vista usada por JugadorCrearPartidoListAdapter.
 */
public class HolderJugadoresCrearPartidoList {
    private CheckBox seleccionado;
    private ImageView imagen;
    private TextView nombre;
    private TextView apellido1;

    public CheckBox getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(CheckBox seleccionado) {
        this.seleccionado = seleccionado;
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getApellido1() {
        return apellido1;
    }

    public void setApellido1(TextView apellido1) {
        this.apellido1 = apellido1;
    }
}
