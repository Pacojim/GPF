package com.gpf.rebisoft.gpf.clasesbase;

import android.database.Cursor;

import com.gpf.rebisoft.gpf.database.GPFDataSource;
import com.gpf.rebisoft.gpf.utils.Utilidades;

import java.util.Calendar;
import java.util.Date;

/**
 * Clase para representar las cuotas de una peña. Se usa tanto para representar la plantilla de una cuota que
 * se puede añpadir a una temporada, como una cuota pagada por un jugador
 */
public class Cuota {
    int id;
    String codigo;
    String nombre;
    String descripcion;
    int orden;
    float cuantia;
    Date fechaLimite;
    Date fechaRealizada;
    int pagada;
    int descartada;
    String motivoDescarte;
    int idTransaccion;
    int idJugador;
    int idTemporada;

    /**
     * Constructor de la clase
     * @param id ID de la cuota
     * @param codigo Código de la cuota
     * @param nombre nombre de la cuota
     * @param descripcion descripción de la cuota
     * @param orden orden en que se ha de pagar la cuota
     * @param fechaLimite fecha límite para realizar el pago de la cuota
     * @param cuantia cuantía a abonar para satisfacer la cuota
     * @param idTemporada ID de la temporada a la que pertenece el pago de la cuota
     */
    public Cuota(int id, String codigo, String nombre, String descripcion, int orden, Date fechaLimite, float cuantia, int idTemporada) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.orden = orden;
        this.fechaLimite = fechaLimite;
        this.cuantia = cuantia;
        this.idTemporada = idTemporada;
    }

    /**
     *
     * @param id ID de la cuota
     * @param codigo Código de la cuota
     * @param nombre nombre de la cuota
     * @param descripcion descripción de la cuota
     * @param orden orden en que se ha de pagar la cuota
     * @param cuantia cuantía a abonar para satisfacer la cuota
     * @param fechaLimite fecha límite para realizar el pago de la cuota
     * @param pagada 1 si la cuota está pagada y 0 si no
     * @param descartada 1 si la cuota ha sido descartada para un jugador, 0 si no
     * @param motivoDescarte motivo por el cual se le ha descartado la cuota a un jugador
     * @param idTransaccion ID de la transacción que ha generado el pago de la cuota
     * @param idJugador ID del jugador que ha pagado la cuota
     * @param idTemporada ID de la temporada a la que pertenece el pago de la cuota
     */
    public Cuota(int id, String codigo, String nombre, String descripcion, int orden, float cuantia, Date fechaLimite, int pagada, int descartada, String motivoDescarte, int idTransaccion, int idJugador, int idTemporada) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.orden = orden;
        this.cuantia = cuantia;
        this.fechaLimite = fechaLimite;
        this.pagada = pagada;
        this.descartada = descartada;
        this.motivoDescarte = motivoDescarte;
        this.idTransaccion = idTransaccion;
        this.idJugador = idJugador;
        this.idTemporada = idTemporada;
    }

    //Getter y setter por defecto

    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getOrden() {
        return orden;
    }

    public float getCuantia() {
        return cuantia;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public Date getFechaRealizada() {
        return fechaRealizada;
    }

    public int getPagada() {
        return pagada;
    }

    public int getDescartada() {
        return descartada;
    }

    public String getMotivoDescarte() {
        return motivoDescarte;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setFechaRealizada(Date fechaRealizada) {
        this.fechaRealizada = fechaRealizada;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public int getIdTemporada() {
        return idTemporada;
    }

    public void setIdTemporada(int idTemporada) {
        this.idTemporada = idTemporada;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setOrden(int orden) {
        this.orden = orden;
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

    public void setPagada(int pagada) {
        this.pagada = pagada;
    }

    public void setDescartada(int descartada) {
        this.descartada = descartada;
    }

    public void setMotivoDescarte(String motivoDescarte) {
        this.motivoDescarte = motivoDescarte;
    }

    /**
     * Crea un objeto de esta clase cuota a partir de los datos contenidos en un cursor. Crea una cuota pagada o descartada por un jugador
     * @param cursor cursor del que se obtienen los datos para crear la cuota
     * @return devuelve el objeto cuota
     */
    public static Cuota cursorToCuotaJugadorTemporada(Cursor cursor) {
        Cuota cuota = null;
        if(cursor != null /*&& cursor.getInt(cursor.getColumnIndex("idTemporada")) != 0*/) {
            int pagada = cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnJugadorCuotas.PAGADA_JUGADOR_CUOTAS));
            int descartada = cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnJugadorCuotas.DESCARTADA_JUGADOR_CUOTAS));
            String fechaRealizada = cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadorCuotas.FECHA_JUGADOR_CUOTAS));
            cuota = new Cuota(cursor.getInt(cursor.getColumnIndex("idCuota")),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnCuotas.CODIGO_CUOTAS)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnCuotas.NOMBRE_CUOTAS)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnCuotas.DESCRIPCION_CUOTAS)),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnCuotas.ORDEN_CUOTAS)),
                    cursor.getFloat(cursor.getColumnIndex(GPFDataSource.ColumnCuotas.CUANTIA_CUOTAS)),
                    Utilidades.pasarFechaAplicacion(cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnCuotas.FECHA_LIMITE_CUOTAS))),
                    pagada,
                    descartada,
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnJugadorCuotas.MOTIVO_DESCARTE_JUGADOR_CUOTAS)),
                    cursor.getInt(cursor.getColumnIndex("idTransaccion")),
                    cursor.getInt(cursor.getColumnIndex("idJugador")),
                    cursor.getInt(cursor.getColumnIndex("idTemporada")));
            if((pagada == 1 || descartada == 1) && fechaRealizada != null && !fechaRealizada.trim().equalsIgnoreCase("")) {
                cuota.setFechaRealizada(Utilidades.pasarFechaAplicacion(fechaRealizada));
            }
        }

        return cuota;
    }

    /**
     * Constructor para crear una cuota tipo plantilla a partir de los datos contenidos en un cursor.
     * Le cambiará la fecha límite a la plantilla original para adaptarla a la temporada donde se pretende añadir
     * @param cursor cursor que contiene los datos para crear la cuota
     * @param anno_inicio año de inicio de temporada a la que se va a asignar la cuota
     * @return devuelve una cuota de tipo plantilla para añadirla a una temporada
     */
    public static Cuota cursorToTemporada(Cursor cursor, int anno_inicio) {
        Cuota cuota = null;
        if(cursor != null){

            Date fecha = Utilidades.pasarFechaAplicacion(cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnCuotas.FECHA_LIMITE_CUOTAS)));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            int month = calendar.get(Calendar.MONTH);
            if (month < 7) {
                anno_inicio++;
            }
            calendar.set(Calendar.YEAR, anno_inicio);
            fecha = calendar.getTime();

            cuota = new Cuota(cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnCuotas.ID_CUOTAS)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnCuotas.CODIGO_CUOTAS)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnCuotas.NOMBRE_CUOTAS)),
                    cursor.getString(cursor.getColumnIndex(GPFDataSource.ColumnCuotas.DESCRIPCION_CUOTAS)),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnCuotas.ORDEN_CUOTAS)),
                    fecha,
                    cursor.getFloat(cursor.getColumnIndex(GPFDataSource.ColumnCuotas.CUANTIA_CUOTAS)),
                    cursor.getInt(cursor.getColumnIndex(GPFDataSource.ColumnTemporadaCuotas.ID_TEMPORADA_TEMPORADA_CUOTAS)));
        }

        return cuota;
    }
}
