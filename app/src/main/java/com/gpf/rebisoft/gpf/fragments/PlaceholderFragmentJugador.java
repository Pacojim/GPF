package com.gpf.rebisoft.gpf.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.clasesbase.Jugador;
import com.gpf.rebisoft.gpf.database.GPFDataSource;
import com.gpf.rebisoft.gpf.utils.Utilidades;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.Iterator;

public class PlaceholderFragmentJugador extends Fragment {


    private FragmentTabHost mTabHost;
    private GPFDataSource dataSource;

    /**
     * Este argumento del fragmento representa el título de cada
     * sección
     */
//    public static final String ARG_SECTION_TITLE = "title";
    public static final String ARG_SECTION_ID_JUGADOR = "idJugador";
    public static final String ARG_SECTION_ID_TEMPORADA = "idTemporada";

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private static final int SECTION_DATOS = 0;
    private static final int SECTION_VICTORIAS = 1;
    private static final int SECTION_GOLES = 2;

    private int idJugador;
    private int idTemporada;
    private int section;
    private Jugador jugador = null;


    /**
     * Crea una instancia prefabricada de {@link PlaceholderFragmentJugador}
     *
     * @return Instancia dle fragmento
     */
    public static PlaceholderFragmentJugador newInstance(int idJugador, int idTemporada, int position) {
        PlaceholderFragmentJugador fragment = new PlaceholderFragmentJugador();
        Bundle args = new Bundle();
//        args.putString(ARG_SECTION_TITLE, sectionTitle);
        args.putInt(ARG_SECTION_ID_JUGADOR, idJugador);
        args.putInt(ARG_SECTION_ID_TEMPORADA, idTemporada);
        args.putInt(ARG_SECTION_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragmentJugador() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//          String title = getArguments().getString(ARG_SECTION_TITLE);
            idJugador = getArguments().getInt(ARG_SECTION_ID_JUGADOR);
            idTemporada = getArguments().getInt(ARG_SECTION_ID_TEMPORADA);
            section = getArguments().getInt(ARG_SECTION_NUMBER);
            dataSource = new GPFDataSource(getContext());
            jugador = dataSource.getJugadorDatos(idJugador, idTemporada);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        if(jugador != null) {
            view = setSectionView(section, inflater, container);
        }
        return view;
    }

    private View setSectionView(int section, LayoutInflater inflater, ViewGroup container) {
        View view = null;
        TextView titulo;
        switch (section) {
            case SECTION_DATOS:
                view = inflater.inflate(R.layout.section_fragment_jugador_datos, container, false);
                TextView nombre = (TextView) view.findViewById(R.id.textViewNombreJugadorDatos);
                TextView apellido1 = (TextView) view.findViewById(R.id.textViewApellido1JugadorDatos);
                TextView apellido2 = (TextView) view.findViewById(R.id.textViewApellido2JugadorDatos);
                TextView apodo = (TextView) view.findViewById(R.id.textViewApodoJugadorDatos);
                TextView partidos = (TextView) view.findViewById(R.id.textViewPartidosJugadorDatos);
                TextView goles = (TextView) view.findViewById(R.id.textViewGolesJugadorDatos);
                TextView golesPartido = (TextView) view.findViewById(R.id.textViewGolesPartidoJugadorDatos);
                TextView victorias = (TextView) view.findViewById(R.id.textViewVictoriasJugadorDatos);
                TextView victoriasPartido = (TextView) view.findViewById(R.id.textViewVictoriasPartidoJugadorDatos);
                TextView puntos = (TextView) view.findViewById(R.id.textViewPuntosJugadorDatos);
                TextView puntosPartido = (TextView) view.findViewById(R.id.textViewPuntosPartidoJugadorDatos);
                TextView socio = (TextView) view.findViewById(R.id.textViewSocioJugadorDatos);
                TextView activo = (TextView) view.findViewById(R.id.textViewActivoJugadorDatos);
                TextView dorsal = (TextView) view.findViewById(R.id.textViewDorsalJugadorDatos);
                ImageView foto = (ImageView) view.findViewById(R.id.imageViewJugadorDatos);
                RatingBar calidad = (RatingBar) view.findViewById(R.id.ratingBarCalidadJugadorDatos);

                nombre.setText(jugador.getNombre());
                apellido1.setText(jugador.getApellido1());
                apellido2.setText(jugador.getApellido2());
                apodo.setText(jugador.getApodo());
                partidos.setText(String.valueOf(jugador.getPartidos()));
                goles.setText(String.valueOf(jugador.getGoles()));
                victorias.setText(String.valueOf(jugador.getVictorias()));
                puntos.setText(String.valueOf(jugador.getPuntos()));
                if(jugador.getPartidos() > 0) {
                    golesPartido.setText(String.valueOf(Utilidades.round2Decimal((float) jugador.getGoles() / (float) jugador.getPartidos())));
                    victoriasPartido.setText(String.valueOf(Utilidades.round2Decimal((float) jugador.getVictorias() / (float) jugador.getPartidos())));
                    puntosPartido.setText(String.valueOf(Utilidades.round2Decimal((float) jugador.getPuntos() / (float) jugador.getPartidos())));
                } else {
                    golesPartido.setText(String.valueOf(0));
                    victoriasPartido.setText(String.valueOf(0));
                    puntosPartido.setText(String.valueOf(0));
                }
                socio.setText(jugador.getSocio() == 1 ? "Sí" : "No");
                activo.setText(jugador.getActivo() == 1 ? "Sí" : "No");
                dorsal.setText(String.valueOf(jugador.getDorsal()));
                if(jugador.getFoto() != null && !jugador.getFoto().trim().equalsIgnoreCase("")){
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    foto.setImageBitmap(Utilidades.getBitmapFromPath(extStorageDirectory + Utilidades.PATH_IMAGES_APP + jugador.getFoto()));
                }
                calidad.setRating(jugador.getCalidad() / 2f);

                break;
            case SECTION_VICTORIAS:
                if(jugador.getPartidos() == 0) {
                    view = inflater.inflate(R.layout.section_fragment_jugador, container, false);
                    titulo = (TextView) view.findViewById(R.id.title);
                    titulo.setText("Este jugador aún no ha disputado ningún partido.");
                    break;
                }

                view = inflater.inflate(R.layout.section_fragment_jugador_estadistica_1, container, false);

                ArrayList<Integer[]> datosList = dataSource.getJugadoresGraficaPartidos(idJugador, idTemporada);

                GraphView graph = (GraphView) view.findViewById(R.id.graph);

                DataPoint[] pointsVictorias = new DataPoint[datosList.size()];
                DataPoint[] pointsGoles = new DataPoint[datosList.size()];

                Iterator<Integer[]> datosIterator = datosList.iterator();
                Integer[] aux;
                int i = 0;
                while (datosIterator.hasNext()) {
                    aux = datosIterator.next();
                    pointsVictorias[i] = new DataPoint(aux[2], victoriaEmpateDerrota(aux[0]));
                    pointsGoles[i] = new DataPoint(aux[2], aux[1]);
                    i++;
                }

                PointsGraphSeries<DataPoint> series = new PointsGraphSeries<DataPoint>(pointsVictorias);
                graph.addSeries(series);
                series.setShape(PointsGraphSeries.Shape.POINT);

                graph.getGridLabelRenderer().setLabelFormatter(new StaticLabelsFormatter(graph, null, new String[]{
                        "Derrota", "Empate", "Victoria"
                }));

                // set manual Y bounds
                graph.getViewport().setYAxisBoundsManual(true);
                graph.getViewport().setMinY(-1);
                graph.getViewport().setMaxY(1);

                // set manual X bounds
                graph.getViewport().setXAxisBoundsManual(true);
                graph.getViewport().setMinX(1);
                graph.getViewport().setMaxX(5);

                graph.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });

                // enable scrolling
                graph.getViewport().setScrollable(true);

                GraphView graph2 = (GraphView) view.findViewById(R.id.graph2);
                BarGraphSeries<DataPoint> series2 = new BarGraphSeries<DataPoint>(pointsGoles);
                graph2.addSeries(series2);

                // styling
//                series2.setValueDependentColor(new ValueDependentColor<DataPoint>() {
//                    @Override
//                    public int get(DataPoint data) {
//                        return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
//                    }
//                });

                series2.setSpacing(10);

                // draw values on top
                series2.setDrawValuesOnTop(true);
                series2.setValuesOnTopColor(Color.RED);
                graph2.getViewport().setMinX(1);
                graph2.getViewport().setMaxX(10);
                graph2.getViewport().setYAxisBoundsManual(true);
                graph2.getViewport().setMinY(0);
                graph2.getViewport().setMaxY(10);
                //series.setValuesOnTopSize(50);

                // legend
//                series.setTitle("foo");
//                graph.getLegendRenderer().setVisible(true);
//                graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

                // enable scrolling
                graph2.getViewport().setScrollable(true);
                graph2.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });

                break;
            case SECTION_GOLES:
                view = inflater.inflate(R.layout.section_fragment_jugador, container, false);
                titulo = (TextView) view.findViewById(R.id.title);
                titulo.setText("Espacio reservado para futuras funcionalidades.");
                break;
        }


        return view;
    }

    private int victoriaEmpateDerrota(int i) {
        if (i == 0)
            return -1;
        else if (i == 1)
            return 1;
        else
            return 0;
    }


}
