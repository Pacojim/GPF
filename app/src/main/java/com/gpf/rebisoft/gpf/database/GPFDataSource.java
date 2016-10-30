package com.gpf.rebisoft.gpf.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.gpf.rebisoft.gpf.clasesauxiliares.JugadorCuota;
import com.gpf.rebisoft.gpf.clasesbase.Cuota;
import com.gpf.rebisoft.gpf.clasesbase.Jugador;
import com.gpf.rebisoft.gpf.clasesbase.Partido;
import com.gpf.rebisoft.gpf.clasesbase.Temporada;
import com.gpf.rebisoft.gpf.clasesbase.Transaccion;
import com.gpf.rebisoft.gpf.utils.Utilidades;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Clase que representa el gestor de la base de datos
 */
public class GPFDataSource {

    private Context context;
    // Helper
    private static GPFBDHelper openHelper = null;
    private SQLiteDatabase database;

    /**
     * Constructor
     *
     * @param context contexto de la aplicación
     */
    public GPFDataSource(Context context) {
        this.context = context;
        if (openHelper == null) {
            openHelper = new GPFBDHelper(context);
        }
        database = openHelper.getWritableDatabase();
    }

    // Metainformación de la base de datos
    // Nombres de las tablas
    public static final String TEMPORADAS_TABLE_NAME = "temporadas";
    public static final String PARTIDOS_TABLE_NAME = "partidos";
    public static final String JUGADORES_TABLE_NAME = "jugadores";
    public static final String CUOTAS_TABLE_NAME = "cuotas";
    public static final String TRANSACCIONES_TABLE_NAME = "transacciones";
    public static final String JUGADOR_PARTIDOS_TABLE_NAME = "jugador_partidos";
    public static final String JUGADOR_CUOTAS_TABLE_NAME = "jugador_cuotas";
    public static final String TEMPORADAS_CUOTAS_TABLE_NAME = "temporada_cuotas";

    // Tipos de datos

    public static final String TEXT_TYPE = "text";
    public static final String INTEGER_TYPE = "integer";
    public static final String REAL_TYPE = "real";

    // Campos de las tablas

    // Campos de la tabla temporadas
    public static class ColumnTemporadas {
        public static final String ID_TEMPORADAS = BaseColumns._ID;
        public static final String ANNO_INICIO_TEMPORADAS = "anno_inicio";
        public static final String ANNO_FIN_TEMPORADAS = "anno_fin";
    }

    // Campos de la tabla partidos
    public static class ColumnPartidos {
        public static final String ID_PARTIDOS = BaseColumns._ID;
        public static final String JORNADA_PARTIDOS = "jornada";
        public static final String FECHA_PARTIDOS = "fecha";
        public static final String DISPUTADO_PARTIDOS = "disputado";
        public static final String ID_TEMPORADA_PARTIDOS = "id_temporada";
    }

    // Campos de la tabla jugadores
    public static class ColumnJugadores {
        public static final String ID_JUGADORES = BaseColumns._ID;
        public static final String NOMBRE_JUGADORES = "nombre";
        public static final String APELLIDO1_JUGADORES = "apellido1";
        public static final String APELLIDO2_JUGADORES = "apellido2";
        public static final String APODO_JUGADORES = "apodo";
        public static final String DORSAL_JUGADORES = "dorsal";
        public static final String CALIDAD_JUGADORES = "calidad";
        public static final String ACTIVO_JUGADORES = "activo";
        public static final String SOCIO_JUGADORES = "socio";
        public static final String FOTO_JUGADORES = "foto";
    }

    // Campos de la tabla cuotas
    public static class ColumnCuotas {
        public static final String ID_CUOTAS = BaseColumns._ID;
        public static final String CODIGO_CUOTAS = "codigo";
        public static final String NOMBRE_CUOTAS = "nombre";
        public static final String DESCRIPCION_CUOTAS = "descripcion";
        public static final String ORDEN_CUOTAS = "orden";
        public static final String FECHA_LIMITE_CUOTAS = "fecha_limite";
        public static final String CUANTIA_CUOTAS = "cuantia";
    }

    // Campos de la tabla transacciones
    public static class ColumnTranscacciones {
        public static final String ID_TRANSACCIONES = BaseColumns._ID;
        public static final String CODIGO_TRANSACCIONES = "codigo";
        public static final String NOMBRE_TRANSACCIONES = "nombre";
        public static final String DESCRIPCION_TRANSACCIONES = "descripcion";
        public static final String TIPO_TRANSACCIONES = "tipo";
        public static final String FECHA_LIMITE_TRANSACCIONES = "fecha_limite";
        public static final String CUANTIA_TRANSACCIONES = "cuantia";
        public static final String REALIZADA_TRANSACCIONES = "realizada";
        public static final String FECHA_REALIZADA_TRANSACCIONES = "fecha_realizada";
    }

    // Campos de la tabla jugador_partidos
    public static class ColumnJugadorPartidos {
        public static final String ID_PARTIDO_JUGADOR_PARTIDOS = "id_partido";
        public static final String ID_JUGADOR_JUGADOR_PARTIDOS = "id_jugador";
        public static final String EQUIPO_JUGADOR_PARTIDOS = "equipo";
        public static final String GOLES_JUGADOR_PARTIDOS = "goles";
        public static final String PUNTOS_JUGADOR_PARTIDOS = "puntos";
        public static final String VICTORIA_JUGADOR_PARTIDOS = "victoria";
    }

    // Campos de la tabla jugador_cuotas
    public static class ColumnJugadorCuotas {
        public static final String ID_CUOTA_JUGADOR_CUOTAS = "id_cuota";
        public static final String ID_JUGADOR_JUGADOR_CUOTAS = "id_jugador";
        public static final String ID_TEMPORADA_JUGADOR_CUOTAS = "id_temporada";
        public static final String FECHA_JUGADOR_CUOTAS = "fecha";
        public static final String PAGADA_JUGADOR_CUOTAS = "pagada";
        public static final String DESCARTADA_JUGADOR_CUOTAS = "descartada";
        public static final String MOTIVO_DESCARTE_JUGADOR_CUOTAS = "motivo_descarte";
        public static final String ID_TRANSACCION_JUGADOR_CUOTAS = "id_transaccion";
    }

    // Campos de la tabla temporada_cuotas
    public static class ColumnTemporadaCuotas {
        public static final String ID_TEMPORADA_TEMPORADA_CUOTAS = "id_temporada";
        public static final String ID_CUOTAS_TEMPORADA_CUOTAS = "id_cuotas";
        public static final String FECHA_LIMITE_CUOTAS = "fecha_limite";
    }

    // Script de creación de las tablas

    // Script de creación de la tabla temporadas
    public static final String CREATE_TEMPORADAS_SCRIPT = "create table " + TEMPORADAS_TABLE_NAME + "("
            + ColumnTemporadas.ID_TEMPORADAS + " " + INTEGER_TYPE + " primary key autoincrement, "
            + ColumnTemporadas.ANNO_INICIO_TEMPORADAS + " " + INTEGER_TYPE + " not null, "
            + ColumnTemporadas.ANNO_FIN_TEMPORADAS + " " + INTEGER_TYPE + " not null)";

    // Script de creación de la tabla partidos
    public static final String CREATE_PARTIDOS_SCRIPT = "create table " + PARTIDOS_TABLE_NAME + "("
            + ColumnPartidos.ID_PARTIDOS + " " + INTEGER_TYPE + " primary key autoincrement, "
            + ColumnPartidos.JORNADA_PARTIDOS + " " + INTEGER_TYPE + " not null, "
            + ColumnPartidos.FECHA_PARTIDOS + " " + TEXT_TYPE + " not null, "
            + ColumnPartidos.DISPUTADO_PARTIDOS + " " + INTEGER_TYPE + " not null default 0, "
            + ColumnPartidos.ID_TEMPORADA_PARTIDOS + " " + INTEGER_TYPE + " not null, "
            + "foreign key (" + ColumnPartidos.ID_TEMPORADA_PARTIDOS + ") references "
            + TEMPORADAS_TABLE_NAME + "(" + ColumnTemporadas.ID_TEMPORADAS + "))";

    // Script de creación de la tabla jugadores
    public static final String CREATE_JUGADORES_SCRIPT = "create table " + JUGADORES_TABLE_NAME + "("
            + ColumnJugadores.ID_JUGADORES + " " + INTEGER_TYPE + " primary key autoincrement, "
            + ColumnJugadores.NOMBRE_JUGADORES + " " + TEXT_TYPE + " not null, "
            + ColumnJugadores.APELLIDO1_JUGADORES + " " + TEXT_TYPE + ", "
            + ColumnJugadores.APELLIDO2_JUGADORES + " " + TEXT_TYPE + ", "
            + ColumnJugadores.APODO_JUGADORES + " " + TEXT_TYPE + ", "
            + ColumnJugadores.DORSAL_JUGADORES + " " + INTEGER_TYPE + ", "
            + ColumnJugadores.CALIDAD_JUGADORES + " " + INTEGER_TYPE + " not null default 5, "
            + ColumnJugadores.ACTIVO_JUGADORES + " " + INTEGER_TYPE + " not null default 0, "
            + ColumnJugadores.SOCIO_JUGADORES + " " + INTEGER_TYPE + " not null default 1, "
            + ColumnJugadores.FOTO_JUGADORES + " " + TEXT_TYPE + ")";

    // Script de creación de la tabla cuotas
    public static final String CREATE_CUOTAS_SCRIPT = "create table " + CUOTAS_TABLE_NAME + "("
            + ColumnCuotas.ID_CUOTAS + " " + INTEGER_TYPE + " primary key autoincrement, "
            + ColumnCuotas.CODIGO_CUOTAS + " " + TEXT_TYPE + " not null, "
            + ColumnCuotas.NOMBRE_CUOTAS + " " + TEXT_TYPE + " not null, "
            + ColumnCuotas.DESCRIPCION_CUOTAS + " " + TEXT_TYPE + ", "
            + ColumnCuotas.ORDEN_CUOTAS + " " + INTEGER_TYPE + " not null, "
            + ColumnCuotas.FECHA_LIMITE_CUOTAS + " " + TEXT_TYPE + " not null, "
            + ColumnCuotas.CUANTIA_CUOTAS + " " + REAL_TYPE + " not null)";

    // Script de creación de la tabla transacciones
    public static final String CREATE_TRANSACCIONES_SCRIPT = "create table " + TRANSACCIONES_TABLE_NAME + "("
            + ColumnTranscacciones.ID_TRANSACCIONES + " " + INTEGER_TYPE + " primary key autoincrement, "
            + ColumnTranscacciones.CODIGO_TRANSACCIONES + " " + TEXT_TYPE + " not null, "
            + ColumnTranscacciones.NOMBRE_TRANSACCIONES + " " + TEXT_TYPE + " not null, "
            + ColumnTranscacciones.DESCRIPCION_TRANSACCIONES + " " + TEXT_TYPE + ", "
            // Tipo de transacciones, 0: cobro, 1: pago
            + ColumnTranscacciones.TIPO_TRANSACCIONES + " " + INTEGER_TYPE + " not null, "
            + ColumnTranscacciones.FECHA_LIMITE_TRANSACCIONES + " " + TEXT_TYPE + ", "
            + ColumnTranscacciones.FECHA_REALIZADA_TRANSACCIONES + " " + TEXT_TYPE + ", "
            + ColumnTranscacciones.CUANTIA_TRANSACCIONES + " " + REAL_TYPE + " not null, "
            + ColumnTranscacciones.REALIZADA_TRANSACCIONES + " " + INTEGER_TYPE + " not null default 0)";

