package com.gpf.rebisoft.gpf.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.adapters.SpinnerToolbarAdapter;
import com.gpf.rebisoft.gpf.clasesbase.Temporada;
import com.gpf.rebisoft.gpf.database.GPFDataSource;
import com.gpf.rebisoft.gpf.fragments.PlaceHolderFragmentListViewPrincipal;

import java.util.ArrayList;

/**
 * Clase de la Activity principal de la aplicación.
 */
public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GPFDataSource dataSource;
    //    ListView listView;

    Bundle args;
    Fragment fragment;
    FragmentManager fragmentManager;

    /**
     * Variable para almacenar el ID de la temporada cuyos datos se van a mostrar
     */
    int idTemporada;

    /**
     * Variable para almacenar la opción del drawer que se tiene seleccionada
     */
    int idSectionDrawer;
    FloatingActionButton fab;

    /**
     * Almacena una lista con las temporadas que hay creadas en la aplicación.
     */
    ArrayList<Temporada> temporadas;

    /**
     * Adapter para el selector de temporada
     */
    private SpinnerToolbarAdapter<Temporada> adapterSpinner;

    /**
     * Selector de temporada
     */
    private Spinner spinnerTemporadas;

    /**
     * Método sobreescrito que se lanza al crear la activity. Inicializa las variables,
     * establece el fragment por defecto a mostrar y la posición por defecto del navigation view.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showActivityCreate();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Inicializamos el objeto para las consultas a la base de datos.
        dataSource = new GPFDataSource(this);
        // Establecemos el título por defecto de la vista
        setTitle(R.string.title_jugadores);

        //Inicializamos la lista de temporadas que gestiona en estos momentos la aplicación
        temporadas = dataSource.getTemporadas();

        // Establecemos la posición por defecto del navigation view y la guardamos
        navigationView.getMenu().getItem(0).setChecked(true);
        idSectionDrawer = R.id.drawer_jugadores;

        // Establecemos el fragment que se muestra por defecto al entrar en la aplicación
        args = new Bundle();
        fragment = PlaceHolderFragmentListViewPrincipal.newInstance(idTemporada, "jugadores");
        fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.layout_content_principal, fragment)
                .commit();
    }

    /**
     * Muestra el activity adecuado para crear elementos dependiendo de
     * los datos que estemos consultando, lo que será determinado por la posición seleccionada en el
     * menú del drawer
     */
    private void showActivityCreate() {
        if (idSectionDrawer == R.id.drawer_jugadores) {
            Intent i = new Intent(this, CreateJugadorActivity.class);
            startActivity(i);
        } else if (idSectionDrawer == R.id.drawer_partidos) {
            Intent i = new Intent(this, CreateActivity.class);
            i.putExtra("content", "partido");
            i.putExtra("idTemporada", idTemporada);
            startActivity(i);
        } else if (idSectionDrawer == R.id.drawer_caja) {
            Intent i = new Intent(this, CreateActivity.class);
            i.putExtra("content", "transaccion");
            startActivity(i);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.
        getSupportFragmentManager().popBackStack();
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Crea las opciones de menú de la action bar. Es este caso crea un selector para elegir
     * la temporada de la que se quieren consultar los datos
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        MenuItem item = menu.findItem(R.id.spinner);
        spinnerTemporadas = (Spinner) MenuItemCompat.getActionView(item);
        adapterSpinner = new SpinnerToolbarAdapter(this, temporadas);
//        final ArrayAdapter<Temporada> adapter = new ArrayAdapter<Temporada>(this,
//                android.R.layout.simple_spinner_item, temporadas);
        spinnerTemporadas.setAdapter(adapterSpinner);
        spinnerTemporadas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idTemporada = adapterSpinner.getItem(position).getId();
                setFragment();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        idSectionDrawer = item.getItemId();

        setFragment();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Establece el fragment a mostrar dependiendo de la selección del menú del drawer.
     * Siempre va a mostrar un fragment de tipo PlaceHolderFragmentListViewPrincipal que será rellenado
     * dependiendo del argumento que le pasamos en el método que crea la instancia.
     */
    private void setFragment() {
        if (idSectionDrawer == R.id.drawer_jugadores) {
            setTitle(R.string.title_jugadores);
            fab.setVisibility(View.VISIBLE);
//            args.putString(PlaceHolderFragmentListViewPrincipal.ARG_LIST, "jugadores");
            fragment = PlaceHolderFragmentListViewPrincipal.newInstance(idTemporada, "jugadores");
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.layout_content_principal, fragment)
                    .commit();
        } else if (idSectionDrawer == R.id.drawer_partidos) {
            fab.setVisibility(View.VISIBLE);
            setTitle(R.string.title_partidos);
//            args.putString(PlaceHolderFragmentListViewPrincipal.ARG_LIST, "partidos");
            fragment = PlaceHolderFragmentListViewPrincipal.newInstance(idTemporada, "partidos");
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.layout_content_principal, fragment)
                    .commit();
        } else if (idSectionDrawer == R.id.drawer_caja) {
            fab.setVisibility(View.VISIBLE);
            setTitle(R.string.title_caja);
//            args.putString(PlaceHolderFragmentListViewPrincipal.ARG_LIST, "caja");
            fragment = PlaceHolderFragmentListViewPrincipal.newInstance(idTemporada, "caja");
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.layout_content_principal, fragment)
                    .commit();
        } else if (idSectionDrawer == R.id.drawer_cuotas) {
            fab.setVisibility(View.INVISIBLE);
            setTitle(R.string.title_cuotas);
//            args.putString(PlaceHolderFragmentListViewPrincipal.ARG_LIST, "cuotas");
            fragment = PlaceHolderFragmentListViewPrincipal.newInstance(idTemporada, "cuotas");
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.layout_content_principal, fragment)
                    .commit();
        } else if (idSectionDrawer == R.id.drawer_ajustes) {
            fab.setVisibility(View.INVISIBLE);
            setTitle(R.string.title_ajustes);
//            args.putString(PlaceHolderFragmentListViewPrincipal.ARG_LIST, "ajustes");
            fragment = PlaceHolderFragmentListViewPrincipal.newInstance(idTemporada, "ajustes");
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.layout_content_principal, fragment)
                    .commit();
        }
    }

    /**
     * Método que se llama al volver a mostrarse el Activity.
     * Lo uso como forma provisional para recargar el spinner de temporadas.
     */
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        temporadas = dataSource.getTemporadas();
//        adapterSpinner = new SpinnerToolbarAdapter(this, temporadas);
//        spinnerTemporadas.setAdapter(adapterSpinner);
//    }
}
