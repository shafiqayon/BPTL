package ayon.rahman.shafiqur.bptl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class dash extends AppCompatActivity {
    Button customer, dailywork, clientView, alert;
    String usernamepassed = "empty";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        usernamepassed = getIntent().getExtras().getString("username");
       /* Toast.makeText(dash.this, usernamepassed, Toast.LENGTH_LONG).show();*/
        customer = (Button) findViewById(R.id.customer);
        dailywork = (Button) findViewById(R.id.dailywork);
        clientView = (Button) findViewById(R.id.clientview);
        alert = (Button) findViewById(R.id.datepicker);
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(dash.this, Client_Setup.class);
                i.putExtra("username", usernamepassed);
                startActivity(i);
            }
        });
        dailywork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dash.this, dailyworkprogram.class);
                i.putExtra("username", usernamepassed);
                startActivity(i);
            }
        });
        clientView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dash.this, clientView.class);
                i.putExtra("username", usernamepassed);
                startActivity(i);
            }
        });
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dash.this, clientViewByDate.class);
                i.putExtra("username", usernamepassed);
                startActivity(i);
            }
        });

    }
}
