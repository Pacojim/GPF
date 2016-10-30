package com.gpf.rebisoft.gpf.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.activitys.AjustesActivity;
import com.gpf.rebisoft.gpf.activitys.DetailActivity;
import com.gpf.rebisoft.gpf.activitys.JugadorActivity;
import com.gpf.rebisoft.gpf.adapters.JugadorCuotaListAdapter;
import com.gpf.rebisoft.gpf.adapters.JugadorListAdapter;
import com.gpf.rebisoft.gpf.adapters.PartidoListAdapter;
import com.gpf.rebisoft.gpf.adapters.TransaccionListAdapter;
import com.gpf.rebisoft.gpf.clasesauxiliares.JugadorCuota;
import com.gpf.rebisoft.gpf.clasesbase.Jugador;
import com.gpf.rebisoft.gpf.clasesbase.Partido;
import com.gpf.rebisoft.gpf.clasesbase.Transaccion;
import com.gpf.rebisoft.gpf.database.GPFDataSource;
import com.gpf.rebisoft.gpf.utils.Utilidades;

/**
 * Created by pacos on 16/05/2016.
 */
public class PlaceHolderFragmentListViewPrincipal extends Fragment {
    private GPFDataSource dataSource;
    private ListView listView;
    private LinearLayout linearLayoutTotalCaja;
    private TextView textViewTotalCaja;
    private ListAdapter adaptador;
    private int cuotasTemporada;
    /**
     * Este argumento del fragmento representa el título de cada
     * sección
     */
    public static final String ARG_ID_TEMPORDA = "idTemporada";
    public static final String ARG_LIST = "list";

    private int idTemporada;
    private String list;
    private Context context;


    /**
     * Crea una instancia prefabricada de {@link PlaceHolderFragmentListViewPrincipal}
     *
     * @param idTemporada Título usado en el contenido
     * @return Instancia dle fragmento
     */
    public static PlaceHolderFragmentListViewPrincipal newInstance(int idTemporada, String list) {
        PlaceHolderFragmentListViewPrincipal fragment = new PlaceHolderFragmentListViewPrincipal();
        Bundle args = new Bundle();
        args.putInt(ARG_ID_TEMPORDA, idTemporada);
        args.putString(ARG_LIST, list);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceHolderFragmentListViewPrincipal() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        if (getArguments() != null) {
            idTemporada = getArguments().getInt(ARG_ID_TEMPORDA);
            list = getArguments().getString(ARG_LIST);
            dataSource = new GPFDataSource(context);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_content, container, false);

        linearLayoutTotalCaja = (LinearLayout) view.findViewById(R.id.linearLayoutTotalCaja);
        textViewTotalCaja = (TextView) view.findViewById(R.id.textViewTotalCaja);
        listView = (ListView) view.findViewById(R.id.listViewPrincipal);

        adaptador = selectList();
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (adaptador.getItem(position) instanceof Jugador) {
                    String nombreJugador = ((Jugador) adaptador.getItem(position)).getNombre();
                    int idJugador = (int) adaptador.getItemId(position);
                    Intent i = new Intent(getActivity(), JugadorActivity.class);
                    i.putExtra("title", nombreJugador);
                    i.putExtra("idJugador", idJugador);
                    i.putExtra("idTemporada", idTemporada);
                    startActivity(i);
                } else if (adaptador.getItem(position) instanceof Partido) {
//                    String fechaPartido = Utilidades.pasarDateString(((Partido) adaptador.getItem(position)).getFecha());
                    int idPartido = (int) adaptador.getItemId(position);
                    Intent i = new Intent(getActivity(), DetailActivity.class);
//                    i.putExtra("title", "Partido del " + fechaPartido);
                    i.putExtra("title", "Jornada " + ((Partido) adaptador.getItem(position)).getJornada());
                    i.putExtra("id", idPartido);
                    i.putExtra("idTemporada", idTemporada);
                    i.putExtra("content", "partido");
                    startActivity(i);
                } else if (adaptador.getItem(position) instanceof Transaccion) {
                    String nombreTransaccion = ((Transaccion) adaptador.getItem(position)).getNombre();
                    int idTransaccion = (int) adaptador.getItemId(position);
                    Intent i = new Intent(getActivity(), DetailActivity.class);
                    i.putExtra("title", nombreTransaccion);
                    i.putExtra("id", idTransaccion);
                    i.putExtra("content", "transaccion");
                    startActivity(i);
                } else if (adaptador.getItem(position) instanceof JugadorCuota) {
                    String nombreJugadorCuota = ((JugadorCuota) adaptador.getItem(position)).getNombre();
                    int idJugadorCuota = (int) adaptador.getItemId(position);
                    Intent i = new Intent(getActivity(), DetailActivity.class);
                    i.putExtra("title", "Cuotas de " + nombreJugadorCuota);
                    i.putExtra("id", idJugadorCuota);
                    i.putExtra("idTemporada", idTemporada);
                    i.putExtra("content", "jugadorCuota");
                    startActivity(i);
                } else if(adaptador instanceof ListAdapter) {
                    Intent i = new Intent(getActivity(), AjustesActivity.class);
                    String opcion = (String) adaptador.getItem(position);
                    i.putExtra("opcion", opcion);
                    startActivity(i);
                }
            }
        });
        return view;
    }

    private ListAdapter selectList() {
        ListAdapter adaptador = null;
        switch (list) {
            case "jugadores":
//                listView.setPadding(0, 0, 0, 0);
//                linearLayoutTotalCaja.setVisibility(View.INVISIBLE);
                JugadorListAdapter jugadorListAdapter = new JugadorListAdapter(context, dataSource.getJugadoresLista(idTemporada));
                adaptador = jugadorListAdapter;
                break;
            case "partidos":
//                listView.setPadding(0, 0, 0, 0);
//                linearLayoutTotalCaja.setVisibility(View.INVISIBLE);
                PartidoListAdapter partidosListAdapter = new PartidoListAdapter(context, dataSource.getPartidosLista(idTemporada));
                adaptador = partidosListAdapter;
                break;
            case "caja":
                listView.setPadding(0, 70, 0, 0);
                linearLayoutTotalCaja.setVisibility(View.VISIBLE);
                textViewTotalCaja.setText("Total: " + String.valueOf(dataSource.getTotalCaja()));
                TransaccionListAdapter transaccionesListAdapter = new TransaccionListAdapter(context, dataSource.getTransaccionesLista());
                adaptador = transaccionesListAdapter;
                break;
            case "cuotas":
//                listView.setPadding(0, 0, 0, 0);
//                linearLayoutTotalCaja.setVisibility(View.INVISIBLE);
                cuotasTemporada = dataSource.getNumeroCuotasTemporada(idTemporada);
                JugadorCuotaListAdapter jugadorCuotaListAdapter = new JugadorCuotaListAdapter(context, dataSource.getJugadorCuotasLista(idTemporada), cuotasTemporada);
                adaptador = jugadorCuotaListAdapter;
                break;
            case "ajustes":
//                String[] opciones = new String[]{"Personalización", "Temporadas", "Cuotas", "Puntos"};
                ListAdapter listAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.arrayOpcionesAjustes));
                adaptador = listAdapter;
                break;

        }
        return adaptador;
    }

    @Override
    public void onResume() {
        super.onResume();
        adaptador = selectList();
        listView.setAdapter(adaptador);

    }
}