    // Script de creación de la tabla jugador_partidos
    public static final String CREATE_JUGADOR_PARTIDOS_SCRIPT = "create table " + JUGADOR_PARTIDOS_TABLE_NAME + "("
            + ColumnJugadorPartidos.ID_PARTIDO_JUGADOR_PARTIDOS + " " + INTEGER_TYPE + " not null, "
            + ColumnJugadorPartidos.ID_JUGADOR_JUGADOR_PARTIDOS + " " + INTEGER_TYPE + " not null, "
            + ColumnJugadorPartidos.EQUIPO_JUGADOR_PARTIDOS + " " + TEXT_TYPE + " not null, "
            + ColumnJugadorPartidos.GOLES_JUGADOR_PARTIDOS + " " + INTEGER_TYPE + " not null default 0, "
            + ColumnJugadorPartidos.PUNTOS_JUGADOR_PARTIDOS + " " + INTEGER_TYPE + " not null default 0, "
            + ColumnJugadorPartidos.VICTORIA_JUGADOR_PARTIDOS + " " + INTEGER_TYPE + " not null default 2, "
            + "foreign key (" + ColumnJugadorPartidos.ID_PARTIDO_JUGADOR_PARTIDOS + ") references "
            + PARTIDOS_TABLE_NAME + "(" + ColumnPartidos.ID_PARTIDOS + "), "
            + "foreign key (" + ColumnJugadorPartidos.ID_JUGADOR_JUGADOR_PARTIDOS + ") references "
            + JUGADORES_TABLE_NAME + "(" + ColumnJugadores.ID_JUGADORES + "))";

    // Script de creación de la tabla jugador_cuotas
    public static final String CREATE_JUGADOR_CUOTAS_SCRIPT = "create table " + JUGADOR_CUOTAS_TABLE_NAME + "("
            + ColumnJugadorCuotas.ID_CUOTA_JUGADOR_CUOTAS + " " + INTEGER_TYPE + " not null, "
            + ColumnJugadorCuotas.ID_JUGADOR_JUGADOR_CUOTAS + " " + INTEGER_TYPE + " not null, "
            + ColumnJugadorCuotas.ID_TEMPORADA_JUGADOR_CUOTAS + " " + INTEGER_TYPE + " not null, "
            + ColumnJugadorCuotas.FECHA_JUGADOR_CUOTAS + " " + TEXT_TYPE + ", "
            + ColumnJugadorCuotas.PAGADA_JUGADOR_CUOTAS + " " + INTEGER_TYPE + " not null default 0, "
            + ColumnJugadorCuotas.DESCARTADA_JUGADOR_CUOTAS + " " + INTEGER_TYPE + " not null default 0, "
            + ColumnJugadorCuotas.MOTIVO_DESCARTE_JUGADOR_CUOTAS + " " + TEXT_TYPE + ", "
            + ColumnJugadorCuotas.ID_TRANSACCION_JUGADOR_CUOTAS + " " + INTEGER_TYPE + " not null default 0, "
            + "foreign key (" + ColumnJugadorCuotas.ID_CUOTA_JUGADOR_CUOTAS + ") references "
            + CUOTAS_TABLE_NAME + "(" + ColumnCuotas.ID_CUOTAS + "), "
            + "foreign key (" + ColumnJugadorCuotas.ID_JUGADOR_JUGADOR_CUOTAS + ") references "
            + JUGADORES_TABLE_NAME + "( " + ColumnJugadores.ID_JUGADORES + "), "
            + "foreign key (" + ColumnJugadorCuotas.ID_TEMPORADA_JUGADOR_CUOTAS + ") references "
            + TEMPORADAS_TABLE_NAME + "( " + ColumnTemporadas.ID_TEMPORADAS + "))";

    // Script de creación de la tabla temporada_cuotas
    public static final String CREATE_TEMPORADA_CUOTAS_SCRIPT = "create table " + TEMPORADAS_CUOTAS_TABLE_NAME + "("
            + ColumnTemporadaCuotas.ID_TEMPORADA_TEMPORADA_CUOTAS + " " + INTEGER_TYPE + " not null, "
            + ColumnTemporadaCuotas.ID_CUOTAS_TEMPORADA_CUOTAS + " " + INTEGER_TYPE + " not null, "
            + ColumnTemporadaCuotas.FECHA_LIMITE_CUOTAS + " " + TEXT_TYPE + " not null, "
            + "foreign key (" + ColumnTemporadaCuotas.ID_TEMPORADA_TEMPORADA_CUOTAS + ") references "
            + TEMPORADAS_TABLE_NAME + "(" + ColumnTemporadas.ID_TEMPORADAS + "), "
            + "foreign key (" + ColumnTemporadaCuotas.ID_CUOTAS_TEMPORADA_CUOTAS + ") references "
            + CUOTAS_TABLE_NAME + "(" + ColumnCuotas.ID_CUOTAS + "))";

    // Script de inserción por defecto para pruebas

    // Tabla temporadas
    public static final String INSERT_TEMPORADAS_SCRIPT = "insert into " + TEMPORADAS_TABLE_NAME + " values"
            + "(1, 2015, 2016)";

    // Tabla jugadores
    public static final String INSERT_JUGADORES_SCRIPT = "insert into " + JUGADORES_TABLE_NAME + " values"
            + "(1, 'Francisco', 'Jiménez', 'del Río', 'Paco', 5, 6, 1, 1, ''),"
            + "(2, 'Jose Miguel', 'del Río', 'Pérez', 'Maira', 7, 6, 1, 1, ''),"
            + "(3, 'Jose Manuel', 'Vargas', 'Alonso', 'Lete', 2, 5, 1, 1, ''),"
            + "(4, 'Francisco', 'Ruiz', '', 'Fran', 10, 4, 1, 1, ''),"
            + "(5, 'David', 'Jiménez', 'Molina', 'Cobra', 3, 6, 1, 1, ''),"
            + "(6, 'Adrián', 'Cabrera', '', '', 9, 10, 1, 1, ''),"
            + "(7, 'Antonio', 'Bermudez', '', 'Verbenas', 2, 8, 1, 1, ''),"
            + "(8, 'Ismael', 'López', '', 'Luismi', 9, 10, 1, 1, ''),"
            + "(9, 'Rubén', 'Carrascosa', 'Castillo', 'Mencho', 5, 4, 1, 1, ''),"
            + "(10, 'Jose', 'Guzman', '', 'Pepe', 4, 8, 1, 1, ''),"
            + "(11, 'David', 'Ruiz', '', '', 4, 6, 1, 0, ''),"
            + "(12, 'Rafael', 'Agudo', 'García', 'Rafa', 1, 4, 0, 0, ''),"
            + "(13, 'Raúl', 'Borja', 'Expósito', 'Boa', 1, 5, 1, 0, '')";

    // Tabla cuotas
    public static final String INSERT_CUOTAS_SCRIPT = "insert into " + CUOTAS_TABLE_NAME + " values"
            + "(1, '001', 'Cuota 1', 'Cuota inicial de la temporada', 1, '2015-09-10', 50),"
            + "(2, '001', 'Cuota Papeletas', 'Cuota venta de papeletas', 2, '2015-12-20', 50),"
            + "(3, '001', 'Cuota adicional', 'Cuota para cubrir gasto de balones', 3, '2016-02-01', 10),"
            + "(4, '004', 'Cuota final', 'Cuota para hacer prueba', 4, '2016-02-01', 100)";

    // Tabla partidos
    public static final String INSERT_PARTIDOS_SCRIPT = "insert into " + PARTIDOS_TABLE_NAME + " values"
            + "(1, 1, '2015-09-01', 1, 1),"
            + "(2, 2, '2015-09-08', 1, 1),"
            + "(3, 3, '2015-09-15', 1, 1),"
            + "(4, 4, '2015-09-15', 0, 1)";

    // Tabla transacciones
    public static final String INSERT_TRANSACCIONES_SCRIPT = "insert into " + TRANSACCIONES_TABLE_NAME + " values"
            + "(1, 'I-001', 'Depósito inicial', 'Cantidad que había en la caja al comienzo de la gestión.', 1, '2015-09-01', '2015-09-01', 1000, 1),"
            + "(2, 'C-101', 'Cuota 1', 'Pago Cuota 1 miembro Paco', 1, '2015-09-10', '2015-08-23', 50, 1),"
            + "(3, 'G-Ball', 'Compra balón', 'Se realiza la compra de un nuevo balón', 0, '2015-09-10', '2015-09-10', -29.99, 1),"
            + "(4, 'A-001', 'Plazo 1 Alquilé', 'Plazo uno del alquilé del pabellón', 0, '2015-09-10', '2015-09-7', -500.99, 1),"
            + "(5, 'A-002', 'Plazo 2 Alquilé', 'Plazo uno del alquilé del pabellón', 0, '2016-02-10', '', -500.99, 0)";

    // Tabla temporada_cuotas
    public static final String INSERT_TEMPORADA_CUOTAS_SCRIPT = "insert into " + TEMPORADAS_CUOTAS_TABLE_NAME + " values"
            + "(1, 1, '2016-09-10'),"
            + "(1, 2, '2016-12-20'),"
            + "(1, 3, '2017-02-01')";

    // Tabla jugador_partidos
    public static final String INSERT_JUGADOR_PARTIDOS_SCRIPT = "insert into " + JUGADOR_PARTIDOS_TABLE_NAME + " values"
            + "(1, 1, 'A', 2, 7, 1),"
            + "(1, 2, 'A', 3, 10, 1),"
            + "(1, 3, 'A', 0, 1, 1),"
            + "(1, 4, 'A', 1, 4, 1),"
            + "(1, 5, 'A', 4, 13, 1),"
            + "(1, 6, 'B', 2, 6, 0),"
            + "(1, 7, 'B', 1, 3, 0),"
            + "(1, 8, 'B', 3, 9, 0),"
            + "(1, 9, 'B', 0, 0, 0),"
            + "(1, 10, 'B', 3, 9, 0),"
            + "(2, 1, 'A', 1, 3, 0),"
            + "(2, 2, 'A', 2, 6, 0),"
            + "(2, 3, 'A', 0, 0, 0),"
            + "(2, 4, 'A', 1, 3, 0),"
            + "(2, 5, 'A', 4, 12, 0),"
            + "(2, 6, 'B', 1, 4, 1),"
            + "(2, 7, 'B', 1, 4, 1),"
            + "(2, 8, 'B', 4, 13, 1),"
            + "(2, 9, 'B', 3, 10, 1),"
            + "(2, 10, 'B', 3, 10, 1),"
            + "(3, 1, 'A', 2, 6, 2),"
            + "(3, 2, 'A', 3, 9, 2),"
            + "(3, 3, 'A', 4, 12, 2),"
            + "(3, 4, 'A', 0, 0, 2),"
            + "(3, 5, 'A', 3, 9, 2),"
            + "(3, 6, 'B', 6, 18, 2),"
            + "(3, 7, 'B', 1, 3, 2),"
            + "(3, 8, 'B', 3, 9, 2),"
            + "(3, 9, 'B', 1, 3, 2),"
            + "(3, 10, 'B', 1, 3, 2),"
            + "(4, 1, 'A', 0, 0, 0),"
            + "(4, 2, 'A', 0, 0, 0),"
            + "(4, 3, 'A', 0, 0, 0),"
            + "(4, 4, 'A', 0, 0, 0),"
            + "(4, 5, 'A', 0, 0, 0),"
            + "(4, 6, 'B', 0, 0, 0),"
            + "(4, 7, 'B', 0, 0, 0),"
            + "(4, 8, 'B', 0, 0, 0),"
            + "(4, 9, 'B', 0, 0, 0),"
            + "(4, 10, 'B', 0, 0, 0)";

