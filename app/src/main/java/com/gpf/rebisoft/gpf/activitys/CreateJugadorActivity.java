package com.gpf.rebisoft.gpf.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.gpf.rebisoft.gpf.R;
import com.gpf.rebisoft.gpf.clasesbase.Jugador;
import com.gpf.rebisoft.gpf.database.GPFDataSource;
import com.gpf.rebisoft.gpf.utils.Utilidades;

import org.hybridsquad.android.library.CropHandler;
import org.hybridsquad.android.library.CropHelper;
import org.hybridsquad.android.library.CropParams;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

/**

 */
public class CreateJugadorActivity extends AppCompatActivity implements CropHandler {

    private ImageView imageView;
    private EditText nombre;
    private EditText apellido1;
    private EditText apellido2;
    private EditText dorsal;
    private EditText apodo;
    private RatingBar calidad;
    private CheckBox activo;
    private CheckBox socio;
    private Button botonCrear;
    private CropParams mCropParams;
    private Bitmap imagen = null;
    private GPFDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_jugador);

        setTitle("Crear nuevo jugador");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.imageViewCrearJugador);
        nombre = (EditText) findViewById(R.id.editTextNombreCrearJugador);
        apellido1 = (EditText) findViewById(R.id.editTextApellido1CrearJugador);
        apellido2 = (EditText) findViewById(R.id.editTextApellido2CrearJugador);
        dorsal = (EditText) findViewById(R.id.editTextDorsalCrearJugador);
        apodo = (EditText) findViewById(R.id.editTextApodoCrearJugador);
        calidad = (RatingBar) findViewById(R.id.ratingBarCalidadCrearJugador);
        activo = (CheckBox) findViewById(R.id.checkBoxActivoCrearJugador);
        socio = (CheckBox) findViewById(R.id.checkBoxSocioCrearJugador);
        botonCrear = (Button) findViewById(R.id.buttonCrearJugador);
        mCropParams = new CropParams();

        mCropParams.scale = true;

        dataSource = new GPFDataSource(this);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CropHelper.buildCaptureIntent(mCropParams.uri);
                startActivityForResult(intent, CropHelper.REQUEST_CAMERA);
            }
        });

        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            //String nombre, String apodo, String apellido1, String apellido2, int dorsal, int activo, int calidad, String foto, int socio
            public void onClick(View v) {
                String nombreImage = "";
                if (imagen != null) {
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    File directory = new File(extStorageDirectory + Utilidades.PATH_IMAGES_APP);
                    directory.mkdirs();
                    nombreImage = "jugador_" + Calendar.getInstance().getTimeInMillis() + ".PNG";
                    File file = new File(directory, nombreImage);
                    try {
                        FileOutputStream outStream = new FileOutputStream(file);
                        imagen.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                        outStream.flush();
                        outStream.close();
                    } catch (Exception ex) {
                        Toast.makeText(CreateJugadorActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                String nombreText = nombre.getText().toString();
                String apellido1Text = apellido1.getText().toString();
                String apellido2Text = apellido1.getText().toString();
                String dorsalText = dorsal.getText().toString();
                String apodoText = apodo.getText().toString();
                if(nombreText.trim().equals("")) {
                    nombre.setError("Nombre es obligatorio");
                } else if (apellido1Text.trim().equals("")){
                    apellido1.setError("Primer apellido es obligatorio");
                } else if (dorsalText.toString().trim().equals("")){
                    dorsal.setError("Dorsal es obligatorio");
                }else {
                    Jugador jugador = new Jugador(nombreText, apodoText,
                            apellido1Text, apellido2Text, Integer.parseInt(dorsalText),
                            activo.isChecked() ? 1 : 0, ((int) (calidad.getRating() * 2)), nombreImage, socio.isChecked() ? 1 : 0);
                    dataSource.saveJugador(jugador);
                    finish();
                }

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropHelper.handleResult(this, requestCode, resultCode, data);
    }


    @Override
    public Activity getContext() {
        return this;
    }

    @Override
    public void onPhotoCropped(Uri uri) {
        imagen = CropHelper.decodeUriAsBitmap(getContext(), uri);
        imageView.setImageBitmap(imagen);

    }

    @Override
    public void onCropCancel() {

    }

    @Override
    public void onCropFailed(String message) {
        System.out.println("Error");
    }

    @Override
    public CropParams getCropParams() {
        mCropParams = new CropParams();
        return mCropParams;
    }

    @Override
    protected void onDestroy() {
        if (this.getCropParams() != null)
            CropHelper.clearCachedCropFile(this.getCropParams().uri);
        super.onDestroy();
    }
}
