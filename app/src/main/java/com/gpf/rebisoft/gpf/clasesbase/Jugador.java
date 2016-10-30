package com.gpf.rebisoft.gpf.clasesbase;

import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.gpf.rebisoft.gpf.database.GPFDataSource;

/**
 * Created by pacos on 08/05/2016.
 */
public class Jugador implements Parcelable {
    private int id;
    private String nombre;
    private String apodo;
    private String apellido1;
    private String apellido2;
    private int dorsal;
    private int activo;
    private int calidad;
    private int goles;
    private int partidos;
    private int victorias;
    private int puntos;
    private String foto;
    private int socio;
    private String equipo;

    public Jugador(int id, String nombre, String apodo, String apellido1, int calidad, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.apodo = apodo;
        this.apellido1 = apellido1;
        this.foto = foto;
        this.calidad = calidad;
    }

    public Jugador(int id, String nombre, String apodo, String apellido1, String foto, int puntosTemporada) {
        this.id = id;
        this.nombre = nombre;
        this.apodo = apodo;
        this.apellido1 = apellido1;
        this.foto = foto;
        this.puntos = puntosTemporada;
    }

    public Jugador(int id, String nombre, String apodo, String apellido1, String apellido2, int dorsal, int activo, int calidad, String foto) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dorsal = dorsal;
        this.activo = activo;
        this.calidad = calidad;
        this.foto = foto;
    }

    public Jugador(String nombre, String apodo, String apellido1, String apellido2, int dorsal, int activo, int calidad, String foto, int socio) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dorsal = dorsal;
        this.activo = activo;
        this.calidad = calidad;
        this.foto = foto;
        this.socio = socio;
    }

    public Jugador(String nombre, String apodo, String apellido1, String apellido2, int dorsal, int activo, int calidad, int goles, int partidos, int victorias, int puntos, String foto, int socio) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dorsal = dorsal;
        this.activo = activo;
        this.calidad = calidad;
        this.goles = goles;
        this.partidos = partidos;
        this.victorias = victorias;
        this.puntos = puntos;
        this.foto = foto;
        this.socio = socio;
        this.activo = activo;
    }

    public Jugador(int id, String nombre, String apodo, String apellido1, int dorsal, int goles, String equipo) {
        this.id = id;
        this.nombre = nombre;
        this.apodo = apodo;
        this.apellido1 = apellido1;
        this.dorsal = dorsal;
        this.goles = goles;
        this.equipo = equipo;
    }

    public Jugador(String nombre, String apodo, String apellido1, int dorsal, int goles, String equipo) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.apellido1 = apellido1;
        this.dorsal = dorsal;
        this.goles = goles;
        this.equipo = equipo;
    }

    protected Jugador(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        apodo = in.readString();
        apellido1 = in.readString();
        apellido2 = in.readString();
        dorsal = in.readInt();
        activo = in.readInt();
        calidad = in.readInt();
        goles = in.readInt();
        partidos = in.readInt();
        victorias = in.readInt();
        puntos = in.readInt();
        foto = in.readString();
        socio = in.readInt();
        equipo = in.readString();
    }

    public String getEquipo() {
        return equipo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getFoto() {
        return foto;
    }

    public int getPuntos() {
        return puntos;
    }

    public String getApellido2() {
        return apellido2;
    }

    public int getDorsal() {
        return dorsal;
    }

    public int getActivo() {
        return activo;
    }

    public int getCalidad() {
        return calidad;
    }

    public int getGoles() {
        return goles;
    }

    public int getPartidos() {
        return partidos;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getSocio() {
        return socio;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public static Jugador cursorToJugadorList(Context context, Cursor cursor) {
        Jugador jugador = null;
        if(cursor != null) {
            jugador = new Jugador(cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.ID_JUGADORES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.NOMBRE_JUGADORES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.APODO_JUGADORES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.APELLIDO1_JUGADORES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.FOTO_JUGADORES)),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.JUGADOR_PUNTOS_TEMPORADA)));
        }
        return jugador;
    }

//    Jugador(String nombre, String apodo, String apellido1, String apellido2, int dorsal, int activo, int calidad, int goles, int partidos, int victorias, int puntos, int foto, int socio)

    public static Jugador cursorToJugadorDatos(Context context, Cursor cursor) {
        Jugador jugador = null;
        if(cursor != null) {
            jugador = new Jugador(cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.NOMBRE_JUGADORES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.APODO_JUGADORES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.APELLIDO1_JUGADORES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.APELLIDO2_JUGADORES)),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.DORSAL_JUGADORES)),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.ACTIVO_JUGADORES)),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.CALIDAD_JUGADORES)),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.JUGADOR_GOLES_TEMPORADA)),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.JUGADOR_PARTIDOS_TEMPORADA)),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.JUGADOR_VICTORIAS_TEMPORADA)),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.JUGADOR_PUNTOS_TEMPORADA)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.FOTO_JUGADORES)),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.SOCIO_JUGADORES)));
        }
        return jugador;
    }
//    Jugador(String nombre, String apodo, String apellido1, int dorsal, int goles, String equipo)
    public static Jugador cursorToJugadorPartido(Cursor cursor) {
        Jugador jugador = null;
        if(cursor != null) {
            jugador = new Jugador(cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnJugadorPartidos.ID_JUGADOR_JUGADOR_PARTIDOS)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.NOMBRE_JUGADORES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.APODO_JUGADORES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.APELLIDO1_JUGADORES)),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.DORSAL_JUGADORES)),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnJugadorPartidos.GOLES_JUGADOR_PARTIDOS)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadorPartidos.EQUIPO_JUGADOR_PARTIDOS)));
        }
        return jugador;
    }

    //Jugador(int id, String nombre, String apodo, String apellido1, String foto)
    public static Jugador cursorToJugadorCrearPartido(Cursor cursor) {
        Jugador jugador = null;
        if(cursor != null) {
            jugador = new Jugador(cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.ID_JUGADORES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.NOMBRE_JUGADORES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.APODO_JUGADORES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.APELLIDO1_JUGADORES)),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.CALIDAD_JUGADORES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadores.FOTO_JUGADORES)));
        }
        return jugador;
    }

    public String getNombreApodo() {
        String nombre = this.nombre;
        if(this.apodo != null && !this.apodo.trim().equals("")) {
            nombre = this.apodo;
        }
        return nombre;
    }

    public String getNombreParaEquipo() {
        String nombre = getNombreApodo();
        if(this.apellido1 != null && !this.apellido1.trim().equals("")) {
            nombre += " " + this.apellido1.substring(0, 1) + ".";
        }
        return nombre;
    }

    public static final Creator<Jugador> CREATOR = new Creator<Jugador>() {
        @Override
        public Jugador createFromParcel(Parcel in) {
            return new Jugador(in);
        }

        @Override
        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(dorsal);
        dest.writeInt(activo);
        dest.writeInt(calidad);
        dest.writeInt(goles);
        dest.writeInt(partidos);
        dest.writeInt(victorias);
        dest.writeInt(puntos);
        dest.writeInt(socio);
        dest.writeString(nombre);
        dest.writeString(apodo);
        dest.writeString(apellido1);
        dest.writeString(apellido2);
        dest.writeString(foto);
        dest.writeString(equipo);
    }
}
