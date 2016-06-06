package ayon.rahman.shafiqur.bptl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.util.HashMap;
import java.util.Map;

public class workupdateentry extends AppCompatActivity {

    Button starttime, endtime;
    TextView startTV, endTV;
    EditText Remarks, hourset;

    String[] servicenamearray;
    Spinner workstationnamespinner, mediumoftransportspinner, clientSpinner, servicenamespinner;


    String sworkstationnamespinner, smediumoftransportspinner, sclientSpinner, sservicenamespinner, sstarttime, sendtime, sPRE_JOB_REF_NO;


    String selectedworkspinner, PRE_JOB_REF_NO, selectedmot, refNoPassed, usernamepassed, serverdailywork = "http://103.229.84.171/dailywork.php",
            serviceselected = "", servernameforservice = "http://103.229.84.171/service.php",
            sernameforclientinfo = "http://103.229.84.171/clientnametowork.php", clients = "", clientselected = "",
            temp = null, temp2 = null, servernameforwork = "http://103.229.84.171/wsnames.php";
    String previousDate = "http://103.229.84.171/updateDataShow.php";
    ArrayAdapter<String> clientAdapter, serviceNameAdapter;
    public long endh, endm, starth, startm;
    public int endmonth, enddate, endyear, startmonth, startdate, startyear;
    long startmil;
    Button savebutton;
    RequestQueue requestQueue;


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
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.isNull("PRE_JOB_REF_NO") == false) {

                    } else {

                    }
                    if (jsonObject.isNull("CLIENT_ID") == false) {

                    } else {

                    }
                    if (jsonObject.isNull("JOB_ID") == false) {

                    } else {

                    }
                    if (jsonObject.isNull("REMARKS") == false) {

                    } else {

                    }
                    if (jsonObject.isNull("START_TIME") == false) {
                      /*  startTV.setText((String) jsonObject.get("START_TIME"));*/
                        startTV.setText("Could nt find Start");
                    } else {
                        startTV.setText("Could nt find Start");
                    }
                    if (jsonObject.isNull("MOT") == false) {

                    } else {

                    }
                    if (jsonObject.isNull("FWS_CODE") == false) {

                    } else {

                    }
                    if (jsonObject.isNull("END_TIME") == false) {
                      /*  endTV.setText((String) jsonObject.get("END_TIME"));*/
                        endTV.setText("Could nt find end");
                    } else {
                        endTV.setText("Could nt find end");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


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
                params.put("refno", refNoPassed);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
