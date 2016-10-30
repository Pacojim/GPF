package com.gpf.rebisoft.gpf.ajustes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.database.GPFDataSource;

import java.util.Calendar;

/**
 * Fragment que permite crear o editar una nueva temporada
 */
public class PlaceholderAjustesTemporadaCreate extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ID_TEMPORADA = "idTemporada";

    // TODO: Rename and change types of parameters
    private int idTemporada;
    private TextView annoInicio;
    private TextView annoFin;
    private int inicio;
    private int fin;

    /**
     * Constructor por defecto requerido
     */
    public PlaceholderAjustesTemporadaCreate() {
        // Required empty public constructor
    }

    /**
     * Método para instanciar el fragment y pasarle los argumentos
     */
    public static PlaceholderAjustesTemporadaCreate newInstance(int idTemporada) {
        PlaceholderAjustesTemporadaCreate fragment = new PlaceholderAjustesTemporadaCreate();
        Bundle args = new Bundle();
        args.putInt(ARG_ID_TEMPORADA, idTemporada);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Se llama automáticamente al crearse el fragment
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idTemporada = getArguments().getInt(ARG_ID_TEMPORADA);
        }
        setHasOptionsMenu(true);
    }

    /**
     * Se llama al crearse la vista
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_placeholder_ajustes_temporada_create, container, false);
        ImageView botonMenos = (ImageView) view.findViewById(R.id.imageViewButtonMinusTemporada);
        ImageView botonMas = (ImageView) view.findViewById(R.id.imageViewButtonAddTemporada);
        annoInicio = (TextView) view.findViewById(R.id.textViewAnnoInicioNuevaTemporada);
        annoFin = (TextView) view.findViewById(R.id.textViewAnnoFinNuevaTemporada);
        inicio = Calendar.getInstance().get(Calendar.YEAR);
        fin = inicio + 1;
        annoInicio.setText(String.valueOf(inicio));
        annoFin.setText(String.valueOf(fin));
        botonMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                annoInicio.setText(String.valueOf(++inicio));
                annoFin.setText(String.valueOf(++fin));
            }
        });
        botonMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                annoInicio.setText(String.valueOf(--inicio));
                annoFin.setText(String.valueOf(--fin));
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here

        inflater.inflate(R.menu.menu_insertar, menu);
    }

    /**
     * Establecemos el comportamiento de las opciones del toolbar
     * @param item item pulsado
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert:
                GPFDataSource gpfDataSource = new GPFDataSource(getContext());
                if (idTemporada == 0)
                    gpfDataSource.crearTemporada(inicio, fin);
                else
                    gpfDataSource.editarTemporada(idTemporada, inicio, fin);
                getFragmentManager().popBackStack();
                return true;
            case android.R.id.home:
                getFragmentManager().popBackStack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
