package com.gpf.rebisoft.gpf.adapters;

import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Clase auxiliar para gestionar los elementos de la vista usada por CuotasJugadorListAdapter.
 */
public class HolderCuotasJugador {
    TextView codigo;
    TextView nombre;
    TextView descripcion;
    TextView orden;
    TextView cuantia;
    TextView fechaLimite;
    TextView fechaPago;
    TextView fechaDescarte;
    ToggleButton pagar;
    ToggleButton descartar;
    TextView motivoDescarte;
    TextView labelMotivoDescarte;

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

    public TextView getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(TextView fechaPago) {
        this.fechaPago = fechaPago;
    }

    public TextView getFechaDescarte() {
        return fechaDescarte;
    }

    public void setFechaDescarte(TextView fechaDescarte) {
        this.fechaDescarte = fechaDescarte;
    }

    public ToggleButton getPagar() {
        return pagar;
    }

    public void setPagar(ToggleButton pagar) {
        this.pagar = pagar;
    }

    public ToggleButton getDescartar() {
        return descartar;
    }

    public void setDescartar(ToggleButton descartar) {
        this.descartar = descartar;
    }

    public TextView getMotivoDescarte() {
        return motivoDescarte;
    }

    public void setMotivoDescarte(TextView motivoDescarte) {
        this.motivoDescarte = motivoDescarte;
    }

    public TextView getLabelMotivoDescarte() {
        return labelMotivoDescarte;
    }

    public void setLabelMotivoDescarte(TextView labelMotivoDescarte) {
        this.labelMotivoDescarte = labelMotivoDescarte;
    }
}
