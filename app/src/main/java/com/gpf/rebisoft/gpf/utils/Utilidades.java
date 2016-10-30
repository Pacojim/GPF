package com.gpf.rebisoft.gpf.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.DatePicker;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Francisco on 21/07/2015.
 */
public class Utilidades {

    public static final String PATH_IMAGES_APP = "/imagePlayersGPF/";

    public static String pasarFechaBD(String fechaAplicacion){
        SimpleDateFormat formatoFechaAplicacion = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoFechaBD = new SimpleDateFormat("yyyy-MM-dd");
        String fechaBaseDatos = null;
        try {
            Date fecha = formatoFechaAplicacion.parse(fechaAplicacion);
            fechaBaseDatos = formatoFechaBD.format(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fechaBaseDatos;
    }

    public static String pasarStringBDStringApp(String fechaBaseDatos){
        SimpleDateFormat formatoFechaAplicacion = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoFechaBD = new SimpleDateFormat("yyyy-MM-dd");
        String fechaAplicacion = null;
        try {
            Date fecha = formatoFechaBD.parse(fechaBaseDatos);
            fechaAplicacion = formatoFechaAplicacion.format(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fechaAplicacion;
    }

    public static Date pasarFechaAplicacion(String fechaBD){
        SimpleDateFormat formatoFechaAplicacion = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoFechaBD = new SimpleDateFormat("yyyy-MM-dd");
//        String fechaAplicacion = null;
        Date fecha = null;
        try {
//            Date fecha = formatoFechaBD.parse(fechaBD);
            fecha = formatoFechaBD.parse(fechaBD);
//            fechaAplicacion = formatoFechaAplicacion.format(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        return fechaAplicacion;
        return fecha;
    }

    public static String pasarDateString(Date fecha) {
        String fechaAplicacion = "";
        if(fecha != null) {
            SimpleDateFormat formatoFechaAplicacion = new SimpleDateFormat("dd/MM/yyyy");
            fechaAplicacion = formatoFechaAplicacion.format(fecha);
        }

        return fechaAplicacion;

    }

    public static String fechaActualParaBD(){
        SimpleDateFormat formatoFechaBD = new SimpleDateFormat("yyyy-MM-dd");
        return formatoFechaBD.format(new Date());
    }

    public static String pasarDateBD(Date date) {
        return date != null ? pasarFechaBD(pasarDateString(date)) : "";
    }

    public static double round2Decimal(double number){
        return Math.round(number * 100.0) / 100.0;
    }

    public static Bitmap getBitmapFromPath(String path) {
        Bitmap myBitmap = null;
        File imgFile = new File(path);
        if(imgFile.exists()){
            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
        return myBitmap;
    }

    public static Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
