package ayon.rahman.shafiqur.bptl;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class alerttest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerttest);
        AlertDialog alBuilder = new AlertDialog.Builder(alerttest.this).create();
        alBuilder.setMessage("Please Choose an Option");
        alBuilder.setTitle("Client");
        alBuilder.setIcon(R.drawable.ic_launcher);
        alBuilder.setCancelable(true);
        alBuilder.setButton(DialogInterface.BUTTON1, "o k 1 ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Clicked on View", Toast.LENGTH_SHORT).show();
            }
        });
        alBuilder.setButton(DialogInterface.BUTTON2, "o k 2", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Clicked on View", Toast.LENGTH_SHORT).show();
            }
        });
        alBuilder.show();
//        alBuilder.setNeutralButton("View", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(), "Clicked on View", Toast.LENGTH_SHORT).show();
//            }
//        });
//        alBuilder.setNeutralButton("Edit", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(), "Edit", Toast.LENGTH_SHORT).show();
//            }
//        });


    }
}
