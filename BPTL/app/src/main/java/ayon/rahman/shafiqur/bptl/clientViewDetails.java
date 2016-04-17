package ayon.rahman.shafiqur.bptl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class clientViewDetails extends AppCompatActivity {
    String clientName = "empty", serverAddress = "http://103.229.84.171/clientDetail.php";
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view_details);
        clientName = getIntent().getExtras().getString("item");
        requestQueue = Volley.newRequestQueue(clientViewDetails.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, serverAddress, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response", response.toString());
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
                params.put("customername", clientName);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
}
