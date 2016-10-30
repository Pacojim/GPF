package com.gpf.rebisoft.gpf.activitys;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.ajustes.PlaceholderAjustesCuotas;
import com.gpf.rebisoft.gpf.ajustes.PlaceholderAjustesPersonalizacion;
import com.gpf.rebisoft.gpf.ajustes.PlaceholderAjustesPuntos;
import com.gpf.rebisoft.gpf.ajustes.PlaceholderAjustesTemporada;
import com.gpf.rebisoft.gpf.fragments.PlaceholderFragmentCuotasJugador;
import com.gpf.rebisoft.gpf.fragments.PlaceholderFragmentPartido;
import com.gpf.rebisoft.gpf.fragments.PlaceholderFragmentTransaccion;

/**
 * Activity para mostrar los ajustes de la aplicación.
 */
public class AjustesActivity extends AppCompatActivity {

    private Bundle bundle;
    private int id;
    private int idTemporada;
    String opcion;

    /**
     * Método sobreescrito que se lanza al crear la Activity.
     * Establece el layout, obtiene el action bar y habilita el botón por defecto de volver del action bar.
     * Obtiene las variables que llegan del activity que ha lanzado a éste, pone el título y llama a un métdo
     * que establece un fragment requerido.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        bundle = getIntent().getExtras();
        opcion = bundle.getString("opcion");
//        idSectionDrawer = bundle.getInt("idSectionDrawer");
//        idTemporada = bundle.getInt("idTemporada");
//        content = bundle.getString("content");
        setTitle(opcion);
        loadContentDetail();

    }

    /**
     * Carga el fragment adecuado según el parámetro que se recibe
     * de la activity que lanza a esta activity.
     * Los fragment puden ser para que el usuario establezca los ajustes de:
     * - Temporadas
     * - Cuotas
     * - Puntos
     * - Parámetros de personalización
     */
    private void loadContentDetail() {
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (opcion) {
            case "Temporadas":
                fragment = PlaceholderAjustesTemporada.newInstance();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.layout_content_ajustes, fragment)
                        .addToBackStack("inicio")
                        .commit();
                break;
            case "Cuotas":
                fragment = PlaceholderAjustesCuotas.newInstance();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.layout_content_ajustes, fragment)
                        .commit();
                break;
            case "Puntos":
                fragment = PlaceholderAjustesPuntos.newInstance();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.layout_content_ajustes, fragment)
                        .commit();
                break;
            case "Personalización":
                fragment = PlaceholderAjustesPersonalizacion.newInstance();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.layout_content_ajustes, fragment)
                        .commit();
                break;

        }


    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem menuItem) {
//        if (menuItem.getItemId() == android.R.idSectionDrawer.home) {
//            finish();
//        }
//        return super.onOptionsItemSelected(menuItem);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.idSectionDrawer.home:
//                finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    /**
     * Método sobreescrito para la gestión de los botones físicos de los dispositivos móviles.
     * Cierra la activity al pulsar el botón de volver.
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finish();
        return true;
    }
}