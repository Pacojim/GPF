package com.gpf.rebisoft.gpf.fragments;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.clasesbase.Jugador;
import com.gpf.rebisoft.gpf.database.GPFDataSource;
import com.gpf.rebisoft.gpf.utils.Utilidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 */
public class PlaceHolderFragmentCrearPartidoPaso2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_JUGADORES = "jugadores";
    private static final String ARG_ID_TEMPORADA = "idTemporada";
    private Serializable viewOnPause;


    // TODO: Rename and change types of parameters
    private ArrayList<Jugador> jugadores;
    private String extStorageDirectory;
    private Context context;
    private View view;
    private GPFDataSource dataSource;
    private LinearLayout campo1;
    private LinearLayout campo2;
    private int idTemporada;

    public PlaceHolderFragmentCrearPartidoPaso2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param jugadores Parameter 1..
     * @return A new instance of fragment PlaceHolderFragmentCrearPartidoPaso2.
     */
    // TODO: Rename and change types and number of parameters
    public static PlaceHolderFragmentCrearPartidoPaso2 newInstance(ArrayList<Jugador> jugadores, int idTemporada) {
        PlaceHolderFragmentCrearPartidoPaso2 fragment = new PlaceHolderFragmentCrearPartidoPaso2();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_JUGADORES, (ArrayList<? extends Parcelable>) jugadores);
        args.putInt(ARG_ID_TEMPORADA, idTemporada);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("Crear equipos");
        if (getArguments() != null) {
            jugadores = getArguments().getParcelableArrayList(ARG_JUGADORES);
            idTemporada = getArguments().getInt(ARG_ID_TEMPORADA);
        }
        extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        context = getContext();
        setHasOptionsMenu(true);
        dataSource = new GPFDataSource(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.place_holder_fragment_crear_partido_paso2, container, false);


        if(campo1 == null && campo2 == null) {
            ArrayList<LinearLayout> layoutImagenes = new ArrayList<>();
            campo1 = (LinearLayout) view.findViewById(R.id.layout_campo_equipo1);
            campo2 = (LinearLayout) view.findViewById(R.id.layout_campo_equipo2);
            LinearLayout row1 = createRow();
            LinearLayout row2 = createRow();
            for (int i = 0; i < jugadores.size(); i++) {
                if (row1 == null && row2 == null) {
                    row1 = createRow();
                    row2 = createRow();
                }
                ImageView imageView = new ImageView(context);
                TextView nombre = new TextView(context);
                LinearLayout layout = new LinearLayout(context);

                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setPadding(30, 0, 30, 0);
                nombre.setText(this.jugadores.get(i).getNombreParaEquipo());
                nombre.setMaxWidth(40);
                nombre.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7);
                nombre.setGravity(Gravity.CENTER_HORIZONTAL);
                Bitmap bitmap;

                if (this.jugadores.get(i).getFoto() != null && !this.jugadores.get(i).getFoto().trim().equals("")) {
                    bitmap = Utilidades.getBitmapFromPath(extStorageDirectory + Utilidades.PATH_IMAGES_APP + this.jugadores.get(i).getFoto());
                    bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
                    imageView.setImageBitmap(bitmap);
                } else {
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jugador_default);
                    bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
                    imageView.setImageBitmap(bitmap);
                }

//            imageView.setOnLongClickListener(listenClick);
//            imageView.setOnDragListener(listenDrag);

                imageView.setId(this.jugadores.get(i).getId());
                layout.addView(imageView);
                layout.addView(nombre);
                layout.setOnLongClickListener(listenClick);
                layout.setOnDragListener(listenDrag);

                if (i % 2 == 0) {
                    row1.addView(layout);
                } else {
                    row2.addView(layout);
                }
                if ((i + 1) % 6 == 0) {
                    campo1.addView(row1);
                    campo2.addView(row2);
                    row1 = null;
                    row2 = null;
                }
                layoutImagenes.add(layout);
            }
            if (row1 != null)
                campo1.addView(row1);
            if (row2 != null)
                campo2.addView(row2);
        }


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here

        inflater.inflate(R.menu.menu_create_partido, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create:
                Vector<Integer> idsEquipo1 = getIDsEquipo(campo1);
                Vector<Integer> idsEquipo2 = getIDsEquipo(campo2);
                if (dataSource.crearEquipos(idsEquipo1, idsEquipo2, idTemporada))
                    getActivity().finish();
                    return true;
            case android.R.id.home:
                getFragmentManager().popBackStack("inicio", 0);
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Vector<Integer> getIDsEquipo(LinearLayout campo) {
        Vector<Integer> idJugadoresEquipo = new Vector<>();
        for (int i = 0; i < campo.getChildCount(); i++) {
            for (int j = 0; j < ((LinearLayout) campo.getChildAt(i)).getChildCount(); j++) {
                idJugadoresEquipo.add(((LinearLayout) ((LinearLayout) campo.getChildAt(i)).getChildAt(j)).getChildAt(0).getId());
            }
        }
        return idJugadoresEquipo;
    }

    private LinearLayout createRow() {
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setPadding(0, 10, 0, 10);
        row.setGravity(Gravity.CENTER);
        return row;
    }


    View.OnLongClickListener listenClick = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {

            ClipData data = ClipData.newPlainText("", "");
            DragShadow dragShadow = new DragShadow(v);

            v.startDrag(data, dragShadow, v, 0);

            return false;
        }
    };

    private class DragShadow extends View.DragShadowBuilder {
        //        ColorDrawable greyBox;
        LinearLayout greyBox;

        public DragShadow(View view) {
            super(view);
//            greyBox = new ColorDrawable(Color.LTGRAY);
            greyBox = (LinearLayout) view;
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            greyBox.draw(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize,
                                           Point shadowTouchPoint) {
            View v = getView();

            int height = (int) v.getHeight();
            int width = (int) v.getWidth();

//            greyBox.setBounds(0, 0, width, height);

            shadowSize.set(width, height);

            shadowTouchPoint.set((int) 2 * width / 3, (int) 2 * height / 3);
        }
    }


    View.OnDragListener listenDrag = new View.OnDragListener() {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();

            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.i("Drag Event", "Entered");
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    Log.i("Drag Event", "Exited");
                    break;

                case DragEvent.ACTION_DROP:
                    LinearLayout target = (LinearLayout) v;

                    LinearLayout dragged = (LinearLayout) event.getLocalState();
                    if (target != dragged) {
//                        ImageView target_image = (ImageView) target.getChildAt(0);
                        View[] target_views = new View[target.getChildCount()];
                        View[] dragged_views = new View[dragged.getChildCount()];
                        for (int i = 0; i < target.getChildCount(); i++) {
                            target_views[i] = (View) target.getChildAt(i);
                            dragged_views[i] = (View) dragged.getChildAt(i);
                        }
                        target.removeAllViews();
                        dragged.removeAllViews();

                        for (int i = 0; i < target_views.length; i++) {
                            dragged.addView(target_views[i]);
                            target.addView(dragged_views[i]);
                        }
                    }
                    break;

            }

            return true;
        }
    };
}
