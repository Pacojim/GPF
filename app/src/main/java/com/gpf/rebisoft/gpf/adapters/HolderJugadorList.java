package com.gpf.rebisoft.gpf.adapters;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Clase auxiliar para gestionar los elementos de la vista usada por JugadorListAdapter.
 */
public class HolderJugadorList {
    private ImageView image;
    private TextView nombre;
    private TextView apellido;
    private TextView puntos;

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getApellido() {
        return apellido;
    }

    public void setApellido(TextView apellido) {
        this.apellido = apellido;
    }

    public TextView getPuntos() {
        return puntos;
    }

    public void setPuntos(TextView puntos) {
        this.puntos = puntos;
    }
}
