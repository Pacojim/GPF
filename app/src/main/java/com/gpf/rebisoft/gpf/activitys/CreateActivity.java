package com.gpf.rebisoft.gpf.activitys;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.fragments.PlaceHolderFragmentCrearPartido;
import com.gpf.rebisoft.gpf.fragments.PlaceholderFragmentCrearTransaccion;

/**
 * Activity para mostrar fragment de creación de datos.
 */
public class CreateActivity extends AppCompatActivity {
    /** Bundle del que se obtienen los datos de la Activity que a lanzado a ésta */
    private Bundle bundle;
    String content;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private int idTemporada;
    private ActionBar actionBar;

    /**
     * Método sobreescrito que se lanza al crear la avtivity. Inicializa las variables a usar,
     * obtiene el action bar y llama a un método para establecer el Fragment adecuado dependiendo del
     * parámetro obternido de la Activity que ha lanzado a ésta.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        bundle = getIntent().getExtras();
        idTemporada = bundle.getInt("idTemporada");
        content = bundle.getString("content");
        fragmentManager = getSupportFragmentManager();
        loadContentCreate();
    }

    /**
     * Establece el Fragment adecuado según el parámetro obtenido de la Activity que lanzó a ésta.
     * Los fragment pueden ser:
     * - Crear partido.
     * - Crear Transacción
     */
    private void loadContentCreate() {
        switch (content) {
            case "partido":
//                actionBar.setShowHideAnimationEnabled(true);
                setTitle("Crear nuevo partido");
                fragment = PlaceHolderFragmentCrearPartido.newInstance(idTemporada);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.layout_content_create, fragment)
                        .addToBackStack("inicio")
                        .commit();
                break;
            case "transaccion":
                setTitle("Crear nueva transacción");
                fragment = PlaceholderFragmentCrearTransaccion.newInstance(0);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.layout_content_create, fragment)
                        .commit();
                break;

        }
    }

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
