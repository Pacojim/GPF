package com.gpf.rebisoft.gpf.clasesbase;

import android.database.Cursor;

import com.gpf.rebisoft.gpf.database.GPFDataSource;

/**
 * Clase que representa una temporada
 */
public class Temporada {
    private int id;
    private int annoInicio;
    private int annoFin;

    /**
     * Constructor básico
     * @param annoInicio año de inicio de la temporada
     * @param annoFin año de fin de la temporada
     */
    public Temporada(int annoInicio, int annoFin) {
        this.annoInicio = annoInicio;
        this.annoFin = annoFin;
    }

    /**
     * Constructor de la clase
     * @param id ID de la temporada
     * @param annoInicio año de inicio de la temporada
     * @param annoFin año de fin de la temporada
     */
    public Temporada(int id, int annoInicio, int annoFin) {
        this.id = id;
        this.annoInicio = annoInicio;
        this.annoFin = annoFin;
    }

    //Getter y setter por defecto

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnnoInicio() {
        return annoInicio;
    }

    public void setAnnoInicio(int annoInicio) {
        this.annoInicio = annoInicio;
    }

    public int getAnnoFin() {
        return annoFin;
    }

    public void setAnnoFin(int annoFin) {
        this.annoFin = annoFin;
    }

    /**
     * Crea una temporada a partir de los datos de un cursor
     * @param cursor cursor con los datos para crear la temporada
     * @return temporada
     */
    public static Temporada cursorToTemporada(Cursor cursor) {
        Temporada temporada = null;
        if(cursor != null) {
            temporada = new Temporada(cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnTemporadas.ID_TEMPORADAS)),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnTemporadas.ANNO_INICIO_TEMPORADAS)),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnTemporadas.ANNO_FIN_TEMPORADAS)));
        }
        return temporada;
    }

    /**
     * Método para mostrar los datos de una temporada
     * @return
     */
    public String toString() {
        return this.annoInicio + "/" +this.annoFin;
    }
}
