package ayon.rahman.shafiqur.bptl;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class dailyworkprogram extends AppCompatActivity {
    private ArrayList<String> clientListForSpinner = new ArrayList<String>();
    private ArrayList<String> workstationnamelistforspinner = new ArrayList<String>();
    private ArrayList<ArrayList<String>> serviceName = new ArrayList<ArrayList<String>>();

    Button starttime, endtime;
    TextView startTV, endTV;
    EditText Remarks, hourset;
    RequestQueue requestQueue;
    String[] servicenamearray;
    Spinner workstationnamespinner, mediumoftransportspinner, clientSpinner, servicenamespinner;
    String selectedworkspinner, selectedmot, usernamepassed, serverdailywork = "http://192.168.1.107/dailywork.php",
            serviceselected = "", servernameforservice = "http://192.168.1.107/service.php",
            sernameforclientinfo = "http://192.168.1.107/clientnametowork.php", clients = "", clientselected = "",
            temp = null, temp2 = null, servernameforwork = "http://192.168.1.107/wsnames.php";
    ArrayAdapter<String> clientAdapter, serviceNameAdapter;
    public long endh, endm, starth, startm;
    long startmil;
    Button savebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailyworkprogram);
        usernamepassed = getIntent().getExtras().getString("username");
       /* Toast.makeText(dailyworkprogram.this, "User name passed in daily work :" + usernamepassed, Toast.LENGTH_SHORT).show();*/
        starttime = (Button) findViewById(R.id.startTimeBtn);
        endtime = (Button) findViewById(R.id.endButton);
        startTV = (TextView) findViewById(R.id.startTimetextView);
        endTV = (TextView) findViewById(R.id.endTimetextView);
        Remarks = (EditText) findViewById(R.id.remarks);
      
        workstationnamespinner = (Spinner) findViewById(R.id.ws);
        mediumoftransportspinner = (Spinner) findViewById(R.id.mot);
        clientSpinner = (Spinner) findViewById(R.id.clientNameInSpinner);
        servicenamespinner = (Spinner) findViewById(R.id.serviceNamespinner);

        requestQueue = Volley.newRequestQueue(dailyworkprogram.this);

        savebutton = (Button) findViewById(R.id.savework);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, sernameforclientinfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        temp = null;
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        temp = (String) jsonObject.get("CLIENT_NAME");
                        clientListForSpinner.add(temp);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Toast.makeText(dailyworkprogram.this, clients, Toast.LENGTH_LONG).show();

                clientAdapter = new ArrayAdapter<String>(dailyworkprogram.this, android.R.layout.simple_spinner_item, clientListForSpinner);
                clientAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                clientSpinner.setAdapter(clientAdapter);
                clientAdapter.notifyDataSetChanged();
                clientSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("item", (String) parent.getItemAtPosition(position));
                        clientselected = parent.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error", error.toString());
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user", usernamepassed);
                return params;
            }
        };
        requestQueue.add(stringRequest);


        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(dailyworkprogram.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        starth = hourOfDay;
                        startm = minute;
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
                        endh = hourOfDay;
                        endm = minute;
                        endTV.setText(hourOfDay + ":" + minute);
                        startmil = (TimeUnit.HOURS.toMillis(endh) + TimeUnit.MINUTES.toMillis(endm)) - (TimeUnit.HOURS.toMillis(starth) + TimeUnit.MINUTES.toMillis(startm));
                        Toast.makeText(dailyworkprogram.this, "Time Duration " + TimeUnit.MILLISECONDS.toMinutes(startmil) / 60 + ":" + TimeUnit.MILLISECONDS.toMinutes(startmil) % 60, Toast.LENGTH_LONG).show();

                    }
                }, hour, minute, false);
                timePickerDialog.setTitle("Select End Time");
                timePickerDialog.show();


            }
        });


        //setting up the medium of transport
        String[] mots = new String[]{"Bus", "Rickshaw", "CNG", "Taxicab", "Train"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mots);
        mediumoftransportspinner.setAdapter(adapter3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mediumoftransportspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("selectedmot", (String) parent.getItemAtPosition(position));
                selectedmot = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(servernameforservice, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject object = (JSONObject) jsonArray.get(i);
                        temp = null;
                        temp2 = null;
                        temp = (String) object.get("JOB_ID");
                        temp2 = (String) object.get("JOB_NAME");
                        ArrayList<String> tempRow = new ArrayList<String>();
                        tempRow.add(temp);
                        tempRow.add(temp2);
                        serviceName.add(tempRow);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                servicenamearray = new String[serviceName.size()];
                for (int i = 0; i < serviceName.size(); i++) {
                    servicenamearray[i] = serviceName.get(i).get(1);
                    Log.e("from 2d array ", i + " " + serviceName.get(i).get(1));
                }
                for (int i = 0; i < servicenamearray.length; i++) {
                    Log.e("Service Name", servicenamearray[i]);
                }

                //setting up the spinner
                serviceNameAdapter = new ArrayAdapter<String>(dailyworkprogram.this, android.R.layout.simple_spinner_item, servicenamearray);
                serviceNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                servicenamespinner.setAdapter(serviceNameAdapter);
                serviceNameAdapter.notifyDataSetChanged();
                servicenamespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("service selected", (String) parent.getItemAtPosition(position));
                        serviceselected = parent.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        JsonArrayRequest jsonArrayRequestworkstation = new JsonArrayRequest(servernameforwork, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        temp = null;
                        JSONObject object = (JSONObject) jsonArray.get(i);
                        temp = object.getString("WS_NAME");
                        workstationnamelistforspinner.add(temp);
                        Log.e("WS_NAME", temp);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //setting up the spinner for workstations
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(dailyworkprogram.this, android.R.layout.simple_spinner_item, workstationnamelistforspinner);
                workstationnamespinner.setAdapter(adapter3);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("ws error", volleyError.toString());
            }
        });


        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String starttime = starth + ":" + startm;
                final String endtime = endh + ":" + endm;
                startmil = (TimeUnit.HOURS.toMillis(endh) + TimeUnit.MINUTES.toMillis(endm)) - (TimeUnit.HOURS.toMillis(starth) + TimeUnit.MINUTES.toMillis(startm));

                final double hours = TimeUnit.MILLISECONDS.toHours(startmil);
                Log.e("Converted", String.valueOf(hours));

                Toast.makeText(dailyworkprogram.this, "in long" + hours, Toast.LENGTH_LONG).show();
                final String remarks = String.valueOf(Remarks.getText());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, serverdailywork, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("user", usernamepassed);
                        params.put("clientName", clientselected);
                        params.put("servicename", serviceselected);
                        params.put("hrs", String.valueOf(hours));
                        params.put("startTime", starttime);
                        params.put("endTime", endtime);
                        params.put("mot", selectedmot);
                        params.put("wsname", selectedworkspinner);
                        params.put("remarks", remarks);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);

            }
        });
        requestQueue.add(jsonArrayRequestworkstation);
        requestQueue.add(jsonArrayRequest);

    }
}
