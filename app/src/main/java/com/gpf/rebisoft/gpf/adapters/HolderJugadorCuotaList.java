package com.gpf.rebisoft.gpf.adapters;

import android.widget.TextView;

/**
 * Clase auxiliar para gestionar los elementos de la vista usada por JugadorCuotasListAdapter.
 */
public class HolderJugadorCuotaList {
    TextView nombreJugador;
    TextView apellido1Jugador;
    TextView cuotasPagadas;
    TextView cuotasTotales;

    public TextView getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(TextView nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public TextView getApellido1Jugador() {
        return apellido1Jugador;
    }

    public void setApellido1Jugador(TextView apellido1Jugador) {
        this.apellido1Jugador = apellido1Jugador;
    }

    public TextView getCuotasPagadas() {
        return cuotasPagadas;
    }

    public void setCuotasPagadas(TextView cuotasPagadas) {
        this.cuotasPagadas = cuotasPagadas;
    }

    public TextView getCuotasTotales() {
        return cuotasTotales;
    }

    public void setCuotasTotales(TextView cuotasTotales) {
        this.cuotasTotales = cuotasTotales;
    }
}
