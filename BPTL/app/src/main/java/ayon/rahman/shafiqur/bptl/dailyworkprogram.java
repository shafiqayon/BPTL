package ayon.rahman.shafiqur.bptl;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class dailyworkprogram extends AppCompatActivity {
    private ArrayList<String> clientListForSpinner = new ArrayList<String>();
    Button starttime, endtime;
    TextView startTV, endTV;
    Spinner workstationnamespinner, mediumoftransportspinner;
    String selectedworkspinner, selectedmot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailyworkprogram);
        starttime = (Button) findViewById(R.id.startTimeBtn);
        endtime = (Button) findViewById(R.id.endButton);
        startTV = (TextView) findViewById(R.id.startTimetextView);
        endTV = (TextView) findViewById(R.id.endTimetextView);
        workstationnamespinner = (Spinner) findViewById(R.id.ws);
        mediumoftransportspinner = (Spinner) findViewById(R.id.mot);
        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(dailyworkprogram.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        startTV.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, false);
                timePickerDialog.setTitle("Select Start Time");
                timePickerDialog.show();
            }
        });

        endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(dailyworkprogram.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        endTV.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, false);
                timePickerDialog.setTitle("Select End Time");
                timePickerDialog.show();
            }
        });
        //workstation spinner

        String[] workstations = new String[]{"Dhaka", "Savar"};

        //setting up the address type spinner
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, workstations);

        workstationnamespinner.setAdapter(adapter2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        workstationnamespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("selectedworkspinner", (String) parent.getItemAtPosition(position));
                selectedworkspinner = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //setting up the medium of transport

        String[] mots = new String[]{"Bus", "Rickshaw", "CNG", "Taxicab", "Train"};

        //setting up the address type spinner
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mots);

        mediumoftransportspinner.setAdapter(adapter3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mediumoftransportspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("selectedmot", (String) parent.getItemAtPosition(position));
                selectedworkspinner = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
