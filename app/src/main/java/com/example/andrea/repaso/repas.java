package com.example.andrea.repaso;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class repas extends Activity {

    Button button;
    TextView textView;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repas);

        button= (Button) findViewById(R.id.boton);
        textView= (TextView) findViewById(R.id.tv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = count + 1;
                if (count < 6) {
                    textView.setText("" + count);
                }

                if (count == 5) {
                    Intent i = new Intent(getApplicationContext(), formulario.class);
                    startActivity(i);
                }

                //crear la notificacion
                Notification.Builder mBuilder = new Notification.Builder(getApplicationContext());
                mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                mBuilder.setContentTitle("Pulsaciones");
                mBuilder.setContentText(count + " pulsacion/es");

                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);

                mNotificationManager.notify(1, mBuilder.build());//envio de la notificacion con ID 1
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_repas, menu);
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

    @Override
    protected void onResume() {

        super.onResume();
        this.onCreate(null);
    }
}