    // Tabla jugador_cuotas
    public static final String INSERT_JUGADOR_CUOTAS_SCRIPT = "insert into " + JUGADOR_CUOTAS_TABLE_NAME + " values"
            + "(1, 1, 1, '2015-09-01', 1, 0, null, 2),"
            + "(1, 3, 1, '2015-09-01', 0, 1, null, 2)";

    // Constante con alias de propiedad
    public static final String JUGADOR_PUNTOS_TEMPORADA = "puntos_total";

    /**
     * Select para obtener la lista de jugadores con los puntos acumulados en la temporada seleccionada
     */
    private final String SELECT_LIST_JUGADORES = "select " + JUGADORES_TABLE_NAME + "." + ColumnJugadores.ID_JUGADORES + ", "
            + ColumnJugadores.FOTO_JUGADORES + ", "
            + ColumnJugadores.NOMBRE_JUGADORES + ", " + ColumnJugadores.APODO_JUGADORES + ", "
            + ColumnJugadores.APELLIDO1_JUGADORES + ", sum(" + ColumnJugadorPartidos.PUNTOS_JUGADOR_PARTIDOS + ") as puntos_total "
            + "from " + JUGADORES_TABLE_NAME + " join " + JUGADOR_PARTIDOS_TABLE_NAME + " on " + JUGADORES_TABLE_NAME + "." + ColumnJugadores.ID_JUGADORES
            + " = " + ColumnJugadorPartidos.ID_JUGADOR_JUGADOR_PARTIDOS + " join " + PARTIDOS_TABLE_NAME + " on " + ColumnJugadorPartidos.ID_PARTIDO_JUGADOR_PARTIDOS
            + " = " + PARTIDOS_TABLE_NAME + "." + ColumnPartidos.ID_PARTIDOS + " join " + TEMPORADAS_TABLE_NAME + " on " + ColumnPartidos.ID_TEMPORADA_PARTIDOS
            + " = " + TEMPORADAS_TABLE_NAME + "." + ColumnTemporadas.ID_TEMPORADAS + " where " + TEMPORADAS_TABLE_NAME + "." + ColumnTemporadas.ID_TEMPORADAS + " = ? "
            + " group by " + ColumnJugadores.NOMBRE_JUGADORES + ", " + ColumnJugadores.APODO_JUGADORES + ", "
            + ColumnJugadores.APELLIDO1_JUGADORES + " order by puntos_total desc";

    /**
     * Ejecutamos la consulta para devolver la lista de jugadores con los apuntos acumulados en la temporada seleccionada
     *
     * @param idTemporada ID de la temporada seleccionada
     * @return lista de jugadores con los puntos acumulados en la temporada seleccionada
     */
    public ArrayList<Jugador> getJugadoresLista(int idTemporada) {

        SQLiteDatabase database = openHelper.getReadableDatabase();
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Cursor mCursor = database.rawQuery(SELECT_LIST_JUGADORES, new String[]{String.valueOf(idTemporada)});
        for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
            Jugador jugador = Jugador.cursorToJugadorList(this.context, mCursor);
            jugadores.add(jugador);
        }

