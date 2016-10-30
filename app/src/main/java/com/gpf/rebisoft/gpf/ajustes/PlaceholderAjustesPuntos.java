package com.gpf.rebisoft.gpf.ajustes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.gpf.rebisoft.gpf.R;

/**
 * Fragment para establecer los puntos que se le otorgarán a cada jugador por goles o victoria
 */
public class PlaceholderAjustesPuntos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    /**
     * Constructor por defecto requerido
     */
    public PlaceholderAjustesPuntos() {
        // Required empty public constructor
    }

    /**
     * Método para instanciar el fragment y pasarle los argumentos
     */
    // TODO: Rename and change types and number of parameters
    public static PlaceholderAjustesPuntos newInstance(/*String param1, String param2*/) {
        PlaceholderAjustesPuntos fragment = new PlaceholderAjustesPuntos();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Se llama automáticamente al crearse el fragment
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
        setHasOptionsMenu(true);
    }

    /**
     * Se llama al crearse la vista
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_placeholder_ajustes_puntos, container, false);
    }

    /**
     * Establecemos el comportamiento de las opciones del toolbar
     * @param item item pulsado
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:

                return true;
            case android.R.id.home:
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
