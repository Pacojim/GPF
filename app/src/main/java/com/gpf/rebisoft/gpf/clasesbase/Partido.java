package com.gpf.rebisoft.gpf.clasesbase;

import android.database.Cursor;

import com.gpf.rebisoft.gpf.database.GPFDataSource;
import com.gpf.rebisoft.gpf.utils.Utilidades;

import java.util.Date;

/**
 * Clase para representar los partidos que se convocan y juegan los jugadores
 */
public class Partido {
    private int id;
    private int jornada;
    private Date fecha;
    private int disputado;
    private int golesEquipoA;
    private int golesEquipoB;

    /**
     * Constructor básico de la clase
     * @param id ID del partido
     * @param jornada jornada del partido en relación a la temporada a la que pertenece
     * @param fecha fecha en la que se disputa
     * @param disputado 1 si el partido se ha disputado, 0 si no
     */
    public Partido(int id, int jornada, Date fecha, int disputado) {
        this.id = id;
        this.jornada = jornada;
        this.fecha = fecha;
        this.disputado = disputado;
    }

    /**
     * Constructor para crear un partido disputado y almacenar los goles de un equipo
     * @param id ID del partiodo
     * @param jornada jornada en relación a la temporda
     * @param fecha fecha en la que se disputa
     * @param disputado 1 si el partido se ha disputado, 0 si no
     * @param golesEquipoA goles de un equipo
     */
    public Partido(int id, int jornada, Date fecha, int disputado, int golesEquipoA) {
        this.id = id;
        this.jornada = jornada;
        this.fecha = fecha;
        this.disputado = disputado;
        this.golesEquipoA = golesEquipoA;
    }

    //Getter y setter por defecto

    public int getId() {
        return id;
    }

    public void setGolesEquipoB(int golesEquipoB) {
        this.golesEquipoB = golesEquipoB;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getGolesEquipoA() {
        return golesEquipoA;
    }

    public int getGolesEquipoB() {
        return golesEquipoB;
    }

    public int getDisputado() {
        return disputado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

    public void setDisputado(int disputado) {
        this.disputado = disputado;
    }

    /**
     * Crea un partido a partir de los datos obtenidos de un cursor
     * Inserta los datos adecuados si el partido ha sido disputado o no.
     * Si ha sido disputado se insertan solo los datos del equipo A, los del B se introducen a través
     * del setter en donde se llama a este método.
     * @param cursor cursor del que se obtienen los datos
     * @return partido
     */
    public static Partido cursorToPartidoList(Cursor cursor) {
        Partido partido = null;
        if(cursor != null) {
            if(cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnPartidos.DISPUTADO_PARTIDOS)) == 0) {
                partido = new Partido(cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnPartidos.ID_PARTIDOS)),
                        cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnPartidos.JORNADA_PARTIDOS)),
                        Utilidades.pasarFechaAplicacion(cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnPartidos.FECHA_PARTIDOS))), 0);
            } else {
                partido = new Partido(cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnPartidos.ID_PARTIDOS)),
                        cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnPartidos.JORNADA_PARTIDOS)),
                        Utilidades.pasarFechaAplicacion(cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnPartidos.FECHA_PARTIDOS))), 1,
                        cursor.getInt(cursor.getColumnIndex("goles")));
            }
        }
        return partido;
    }
}
