package com.gpf.rebisoft.gpf.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Clase que representa al objeto que se comunica con la base de datos
 */
public class GPFBDHelper extends SQLiteOpenHelper{

    /**
     * Archivo dende se almacena la base de datos
     */
    private static final String BD_NAME = "bdgpf.db";

    /**
     * Versión de la base de datos
     */
    private static final int DATABASE_VERSION = 2;

    /**
     * Constructor por defecto
     * @param context contexto de la aplicación
     */
    public GPFBDHelper(Context context) {

        super(context, BD_NAME, null, DATABASE_VERSION);
    }

    /**
     * Método que crea la base de datos e inserta los datos por defecto para las pruebas
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Primero creamos las tablas
        db.execSQL(GPFDataSource.CREATE_TEMPORADAS_SCRIPT);
        db.execSQL(GPFDataSource.CREATE_PARTIDOS_SCRIPT);
        db.execSQL(GPFDataSource.CREATE_JUGADORES_SCRIPT);
        db.execSQL(GPFDataSource.CREATE_CUOTAS_SCRIPT);
        db.execSQL(GPFDataSource.CREATE_TRANSACCIONES_SCRIPT);
        db.execSQL(GPFDataSource.CREATE_JUGADOR_PARTIDOS_SCRIPT);
        db.execSQL(GPFDataSource.CREATE_JUGADOR_CUOTAS_SCRIPT);
        db.execSQL(GPFDataSource.CREATE_TEMPORADA_CUOTAS_SCRIPT);
//        // Insertamos los datos iniciales o de prueba en las tablas
        db.execSQL(GPFDataSource.INSERT_TEMPORADAS_SCRIPT);
        db.execSQL(GPFDataSource.INSERT_JUGADORES_SCRIPT);
        db.execSQL(GPFDataSource.INSERT_CUOTAS_SCRIPT);
        db.execSQL(GPFDataSource.INSERT_PARTIDOS_SCRIPT);
        db.execSQL(GPFDataSource.INSERT_TEMPORADA_CUOTAS_SCRIPT);
        db.execSQL(GPFDataSource.INSERT_JUGADOR_PARTIDOS_SCRIPT);
    }

    /**
     * Método que se lanza al instalar la aplicación con una versión nueva de la base de datos.
     * Durante la fase de pruebas borra la base de datos y la vuelve a crear con los valosres por defecto para las pruebas
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GPFDataSource.TEMPORADAS_CUOTAS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GPFDataSource.JUGADOR_CUOTAS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GPFDataSource.JUGADOR_PARTIDOS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GPFDataSource.TRANSACCIONES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GPFDataSource.CUOTAS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GPFDataSource.JUGADORES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GPFDataSource.PARTIDOS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GPFDataSource.TEMPORADAS_TABLE_NAME);
        onCreate(db);
    }

    /**
     * Método que se lanza al instalar la aplicación con una versión antigua de la base de datos.
     * Durante la fase de pruebas borra la base de datos y la vuelve a crear con los valosres por defecto para las pruebas
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GPFDataSource.TEMPORADAS_CUOTAS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GPFDataSource.JUGADOR_CUOTAS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GPFDataSource.JUGADOR_PARTIDOS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GPFDataSource.TRANSACCIONES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GPFDataSource.CUOTAS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GPFDataSource.JUGADORES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GPFDataSource.PARTIDOS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GPFDataSource.TEMPORADAS_TABLE_NAME);
        onCreate(db);
    }
}
