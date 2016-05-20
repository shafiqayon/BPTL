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

public class clientView extends AppCompatActivity {
    String usernamepassed, clientGet = "http://103.229.84.171/getClient.php", temp, CLIENT_ID, CLIENT_NAME, ADDRESS, PHONE_NUMBER,
            EMAIL, OFFICE_PHONE, WEB_ADDRESS, CONTACT_PERSON, ADDRESS_2, INDUSTRY_NAME, CLIENT_TYPE, ENTER_DT, USER_NAME, COMPANY_NAME;
    RequestQueue requestQueue;
    ListView listView;
    ArrayAdapter<String> adapter;
    public ArrayList<String> companyarray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);
        usernamepassed = getIntent().getExtras().getString("username");
        listView = (ListView) findViewById(R.id.listView);

        Log.e("username passed ", usernamepassed);
        requestQueue = Volley.newRequestQueue(clientView.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, clientGet, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        temp = null;
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        CLIENT_NAME = (String) jsonObject.get("CLIENT_NAME");
                        companyarray.add(CLIENT_NAME);
                        final String[] category = companyarray.toArray(new String[companyarray.size()]);
                        adapter = new ArrayAdapter<String>(clientView.this, R.layout.custom_textview, category);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                                AlertDialog alBuilder = new AlertDialog.Builder(clientView.this).create();
                                alBuilder.setMessage("Please Choose an Option");
                                alBuilder.setTitle("Client");
                                alBuilder.setIcon(R.drawable.ic_launcher);
                                alBuilder.setButton(DialogInterface.BUTTON1, "View ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i = new Intent(clientView.this, clientViewDetails.class);
                                        i.putExtra("item", companyarray.get(position));
                                        startActivity(i);
                                    }
                                });
                                alBuilder.setButton(DialogInterface.BUTTON2, "Edit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i = new Intent(clientView.this, clientUpdate.class);
                                        i.putExtra("item", companyarray.get(position));
                                        startActivity(i);


                                      /*  Toast.makeText(getApplicationContext(), "Edit Option Coming Soon", Toast.LENGTH_SHORT).show();*/
                                    }
                                });

                                alBuilder.show();
                            }
                        });
                        adapter.notifyDataSetChanged();
                        Log.e(i + " objects value ", CLIENT_ID + " " + CLIENT_NAME + " " + ADDRESS + " " + PHONE_NUMBER + " " + EMAIL + " " + OFFICE_PHONE + " " + WEB_ADDRESS + " " + CONTACT_PERSON + " " + ADDRESS_2 + " " + INDUSTRY_NAME + " " + CLIENT_TYPE + " " + ENTER_DT + " " + USER_NAME + " " + COMPANY_NAME);
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
                params.put("user", usernamepassed);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    /*@Override
     public void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),"Refreshing Restart", Toast.LENGTH_SHORT).show();
        //When BACK BUTTON is pressed, the activity on the stack is restarted
        //Do what you want on the refresh procedure here
    }*/

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "Refreshing List", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }
}
