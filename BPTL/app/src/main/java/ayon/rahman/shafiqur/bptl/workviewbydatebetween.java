package ayon.rahman.shafiqur.bptl;

import android.os.Bundle;
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
    String startDatePassed, endDatePassed, CLIENT_NAME, ENTER_DATE, usernamepassed;
    RequestQueue requestQueue;
    String gettingClientNames = "http://103.229.84.171/clientnamebetween.php";
    public ArrayList<String> clientArray = new ArrayList<String>();
    public ArrayList<String> onlyClient = new ArrayList<String>();
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
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        CLIENT_NAME = (String) jsonObject.get("CLIENT_NAME");
                        ENTER_DATE = (String) jsonObject.get("ENTER_DT");
                        clientArray.add(CLIENT_NAME + "\nOn Date : " + ENTER_DATE);
                        onlyClient.add(CLIENT_NAME);

                        final String[] client = clientArray.toArray(new String[clientArray.size()]);
                        adapter = new ArrayAdapter<String>(workviewbydatebetween.this, R.layout.custom_textview, client);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(workviewbydatebetween.this, onlyClient.get(position), Toast.LENGTH_LONG).show();
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
}
