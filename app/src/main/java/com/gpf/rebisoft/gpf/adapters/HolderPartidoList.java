package com.gpf.rebisoft.gpf.adapters;

import android.widget.TextView;

/**
 * Clase auxiliar para gestionar los elementos de la vista usada por PartidoListAdapter.
 */
public class HolderPartidoList {
    private TextView fecha;
    private TextView nombreEquipo1;
    private TextView golesEquipo1;
    private TextView nombreEquipo2;
    private TextView golesEquipo2;
    private TextView jornada;

    public TextView getFecha() {
        return fecha;
    }

    public void setFecha(TextView fecha) {
        this.fecha = fecha;
    }

    public TextView getGolesEquipo1() {
        return golesEquipo1;
    }

    public void setGolesEquipo1(TextView golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public TextView getGolesEquipo2() {
        return golesEquipo2;
    }

    public void setGolesEquipo2(TextView golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    public TextView getJornada() {
        return jornada;
    }

    public void setJornada(TextView jornada) {
        this.jornada = jornada;
    }
}
