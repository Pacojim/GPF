package com.gpf.rebisoft.gpf.fragments;

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
import android.widget.ListView;
import android.widget.Toast;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.adapters.JugadorListAdapter;
import com.gpf.rebisoft.gpf.adapters.JugadoresCrearPartidoListAdapter;
import com.gpf.rebisoft.gpf.clasesbase.Jugador;
import com.gpf.rebisoft.gpf.database.GPFDataSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Fragment para crear un partido. Primer paso ver una lista de jugadores para seleccionar
 * los que jugarán el partido
 */
public class PlaceHolderFragmentCrearPartido extends Fragment {

    private static final String ARG_PARAM_ID_TEMPORADA = "idTemporda";

    // TODO: Rename and change types of parameters
    private int idTemporada;

    private GPFDataSource dataSource;
    private Context context;
    private JugadoresCrearPartidoListAdapter adapter;
//    private Bundle bundle;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    public PlaceHolderFragmentCrearPartido() {
        // Required empty public constructor
    }

    /**
     * Método para instanciar el fragment y pasarle los argumentos
     * @param idTemporada ID de la temporada donde se emplaza el partido
     * @return
     */
    public static PlaceHolderFragmentCrearPartido newInstance(int idTemporada) {
        PlaceHolderFragmentCrearPartido fragment = new PlaceHolderFragmentCrearPartido();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_ID_TEMPORADA, idTemporada);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Se llama automáticamente al crearse el fragment. Se inicializan las variables a usar
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idTemporada = getArguments().getInt(ARG_PARAM_ID_TEMPORADA);
        }
        context = getContext();
        dataSource = new GPFDataSource(context);
        fragmentManager = getActivity().getSupportFragmentManager();
        setHasOptionsMenu(true);
    }

    /**
     * Se llama al crearse la vista. Creamos el listview con los jugadores para mostrarlo
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.section_fragment_crear_partido_list_view_jugadores, container, false);

        final ListView listView = (ListView) view.findViewById(R.id.listViewJugadoresCrearPartido);
        adapter = new JugadoresCrearPartidoListAdapter(context, dataSource.getJugadoresCrearPartido());
        listView.setAdapter(adapter);

        return view;
    }

    /**
     * Crea el menú de opciones en el toolbar
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here

        inflater.inflate(R.menu.menu_create_pre_partido, menu);
    }

    /**
     * Establecemos el comportamiento de las opciones del toolbar.
     * Si se pulsa en la opción siguiente, se envía una lista de jugadores ordenada por calidad para
     * el fragment del paso 2 de creación de partidos
     * @param item item pulsado
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_foward:
                ArrayList<Jugador> jugadores = adapter.getJugadoresSeleccionados();
                jugadores = sortearJugadores(jugadores);


                String msg = String.valueOf(jugadores.size());
                Bundle bundle;
                if(jugadores.size() >= 2) {
                    fragment = PlaceHolderFragmentCrearPartidoPaso2.newInstance(jugadores, idTemporada);
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.layout_content_create, fragment)
                            .addToBackStack(null)
                            .commit();

                }
                return true;
            case android.R.id.home:
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Sortea los jugadores para cada equipo, de forma equitativa teniendo en cuenta la calidad
     * definida por el usuario al crear cada jugador
     * @param jugadores lista con los jugadores a ordenar
     * @return lista con los jugadores ordenados para cada equipo
     */
    private ArrayList<Jugador> sortearJugadores(ArrayList<Jugador> jugadores) {
        ArrayList<Jugador> jugadoresAux = new ArrayList<>();
        Collections.sort(jugadores, new Comparator<Jugador>() {
            @Override
            public int compare(Jugador jugador1, Jugador jugador2) {
                return jugador2.getCalidad() - jugador1.getCalidad();
            }
        });
        int size = jugadores.size();
        for(int i=0; i<size - 1; i++) {
            Random rnd = new Random();
            if((int) (rnd.nextDouble() * 2) == 0) {
                jugadoresAux.add(jugadores.get(i));
                jugadoresAux.add(jugadores.get(i+1));
            } else {
                jugadoresAux.add(jugadores.get(i+1));
                jugadoresAux.add(jugadores.get(i));
            }
            i++;
        }
        if(size % 2 == 1)
            jugadoresAux.add(jugadores.get(size - 1));
        return jugadoresAux;

    }
}