        mCursor.close();
        return jugadores;
    }

    /**
     * Guarda un jugador en la base de datos
     *
     * @param jugador objeto jugador
     * @return ID del jugador creado en la base de datos
     */
    public int saveJugador(Jugador jugador) {

        int idCreate = 0;
        if (jugador.getId() == 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ColumnJugadores.NOMBRE_JUGADORES, jugador.getNombre());
            contentValues.put(ColumnJugadores.APELLIDO1_JUGADORES, jugador.getApellido1());
            contentValues.put(ColumnJugadores.APELLIDO2_JUGADORES, jugador.getApellido2());
            contentValues.put(ColumnJugadores.APELLIDO1_JUGADORES, jugador.getApellido1());
            contentValues.put(ColumnJugadores.DORSAL_JUGADORES, jugador.getDorsal());
            contentValues.put(ColumnJugadores.APODO_JUGADORES, jugador.getApodo());
            contentValues.put(ColumnJugadores.CALIDAD_JUGADORES, jugador.getCalidad());
            contentValues.put(ColumnJugadores.SOCIO_JUGADORES, jugador.getSocio());
            contentValues.put(ColumnJugadores.ACTIVO_JUGADORES, jugador.getActivo());
            contentValues.put(ColumnJugadores.FOTO_JUGADORES, jugador.getFoto());
            idCreate = (int) database.insert(JUGADORES_TABLE_NAME, null, contentValues);
        }

        return idCreate;

    }

    // Alias de propiedades
    public static final String JUGADOR_GOLES_TEMPORADA = "goles";
    public static final String JUGADOR_PARTIDOS_TEMPORADA = "partidos";
    public static final String JUGADOR_VICTORIAS_TEMPORADA = "victorias";

    /**
     * Consula para obtener los datos de un jugador, en la temporada seleccionada, a mostrar en la vista de detalle
     */
    private final String SELECT_JUGADORES_DATOS = "select "
            + ColumnJugadores.FOTO_JUGADORES + ", " + ColumnJugadores.NOMBRE_JUGADORES + ", " + ColumnJugadores.APODO_JUGADORES + ", "
            + ColumnJugadores.APELLIDO1_JUGADORES + ", " + ColumnJugadores.APELLIDO2_JUGADORES + ", " + ColumnJugadores.ACTIVO_JUGADORES + ", "
            + ColumnJugadores.CALIDAD_JUGADORES + ", " + ColumnJugadores.DORSAL_JUGADORES + ", " + ColumnJugadores.SOCIO_JUGADORES
            + ", sum(" + PARTIDOS_TABLE_NAME + "." + ColumnPartidos.DISPUTADO_PARTIDOS + ") 'partidos' "
            + ", sum(" + ColumnJugadorPartidos.GOLES_JUGADOR_PARTIDOS + ") 'goles' "
            + ", sum(" + ColumnJugadorPartidos.VICTORIA_JUGADOR_PARTIDOS + " % 2) 'victorias' "
            + ", sum(" + ColumnJugadorPartidos.PUNTOS_JUGADOR_PARTIDOS + ") 'puntos_total' "
            + "from " + JUGADORES_TABLE_NAME + " join " + JUGADOR_PARTIDOS_TABLE_NAME + " on " + JUGADORES_TABLE_NAME + "." + ColumnJugadores.ID_JUGADORES
            + " = " + JUGADOR_PARTIDOS_TABLE_NAME + "." + ColumnJugadorPartidos.ID_JUGADOR_JUGADOR_PARTIDOS + " join " + PARTIDOS_TABLE_NAME + " on "
            + JUGADOR_PARTIDOS_TABLE_NAME + "." + ColumnJugadorPartidos.ID_PARTIDO_JUGADOR_PARTIDOS
            + " = " + PARTIDOS_TABLE_NAME + "." + ColumnPartidos.ID_PARTIDOS + " where " + JUGADORES_TABLE_NAME + "." + ColumnJugadores.ID_JUGADORES + " = ? and "
            + PARTIDOS_TABLE_NAME + "." + ColumnPartidos.ID_TEMPORADA_PARTIDOS + " = ? " + " group by " + ColumnJugadores.FOTO_JUGADORES + ", " + ColumnJugadores.NOMBRE_JUGADORES + ", "
            + ColumnJugadores.APODO_JUGADORES + ", " + ColumnJugadores.APELLIDO1_JUGADORES + ", " + ColumnJugadores.APELLIDO2_JUGADORES + ", " + ColumnJugadores.ACTIVO_JUGADORES + ", "
            + ColumnJugadores.CALIDAD_JUGADORES + ", " + ColumnJugadores.DORSAL_JUGADORES + ", " + ColumnJugadores.SOCIO_JUGADORES;

    /**
     * Ejecuta el select para mostrar los datos del jugador en la temporada seleccionada en la
     * vista de detalle y devuelve un objeto jugador con los datos
     *
     * @param idJugador   ID del jugador a consultar
     * @param idTemporada ID de la temporada en la que se consultan los datos
     * @return jugador
     */
    public Jugador getJugadorDatos(int idJugador, int idTemporada) {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        Jugador jugador = null;
        Cursor mCursor = database.rawQuery(SELECT_JUGADORES_DATOS, new String[]{String.valueOf(idJugador), String.valueOf(idTemporada)});
        mCursor.moveToFirst();
        jugador = Jugador.cursorToJugadorDatos(this.context, mCursor);

        mCursor.close();
        return jugador;
    }

    /**
     * Consulta para obtener los datos que se usan en las gráficas de la vista detalle de jugador
     */
    public final String SELECT_JUGADORES_GRAFICA_PARTIDOS = "select " + JUGADOR_PARTIDOS_TABLE_NAME + "." + ColumnJugadorPartidos.VICTORIA_JUGADOR_PARTIDOS + ", "
            + JUGADOR_PARTIDOS_TABLE_NAME + "." + ColumnJugadorPartidos.GOLES_JUGADOR_PARTIDOS + ", " + PARTIDOS_TABLE_NAME + "." + ColumnPartidos.JORNADA_PARTIDOS + " "
            + " from " + JUGADOR_PARTIDOS_TABLE_NAME + " join " + JUGADORES_TABLE_NAME + " on " + JUGADOR_PARTIDOS_TABLE_NAME + "." + ColumnJugadorPartidos.ID_JUGADOR_JUGADOR_PARTIDOS
            + " = " + JUGADORES_TABLE_NAME + "." + ColumnJugadores.ID_JUGADORES + " join " + PARTIDOS_TABLE_NAME + " on " + JUGADOR_PARTIDOS_TABLE_NAME + "." + ColumnJugadorPartidos.ID_PARTIDO_JUGADOR_PARTIDOS
            + " = " + PARTIDOS_TABLE_NAME + "." + ColumnPartidos.ID_PARTIDOS + " where " + JUGADORES_TABLE_NAME + ". " + ColumnJugadores.ID_JUGADORES + " = ? and "
            + PARTIDOS_TABLE_NAME + "." + ColumnPartidos.ID_TEMPORADA_PARTIDOS + " = ? order by " + PARTIDOS_TABLE_NAME + "." + ColumnPartidos.FECHA_PARTIDOS;

    /**
     * Ejecuta la consulta para obtener los datos de las gráficas de la vista detalle de jugador
     *
     * @param idJugador   ID del jugador a consultar los datos
     * @param idTemporada ID de la temporada a la que pertenecen los datos
     * @return lista de datos enteros
     */
    public ArrayList<Integer[]> getJugadoresGraficaPartidos(int idJugador, int idTemporada) {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        ArrayList<Integer[]> datos = new ArrayList<>();
        Cursor mCursor = database.rawQuery(SELECT_JUGADORES_GRAFICA_PARTIDOS, new String[]{String.valueOf(idJugador), String.valueOf(idTemporada)});
        for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
            datos.add(new Integer[]{mCursor.getInt(mCursor.getColumnIndex(ColumnJugadorPartidos.VICTORIA_JUGADOR_PARTIDOS)),
                    mCursor.getInt(mCursor.getColumnIndex(ColumnJugadorPartidos.GOLES_JUGADOR_PARTIDOS)),
                    mCursor.getInt(mCursor.getColumnIndex(ColumnPartidos.JORNADA_PARTIDOS))});
        }

        mCursor.close();
        return datos;
    }

    /**
     * Consulta para obtener los partidos jugados y creados en una temporada
     */
    public final String SELECT_LIST_PARTIDOS = "select " + PARTIDOS_TABLE_NAME + "." + ColumnPartidos.ID_PARTIDOS + ", "
            + ColumnPartidos.JORNADA_PARTIDOS + ", " + ColumnPartidos.FECHA_PARTIDOS + ", " + ColumnPartidos.DISPUTADO_PARTIDOS + ", "
            + ColumnJugadorPartidos.EQUIPO_JUGADOR_PARTIDOS + ", " + "sum(" + JUGADOR_PARTIDOS_TABLE_NAME + "." + ColumnJugadorPartidos.GOLES_JUGADOR_PARTIDOS + ") as goles "
            + " from " + PARTIDOS_TABLE_NAME + " left join " + JUGADOR_PARTIDOS_TABLE_NAME + " on " + JUGADOR_PARTIDOS_TABLE_NAME + "." + ColumnJugadorPartidos.ID_PARTIDO_JUGADOR_PARTIDOS
            + " = " + PARTIDOS_TABLE_NAME + "." + ColumnPartidos.ID_PARTIDOS + " where " + PARTIDOS_TABLE_NAME + "." + ColumnPartidos.ID_TEMPORADA_PARTIDOS + " = ? group by "
            + PARTIDOS_TABLE_NAME + "." + ColumnPartidos.ID_PARTIDOS + ", " + ColumnPartidos.JORNADA_PARTIDOS + ", " + ColumnPartidos.FECHA_PARTIDOS + ", " + ColumnPartidos.DISPUTADO_PARTIDOS + ", " + ColumnJugadorPartidos.EQUIPO_JUGADOR_PARTIDOS
            + " order by " + ColumnPartidos.JORNADA_PARTIDOS + " desc, " + PARTIDOS_TABLE_NAME + "." + ColumnPartidos.ID_PARTIDOS + ", " + ColumnJugadorPartidos.EQUIPO_JUGADOR_PARTIDOS;

    /**
     * Ejecuta la consulta para obtener los partidos jugados y creados en una temporada
     *
     * @param idTemporada ID de la temporada seleccionada
     * @return lista de partidos
     */
    public ArrayList<Partido> getPartidosLista(int idTemporada) {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        ArrayList<Partido> partidos = new ArrayList<>();
        Cursor mCursor = database.rawQuery(SELECT_LIST_PARTIDOS, new String[]{String.valueOf(idTemporada)});
        int idPartidoAux = -1;
        Partido partido = null;
        for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
            if (mCursor.getInt(mCursor.getColumnIndex(ColumnPartidos.DISPUTADO_PARTIDOS)) == 1) {
                if (mCursor.getString(mCursor.getColumnIndex(ColumnJugadorPartidos.EQUIPO_JUGADOR_PARTIDOS)).equalsIgnoreCase("A")) {
                    idPartidoAux = mCursor.getInt(mCursor.getColumnIndex(ColumnPartidos.ID_PARTIDOS));
                    partido = Partido.cursorToPartidoList(mCursor);
                } else {
                    if (mCursor.getInt(mCursor.getColumnIndex(ColumnPartidos.ID_PARTIDOS)) == idPartidoAux) {
                        partido.setGolesEquipoB(mCursor.getInt(mCursor.getColumnIndex("goles")));
                        partidos.add(partido);
                    }
                }
            } else if (mCursor.getString(mCursor.getColumnIndex(ColumnJugadorPartidos.EQUIPO_JUGADOR_PARTIDOS)).equalsIgnoreCase("A")) {
                partido = Partido.cursorToPartidoList(mCursor);
                partidos.add(partido);
            }
        }

        mCursor.close();
        return partidos;
    }

    /**
     * Consulta para obtener los datos de un partido
     */
    public final String SELECT_PARTIDO_DATOS = "select " + PARTIDOS_TABLE_NAME + "." + ColumnPartidos.ID_PARTIDOS + ", "
            + ColumnPartidos.JORNADA_PARTIDOS + ", " + ColumnPartidos.FECHA_PARTIDOS + ", " + ColumnPartidos.DISPUTADO_PARTIDOS + ", "
            + ColumnJugadorPartidos.EQUIPO_JUGADOR_PARTIDOS + ", " + "sum(" + JUGADOR_PARTIDOS_TABLE_NAME + "." + ColumnJugadorPartidos.GOLES_JUGADOR_PARTIDOS + ") as goles "
            + " from " + PARTIDOS_TABLE_NAME + " left join " + JUGADOR_PARTIDOS_TABLE_NAME + " on " + JUGADOR_PARTIDOS_TABLE_NAME + "." + ColumnJugadorPartidos.ID_PARTIDO_JUGADOR_PARTIDOS
            + " = " + PARTIDOS_TABLE_NAME + "." + ColumnPartidos.ID_PARTIDOS + " where " + PARTIDOS_TABLE_NAME + "." + ColumnPartidos.ID_PARTIDOS + " = ? group by "
            + ColumnPartidos.JORNADA_PARTIDOS + ", " + ColumnPartidos.FECHA_PARTIDOS + ", " + ColumnPartidos.DISPUTADO_PARTIDOS + ", " + ColumnJugadorPartidos.EQUIPO_JUGADOR_PARTIDOS
            + " order by " + ColumnPartidos.FECHA_PARTIDOS + " desc, " + ColumnJugadorPartidos.EQUIPO_JUGADOR_PARTIDOS;

    /**
     * Ejecuta la consulta para obtener los datos de un partido
     *
     * @param idPartido ID del partido a consultar
     * @return partido
     */
    public Partido getDatosPartido(int idPartido) {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        Cursor mCursor = database.rawQuery(SELECT_PARTIDO_DATOS, new String[]{String.valueOf(idPartido)});
        Partido partido = null;
        for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
            if (mCursor.getInt(mCursor.getColumnIndex(ColumnPartidos.DISPUTADO_PARTIDOS)) == 1) {
                if (mCursor.getString(mCursor.getColumnIndex(ColumnJugadorPartidos.EQUIPO_JUGADOR_PARTIDOS)).equalsIgnoreCase("A")) {
                    partido = Partido.cursorToPartidoList(mCursor);
                } else {
                    partido.setGolesEquipoB(mCursor.getInt(mCursor.getColumnIndex("goles")));
                }
            } else {
                partido = Partido.cursorToPartidoList(mCursor);
            }
        }

        mCursor.close();
        return partido;
    }

    /**
     * Consulta que obtiene los datos de los jugadores que disputan un partido
     */
    public final String SELECT_PARTIDO_DATOS_JUGADORES = "select " + JUGADOR_PARTIDOS_TABLE_NAME + "." + ColumnJugadorPartidos.ID_JUGADOR_JUGADOR_PARTIDOS + ", " + JUGADOR_PARTIDOS_TABLE_NAME + "." + ColumnJugadorPartidos.GOLES_JUGADOR_PARTIDOS + ", "
            + JUGADOR_PARTIDOS_TABLE_NAME + "." + ColumnJugadorPartidos.EQUIPO_JUGADOR_PARTIDOS + ", " + JUGADORES_TABLE_NAME + "." + ColumnJugadores.NOMBRE_JUGADORES + ", "
            + JUGADORES_TABLE_NAME + "." + ColumnJugadores.APELLIDO1_JUGADORES + ", " + JUGADORES_TABLE_NAME + "." + ColumnJugadores.DORSAL_JUGADORES + ", " + JUGADORES_TABLE_NAME + "." + ColumnJugadores.APODO_JUGADORES
            + " from " + JUGADOR_PARTIDOS_TABLE_NAME + " join " + JUGADORES_TABLE_NAME + " on " + JUGADOR_PARTIDOS_TABLE_NAME + "." + ColumnJugadorPartidos.ID_JUGADOR_JUGADOR_PARTIDOS
            + " = " + JUGADORES_TABLE_NAME + "." + ColumnJugadores.ID_JUGADORES + " where " + JUGADOR_PARTIDOS_TABLE_NAME + "." + ColumnJugadorPartidos.ID_PARTIDO_JUGADOR_PARTIDOS + " = ? "
            + " order by " + JUGADOR_PARTIDOS_TABLE_NAME + "." + ColumnJugadorPartidos.EQUIPO_JUGADOR_PARTIDOS;

    /**
     * Ejecuta la consulta que obtiene los datos de los jugadores que disputan un partido
     * @param idPartido ID del partido a consultar
     * @return lista de jugadores con los datos
     */
    public ArrayList<Jugador> getDatosPartidoJugadores(int idPartido) {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador jugador = null;
        Cursor mCursor = database.rawQuery(SELECT_PARTIDO_DATOS_JUGADORES, new String[]{String.valueOf(idPartido)});
        for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
            jugador = Jugador.cursorToJugadorPartido(mCursor);
            jugadores.add(jugador);
        }
        return jugadores;
    }

    /**
     * Elimina un partido de la base de datos y los datos que este generó en los jugadores
     * @param idPartido ID del partido a eliminar
     */
    public void deletePartido(int idPartido) {

        database.delete(JUGADOR_PARTIDOS_TABLE_NAME, ColumnJugadorPartidos.ID_PARTIDO_JUGADOR_PARTIDOS + "=" + idPartido, null);
        database.delete(PARTIDOS_TABLE_NAME, ColumnPartidos.ID_PARTIDOS + " = " + idPartido, null);

    }

    /**
     * Inserta los datos del resultado de un partido y los goles marcados por un jugador
     * @param idPartido ID del partido
     * @param jugador ID del jugador
     */
    public void setResultadosPartido(int idPartido, Jugador jugador) {

        ContentValues contentPartido = new ContentValues();
        contentPartido.put(ColumnPartidos.DISPUTADO_PARTIDOS, 1);
        database.update(PARTIDOS_TABLE_NAME, contentPartido, ColumnPartidos.ID_PARTIDOS + " = " + idPartido, null);
        ContentValues valuesJugadorPartido = new ContentValues();
        valuesJugadorPartido.put(ColumnJugadorPartidos.ID_PARTIDO_JUGADOR_PARTIDOS, idPartido);
        valuesJugadorPartido.put(ColumnJugadorPartidos.EQUIPO_JUGADOR_PARTIDOS, jugador.getEquipo());
        valuesJugadorPartido.put(ColumnJugadorPartidos.GOLES_JUGADOR_PARTIDOS, jugador.getGoles());
        valuesJugadorPartido.put(ColumnJugadorPartidos.PUNTOS_JUGADOR_PARTIDOS, jugador.getPuntos());
        valuesJugadorPartido.put(ColumnJugadorPartidos.VICTORIA_JUGADOR_PARTIDOS, jugador.getVictorias());
        database.update(JUGADOR_PARTIDOS_TABLE_NAME, valuesJugadorPartido, ColumnJugadorPartidos.ID_PARTIDO_JUGADOR_PARTIDOS + " = " + idPartido + " and " + ColumnJugadorPartidos.ID_JUGADOR_JUGADOR_PARTIDOS + " = " + jugador.getId(), null);

    }

    /**
     * Consulta para obtener el total de fondos de la caja de la peña
     */
    public final String SELECT_TOTAL_CAJA = "select sum(" + ColumnTranscacciones.CUANTIA_TRANSACCIONES + ") as total from " + TRANSACCIONES_TABLE_NAME
            + " where " + ColumnTranscacciones.REALIZADA_TRANSACCIONES + " = 1";

    /**
     * Consulta para obtener el total de transacciones realizadas
     */
    public final String SELECT_LIST_TRANSACCIONES = "select " + ColumnTranscacciones.ID_TRANSACCIONES + ", " + ColumnTranscacciones.FECHA_LIMITE_TRANSACCIONES + ", " + ColumnTranscacciones.NOMBRE_TRANSACCIONES
            + ", " + ColumnTranscacciones.CUANTIA_TRANSACCIONES + ", " + ColumnTranscacciones.REALIZADA_TRANSACCIONES + ", " + ColumnTranscacciones.TIPO_TRANSACCIONES + " from " + TRANSACCIONES_TABLE_NAME
            + " order by " + ColumnTranscacciones.FECHA_LIMITE_TRANSACCIONES + " desc";

    /**
     * Ejecuta la consulta para obtener el total de fondos de la caja de la peña
     * @return total de fondos en la caja de la peña
     */
    public float getTotalCaja() {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        Cursor mCursor = database.rawQuery(SELECT_TOTAL_CAJA, new String[]{});
        mCursor.moveToFirst();

        float totalCaja = mCursor.getFloat(mCursor.getColumnIndex("total"));
        mCursor.close();
        return totalCaja;
    }

    /**
     * Ejecuta la consulta para obtener el total de transacciones realizadas
     * @return lista de transacciones
     */
    public ArrayList<Transaccion> getTransaccionesLista() {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        ArrayList<Transaccion> transacciones = new ArrayList<>();
        Cursor mCursor = database.rawQuery(SELECT_LIST_TRANSACCIONES, new String[]{});
        for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
            Transaccion transaccion = Transaccion.cursorToTransaccionesList(mCursor);
            transacciones.add(transaccion);
        }

        mCursor.close();
        return transacciones;
    }

    /**
     * Consulta para obtener la información que se muestra en la vista detalle de una transacción
     */
    public final String SELECT_TRANSACCION = "select * from " + TRANSACCIONES_TABLE_NAME + " where " + TRANSACCIONES_TABLE_NAME + "." + ColumnTranscacciones.ID_TRANSACCIONES + " = ? order by " + ColumnTranscacciones.FECHA_LIMITE_TRANSACCIONES + " desc";

    /**
     * Ejecuta la consulta para obtener la información que se muestra en la vista detalle de una transacción
     * @param idTransaccion ID de la transacción a consultar
     * @return transacción
     */
    public Transaccion getTransaccion(int idTransaccion) {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        Cursor mCursor = database.rawQuery(SELECT_TRANSACCION, new String[]{String.valueOf(idTransaccion)});
        mCursor.moveToFirst();

        Transaccion transaccion = Transaccion.cursorToTransaccion(mCursor);
        mCursor.close();
        return transaccion;
    }

    /**
     * Crea una transacción en la base de datos
     * @param codigo código de la transacción
     * @param nombre nombre de la transacción
     * @param descripcion descipción de la transacción
     * @param tipo 0: pago, 1: ingreso
     * @param fechaLimite fecha límite para realizar la transacción
     * @param fechaRealizada fecha en la que se realiza el pago o cobro la transacción
     * @param cuantia cuantía de la transacción
     * @param realizada 0: no realizada, 1: realizada
     * @return
     */
    public int crearTransaccion(String codigo, String nombre, String descripcion, int tipo, String fechaLimite, String fechaRealizada, float cuantia, int realizada) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ColumnTranscacciones.CODIGO_TRANSACCIONES, codigo);
        contentValues.put(ColumnTranscacciones.NOMBRE_TRANSACCIONES, nombre);
        contentValues.put(ColumnTranscacciones.DESCRIPCION_TRANSACCIONES, descripcion);
        contentValues.put(ColumnTranscacciones.TIPO_TRANSACCIONES, tipo);
        contentValues.put(ColumnTranscacciones.FECHA_LIMITE_TRANSACCIONES, fechaLimite);
        contentValues.put(ColumnTranscacciones.FECHA_REALIZADA_TRANSACCIONES, fechaRealizada);
        contentValues.put(ColumnTranscacciones.CUANTIA_TRANSACCIONES, cuantia);
        contentValues.put(ColumnTranscacciones.REALIZADA_TRANSACCIONES, realizada);
        int resultado = (int) database.insert(TRANSACCIONES_TABLE_NAME, null, contentValues);

        return resultado;
    }

    /**
     * Crea una transacción en la base de datos
     * @param transaccion transacción a crear
     * @return
     */
    public int crearTransaccion(Transaccion transaccion) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ColumnTranscacciones.CODIGO_TRANSACCIONES, transaccion.getCodigo());
        contentValues.put(ColumnTranscacciones.NOMBRE_TRANSACCIONES, transaccion.getNombre());
        contentValues.put(ColumnTranscacciones.DESCRIPCION_TRANSACCIONES, transaccion.getDescripcion());
        contentValues.put(ColumnTranscacciones.TIPO_TRANSACCIONES, transaccion.getTipo());
        contentValues.put(ColumnTranscacciones.FECHA_LIMITE_TRANSACCIONES, Utilidades.pasarDateBD(transaccion.getFechaLimite()));
        contentValues.put(ColumnTranscacciones.FECHA_REALIZADA_TRANSACCIONES, Utilidades.pasarDateBD(transaccion.getFechaRealizada()));
        contentValues.put(ColumnTranscacciones.CUANTIA_TRANSACCIONES, transaccion.getCuantia());
        contentValues.put(ColumnTranscacciones.REALIZADA_TRANSACCIONES, transaccion.getRealizada());
        int resultado = (int) database.insert(TRANSACCIONES_TABLE_NAME, null, contentValues);

        return resultado;
    }

    /**
     * Realiza una transacción, es decir, la marca como pagada o cobrada
     * @param idTransaccion ID de la transacción a realizar
     * @return
     */
    public String realizarTransaccion(int idTransaccion) {

        String fechaRealizada = Utilidades.fechaActualParaBD();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ColumnTranscacciones.REALIZADA_TRANSACCIONES, 1);
        contentValues.put(ColumnTranscacciones.FECHA_REALIZADA_TRANSACCIONES, fechaRealizada);
        int rows = database.update(TRANSACCIONES_TABLE_NAME, contentValues, ColumnTranscacciones.ID_TRANSACCIONES + " = " + idTransaccion, null);
        ContentValues contentCuotaJugador = new ContentValues();
        contentCuotaJugador.put(ColumnJugadorCuotas.PAGADA_JUGADOR_CUOTAS, 1);
        contentCuotaJugador.put(ColumnJugadorCuotas.FECHA_JUGADOR_CUOTAS, fechaRealizada);
        database.update(JUGADOR_CUOTAS_TABLE_NAME, contentCuotaJugador, ColumnJugadorCuotas.ID_TRANSACCION_JUGADOR_CUOTAS + " = ?", new String[]{String.valueOf(idTransaccion)});

        return fechaRealizada;
    }

    /**
     * Consulta que identifica si una transacción se generó automáticamente al pagar una cuota
     */
    public final String SELECT_IS_TRANSACCION_CUOTA = "select " + ColumnJugadorCuotas.ID_CUOTA_JUGADOR_CUOTAS + " from " + JUGADOR_CUOTAS_TABLE_NAME + " where "
            + ColumnJugadorCuotas.ID_TRANSACCION_JUGADOR_CUOTAS + " = ?";

    /**
     * Ejecuta la consulta que identifica si una transacción se generó automáticamente al pagar una cuota
     * @param idTransaccion ID de la transacción a consultar
     * @return true si la generó una cuota, false en caso contrario
     */
    public boolean isTransaccionCuota(int idTransaccion) {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        Cursor mCursor = database.rawQuery(SELECT_IS_TRANSACCION_CUOTA, new String[]{String.valueOf(idTransaccion)});


        boolean respuesta = mCursor.getCount() > 0;
        mCursor.close();
        return respuesta;
    }

    /**
     * Actualiza una transacción
     * @param transaccion transacción a actualizar
     * @return numero de registros actualizados
     */
    public int updateTransaccion(Transaccion transaccion) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ColumnTranscacciones.CODIGO_TRANSACCIONES, transaccion.getCodigo());
        contentValues.put(ColumnTranscacciones.NOMBRE_TRANSACCIONES, transaccion.getNombre());
        contentValues.put(ColumnTranscacciones.DESCRIPCION_TRANSACCIONES, transaccion.getDescripcion());
        contentValues.put(ColumnTranscacciones.TIPO_TRANSACCIONES, transaccion.getTipo());
        contentValues.put(ColumnTranscacciones.FECHA_LIMITE_TRANSACCIONES, Utilidades.pasarDateBD(transaccion.getFechaLimite()));
        contentValues.put(ColumnTranscacciones.FECHA_REALIZADA_TRANSACCIONES, Utilidades.pasarDateBD(transaccion.getFechaRealizada()));
        contentValues.put(ColumnTranscacciones.CUANTIA_TRANSACCIONES, transaccion.getCuantia());
        contentValues.put(ColumnTranscacciones.REALIZADA_TRANSACCIONES, transaccion.getRealizada());
        int resultado = (int) database.update(TRANSACCIONES_TABLE_NAME, contentValues, ColumnTranscacciones.ID_TRANSACCIONES + " = ?", new String[]{String.valueOf(transaccion.getId())});

        return resultado;
    }

    /**
     * Anula una transacción, es decir, la marca cómo no realizada
     * @param idTransaccion ID de la transacción a anular
     * @return true si la ha anulado, false en caso contrario
     */
    public boolean anularTransaccion(int idTransaccion) {

        ContentValues contentTransaccion = new ContentValues();
        contentTransaccion.put(ColumnTranscacciones.REALIZADA_TRANSACCIONES, 0);
        contentTransaccion.put(ColumnTranscacciones.FECHA_REALIZADA_TRANSACCIONES, "");
        int rows = database.update(TRANSACCIONES_TABLE_NAME, contentTransaccion, ColumnTranscacciones.ID_TRANSACCIONES + " = ?", new String[]{String.valueOf(idTransaccion)});
        ContentValues contentCuotaJugador = new ContentValues();
        contentCuotaJugador.put(ColumnJugadorCuotas.PAGADA_JUGADOR_CUOTAS, 0);
        contentCuotaJugador.put(ColumnJugadorCuotas.FECHA_JUGADOR_CUOTAS, "");
        database.update(JUGADOR_CUOTAS_TABLE_NAME, contentCuotaJugador, ColumnJugadorCuotas.ID_TRANSACCION_JUGADOR_CUOTAS + " = ?", new String[]{String.valueOf(idTransaccion)});

        return rows > 0;
    }

    /**
     * Elimina una transacción
     * @param idTransaccion ID de la transacción a eliminar
     * @return true si la ha eliminado, false en caso contrario
     */
    public boolean eliminarTransaccion(int idTransaccion) {

        int rows = database.delete(TRANSACCIONES_TABLE_NAME, ColumnTranscacciones.ID_TRANSACCIONES + " = ?", new String[]{String.valueOf(idTransaccion)});
        database.delete(JUGADOR_CUOTAS_TABLE_NAME, ColumnJugadorCuotas.ID_TRANSACCION_JUGADOR_CUOTAS + " = ?", new String[]{String.valueOf(idTransaccion)});

        return rows > 0;
    }

    /**
     * Consulta para obtener el número de cuotas que tiene asociada una temporada
     */
    public final String SELECT_NUMERO_CUOTAS_TEMPORADA = "select count(" + CUOTAS_TABLE_NAME + "." + ColumnCuotas.ID_CUOTAS + ") as total_cuotas from "
            + CUOTAS_TABLE_NAME + " join " + TEMPORADAS_CUOTAS_TABLE_NAME + " on " + TEMPORADAS_CUOTAS_TABLE_NAME + "."
            + ColumnTemporadaCuotas.ID_CUOTAS_TEMPORADA_CUOTAS + " = " + CUOTAS_TABLE_NAME + "." + ColumnCuotas.ID_CUOTAS
            + " where " + TEMPORADAS_CUOTAS_TABLE_NAME + "." + ColumnTemporadaCuotas.ID_TEMPORADA_TEMPORADA_CUOTAS + " = ?";

    /**
     * Consulta para obtener el número de cuotas pagadas por un jugador en una temporada
     */
    public final String SELECT_JUGADORES_CUOTAS_PAGADAS_TEMPORADA = "select " + JUGADORES_TABLE_NAME + "." + ColumnJugadores.ID_JUGADORES + ", "
            + JUGADORES_TABLE_NAME + "." + ColumnJugadores.NOMBRE_JUGADORES + ", " + JUGADORES_TABLE_NAME + "." + ColumnJugadores.APELLIDO1_JUGADORES
            + ", ifnull(s.cuotas_pagadas, 0) as cuotas_pagadas from " + JUGADORES_TABLE_NAME + " left join "
            + "(select " + JUGADORES_TABLE_NAME + "." + ColumnJugadores.ID_JUGADORES + ", ifnull(sum(" + JUGADOR_CUOTAS_TABLE_NAME
            + "." + ColumnJugadorCuotas.PAGADA_JUGADOR_CUOTAS + ") + sum(" + JUGADOR_CUOTAS_TABLE_NAME + "." + ColumnJugadorCuotas.DESCARTADA_JUGADOR_CUOTAS + "), 0) as cuotas_pagadas from " + JUGADORES_TABLE_NAME + " left join "
            + JUGADOR_CUOTAS_TABLE_NAME + " on " + JUGADOR_CUOTAS_TABLE_NAME + "." + ColumnJugadorCuotas.ID_JUGADOR_JUGADOR_CUOTAS
            + " = " + JUGADORES_TABLE_NAME + "." + ColumnJugadores.ID_JUGADORES + " where (" + JUGADORES_TABLE_NAME + "." + ColumnJugadores.SOCIO_JUGADORES + " = 1 and "
            + "(" + JUGADOR_CUOTAS_TABLE_NAME + "." + ColumnJugadorCuotas.ID_TEMPORADA_JUGADOR_CUOTAS
            + " = ? or " + JUGADOR_CUOTAS_TABLE_NAME + "." + ColumnJugadorCuotas.ID_TEMPORADA_JUGADOR_CUOTAS + " is null)) group by "
            + JUGADORES_TABLE_NAME + "." + ColumnJugadores.NOMBRE_JUGADORES + ", " + JUGADORES_TABLE_NAME + "." + ColumnJugadores.APELLIDO1_JUGADORES
            + ") s on " + JUGADORES_TABLE_NAME + "." + ColumnJugadores.ID_JUGADORES + " = s." + ColumnJugadores.ID_JUGADORES + " where " + JUGADORES_TABLE_NAME + "." + ColumnJugadores.SOCIO_JUGADORES + " = 1"
            + " order by " + JUGADORES_TABLE_NAME + "." + ColumnJugadores.NOMBRE_JUGADORES + ", " + JUGADORES_TABLE_NAME + "." + ColumnJugadores.APELLIDO1_JUGADORES;

    /**
     * Obtiene el número de cuotas que tiene una temporada
     * @param idTemporada ID de la temporada a consultar
     * @return número de cuotas
     */
    public int getNumeroCuotasTemporada(int idTemporada) {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        Cursor cursorCuotasTotalesTemporada = database.rawQuery(SELECT_NUMERO_CUOTAS_TEMPORADA, new String[]{String.valueOf(idTemporada)});
        cursorCuotasTotalesTemporada.moveToFirst();

        int cuotasTotales = cursorCuotasTotalesTemporada.getInt(cursorCuotasTotalesTemporada.getColumnIndex("total_cuotas"));
        cursorCuotasTotalesTemporada.close();
        return cuotasTotales;
    }

    /**
     * Obtiene una lista con el número de cuotas que ha pagado cada jugador en una temporada
     * @param idTemporada ID de la temporada a consultar
     * @return lista con el número de cuotas pagadas por cada jugador
     */
    public ArrayList<JugadorCuota> getJugadorCuotasLista(int idTemporada) {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        ArrayList<JugadorCuota> jugadoresCuotas = new ArrayList<>();
        Cursor mCursor = database.rawQuery(SELECT_JUGADORES_CUOTAS_PAGADAS_TEMPORADA, new String[]{String.valueOf(idTemporada)});
        for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
            JugadorCuota jugadorCuota = JugadorCuota.cursorToJugadoresCuotasList(mCursor);
            jugadoresCuotas.add(jugadorCuota);
        }

        mCursor.close();
        return jugadoresCuotas;
    }

    /**
     * Consulta para mostrar el estado de las cuotas de una temporada para un jugador, en la vista detalle de las cuotas de un jugador
     */
    public final String SELECT_JUGADORES_CUOTAS_TEMPORADA = "select c." + ColumnCuotas.ID_CUOTAS + " as idCuota, c." + ColumnCuotas.CODIGO_CUOTAS
            + ", c." + ColumnCuotas.NOMBRE_CUOTAS + ", c." + ColumnCuotas.DESCRIPCION_CUOTAS + ", c." + ColumnCuotas.ORDEN_CUOTAS + ", h." + ColumnTemporadaCuotas.FECHA_LIMITE_CUOTAS
            + ", c." + ColumnCuotas.CUANTIA_CUOTAS + ", ? as idJugador, s." + ColumnJugadorCuotas.PAGADA_JUGADOR_CUOTAS + ", s." + ColumnJugadorCuotas.FECHA_JUGADOR_CUOTAS + ", s." + ColumnJugadorCuotas.DESCARTADA_JUGADOR_CUOTAS
            + ", s." + ColumnJugadorCuotas.MOTIVO_DESCARTE_JUGADOR_CUOTAS + ", s." + ColumnJugadorCuotas.ID_TRANSACCION_JUGADOR_CUOTAS + " as idTransaccion, h." + ColumnTemporadaCuotas.ID_TEMPORADA_TEMPORADA_CUOTAS + " as idTemporada from " + CUOTAS_TABLE_NAME
            + " c join " + TEMPORADAS_CUOTAS_TABLE_NAME + " h on c." + ColumnCuotas.ID_CUOTAS + " = h." + ColumnTemporadaCuotas.ID_CUOTAS_TEMPORADA_CUOTAS + " left join "
            + "(select " + CUOTAS_TABLE_NAME + "." + ColumnCuotas.ID_CUOTAS + " as idCuota, "
            + CUOTAS_TABLE_NAME + "." + ColumnCuotas.CODIGO_CUOTAS + ", " + CUOTAS_TABLE_NAME + "." + ColumnCuotas.NOMBRE_CUOTAS + ", "
            + CUOTAS_TABLE_NAME + "." + ColumnCuotas.DESCRIPCION_CUOTAS + ", " + CUOTAS_TABLE_NAME + "." + ColumnCuotas.ORDEN_CUOTAS + ", "
            + CUOTAS_TABLE_NAME + "." + ColumnCuotas.CUANTIA_CUOTAS + ", "
            + JUGADOR_CUOTAS_TABLE_NAME + "." + ColumnJugadorCuotas.PAGADA_JUGADOR_CUOTAS + ", " + JUGADOR_CUOTAS_TABLE_NAME + "." + ColumnJugadorCuotas.FECHA_JUGADOR_CUOTAS + ", "
            + JUGADOR_CUOTAS_TABLE_NAME + "." + ColumnJugadorCuotas.DESCARTADA_JUGADOR_CUOTAS + ", " + JUGADOR_CUOTAS_TABLE_NAME + "." + ColumnJugadorCuotas.MOTIVO_DESCARTE_JUGADOR_CUOTAS + ", "
            + JUGADOR_CUOTAS_TABLE_NAME + "." + ColumnJugadorCuotas.ID_TRANSACCION_JUGADOR_CUOTAS + " from "
            + CUOTAS_TABLE_NAME + " join " + JUGADOR_CUOTAS_TABLE_NAME + " on " + CUOTAS_TABLE_NAME + "." + ColumnCuotas.ID_CUOTAS + " = " + JUGADOR_CUOTAS_TABLE_NAME + "." + ColumnJugadorCuotas.ID_CUOTA_JUGADOR_CUOTAS
            + " where (" + JUGADOR_CUOTAS_TABLE_NAME + "." + ColumnJugadorCuotas.ID_JUGADOR_JUGADOR_CUOTAS + " = ? or " + JUGADOR_CUOTAS_TABLE_NAME + "." + ColumnJugadorCuotas.ID_JUGADOR_JUGADOR_CUOTAS + " is null) and "
            + JUGADOR_CUOTAS_TABLE_NAME + "." + ColumnJugadorCuotas.ID_TEMPORADA_JUGADOR_CUOTAS + " = ?) s on c." + ColumnCuotas.ID_CUOTAS + " = s.idCuota where c."
            + ColumnCuotas.ID_CUOTAS + " in (select " + TEMPORADAS_CUOTAS_TABLE_NAME + "." + ColumnTemporadaCuotas.ID_CUOTAS_TEMPORADA_CUOTAS + " from " + TEMPORADAS_CUOTAS_TABLE_NAME + " where "
            + TEMPORADAS_CUOTAS_TABLE_NAME + "." + ColumnTemporadaCuotas.ID_TEMPORADA_TEMPORADA_CUOTAS + " = ?) and h. " + ColumnTemporadaCuotas.ID_TEMPORADA_TEMPORADA_CUOTAS + " = ? order by "
            + "c." + ColumnCuotas.ORDEN_CUOTAS;

    /**
     * Obtiene el estado de las cuotas de un jugador para una temporada
     * @param idJugador ID del jugador
     * @param idTemporada ID de la temporada
     * @return lista con los datos de las distintas cuotas de un jugador en una temporada
     */
    public ArrayList<Cuota> getCuotasJugadorTemporada(int idJugador, int idTemporada) {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        ArrayList<Cuota> cuotas = new ArrayList<>();
        Cursor mCursor = database.rawQuery(SELECT_JUGADORES_CUOTAS_TEMPORADA, new String[]{String.valueOf(idJugador), String.valueOf(idJugador), String.valueOf(idTemporada), String.valueOf(idTemporada), String.valueOf(idTemporada)});
        for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
            Cuota cuotaJugador = Cuota.cursorToCuotaJugadorTemporada(mCursor);
            if (cuotaJugador != null)
                cuotas.add(cuotaJugador);
        }

        mCursor.close();
        return cuotas;
    }

    /**
     * Crea los datos de una cuota con relación a un jugador en la base de datos
     * @param idCuota ID de la cuota (plantilla)
     * @param idJugador ID del jugador
     * @param idTemporada ID de la temporada
     * @param fecha fecha límite de la cuota o fecha de pago por parte del jugador
     * @param pagada 0: no pagada, 1:pagada
     * @param descartada 0: no decartada, 1: descartada
     * @param motivoDescarte motivo por el que se descarta una cuota
     * @param idTransaccion ID de la transacción asociada al pago de una cuota
     * @return
     */
    public int crearJugadorCuota(int idCuota, int idJugador, int idTemporada, String fecha, int pagada, int descartada, String motivoDescarte, int idTransaccion) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ColumnJugadorCuotas.ID_CUOTA_JUGADOR_CUOTAS, idCuota);
        contentValues.put(ColumnJugadorCuotas.ID_JUGADOR_JUGADOR_CUOTAS, idJugador);
        contentValues.put(ColumnJugadorCuotas.ID_TEMPORADA_JUGADOR_CUOTAS, idTemporada);
        contentValues.put(ColumnJugadorCuotas.FECHA_JUGADOR_CUOTAS, fecha);
        contentValues.put(ColumnJugadorCuotas.PAGADA_JUGADOR_CUOTAS, pagada);
        contentValues.put(ColumnJugadorCuotas.DESCARTADA_JUGADOR_CUOTAS, descartada);
        contentValues.put(ColumnJugadorCuotas.MOTIVO_DESCARTE_JUGADOR_CUOTAS, motivoDescarte);
        contentValues.put(ColumnJugadorCuotas.ID_TRANSACCION_JUGADOR_CUOTAS, idTransaccion);

        int resultado = (int) database.insert(JUGADOR_CUOTAS_TABLE_NAME, null, contentValues);

        return resultado;
    }

    /**
     * Establece una cuota como pagada
     * @param cuota cuota a pagar
     * @return cuota pagada
     */
    public Cuota pagarCuota(Cuota cuota) {

        String fechaRealizada = Utilidades.fechaActualParaBD();
        int idCuota = cuota.getId();
        int idTransaccion = cuota.getIdTransaccion();
        int idJugador = cuota.getIdJugador();
        int idTemporada = cuota.getIdTemporada();
        final int TRANSACCION_REALIZADA = 1;
        final int TRANSACCION_PAGO = 1;
        final int CUOTA_NO_DESCARTADA = 0;
        final int CUOTA_PAGADA = 1;
        final String SIN_MOTIVO_DESCARTE = "";
        if (cuota.getIdTransaccion() > 0) {
            fechaRealizada = realizarTransaccion(idTransaccion);
        } else {

            Cursor datosJugador = database.query(JUGADORES_TABLE_NAME, new String[]{ColumnJugadores.NOMBRE_JUGADORES, ColumnJugadores.APODO_JUGADORES, ColumnJugadores.APELLIDO1_JUGADORES},
                    ColumnJugadores.ID_JUGADORES + " = ?", new String[]{String.valueOf(idJugador)}, null, null, null);
            if (datosJugador != null && datosJugador.getCount() > 0) {
                datosJugador.moveToFirst();
                String apodo = datosJugador.getString(datosJugador.getColumnIndex(ColumnJugadores.APODO_JUGADORES));
                String nombre = (apodo != null && !apodo.trim().equalsIgnoreCase("")) ? apodo : datosJugador.getString(datosJugador.getColumnIndex(ColumnJugadores.NOMBRE_JUGADORES));
                String apellido = datosJugador.getString(datosJugador.getColumnIndex(ColumnJugadores.APELLIDO1_JUGADORES));
                idTransaccion = crearTransaccion("C-" + cuota.getCodigo(), "Cuota " + String.valueOf(cuota.getOrden()) + " de " + nombre + " " + apellido,
                        "Pago de la cuota número " + String.valueOf(cuota.getOrden()) + " de " + nombre + " " + apellido, TRANSACCION_PAGO, Utilidades.pasarDateBD(cuota.getFechaLimite()), fechaRealizada, cuota.getCuantia(), TRANSACCION_REALIZADA);
            }
            datosJugador.close();
        }
        ContentValues contentCuotaJugador = new ContentValues();
        contentCuotaJugador.put(ColumnJugadorCuotas.PAGADA_JUGADOR_CUOTAS, CUOTA_PAGADA);
        contentCuotaJugador.put(ColumnJugadorCuotas.DESCARTADA_JUGADOR_CUOTAS, CUOTA_NO_DESCARTADA);
        contentCuotaJugador.put(ColumnJugadorCuotas.FECHA_JUGADOR_CUOTAS, fechaRealizada);
        contentCuotaJugador.put(ColumnJugadorCuotas.MOTIVO_DESCARTE_JUGADOR_CUOTAS, SIN_MOTIVO_DESCARTE);
        contentCuotaJugador.put(ColumnJugadorCuotas.ID_TRANSACCION_JUGADOR_CUOTAS, idTransaccion);
        int actualizada = database.update(JUGADOR_CUOTAS_TABLE_NAME, contentCuotaJugador, ColumnJugadorCuotas.ID_CUOTA_JUGADOR_CUOTAS + " = ? and "
                        + ColumnJugadorCuotas.ID_JUGADOR_JUGADOR_CUOTAS + " = ? and " + ColumnJugadorCuotas.ID_TEMPORADA_JUGADOR_CUOTAS + " = ? ",
                new String[]{String.valueOf(idCuota), String.valueOf(idJugador), String.valueOf(idTemporada)});
        if (actualizada == 0) {
            crearJugadorCuota(idCuota, idJugador, idTemporada, fechaRealizada, CUOTA_PAGADA, CUOTA_NO_DESCARTADA, SIN_MOTIVO_DESCARTE, idTransaccion);
        }
        cuota.setIdTransaccion(idTransaccion);
        cuota.setFechaRealizada(Utilidades.pasarFechaAplicacion(fechaRealizada));
        cuota.setDescartada(CUOTA_NO_DESCARTADA);
        cuota.setMotivoDescarte(SIN_MOTIVO_DESCARTE);
        cuota.setPagada(CUOTA_PAGADA);


        return cuota;
    }

    /**
     * Anula una cuota pagada
     * @param cuota cuota a anular
     * @return cuota anulada
     */
    public Cuota anularCuota(Cuota cuota) {


        int idCuota = cuota.getId();
        int idJugador = cuota.getIdJugador();
        int idTemporada = cuota.getIdTemporada();
        int idTransaccion = cuota.getIdTransaccion();
        final int CUOTA_NO_PAGADA = 0;
        final String SIN_FECHA_DE_PAGO = "";
        final int TRANSACCION_NO_REALIZADA = 0;


        ContentValues contentCuota = new ContentValues();
        contentCuota.put(ColumnJugadorCuotas.PAGADA_JUGADOR_CUOTAS, CUOTA_NO_PAGADA);
        contentCuota.put(ColumnJugadorCuotas.FECHA_JUGADOR_CUOTAS, SIN_FECHA_DE_PAGO);
        database.update(JUGADOR_CUOTAS_TABLE_NAME, contentCuota, ColumnJugadorCuotas.ID_CUOTA_JUGADOR_CUOTAS + " = ? and "
                        + ColumnJugadorCuotas.ID_JUGADOR_JUGADOR_CUOTAS + " = ? and " + ColumnJugadorCuotas.ID_TEMPORADA_JUGADOR_CUOTAS + " = ? ",
                new String[]{String.valueOf(idCuota), String.valueOf(idJugador), String.valueOf(idTemporada)});


        ContentValues contentTransaccion = new ContentValues();
        contentTransaccion.put(ColumnTranscacciones.REALIZADA_TRANSACCIONES, TRANSACCION_NO_REALIZADA);
        contentTransaccion.put(ColumnTranscacciones.FECHA_REALIZADA_TRANSACCIONES, "");
        database.update(TRANSACCIONES_TABLE_NAME, contentTransaccion, ColumnTranscacciones.ID_TRANSACCIONES + " = ?", new String[]{String.valueOf(idTransaccion)});

        cuota.setPagada(CUOTA_NO_PAGADA);
        cuota.setFechaRealizada(null);

        return cuota;
    }

    /**
     * Descarta una cuota
     * @param cuota cuota a descartar
     * @return cuota descartada
     */
    public Cuota descartarCuota(Cuota cuota) {


        String fechaDescarte = Utilidades.fechaActualParaBD();
        int idCuota = cuota.getId();
        int idJugador = cuota.getIdJugador();
        int idTemporada = cuota.getIdTemporada();
        final int CUOTA_NO_PAGADA = 0;
        final int CUOTA_DESCARTADA = 1;

        ContentValues contentCuotaJugador = new ContentValues();
        contentCuotaJugador.put(ColumnJugadorCuotas.PAGADA_JUGADOR_CUOTAS, CUOTA_NO_PAGADA);
        contentCuotaJugador.put(ColumnJugadorCuotas.DESCARTADA_JUGADOR_CUOTAS, CUOTA_DESCARTADA);
        contentCuotaJugador.put(ColumnJugadorCuotas.FECHA_JUGADOR_CUOTAS, fechaDescarte);
        contentCuotaJugador.put(ColumnJugadorCuotas.MOTIVO_DESCARTE_JUGADOR_CUOTAS, cuota.getMotivoDescarte());
        contentCuotaJugador.put(ColumnJugadorCuotas.ID_TRANSACCION_JUGADOR_CUOTAS, 0);
        int actualizada = database.update(JUGADOR_CUOTAS_TABLE_NAME, contentCuotaJugador, ColumnJugadorCuotas.ID_CUOTA_JUGADOR_CUOTAS + " = ? and "
                        + ColumnJugadorCuotas.ID_JUGADOR_JUGADOR_CUOTAS + " = ? and " + ColumnJugadorCuotas.ID_TEMPORADA_JUGADOR_CUOTAS + " = ? ",
                new String[]{String.valueOf(idCuota), String.valueOf(idJugador), String.valueOf(idTemporada)});
        if (actualizada == 0) {
            crearJugadorCuota(idCuota, idJugador, idTemporada, fechaDescarte, CUOTA_NO_PAGADA, CUOTA_DESCARTADA, cuota.getMotivoDescarte(), 0);
        }

        int idTransaccion = cuota.getIdTransaccion();
        if (idTransaccion > 0) {
            database.delete(TRANSACCIONES_TABLE_NAME, ColumnTranscacciones.ID_TRANSACCIONES + " = ?", new String[]{String.valueOf(idTransaccion)});
        }

        cuota.setPagada(CUOTA_NO_PAGADA);
        cuota.setDescartada(CUOTA_DESCARTADA);
        cuota.setFechaRealizada(Utilidades.pasarFechaAplicacion(fechaDescarte));
        cuota.setIdTransaccion(0);

        return cuota;
    }

    /**
     * Anula el descarte de una cuota
     * @param cuota cuota para anular el descarte
     * @return cuota con el descarte anulado
     */
    public Cuota anularDescarteCuota(Cuota cuota) {

        int idCuota = cuota.getId();
        int idJugador = cuota.getIdJugador();
        int idTemporada = cuota.getIdTemporada();
        final int CUOTA_NO_DESCARTADA = 0;
        final String SIN_FECHA_DE_DESCARTE = "";

        ContentValues contentValues = new ContentValues();
        contentValues.put(ColumnJugadorCuotas.DESCARTADA_JUGADOR_CUOTAS, CUOTA_NO_DESCARTADA);
        contentValues.put(ColumnJugadorCuotas.FECHA_JUGADOR_CUOTAS, SIN_FECHA_DE_DESCARTE);
        database.update(JUGADOR_CUOTAS_TABLE_NAME, contentValues, ColumnJugadorCuotas.ID_CUOTA_JUGADOR_CUOTAS + " = ? and "
                        + ColumnJugadorCuotas.ID_JUGADOR_JUGADOR_CUOTAS + " = ? and " + ColumnJugadorCuotas.ID_TEMPORADA_JUGADOR_CUOTAS + " = ? ",
                new String[]{String.valueOf(idCuota), String.valueOf(idJugador), String.valueOf(idTemporada)});

        cuota.setDescartada(CUOTA_NO_DESCARTADA);
        cuota.setFechaRealizada(null);

        return cuota;
    }

    /**
     * Consulta para obtener la lista de jugadores en activo a usar para crear un partido
     */
    public final String SELECT_JUGADORES_CREAR_PARTIDO = "select " + ColumnJugadores.ID_JUGADORES + ", " + ColumnJugadores.NOMBRE_JUGADORES + ", "
            + ColumnJugadores.APODO_JUGADORES + ", " + ColumnJugadores.APELLIDO1_JUGADORES + ", " + ColumnJugadores.FOTO_JUGADORES + ", " + ColumnJugadores.CALIDAD_JUGADORES
            + " from " + JUGADORES_TABLE_NAME + " where " + ColumnJugadores.ACTIVO_JUGADORES + " = 1 order by " + ColumnJugadores.APELLIDO1_JUGADORES;

    /**
     * Obtiene la lista de jugadores para crear un partido
     * @return lista de jugadores
     */
    public ArrayList<Jugador> getJugadoresCrearPartido() {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador jugador = null;
        Cursor mCursor = database.rawQuery(SELECT_JUGADORES_CREAR_PARTIDO, new String[]{});
        for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
            jugador = Jugador.cursorToJugadorCrearPartido(mCursor);
            jugadores.add(jugador);
        }

        mCursor.close();
        return jugadores;
    }

    /**
     * Consulta para obtener el número de jornada de un partido con respecto a una temporada
     */
    public final String SELECT_NUMERO_JORNADA_PARTIDO = "select max(" + PARTIDOS_TABLE_NAME + "." + ColumnPartidos.JORNADA_PARTIDOS
            + ") jornada from " + PARTIDOS_TABLE_NAME + " where " + PARTIDOS_TABLE_NAME + "." + ColumnPartidos.ID_TEMPORADA_PARTIDOS
            + " = ?";

    /**
     * Crea los equipos para un partido
     * @param idsEquipo1 IDs de jugadores para el equipo 1
     * @param idsEquipo2 IDs de jugadores para el equipo 2
     * @param idTemporada ID de la temporada donde se encuadra el partido
     * @return true
     */
    public boolean crearEquipos(Vector<Integer> idsEquipo1, Vector<Integer> idsEquipo2, int idTemporada) {

        Cursor cursor = database.rawQuery(SELECT_NUMERO_JORNADA_PARTIDO, new String[]{String.valueOf(idTemporada)});
        cursor.moveToFirst();
        int jornada = cursor.getInt(cursor.getColumnIndex("jornada")) + 1;

        ContentValues contentValues = new ContentValues();
        contentValues.put(ColumnPartidos.JORNADA_PARTIDOS, jornada);
        contentValues.put(ColumnPartidos.DISPUTADO_PARTIDOS, 0);
        contentValues.put(ColumnPartidos.ID_TEMPORADA_PARTIDOS, idTemporada);
        contentValues.put(ColumnPartidos.FECHA_PARTIDOS, Utilidades.fechaActualParaBD());

        int idPartido = (int) database.insert(PARTIDOS_TABLE_NAME, null, contentValues);

        ContentValues partidoJugador;

        for (int i = 0; i < idsEquipo1.size(); i++) {
            partidoJugador = new ContentValues();
            partidoJugador.put(ColumnJugadorPartidos.ID_JUGADOR_JUGADOR_PARTIDOS, idsEquipo1.get(i));
            partidoJugador.put(ColumnJugadorPartidos.ID_PARTIDO_JUGADOR_PARTIDOS, idPartido);
            partidoJugador.put(ColumnJugadorPartidos.EQUIPO_JUGADOR_PARTIDOS, "A");
            database.insert(JUGADOR_PARTIDOS_TABLE_NAME, null, partidoJugador);

        }

        for (int i = 0; i < idsEquipo2.size(); i++) {
            partidoJugador = new ContentValues();
            partidoJugador.put(ColumnJugadorPartidos.ID_JUGADOR_JUGADOR_PARTIDOS, idsEquipo2.get(i));
            partidoJugador.put(ColumnJugadorPartidos.ID_PARTIDO_JUGADOR_PARTIDOS, idPartido);
            partidoJugador.put(ColumnJugadorPartidos.EQUIPO_JUGADOR_PARTIDOS, "B");
            database.insert(JUGADOR_PARTIDOS_TABLE_NAME, null, partidoJugador);
        }

        cursor.close();
        return true;

    }

    /**
     * Consulta para seleccionar todos los datos de todas las temporadas
     */
    public final String SELECT_TEMPORADAS = "select * from " + TEMPORADAS_TABLE_NAME + " order by " + ColumnTemporadas.ANNO_INICIO_TEMPORADAS + " desc";

    public ArrayList<Temporada> getTemporadas() {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        ArrayList<Temporada> temporadas = new ArrayList<>();
        Cursor mCursor = database.rawQuery(SELECT_TEMPORADAS, new String[]{});
        Temporada temporada;
        for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
            temporada = Temporada.cursorToTemporada(mCursor);
            temporadas.add(temporada);
        }

        mCursor.close();
        return temporadas;
    }

    /**
     * Obtiene un array con los IDs de todas las temporadas
     * @return
     */
    public Temporada[] getArrayTemporadas() {
        Temporada[] temporadas;
        ArrayList<Temporada> temporadasArrayList = getTemporadas();
        temporadas = new Temporada[temporadasArrayList.size()];
        for (int i = 0; i < temporadas.length; i++) {
            temporadas[i] = temporadasArrayList.get(i);
        }
        return temporadas;
    }

    /**
     * Selecciona todas las plantillas de cuotas con la relación que tienen que una temporada
     */
    public final String SELECT_CUOTAS_TEMPORADA = "select " + CUOTAS_TABLE_NAME + "." + ColumnCuotas.ID_CUOTAS + ", "
            + CUOTAS_TABLE_NAME + "." + ColumnCuotas.CODIGO_CUOTAS + ", " + CUOTAS_TABLE_NAME + "." + ColumnCuotas.NOMBRE_CUOTAS + ", "
            + CUOTAS_TABLE_NAME + "." + ColumnCuotas.DESCRIPCION_CUOTAS + ", " + CUOTAS_TABLE_NAME + "." + ColumnCuotas.ORDEN_CUOTAS + ", "
            + CUOTAS_TABLE_NAME + "." + ColumnCuotas.FECHA_LIMITE_CUOTAS + ", " + CUOTAS_TABLE_NAME + "." + ColumnCuotas.CUANTIA_CUOTAS + ", "
            + "c." + ColumnTemporadaCuotas.ID_TEMPORADA_TEMPORADA_CUOTAS
            + " from " + CUOTAS_TABLE_NAME + " left join (select * from " + CUOTAS_TABLE_NAME + " left join " + TEMPORADAS_CUOTAS_TABLE_NAME
            + " on " + CUOTAS_TABLE_NAME + "." + ColumnCuotas.ID_CUOTAS + " = " + TEMPORADAS_CUOTAS_TABLE_NAME + "." + ColumnTemporadaCuotas.ID_CUOTAS_TEMPORADA_CUOTAS
            + " where " + TEMPORADAS_CUOTAS_TABLE_NAME + "." + ColumnTemporadaCuotas.ID_TEMPORADA_TEMPORADA_CUOTAS + " = ? or "
            + TEMPORADAS_CUOTAS_TABLE_NAME + "." + ColumnTemporadaCuotas.ID_TEMPORADA_TEMPORADA_CUOTAS + " is null "
            + " order by " + CUOTAS_TABLE_NAME + "." + ColumnCuotas.ORDEN_CUOTAS + ") c on " + CUOTAS_TABLE_NAME + "." + ColumnCuotas.ID_CUOTAS + " = c." + ColumnCuotas.ID_CUOTAS;

    /**
     * Obtiene las plantillas de las cuotas con los datos en función de una temporda
     * @param idTemporada ID de la temporada
     * @param anno_inicio Año de inicio de la temporada
     * @return lista de cuotas
     */
    public ArrayList<Cuota> getCuotasTempora(int idTemporada, int anno_inicio) {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        ArrayList<Cuota> cuotas = new ArrayList<>();
        Cursor mCursor = database.rawQuery(SELECT_CUOTAS_TEMPORADA, new String[]{String.valueOf(idTemporada)});
        Cuota cuota;
        for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
            cuota = Cuota.cursorToTemporada(mCursor, anno_inicio);
            cuotas.add(cuota);
        }

        mCursor.close();
        return cuotas;
    }

    /**
     * Añade una cuota a la base de datos
     * @param cuota cuota a ñadir
     * @return ID de la cuota añadida
     */
    public int addCuotaTemporada(Cuota cuota) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ColumnTemporadaCuotas.ID_TEMPORADA_TEMPORADA_CUOTAS, cuota.getIdTemporada());
        contentValues.put(ColumnTemporadaCuotas.ID_CUOTAS_TEMPORADA_CUOTAS, cuota.getId());
        contentValues.put(ColumnTemporadaCuotas.FECHA_LIMITE_CUOTAS, Utilidades.pasarDateBD(cuota.getFechaLimite()));

        int registro = (int) database.insert(TEMPORADAS_CUOTAS_TABLE_NAME, null, contentValues);

        return registro;
    }

    /**
     * Obtiene si una cuota ha sido tramitada en una temporada
     */
    public final String SELECT_CUOTA_PAGADA_TEMPORADA = "select " + JUGADOR_CUOTAS_TABLE_NAME + "." + ColumnJugadorCuotas.ID_CUOTA_JUGADOR_CUOTAS + ", "
            + JUGADOR_CUOTAS_TABLE_NAME + "." + ColumnJugadorCuotas.ID_TEMPORADA_JUGADOR_CUOTAS + " from " + JUGADOR_CUOTAS_TABLE_NAME
            + " where " + JUGADOR_CUOTAS_TABLE_NAME + "." + ColumnJugadorCuotas.ID_CUOTA_JUGADOR_CUOTAS + " = ? and " + JUGADOR_CUOTAS_TABLE_NAME + "." + ColumnJugadorCuotas.ID_TEMPORADA_JUGADOR_CUOTAS + " = ?";

    /**
     * Borra una cuota de una temporda si no se ha hecho ningún trámite con ella
     * @param idCuota ID de la cuota
     * @param idTemporada ID de la temporada
     * @return true si se ha borrado, false en caso contrario
     */
    public boolean deleteCuotaTemporada(int idCuota, int idTemporada) {

        Cursor mCursor = database.rawQuery(SELECT_CUOTA_PAGADA_TEMPORADA, new String[]{String.valueOf(idCuota), String.valueOf(idTemporada)});
        if (mCursor.getCount() == 0) {
            database.delete(TEMPORADAS_CUOTAS_TABLE_NAME, ColumnTemporadaCuotas.ID_TEMPORADA_TEMPORADA_CUOTAS + " = " + idTemporada
                    + " and " + ColumnTemporadaCuotas.ID_CUOTAS_TEMPORADA_CUOTAS + " = " + idCuota, null);

            mCursor.close();
            return true;
        }

        mCursor.close();
        return false;
    }

    /**
     * Crea una temporada en la base de datos
     * @param inicio año de inicio de la temporada
     * @param fin año de fin de la temporada
     * @return ID de la temporada creada
     */
    public int crearTemporada(int inicio, int fin) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ColumnTemporadas.ANNO_INICIO_TEMPORADAS, inicio);
        contentValues.put(ColumnTemporadas.ANNO_FIN_TEMPORADAS, fin);

        int resultado = (int) database.insert(TEMPORADAS_TABLE_NAME, null, contentValues);

        return resultado;
    }

    /**
     * Edita los datos de una temporada
     * @param idTemporada ID de la temporada a editar
     * @param inicio fecha de inicio
     * @param fin fecha de fin
     * @return número de registros actualizados
     */
    public int editarTemporada(int idTemporada, int inicio, int fin) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ColumnTemporadas.ANNO_INICIO_TEMPORADAS, inicio);
        contentValues.put(ColumnTemporadas.ANNO_FIN_TEMPORADAS, fin);

        int reusutado = (int) database.update(TEMPORADAS_TABLE_NAME, contentValues, ColumnTemporadas.ID_TEMPORADAS + " = ?", new String[]{String.valueOf(idTemporada)});

        return reusutado;
    }

}
