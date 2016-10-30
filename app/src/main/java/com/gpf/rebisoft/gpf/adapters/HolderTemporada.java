package com.gpf.rebisoft.gpf.adapters;

import android.widget.TextView;

/**
 * Clase auxiliar para gestionar los elementos de la vista usada por TemporadasListAdapter.
 */
public class HolderTemporada {
    TextView annoInicio;
    TextView annoFin;

    public TextView getAnnoInicio() {
        return annoInicio;
    }

    public void setAnnoInicio(TextView annoInicio) {
        this.annoInicio = annoInicio;
    }

    public TextView getAnnoFin() {
        return annoFin;
    }

    public void setAnnoFin(TextView annoFin) {
        this.annoFin = annoFin;
    }
}
