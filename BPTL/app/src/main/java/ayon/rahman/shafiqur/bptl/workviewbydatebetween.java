package ayon.rahman.shafiqur.bptl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

public class workviewbydatebetween extends AppCompatActivity {
    public ArrayList<String> clientArray = new ArrayList<String>();
    public ArrayList<String> onlyClient = new ArrayList<String>();
    public ArrayList<String> clientIdArray = new ArrayList<String>();
    public ArrayList<String> PRE_JOB_REF_NOArray = new ArrayList<String>();
    String startDatePassed, endDatePassed, CLIENT_NAME, CLIENT_ID, ENTER_DATE, PRE_JOB_REF_NO, usernamepassed;
    RequestQueue requestQueue;
    String gettingClientNames = "http://103.229.84.171/clientnamebetween.php";
    ArrayAdapter<String> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workviewbydatebetween);
        startDatePassed = getIntent().getExtras().getString("startDate");
        endDatePassed = getIntent().getExtras().getString("endDate");
        usernamepassed = getIntent().getExtras().getString("username");
       /* Toast.makeText(workviewbydatebetween.this,"Dates Passed"+startDatePassed+" to "+endDatePassed+usernamepassed,Toast.LENGTH_LONG).show();*/
        requestQueue = Volley.newRequestQueue(workviewbydatebetween.this);
        listView = (ListView) findViewById(R.id.listView2);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, gettingClientNames, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response ", response);
                if (response.isEmpty() || response.equals("[]")) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(workviewbydatebetween.this);
                    alertDialogBuilder.setMessage("No Records Found ");

                    alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent i = new Intent(workviewbydatebetween.this, clientViewByDate.class);
                            i.putExtra("username", usernamepassed);
                            startActivity(i);
                        }
                    });

                     /* alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });*/

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                  /*  Toast.makeText(workviewbydatebetween.this,"No Records Found ",Toast.LENGTH_LONG).show();
                    Toast.makeText(workviewbydatebetween.this,"No Records Found ",Toast.LENGTH_LONG).show();
                    Toast.makeText(workviewbydatebetween.this,"No Records Found ",Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent i = new Intent(workviewbydatebetween.this, clientViewByDate.class);
                    i.putExtra("username", usernamepassed);
                    startActivity(i);*/
                }
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        CLIENT_NAME = (String) jsonObject.get("CLIENT_NAME");
                        ENTER_DATE = (String) jsonObject.get("ENTER_DT");
                        CLIENT_ID = (String) jsonObject.get("CLIENT_ID");
                        PRE_JOB_REF_NO = (String) jsonObject.get("PRE_JOB_REF_NO");
                        clientArray.add(CLIENT_NAME + "\nOn Date : " + ENTER_DATE);
                        onlyClient.add(CLIENT_NAME);
                        clientIdArray.add(CLIENT_ID);
                        PRE_JOB_REF_NOArray.add(PRE_JOB_REF_NO);

                        final String[] client = clientArray.toArray(new String[clientArray.size()]);
                        adapter = new ArrayAdapter<String>(workviewbydatebetween.this, R.layout.custom_textview, client);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                               /* Toast.makeText(workviewbydatebetween.this, onlyClient.get(position) + "id : \n"+clientIdArray.get(position)+"Ref no \n"+PRE_JOB_REF_NOArray.get(position), Toast.LENGTH_LONG).show();*/
                                Intent i = new Intent(workviewbydatebetween.this, workupdateentry.class);
                                i.putExtra("refno", PRE_JOB_REF_NOArray.get(position));
                                startActivity(i);
                            }
                        });
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
                params.put("start", startDatePassed);
                params.put("end", endDatePassed);
                params.put("user", usernamepassed);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "Refreshing List", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }
}
