package com.gpf.rebisoft.gpf.clasesauxiliares;

import android.database.Cursor;

import com.gpf.rebisoft.gpf.database.GPFDataSource;

/**
 * Clase para relacionar los datos básicos de un jugador con el número de cuotas que ha pagado
 */
public class JugadorCuota {
    int id;
    String nombre;
    String apellido1;
    int cuotasPagadas;

    /**
     * Constructor de la calse
     * @param id ID del jugador
     * @param nombre nombre del jugador
     * @param apellido1 primer apellido del jugador
     * @param cuotasPagadas número de cuotas pagadas
     */
    public JugadorCuota(int id, String nombre, String apellido1, int cuotasPagadas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.cuotasPagadas = cuotasPagadas;
    }

    // Getter y setter por defecto

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public int getCuotasPagadas() {
        return cuotasPagadas;
    }

    /**
     * Crea un objeto de esta clase a partir de los datos contenidos en un cursor
     * @param cursor con los datos para crear un objeto de esta clase
     * @return el objeto de la calse
     */
    public static JugadorCuota cursorToJugadoresCuotasList(Cursor cursor) {
        JugadorCuota jugadorCuota = null;
        if(cursor != null) {
            jugadorCuota = new JugadorCuota(cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.ID_JUGADORES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.NOMBRE_JUGADORES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.APELLIDO1_JUGADORES)),
                    cursor.getInt(cursor.getColumnIndex("cuotas_pagadas")));
        }
        return jugadorCuota;
    }
}
