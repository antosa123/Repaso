package com.example.andrea.repaso;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class formulario extends Activity {

    TextView tv_name,pregunta;
    EditText name;
    Button continuar;
    Switch sino;
    Spinner spinner, spinner2;

    ArrayAdapter<String> provincias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        tv_name= (TextView) findViewById(R.id.tv_nombre);
        name= (EditText) findViewById(R.id.nombre);
        pregunta = (TextView) findViewById(R.id.preg);
        continuar = (Button) findViewById(R.id.bContinuar);
        sino = (Switch) findViewById(R.id.bSwich);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinnerLocalidades);

        //ARRAY PROVINCIAS CON SU ADAPTADOR
        String array_provincias[] = getResources().getStringArray(R.array.provincias);
        //adaptador para mostrar las provincias
        provincias = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,array_provincias);
        //para que salga el desplegable hacia abajo
        provincias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(provincias);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //carga las localidades segun la posicion de las provincias en el array
                TypedArray localidad = getResources().obtainTypedArray(R.array.array_provincia_a_localidades);
                int idLocalidad = localidad.getResourceId(position, 0);
                ArrayAdapter<CharSequence> localidades = ArrayAdapter.createFromResource(formulario.this,idLocalidad,android.R.layout.simple_spinner_item);
                localidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(localidades);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //accion sobre la pregunta de notificaciones
        if (sino.isChecked()){

            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //crear la notificacion
                    Notification.Builder notificacion = new Notification.Builder(getApplicationContext());
                    notificacion.setSmallIcon(R.mipmap.ic_launcher);
                    notificacion.setContentTitle("Formulario");
                    notificacion.setContentText("" + name);

                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);

                    mNotificationManager.notify(1, notificacion.build());//envio de la notificacion con ID 1

                    Intent intent = new Intent(getApplicationContext(),repas.class);
                    finish();
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(),name+" ",Toast.LENGTH_SHORT).show();
                }
            });


        }else{
            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //volver al activity anterior y que siga como estaba
                    finish();

                }
            });

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
