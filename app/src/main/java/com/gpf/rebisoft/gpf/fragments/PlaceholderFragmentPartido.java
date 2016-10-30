package com.gpf.rebisoft.gpf.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.clasesbase.Jugador;
import com.gpf.rebisoft.gpf.clasesbase.Partido;
import com.gpf.rebisoft.gpf.database.GPFDataSource;
import com.gpf.rebisoft.gpf.utils.Utilidades;

import java.util.ArrayList;
import java.util.Iterator;

/**
 */
public class PlaceholderFragmentPartido extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    public static final String ARG_SECTION_TITLE = "title";
    public static final String ARG_SECTION_ID_PARTIDO = "idPartido";
    public static final String ARG_EDIT_PARTIDO = "editPartido";

    private int idPartido;
    private boolean editPartido;
    private Context context;
    private int disputado;
    private ArrayList<Jugador> jugadores;
    private GPFDataSource dataSource;
    private View view;

    public PlaceholderFragmentPartido() {
        // Required empty public constructor
    }

    /**
     */
    // TODO: Rename and change types and number of parameters
    public static PlaceholderFragmentPartido newInstance(int idPartido, boolean editPartido) {
        PlaceholderFragmentPartido fragment = new PlaceholderFragmentPartido();
        Bundle args = new Bundle();
//        args.putString(ARG_SECTION_TITLE, title);
        args.putInt(ARG_SECTION_ID_PARTIDO, idPartido);
        args.putBoolean(ARG_EDIT_PARTIDO, editPartido);
//        args.putInt(ARG_SECTION_ID_TEMPORADA, idTemporada);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idPartido = getArguments().getInt(ARG_SECTION_ID_PARTIDO);
            editPartido = getArguments().getBoolean(ARG_EDIT_PARTIDO);
        }
        context = getContext();
        setHasOptionsMenu(true);
        dataSource = new GPFDataSource(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.section_fragment_partido, container, false);

        TextView marcadorEquipo1 = (TextView) view.findViewById(R.id.textViewPartidoMarcador1);
        TextView marcadorEquipo2 = (TextView) view.findViewById(R.id.textViewPartidoMarcador2);
        TextView fecha = (TextView) view.findViewById(R.id.textViewFechaPartido);
        TextView jornada = (TextView) view.findViewById(R.id.textViewJornadaPartido);

        Partido partido = dataSource.getDatosPartido(idPartido);
        jugadores = dataSource.getDatosPartidoJugadores(idPartido);
        disputado = partido.getDisputado();

        fecha.setText(Utilidades.pasarDateString(partido.getFecha()));
        jornada.setText(String.valueOf(partido.getJornada()));
        if (!editPartido && partido.getDisputado() == 1) {
            marcadorEquipo1.setText(String.valueOf(partido.getGolesEquipoA()));
            marcadorEquipo2.setText(String.valueOf(partido.getGolesEquipoB()));
            if (jugadores != null) {
                Iterator<Jugador> it = jugadores.iterator();
                while (it.hasNext()) {
                    Jugador jugador = it.next();
                    TextView textViewDorsal = new TextView(context);
                    TextView textViewNombre = new TextView(context);
                    TextView textViewGoles = new TextView(context);
                    LinearLayout linearLayout = new LinearLayout(context);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    textViewDorsal.setText(String.valueOf(jugador.getDorsal()));
                    textViewNombre.setText(jugador.getNombreParaEquipo());
                    textViewGoles.setText(String.valueOf(jugador.getGoles()));
                    textViewDorsal.setWidth(50);
                    textViewNombre.setWidth(210);
                    textViewGoles.setWidth(50);
                    linearLayout.addView(textViewDorsal);
                    linearLayout.addView(textViewNombre);
                    linearLayout.addView(textViewGoles);
                    if (jugador.getEquipo().equalsIgnoreCase("A")){
                        ((LinearLayout) view.findViewById(R.id.linearLayoutPartidoJugadoresA)).addView(linearLayout);
                    }

                    else{
                        ((LinearLayout) view.findViewById(R.id.linearLayoutPartidoJugadoresB)).addView(linearLayout);
                    }

                }
            }
        } else {
            marcadorEquipo1.setText(" ");
            marcadorEquipo2.setText(" ");
            if (jugadores != null) {
                Iterator<Jugador> it = jugadores.iterator();
                while (it.hasNext()) {
                    Jugador jugador = it.next();

                    LinearLayout row = new LinearLayout(context);
                    row.setOrientation(LinearLayout.HORIZONTAL);
                    row.setGravity(Gravity.LEFT);
                    row.setGravity(Gravity.CENTER_VERTICAL);

                    LinearLayout llEditText = new LinearLayout(context);
                    llEditText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                    TextView textView = new TextView(context);
                    textView.setText(String.valueOf(jugador.getDorsal()) + "   " + jugador.getNombreParaEquipo()
                            + "    ");
                    textView.setWidth(220);
                    final EditText editText = new EditText(context);
                    editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});

                    llEditText.addView(editText);

                    editText.setId(jugador.getId());
                    if(disputado == 1) {
                        editText.setText(String.valueOf(jugador.getGoles()));
                    }

                    row.addView(textView);
                    row.addView(llEditText);

                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    if (jugador.getEquipo().equalsIgnoreCase("A")){
                        ((LinearLayout) view.findViewById(R.id.linearLayoutPartidoJugadoresA)).addView(row);
                    }

                    else{
                        ((LinearLayout) view.findViewById(R.id.linearLayoutPartidoJugadoresB)).addView(row);
                    }

                }
            }
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        if(!editPartido && disputado == 1)
        inflater.inflate(R.menu.menu_editar_eliminar, menu);
        else
            inflater.inflate(R.menu.menu_insertar_eliminar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert:
                saveDatosPartido();
                getActivity().finish();
                return true;

            case R.id.action_edit:
                Fragment fragment;
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragment = PlaceholderFragmentPartido.newInstance(idPartido, true);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.layout_content_detail, fragment)
                        .commit();
                return true;
            case android.R.id.home:
                getActivity().finish();
                return true;
            case R.id.action_eliminar:
                dataSource.deletePartido(idPartido);
                getActivity().finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveDatosPartido() {
        int golesA = 0;
        int golesB = 0;
        // Puntos por gol
        int puntosGol = 3;
        // Puntos por victoria
        int puntosVictoria = 1;
        Jugador jugador;
        for(int i=0; i<jugadores.size(); i++) {
            jugador = jugadores.get(i);
            String edGoles = ((EditText) view.findViewById(jugadores.get(i).getId())).getText().toString();
            int goles = edGoles.trim().equals("") ? 0 : Integer.parseInt(edGoles);
            jugador.setGoles(goles);
            if(jugadores.get(i).getEquipo().equalsIgnoreCase("A")) {
                jugador.setEquipo("A");
                golesA += jugadores.get(i).getGoles();
            } else {
                jugador.setEquipo("B");
                golesB += jugadores.get(i).getGoles();
            }
        }
        for(int i=0; i<jugadores.size(); i++) {
            jugador = jugadores.get(i);
            if(golesA > golesB) {
                if(jugador.getEquipo().equalsIgnoreCase("A")) {
                    jugador.setPuntos(puntosGol * jugador.getGoles() + puntosVictoria);
                    jugador.setVictorias(1);
                } else {
                    jugador.setPuntos(puntosGol * jugador.getGoles());
                    jugador.setVictorias(0);
                }
            } else if(golesA < golesB){
                if(jugador.getEquipo().equalsIgnoreCase("B")) {
                    jugador.setPuntos(puntosGol * jugador.getGoles() + puntosVictoria);
                    jugador.setVictorias(1);
                } else {
                    jugador.setPuntos(puntosGol * jugador.getGoles());
                    jugador.setVictorias(0);
                }
            } else {
                if(jugador.getEquipo().equalsIgnoreCase("B")) {
                    jugador.setPuntos(puntosGol * jugador.getGoles());
                    jugador.setVictorias(0);
                } else {
                    jugador.setPuntos(puntosGol * jugador.getGoles());
                    jugador.setVictorias(0);
                }
            }
            dataSource.setResultadosPartido(idPartido, jugador);
        }
    }

}
