package com.gpf.rebisoft.gpf.adapters;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Clase auxiliar para gestionar los elementos de la vista usada por TransaccionListAdapter.
 */
public class HolderTransaccionList {
    private ImageView colorTransaccion;
    private TextView fechaLimiteTransaccion;
    private TextView nombreTransaccion;
    private TextView tipoTransaccion;
    private TextView cuantiaTransaccion;

    public ImageView getColorTransaccion() {
        return colorTransaccion;
    }

    public void setColorTransaccion(ImageView colorTransaccion) {
        this.colorTransaccion = colorTransaccion;
    }

    public TextView getFechaLimiteTransaccion() {
        return fechaLimiteTransaccion;
    }

    public void setFechaLimiteTransaccion(TextView fechaLimiteTransaccion) {
        this.fechaLimiteTransaccion = fechaLimiteTransaccion;
    }

    public TextView getNombreTransaccion() {
        return nombreTransaccion;
    }

    public void setNombreTransaccion(TextView nombreTransaccion) {
        this.nombreTransaccion = nombreTransaccion;
    }

    public TextView getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TextView tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public TextView getCuantiaTransaccion() {
        return cuantiaTransaccion;
    }

    public void setCuantiaTransaccion(TextView cuantiaTransaccion) {
        this.cuantiaTransaccion = cuantiaTransaccion;
    }
}
