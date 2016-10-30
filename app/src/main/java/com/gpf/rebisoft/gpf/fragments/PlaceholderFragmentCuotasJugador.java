package com.gpf.rebisoft.gpf.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.adapters.CuotasJugadorListAdapter;
import com.gpf.rebisoft.gpf.database.GPFDataSource;

/**
 *
 */
public class PlaceholderFragmentCuotasJugador extends Fragment {
    private static final String ARG_SECTION_ID_JUGADOR_CUOTAS = "idJugadorCuotas";
    private static final String ARG_SECTION_ID_TEMPORDA_CUOTAS = "idTemporada";

    private int idJugadorCuotas;
    private int idTemporada;
    private GPFDataSource dataSource;

    public PlaceholderFragmentCuotasJugador() {

    }

    /**
     *
     */
    public static PlaceholderFragmentCuotasJugador newInstance(int idJugadorCuotas, int idTemporada) {
        PlaceholderFragmentCuotasJugador fragment = new PlaceholderFragmentCuotasJugador();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_ID_JUGADOR_CUOTAS, idJugadorCuotas);
        args.putInt(ARG_SECTION_ID_TEMPORDA_CUOTAS, idTemporada);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idJugadorCuotas = getArguments().getInt(ARG_SECTION_ID_JUGADOR_CUOTAS);
            idTemporada = getArguments().getInt(ARG_SECTION_ID_TEMPORDA_CUOTAS);
            dataSource = new GPFDataSource(getContext());
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.section_fragment_cuotas_jugador, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listViewCuotasJugador);
        CuotasJugadorListAdapter cuotasJugadorListAdapter = new CuotasJugadorListAdapter(getContext(), dataSource.getCuotasJugadorTemporada(idJugadorCuotas, idTemporada));
        listView.setAdapter(cuotasJugadorListAdapter);
        return view;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
