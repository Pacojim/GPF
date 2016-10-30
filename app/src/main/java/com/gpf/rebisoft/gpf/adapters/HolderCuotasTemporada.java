package com.gpf.rebisoft.gpf.adapters;

import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Clase auxiliar para gestionar los elementos de la vista usada por TemporadasCuotasListAdapter.
 */
public class HolderCuotasTemporada {
    TextView codigo;
    TextView nombre;
    TextView descripcion;
    TextView orden;
    TextView cuantia;
    TextView fechaLimite;
    ToggleButton add;

    public TextView getCodigo() {
        return codigo;
    }

    public void setCodigo(TextView codigo) {
        this.codigo = codigo;
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(TextView descripcion) {
        this.descripcion = descripcion;
    }

    public TextView getOrden() {
        return orden;
    }

    public void setOrden(TextView orden) {
        this.orden = orden;
    }

    public TextView getCuantia() {
        return cuantia;
    }

    public void setCuantia(TextView cuantia) {
        this.cuantia = cuantia;
    }

    public TextView getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(TextView fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public ToggleButton getAdd() {
        return add;
    }

    public void setAdd(ToggleButton add) {
        this.add = add;
    }
}
