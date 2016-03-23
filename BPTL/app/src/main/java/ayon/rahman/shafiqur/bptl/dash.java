package ayon.rahman.shafiqur.bptl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dash extends AppCompatActivity {
    Button customer, dailywork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        customer = (Button) findViewById(R.id.customer);
        dailywork = (Button) findViewById(R.id.dailywork);
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dash.this, Client_Setup.class);
                startActivity(i);
            }
        });
        dailywork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dash.this, dailyworkprogram.class);
                startActivity(i);
            }
        });


    }
}
