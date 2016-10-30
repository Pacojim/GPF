package com.gpf.rebisoft.gpf.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.clasesbase.Transaccion;
import com.gpf.rebisoft.gpf.database.GPFDataSource;
import com.gpf.rebisoft.gpf.utils.Utilidades;

/**
 *
 */
public class PlaceholderFragmentTransaccion extends Fragment {

    private static final String ARG_SECTION_ID_TRANSACCION = "idTransaccion";


    private int idTransaccion;
    private GPFDataSource dataSource;
    private Transaccion transaccion = null;

    private TextView fechaRealizada;
    private ToggleButton toggleRealizada;

    private boolean switchBloqueado = false;

    public PlaceholderFragmentTransaccion() {
        // Required empty public constructor
    }

    /**
     *
     */

    public static PlaceholderFragmentTransaccion newInstance(int idTransaccion) {
        PlaceholderFragmentTransaccion fragment = new PlaceholderFragmentTransaccion();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_ID_TRANSACCION, idTransaccion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idTransaccion = getArguments().getInt(ARG_SECTION_ID_TRANSACCION);
            dataSource = new GPFDataSource(getContext());
            transaccion = dataSource.getTransaccion(idTransaccion);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.section_fragment_transaccion, container, false);
        if (transaccion != null) {
            TextView codigo = (TextView) view.findViewById(R.id.textViewTransaccionCodigo);
            TextView nombre = (TextView) view.findViewById(R.id.textViewTransaccionNombre);
            TextView tipo = (TextView) view.findViewById(R.id.textViewTransaccionTipo);
            TextView descripcion = (TextView) view.findViewById(R.id.textViewTransaccionDescripcion);
            TextView fechaLimite = (TextView) view.findViewById(R.id.textViewTransaccionFechaLimite);
            TextView cuantia = (TextView) view.findViewById(R.id.textViewTransaccionCuantia);
            fechaRealizada = (TextView) view.findViewById(R.id.textViewTransaccionFechaRealizada);
            toggleRealizada = (ToggleButton) view.findViewById(R.id.toggleTransaccionRealizada);

            codigo.setText(transaccion.getCodigo());
            nombre.setText(transaccion.getNombre());
            tipo.setText(transaccion.getTipo() == 0 ? "Pago" : "Ingreso");
            descripcion.setText(transaccion.getDescripcion());
            if (!Utilidades.pasarDateString(transaccion.getFechaLimite()).trim().equals("")) {
                fechaLimite.setText(Utilidades.pasarDateString(transaccion.getFechaLimite()));
            } else {
                fechaLimite.setText("Sin fecha límite");
            }
            cuantia.setText(String.valueOf(Math.abs(transaccion.getCuantia())) + " \u20ac");
            fechaRealizada.setText(transaccion.getFechaRealizada() != null ? Utilidades.pasarDateString(transaccion.getFechaRealizada()) : "");
            if (transaccion.getRealizada() == 1) {
                toggleRealizada.setChecked(true);
            } else {
                toggleRealizada.setChecked(false);
            }

            toggleRealizada.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (toggleRealizada.isChecked()) {
                        fechaRealizada.setText(Utilidades.pasarStringBDStringApp(dataSource.realizarTransaccion(transaccion.getId())));
                    } else {
                        AlertDialog dialog = createSimpleDialog("Anular Transacción", "¿Seguro que quieres anular esta transacción? Está ya realizada. " +
                                "Si tiene una cuota de jugador asociada, ésta pasará a estado no pagada.", "anular");
                        dialog.show();
                    }
                }
            });
        }
        return view;
    }

    /**
     * Crea un diálogo de alerta sencillo
     *
     * @return Nuevo diálogo
     */
    private AlertDialog createSimpleDialog(String title, String mensaje, final String anularOEliminar) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(title)
                .setMessage(mensaje)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (anularOEliminar.equals("anular")) {
                                    dataSource.anularTransaccion(transaccion.getId());
                                    fechaRealizada.setText("");
                                } else {
                                    dataSource.eliminarTransaccion(transaccion.getId());
                                    getActivity().finish();
                                }
                            }
                        })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (anularOEliminar.equals("anular"))
                                    toggleRealizada.setChecked(true);
                            }
                        });

        return builder.create();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(!dataSource.isTransaccionCuota(transaccion.getId())) {
            inflater.inflate(R.menu.menu_editar_eliminar, menu);
        } else {
            inflater.inflate(R.menu.menu_eliminar, menu);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                Fragment fragment;
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragment = PlaceholderFragmentCrearTransaccion.newInstance(idTransaccion);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.layout_content_detail, fragment)
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.action_eliminar:
                if(transaccion.getRealizada() == 1) {
                    AlertDialog dialog = createSimpleDialog("Eliminar Transacción", "¿Seguro que quieres eliminar la transacción? Está ya realizada." +
                            " Si tiene una cuota de jugador asociada se eliminará también.", "eliminar");
                    dialog.show();
                } else {
                    dataSource.eliminarTransaccion(transaccion.getId());
                    getActivity().finish();
                }
                return true;
            case android.R.id.home:
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
