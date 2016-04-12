package ayon.rahman.shafiqur.bptl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

public class clientView extends AppCompatActivity {
    String usernamepassed, clientGet = "http://103.229.84.171/getClient.php", temp, CLIENT_ID, CLIENT_NAME, ADDRESS, PHONE_NUMBER,
            EMAIL, OFFICE_PHONE, WEB_ADDRESS, CONTACT_PERSON, ADDRESS_2, INDUSTRY_NAME, CLIENT_TYPE, ENTER_DT, USER_NAME, COMPANY_NAME;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);
        usernamepassed = getIntent().getExtras().getString("username");
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
/*
                        CLIENT_ID, CLIENT_NAME, ADDRESS, PHONE_NUMBER, EMAIL, OFFICE_PHONE, WEB_ADDRESS, CONTACT_PERSON,ADDRESS_2,INDUSTRY_NAME,CLIENT_TYPE,ENTER_DT,USER_NAME, COMPANY_NAME*/
                        /*temp = (String) jsonObject.get("CLIENT_NAME");

                        clientListForSpinner.add(temp);*/
                        CLIENT_ID = (String) jsonObject.get("CLIENT_ID");
                        CLIENT_NAME = (String) jsonObject.get("CLIENT_NAME");
                   /* *//*ADDRESS = (String) jsonObject.get("ADDRESS");*//*
                        PHONE_NUMBER = (String) jsonObject.get("PHONE_NUMBER");
                        EMAIL = (String) jsonObject.get("EMAIL");
                        OFFICE_PHONE = (String) jsonObject.get("OFFICE_PHONE");*/
                        if (jsonObject.isNull("WEB_ADDRESS") == false) {
                            WEB_ADDRESS = (String) jsonObject.get("WEB_ADDRESS");
                        }

                        if (jsonObject.isNull("WEB_ADDRESS") == false) {
                            INDUSTRY_NAME = (String) jsonObject.get("INDUSTRY_NAME");
                        }
                        if (jsonObject.isNull("WEB_ADDRESS") == false) {
                            CLIENT_TYPE = (String) jsonObject.get("CLIENT_TYPE");
                        }

                        if (jsonObject.isNull("WEB_ADDRESS") == false) {
                            ENTER_DT = (String) jsonObject.get("ENTER_DT");
                        }
                      /*  CONTACT_PERSON = (String) jsonObject.get("CONTACT_PERSON");*/



                     /*   COMPANY_NAME = (String) jsonObject.get("COMPANY_NAME");*/
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
}