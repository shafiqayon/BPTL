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

public class workupdateentry extends AppCompatActivity {

    public long endh, endm, starth, startm;
    public int endmonth, enddate, endyear, startmonth, startdate, startyear;
    Button starttime, endtime;
    TextView startTV, endTV;
    EditText Remarks, hourset;
    String[] servicenamearray;
    Spinner workstationnamespinner, mediumoftransportspinner, clientSpinner, servicenamespinner;
    String sworkstationnamespinner, smediumoftransportspinner, sclientSpinner, sremarks, sstarttime, sendtime, sPRE_JOB_REF_NO, sclientId, mot, wsCode, jobid;
    String selectedworkspinner, PRE_JOB_REF_NO, selectedmot, refNoPassed, usernamepassed, serverdailywork = "http://103.229.84.171/dailywork.php",
            serviceselected = "", servernameforservice = "http://103.229.84.171/service.php",
            sernameforclientinfo = "http://103.229.84.171/clientnametoworkid.php", clients = "", clientselected = "", clientselectedid = "",
            temp = null, temp2 = null, servernameforwork = "http://103.229.84.171/wsnamesid.php", previousDate = "http://103.229.84.171/updateDataShow.php", updateServer = "http://103.229.84.171/workEntryUpdate.php";
    ArrayAdapter<String> clientAdapter, serviceNameAdapter;
    long startmil;
    Button savebutton;
    RequestQueue requestQueue;
    private ArrayList<String> clientListName = new ArrayList<String>();
    private ArrayList<String> clientListId = new ArrayList<String>();
    private ArrayList<String> workstationnamelistforspinner = new ArrayList<String>();
    private ArrayList<String> workstationidlistforspinner = new ArrayList<String>();
    private ArrayList<String> serviceName = new ArrayList<String>();
    private ArrayList<String> serviceNameId = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workupdateentry);
        refNoPassed = getIntent().getExtras().getString("refno");
        requestQueue = Volley.newRequestQueue(workupdateentry.this);
        starttime = (Button) findViewById(R.id.startTimeBtnu);
        endtime = (Button) findViewById(R.id.endButtonu);
        startTV = (TextView) findViewById(R.id.startTimetextViewu);
        endTV = (TextView) findViewById(R.id.endTimetextViewu);
        Remarks = (EditText) findViewById(R.id.remarksu);
        savebutton = (Button) findViewById(R.id.saveworku);

        workstationnamespinner = (Spinner) findViewById(R.id.wsu);
        mediumoftransportspinner = (Spinner) findViewById(R.id.motu);
        clientSpinner = (Spinner) findViewById(R.id.clientNameInSpinneru);
        servicenamespinner = (Spinner) findViewById(R.id.serviceNamespinneru);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, previousDate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("workupdateentry ", response);
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Log.e("Json array length", String.valueOf(jsonArray.length()));
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                   /* JSONObject jsonObject = new JSONObject(response);*/
                        Log.e("workupdate entry Json", String.valueOf(jsonObject));
                        if (jsonObject.isNull("PRE_JOB_REF_NO") == false) {
                            Log.e("job ref no", String.valueOf(jsonObject.get("PRE_JOB_REF_NO")));
                        } else {

                        }
                        if (jsonObject.isNull("CLIENT_ID") == false) {
                            Log.e("CLIENT_ID", String.valueOf(jsonObject.get("CLIENT_ID")));
                            sclientId = String.valueOf(jsonObject.get("CLIENT_ID"));
                        } else {

                        }
                        if (jsonObject.isNull("JOB_ID") == false) {
                            Log.e("JOB_ID", String.valueOf(jsonObject.get("JOB_ID")));
                            jobid = String.valueOf(jsonObject.get("JOB_ID"));
                        } else {

                        }
                        if (jsonObject.isNull("REMARKS") == false) {
                            sremarks = String.valueOf(jsonObject.get("REMARKS"));
                            Remarks.setText(sremarks);
                        } else {

                        }
                        if (jsonObject.isNull("START_TIME") == false) {
                            sstarttime = (String) jsonObject.get("START_TIME");
                            startTV.setText(sstarttime);

                        } else {
                            startTV.setText("Could not find Start Time");
                        }
                        if (jsonObject.isNull("MOT") == false) {
                            Log.e("MOT", String.valueOf(jsonObject.get("MOT")));
                            mot = (String) jsonObject.get("MOT");
                            //setting up the medium of transport
                            String[] mots = new String[]{"Train", "Rickshaw", "CNG", "Taxicab", "Bus"};
                            ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(workupdateentry.this, android.R.layout.simple_spinner_item, mots);
                            mediumoftransportspinner.setAdapter(adapter3);
                            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            int previoslySelectedMot = adapter3.getPosition(mot);
                            Log.e("mot in spinner", "Medium Of Transport " + previoslySelectedMot);
                            mediumoftransportspinner.setSelection(previoslySelectedMot);
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
                        } else {

                        }
                        if (jsonObject.isNull("FWS_CODE") == false) {
                            Log.e("FWS_CODE", String.valueOf(jsonObject.get("FWS_CODE")));
                            wsCode = String.valueOf(jsonObject.get("FWS_CODE"));

                        } else {

                        }
                        if (jsonObject.isNull("END_TIME") == false) {

                            sendtime = (String) jsonObject.get("END_TIME");
                            endTV.setText(sendtime);
                            Log.e("END_TIME", String.valueOf(jsonObject.get("END_TIME")));
                        } else {
                            endTV.setText("Could not find end time");
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error", " preset data" + error.toString());
            }
        }

        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("refno", refNoPassed);
                return params;
            }
        };
        requestQueue.add(stringRequest);


        //client name spinner
        StringRequest stringRequestClient = new StringRequest(Request.Method.POST, sernameforclientinfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        temp = null;
                        temp2 = null;
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        temp = (String) jsonObject.get("CLIENT_NAME");
                        clientListName.add(temp);
                        temp2 = (String) jsonObject.get("CLIENT_ID");
                        clientListId.add(temp2);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                clientAdapter = new ArrayAdapter<String>(workupdateentry.this, android.R.layout.simple_spinner_item, clientListName);
                clientAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            /*    Log.e("id get", sclientId);*/

                System.out.println("index of client" + clientListId.indexOf(sclientId));
                System.out.println("index of client name " + clientListName.get(clientListId.indexOf(sclientId)));
                System.out.println("index of client big " + clientAdapter.getPosition(clientListName.get(clientListId.indexOf(sclientId))));


          /*      System.out.println("selection was"+clientAdapter.getPosition(clientListName.get(clientListId.indexOf(sclientId))));*/
                clientSpinner.setAdapter(clientAdapter);
                clientAdapter.notifyDataSetChanged();
                clientSpinner.setSelection(clientAdapter.getPosition(clientListName.get(clientListId.indexOf(sclientId))));
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
                Log.e("Volley E client ", error.toString());
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user", "S-001");
                return params;
            }
        };

        requestQueue.add(stringRequestClient);


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
                        serviceName.add(temp2);
                        serviceNameId.add(temp);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                //setting up the spinner
                serviceNameAdapter = new ArrayAdapter<String>(workupdateentry.this, android.R.layout.simple_spinner_item, serviceName);
                serviceNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                servicenamespinner.setAdapter(serviceNameAdapter);
              /* *//* serviceNameAdapter.notifyDataSetChanged();*//*
                Log.e("Jiob id get", ""+jobid);
                Log.e("Jiob id get in", ""+serviceName.get(serviceNameId.indexOf(jobid)));
               *//* Log.e("Service code","Previously selected"+serviceNameAdapter.getPosition(serviceName.get(serviceNameId.indexOf(jobid))));*/
                servicenamespinner.setSelection(serviceNameAdapter.getPosition(serviceName.get(serviceNameId.indexOf(jobid))));
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
                        temp2 = null;
                        JSONObject object = (JSONObject) jsonArray.get(i);
                        temp = object.getString("WS_NAME");
                        temp2 = object.getString("WS_CODE");
                        workstationnamelistforspinner.add(temp);
                        workstationidlistforspinner.add(temp2);
                       /* Log.e("WS_NAME", temp);
                        Log.e("ws_code", temp2);*/
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //setting up the spinner for workstations
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(workupdateentry.this, android.R.layout.simple_spinner_item, workstationnamelistforspinner);
                workstationnamespinner.setAdapter(adapter3);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                /*clientAdapter.getPosition(clientListName.get(clientListId.indexOf(sclientId)))*/
                workstationnamespinner.setSelection(adapter3.getPosition(workstationnamelistforspinner.get(workstationidlistforspinner.indexOf(wsCode))));
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

        requestQueue.add(jsonArrayRequestworkstation);
        requestQueue.add(jsonArrayRequest);


        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(workupdateentry.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        starth = hourOfDay;
                        startm = minute;
                        sstarttime = hourOfDay + ":" + minute;
                        startTV.setText(sstarttime);

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


                TimePickerDialog timePickerDialog = new TimePickerDialog(workupdateentry.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        endh = hourOfDay;
                        endm = minute;
                        sendtime = hourOfDay + ":" + minute;
                        endTV.setText(sendtime);
                        startmil = (TimeUnit.HOURS.toMillis(endh) + TimeUnit.MINUTES.toMillis(endm)) - (TimeUnit.HOURS.toMillis(starth) + TimeUnit.MINUTES.toMillis(startm));
                        Toast.makeText(workupdateentry.this, "Time Duration " + TimeUnit.MILLISECONDS.toMinutes(startmil) / 60 + ":" + TimeUnit.MILLISECONDS.toMinutes(startmil) % 60, Toast.LENGTH_LONG).show();

                    }
                }, hour, minute, false);
                timePickerDialog.setTitle("Select End Time");
                timePickerDialog.show();


            }
        });
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(workupdateentry.this, "Client Selected" + clientselected + " Service Type " + serviceselected
                        + " Start Time " + sstarttime + " End Time" + sendtime + " MOT " + selectedmot + "work station " + selectedworkspinner, Toast.LENGTH_LONG).show();
                Log.e("Send", "Client Selected" + clientselected + " Service Type " + serviceselected
                        + " Start Time " + sstarttime + " End Time" + sendtime + " MOT " + selectedmot + " work station " + selectedworkspinner + " Remarks " +
                        "" + sremarks + " Reference no" + refNoPassed);

                //sending volley post data to server for update

                sremarks = String.valueOf(Remarks.getText());
                StringRequest udpate = new StringRequest(Request.Method.POST, updateServer, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Reply ", "From server " + response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", " preset data" + error.toString());
                    }
                }

                ) {/*
                    $clientName = $_POST['clientName'];
                    $servicename = $_POST['servicename'];
                    $startT = $_POST['startTime'];
                    $endT = $_POST['endTime'];
                    $mot = $_POST['mot'];
                    $workstationname = $_POST['wsname'];
                    $remarks = $_POST['remarks'];
                    $referenceNo = $_POST['referenceNo'];*/
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("clientName", clientselected);
                        params.put("servicename", serviceselected);
                        params.put("startTime", sstarttime);
                        params.put("endTime", sendtime);
                        params.put("mot", selectedmot);
                        params.put("wsname", selectedworkspinner);
                        params.put("remarks", sremarks);
                        params.put("referenceNo", refNoPassed);
                        return params;
                    }
                };

                requestQueue.add(udpate);
            }

        });

    } // end of on create
}
