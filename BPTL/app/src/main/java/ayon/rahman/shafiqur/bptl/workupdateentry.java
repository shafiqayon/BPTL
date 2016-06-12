package ayon.rahman.shafiqur.bptl;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class workupdateentry extends AppCompatActivity {

    Button starttime, endtime;
    TextView startTV, endTV;
    EditText Remarks, hourset;

    String[] servicenamearray;
    Spinner workstationnamespinner, mediumoftransportspinner, clientSpinner, servicenamespinner;


    String sworkstationnamespinner, smediumoftransportspinner, sclientSpinner, sservicenamespinner, sstarttime, sendtime, sPRE_JOB_REF_NO, sclientId, mot;


    String selectedworkspinner, PRE_JOB_REF_NO, selectedmot, refNoPassed, usernamepassed, serverdailywork = "http://103.229.84.171/dailywork.php",
            serviceselected = "", servernameforservice = "http://103.229.84.171/service.php",
            sernameforclientinfo = "http://103.229.84.171/clientnametoworkid.php", clients = "", clientselected = "", clientselectedid = "",
            temp = null, temp2 = null, servernameforwork = "http://103.229.84.171/wsnames.php";
    String previousDate = "http://103.229.84.171/updateDataShow.php";
    ArrayAdapter<String> clientAdapter, serviceNameAdapter;
    public long endh, endm, starth, startm;
    public int endmonth, enddate, endyear, startmonth, startdate, startyear;
    long startmil;
    Button savebutton;
    RequestQueue requestQueue;
    private ArrayList<String> clientListName = new ArrayList<String>();
    private ArrayList<String> clientListId = new ArrayList<String>();

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
                        } else {

                        }
                        if (jsonObject.isNull("REMARKS") == false) {
                            Log.e("REMARKS", String.valueOf(jsonObject.get("REMARKS")));
                        } else {

                        }
                        if (jsonObject.isNull("START_TIME") == false) {
                            startTV.setText((String) jsonObject.get("START_TIME"));
                       /* startTV.setText("Could nt find Start");*/
                            Log.e("START_TIME", String.valueOf(jsonObject.get("START_TIME")));
                        } else {
                            startTV.setText("Could nt find Start");
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
                        } else {

                        }
                        if (jsonObject.isNull("END_TIME") == false) {
                            endTV.setText((String) jsonObject.get("END_TIME"));
                      /*  endTV.setText("Could nt find end");*/
                            Log.e("END_TIME", String.valueOf(jsonObject.get("END_TIME")));
                        } else {
                            endTV.setText("Could nt find end");
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

        //setting up the medium of transport
       /* String[] mots = new String[]{"Train", "Rickshaw", "CNG", "Taxicab", "Bus"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mots);
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
        });*/


    } // end of on create
}
