package com.gpf.rebisoft.gpf.clasesbase;

import android.database.Cursor;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.database.GPFDataSource;
import com.gpf.rebisoft.gpf.utils.Utilidades;

import java.util.Date;

/**
 * Clase que representa una transacción, un movimiento en la caja
 */
public class Transaccion {
    int id;
    String codigo;
    String nombre;
    String descripcion;
    float cuantia;
    Date fechaLimite;
    Date fechaRealizada;
    // 0: pago, 1: ingreso
    int tipo;
    int realizada;

    /**
     * Constructor básico de la clase
     * @param id ID de la transacción
     * @param nombre nombre de la transacción
     * @param cuantia cuantía de la transacción
     * @param fechaLimite fecha límite para realizar la transacción
     * @param tipo 0: pago, 1: ingreso
     * @param realizada
     */
    public Transaccion(int id, String nombre, float cuantia, Date fechaLimite, int tipo, int realizada) {
        this.id = id;
        this.nombre = nombre;
        this.cuantia = cuantia;
        this.fechaLimite = fechaLimite;
        this.tipo = tipo;
        this.realizada = realizada;
    }

    /**
     * Constructor
     * @param id ID de la transacción
     * @param codigo código de la transacción
     * @param nombre nombre de la transacción
     * @param descripcion descripción de la transacción
     * @param cuantia cuantía de la transacción
     * @param fechaLimite fecha límite para realizar la transacción
     * @param tipo 0: pago, 1: ingreso
     * @param realizada 0: no realizada, 1: realizada
     */
    public Transaccion(int id, String codigo, String nombre, String descripcion, float cuantia, Date fechaLimite, int tipo, int realizada) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cuantia = cuantia;
        this.fechaLimite = fechaLimite;
        this.tipo = tipo;
        this.realizada = realizada;
    }

    /**
     * Constructor
     * @param codigo código de la transacción
     * @param nombre nombre de la transacción
     * @param descripcion descripción de la transacción
     * @param cuantia cuantía de la transacción
     * @param fechaLimite fecha límite para realizar la transacción
     * @param fechaRealizada fecha en la que se realiza el pago o cobro la transacción
     * @param tipo 0: pago, 1: ingreso
     * @param realizada 0: no realizada, 1: realizada
     */
    public Transaccion(String codigo, String nombre, String descripcion, float cuantia, Date fechaLimite, Date fechaRealizada, int tipo, int realizada) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cuantia = cuantia;
        this.fechaLimite = fechaLimite;
        this.tipo = tipo;
        this.realizada = realizada;
        this.fechaRealizada = fechaRealizada;
    }

    //Getter y setter por defecto

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getCuantia() {
        return cuantia;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public int getTipo() {
        return tipo;
    }

    public int getRealizada() {
        return realizada;
    }

    public String getCodigo() {
        return codigo;
    }

    public Date getFechaRealizada() {
        return fechaRealizada;
    }

    public void setFechaRealizada(Date fechaRealizada) {
        this.fechaRealizada = fechaRealizada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCuantia(float cuantia) {
        this.cuantia = cuantia;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setRealizada(int realizada) {
        this.realizada = realizada;
    }

    /**
     * Establece el icono que acompañará a la linea de la transacción
     * en el listview donde se representan. Verde si se ha realizado, rojo si no.
     * @return color del icono de la transacción
     */
    public int getColor() {
        int color = R.drawable.semaforo_rojo;
        if (this.realizada == 1)
            color = R.drawable.semaforo_verde;
        return color;
    }

    /**
     * Devuelve el signo de la transacción dependiendo de si es un ingreso o un pago
     * @return
     */
    public char getSignoTipo() {
        char signo = '+';
        if (this.tipo == 0)
            signo = '-';
        return signo;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Crea una transacción a través de un cursor de datos con la información que aparece en la lista de transacciones
     * @param cursor cursor con los datos para crear la transacción
     * @return transacción
     */
    public static Transaccion cursorToTransaccionesList(Cursor cursor) {
        Transaccion transaccion = null;
        if (cursor != null) {
            transaccion = new Transaccion(cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnTranscacciones.ID_TRANSACCIONES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnTranscacciones.NOMBRE_TRANSACCIONES)),
                    cursor.getFloat(cursor.getColumnIndex(GPFDataSource.ColumnTranscacciones.CUANTIA_TRANSACCIONES)),
                    Utilidades.pasarFechaAplicacion(cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnTranscacciones.FECHA_LIMITE_TRANSACCIONES))),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnTranscacciones.TIPO_TRANSACCIONES)),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnTranscacciones.REALIZADA_TRANSACCIONES)));
        }
        return transaccion;
    }

    /**
     * Crea una transacción a través de un cursor de datos con la información que aparece en la vista de detalle de la transacción
     * @param cursor cursor con los datos para crear la transacción
     * @return transacción
     */
    public static Transaccion cursorToTransaccion(Cursor cursor) {
        Transaccion transaccion = null;
        if(cursor != null) {
            int realizada = cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnTranscacciones.REALIZADA_TRANSACCIONES));
            String fechaRealizada = cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnTranscacciones.FECHA_REALIZADA_TRANSACCIONES));
            transaccion = new Transaccion(cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnTranscacciones.ID_TRANSACCIONES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnTranscacciones.CODIGO_TRANSACCIONES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnTranscacciones.NOMBRE_TRANSACCIONES)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnTranscacciones.DESCRIPCION_TRANSACCIONES)),
                    cursor.getFloat(cursor.getColumnIndex(GPFDataSource.ColumnTranscacciones.CUANTIA_TRANSACCIONES)),
                    Utilidades.pasarFechaAplicacion(cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnTranscacciones.FECHA_LIMITE_TRANSACCIONES))),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnTranscacciones.TIPO_TRANSACCIONES)),
                    realizada);
            if(realizada == 1 && fechaRealizada != null && !fechaRealizada.trim().equalsIgnoreCase("")) {
                transaccion.setFechaRealizada(Utilidades.pasarFechaAplicacion(fechaRealizada));
            }
        }
        return transaccion;
    }
}
