package com.gpf.rebisoft.gpf.activitys;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.fragments.PlaceholderFragmentCuotasJugador;
import com.gpf.rebisoft.gpf.fragments.PlaceholderFragmentPartido;
import com.gpf.rebisoft.gpf.fragments.PlaceholderFragmentTransaccion;

/**
 * Activity para mostrar los fragment con los detalles de los objetos de negocio de la aplicación.
 */
public class DetailActivity extends AppCompatActivity {
    private Bundle bundle;
    /** Este ID depende de los datos a mostrar a través del fragment adecuado, puede ser:
     * - ID de partido
     * - ID de transacción
     * - ID de cuota de un jugador
     */
    private int id;
    /**
     * ID de la temporada que se está contultando
     */
    private int idTemporada;
    /**
     * Para guardar el valor que envía la activity que lanza a está y que nos indicará
     * el fragment a cargar.
     */
    String content;

    /**
     * Método sobreescrito que se lanza al crear la activity. Inicializa las variables,
     * obtiene los valores que envía la activity que lanza a está, llama a un método para establecer
     * el fragment adecuado dependiendo de los datos que se van a consultar.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        bundle = getIntent().getExtras();
        String title = bundle.getString("title");
        id = bundle.getInt("id");
        idTemporada = bundle.getInt("idTemporada");
        content = bundle.getString("content");
        setTitle(title);
        loadContentDetail();

    }

    /**
     * Carga el fragment adecuado para mostrar los datos solicitados por la activity que lanza a ésta. Puede se:
     * - Partidos
     * - Transacciones
     * - Cuotas de jugador
     */
    private void loadContentDetail() {
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (content) {
            case "partido":
                fragment = PlaceholderFragmentPartido.newInstance(id, false);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.layout_content_detail, fragment)
                        .commit();
                break;
            case "transaccion":
                fragment = PlaceholderFragmentTransaccion.newInstance(id);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.layout_content_detail, fragment)
                        .commit();
                break;
            case "jugadorCuota":
                fragment = PlaceholderFragmentCuotasJugador.newInstance(id, idTemporada);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.layout_content_detail, fragment)
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
