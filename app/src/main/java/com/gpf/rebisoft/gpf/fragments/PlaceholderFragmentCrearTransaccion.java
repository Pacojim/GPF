package com.gpf.rebisoft.gpf.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.clasesbase.Transaccion;
import com.gpf.rebisoft.gpf.database.GPFDataSource;
import com.gpf.rebisoft.gpf.utils.Utilidades;

import java.util.Calendar;
import java.util.Date;

/**

 */
public class PlaceholderFragmentCrearTransaccion extends Fragment {

    private static final String ARG_PARAM_ID_TRANSACCION = "idTransaccion";
    private int idTransaccion;
    private Context context;
    private GPFDataSource dataSource;
    private EditText codigo;
    private EditText nombre;
    private EditText descripcion;
    private Spinner spinnerTipo;
    private DatePicker datePickerFecha;
    private EditText editTextCuantia;
    private CheckBox checkBoxRealizada;
    private Transaccion transaccion;

    public PlaceholderFragmentCrearTransaccion() {
        // Required empty public constructor
    }

    /**
     * @return A new instance of fragment PlaceholderFragmentCrearTransaccion.
     */
    // TODO: Rename and change types and number of parameters
    public static PlaceholderFragmentCrearTransaccion newInstance(int idTransaccion) {
        PlaceholderFragmentCrearTransaccion fragment = new PlaceholderFragmentCrearTransaccion();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_ID_TRANSACCION, idTransaccion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idTransaccion = getArguments().getInt(ARG_PARAM_ID_TRANSACCION);
        }
        context = getContext();
        dataSource = new GPFDataSource(context);
//        fragmentManager = getActivity().getSupportFragmentManager();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.section_fragment_crear_transaccion, container, false);
        codigo = (EditText) view.findViewById(R.id.editTextCodigoNuevaTransaccion);
        nombre = (EditText) view.findViewById(R.id.editTextNombreNuevaTransaccion);
        descripcion = (EditText) view.findViewById(R.id.editTextDescripcionNuevaTransaccion);
        spinnerTipo = (Spinner) view.findViewById(R.id.spinnerTipoNuevaTranscaccion);
        datePickerFecha = (DatePicker) view.findViewById(R.id.datePickerFechaLimiteNuevaTransaccion);
        editTextCuantia = (EditText) view.findViewById(R.id.editTextCuantiaNuevaTransaccion);
        checkBoxRealizada = (CheckBox) view.findViewById(R.id.checkBoxRealizadaNuevaTransaccion);
        if(idTransaccion != 0) {
            transaccion = dataSource.getTransaccion(idTransaccion);
            codigo.setText(transaccion.getCodigo());
            nombre.setText(transaccion.getNombre());
            descripcion.setText(transaccion.getDescripcion());
            editTextCuantia.setText(String.valueOf(Math.abs(transaccion.getCuantia())));
            spinnerTipo.setSelection(transaccion.getTipo());
            checkBoxRealizada.setChecked(transaccion.getRealizada() == 1);
            Date fecha;
            if(transaccion.getRealizada() == 1)
                fecha = transaccion.getFechaRealizada();
            else
                fecha = transaccion.getFechaLimite();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            datePickerFecha.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.menu_insertar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert:
                Transaccion transaccionAux = createTransaccion();
                if (transaccionAux != null && idTransaccion == 0) {
                    dataSource.crearTransaccion(transaccionAux);
                    getActivity().finish();
                } else if(transaccionAux != null && idTransaccion != 0) {
                    transaccion.setId(idTransaccion);
                    dataSource.updateTransaccion(transaccionAux);
                    getActivity().finish();
                }
                return true;
//            case R.id.action_eliminar:
//                if(idTransaccion != 0)
//                    dataSource.deleteTransaccion(idTransaccion);
//                getActivity().finish();
//                return true;
            case android.R.id.home:
                if(idTransaccion != 0) {
                    getFragmentManager().popBackStack();
                } else {
                    getActivity().finish();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Transaccion createTransaccion() {
        Transaccion transaccionAux = null;
        int tipo = spinnerTipo.getSelectedItemPosition();
        int realizada = checkBoxRealizada.isChecked() ? 1 : 0;

        if (nombre.getText().toString().trim().equals("")) {
            nombre.setError("Nombre obligatorio");
        } else if (editTextCuantia.getText().toString().trim().equals("")) {
            editTextCuantia.setError("Cuantía obligatoria");
        } else {
            float cuantia = Float.parseFloat(editTextCuantia.getText().toString());
            cuantia = tipo == 1 ? Math.abs(cuantia) : Math.abs(cuantia) * (-1);
            if(idTransaccion == 0) {
                if (realizada == 1)
                    transaccionAux = new Transaccion(codigo.getText().toString(), nombre.getText().toString(), descripcion.getText().toString(),
                            cuantia, null, Utilidades.getDateFromDatePicker(datePickerFecha), tipo, realizada);
                else
                    transaccionAux = new Transaccion(codigo.getText().toString(), nombre.getText().toString(), descripcion.getText().toString(),
                            cuantia, Utilidades.getDateFromDatePicker(datePickerFecha), null, tipo, realizada);
            } else {
                transaccionAux = transaccion;
                transaccionAux.setCodigo(codigo.getText().toString());
                transaccionAux.setNombre(nombre.getText().toString());
                transaccionAux.setDescripcion(descripcion.getText().toString());
                transaccionAux.setCuantia(cuantia);
                transaccionAux.setTipo(tipo);
                transaccionAux.setRealizada(realizada);
                if (realizada == 1)
                    transaccionAux.setFechaRealizada(Utilidades.getDateFromDatePicker(datePickerFecha));
                else
                    transaccionAux.setFechaLimite(Utilidades.getDateFromDatePicker(datePickerFecha));
            }

        }

        return transaccionAux;
    }
}
