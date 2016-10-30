package com.gpf.rebisoft.gpf.ajustes;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.adapters.TemporadasListAdapter;
import com.gpf.rebisoft.gpf.clasesbase.Jugador;
import com.gpf.rebisoft.gpf.clasesbase.Temporada;
import com.gpf.rebisoft.gpf.database.GPFDataSource;
import com.gpf.rebisoft.gpf.fragments.PlaceHolderFragmentCrearPartidoPaso2;

import java.util.ArrayList;

/**
 * Fragment que muestra la lista de temporadas registradas en la aplicación y permite crear nuevas
 * o ver el detalle de alguna
 */
public class PlaceholderAjustesTemporada extends Fragment {

    private GPFDataSource dataSource;
    private Context context;
    Fragment fragment;
    FragmentManager fragmentManager;

    /**
     * Constructor por defecto requerido
     */
    public PlaceholderAjustesTemporada() {
        // Required empty public constructor
    }

    /**
     * Método para instanciar el fragment y pasarle los argumentos
     */
    // TODO: Rename and change types and number of parameters
    public static PlaceholderAjustesTemporada newInstance() {
        PlaceholderAjustesTemporada fragment = new PlaceholderAjustesTemporada();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);

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
        context = getContext();
        dataSource = new GPFDataSource(context);
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
        View view = inflater.inflate(R.layout.fragment_placeholder_ajustes_temporada_list, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listViewTemporadas);
        final ListAdapter adapter = new TemporadasListAdapter(context, dataSource.getTemporadas());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Temporada temporada = (Temporada) adapter.getItem(position);
                int idTemporada = temporada.getId();
                int anno_inicio = temporada.getAnnoInicio();

                fragmentManager = getActivity().getSupportFragmentManager();
                fragment = PlaceholderAjustesTemporadaDetail.newInstance(idTemporada, anno_inicio);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.layout_content_ajustes, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
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

        inflater.inflate(R.menu.menu_create, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                fragmentManager = getActivity().getSupportFragmentManager();
                fragment = PlaceholderAjustesTemporadaCreate.newInstance(0);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.layout_content_ajustes, fragment)
                        .addToBackStack(null)
                        .commit();
                return true;
            case android.R.id.home:
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
