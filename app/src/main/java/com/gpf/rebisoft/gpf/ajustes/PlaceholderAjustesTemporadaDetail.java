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
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.adapters.TemporadaCuotasListAdapter;
import com.gpf.rebisoft.gpf.clasesbase.Cuota;
import com.gpf.rebisoft.gpf.database.GPFDataSource;

import java.util.ArrayList;

/**
 * Fragment que permite ver las cuotas que tiene asignada una temporada y asignarle otras
 */
public class PlaceholderAjustesTemporadaDetail extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ID_TEMPORADA = "param1";
    private static final String ARG_ANNO_INICIO = "param2";

    // TODO: Rename and change types of parameters
    private int idTemporada;
    private int anno_inicio;
    private GPFDataSource dataSource;
    private Context context;

    /**
     * Constructor por defecto requerido
     */
    public PlaceholderAjustesTemporadaDetail() {
        // Required empty public constructor
    }

    /**
     * Método para instanciar el fragment y pasarle los argumentos
     */
    public static PlaceholderAjustesTemporadaDetail newInstance(int idTemporada, int anno_inicio) {
        PlaceholderAjustesTemporadaDetail fragment = new PlaceholderAjustesTemporadaDetail();
        Bundle args = new Bundle();
        args.putInt(ARG_ID_TEMPORADA, idTemporada);
        args.putInt(ARG_ANNO_INICIO, anno_inicio);
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
            anno_inicio = getArguments().getInt(ARG_ANNO_INICIO);
        }
        context = getContext();
        dataSource = new GPFDataSource(context);
        getActivity().setTitle("Temporada " + anno_inicio + " / " + (anno_inicio + 1));
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_placeholder_ajustes_temporada_detail, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listViewCuotasTemporada);
        ListAdapter adapter = new TemporadaCuotasListAdapter(context, dataSource.getCuotasTempora(idTemporada, anno_inicio), idTemporada);
        listView.setAdapter(adapter);
        return view;
    }

    /**
     * Establecemos las opciones del toolbar
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here

        inflater.inflate(R.menu.menu_editar, menu);
    }

    /**
     * Establecemos el comportamiento de las opciones del toolbar
     * @param item item pulsado
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                Fragment fragment;
                FragmentManager fragmentManager;
                fragmentManager = getActivity().getSupportFragmentManager();
                fragment = PlaceholderAjustesTemporadaCreate.newInstance(idTemporada);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.layout_content_ajustes, fragment)
                        .addToBackStack(null)
                        .commit();
                return true;
            case android.R.id.home:
                getFragmentManager().popBackStack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
