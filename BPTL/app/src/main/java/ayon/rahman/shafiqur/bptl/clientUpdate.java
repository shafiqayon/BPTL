package ayon.rahman.shafiqur.bptl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class clientUpdate extends AppCompatActivity {
    String serverAddressfordata = "http://103.229.84.171/clientUpdateView.php", serverAddressforupdate = "http://103.229.84.171/clientUpdate.php";
    String clientName = null;
    String website, contactPerson, phone, address, email, officephone, clientType, industryname,
            decisionMaker, decisionMakerNumber, middleMan, consultant, finance, possibleRequirement, remarks;
    EditText sclientName, swebsite, scontactPerson, sphone, saddress, semail, sofficephone, sclientType, sindustryname,
            sdecisionMaker, sdecisionMakerNumber, smiddleMan, sconsultant, sfinance, spossibleRequirement, sremarks;
    Button updateButton;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_update);
        clientName = getIntent().getExtras().getString("item");
        Toast.makeText(clientUpdate.this, clientName, Toast.LENGTH_LONG).show();
        requestQueue = Volley.newRequestQueue(clientUpdate.this);

        updateButton = (Button) findViewById(R.id.update);


        sclientName = (EditText) findViewById(R.id.EditText2);
        swebsite = (EditText) findViewById(R.id.EditText3);
        scontactPerson = (EditText) findViewById(R.id.EditText4);
        sphone = (EditText) findViewById(R.id.EditText5);
        saddress = (EditText) findViewById(R.id.EditText6);
        semail = (EditText) findViewById(R.id.EditText7);
        sofficephone = (EditText) findViewById(R.id.EditText8);
        sclientType = (EditText) findViewById(R.id.EditText9);
        sindustryname = (EditText) findViewById(R.id.EditText10);
        sdecisionMaker = (EditText) findViewById(R.id.EditText11);
        sdecisionMakerNumber = (EditText) findViewById(R.id.EditText12);
        smiddleMan = (EditText) findViewById(R.id.EditText13);
        sconsultant = (EditText) findViewById(R.id.EditText14);
        sfinance = (EditText) findViewById(R.id.EditText15);
        spossibleRequirement = (EditText) findViewById(R.id.EditText16);
        sremarks = (EditText) findViewById(R.id.EditText17);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, serverAddressfordata, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
                try {
//                    jsonArray = new JSONArray(response);
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("ClientUpdate Json", String.valueOf(jsonObject));
//                    category = object.getString("status");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//
//                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    String clientNameget;
                    sclientName.setText((String) jsonObject.get("CLIENT_NAME"));

                    if (jsonObject.isNull("WEB_ADDRESS") == false) {
                        swebsite.setText((String) jsonObject.get("WEB_ADDRESS"));
                    } else {
                        swebsite.setText("No Data for website");
                    }


                    if (jsonObject.isNull("INDUSTRY_NAME") == false) {
                        sindustryname.setText((String) jsonObject.get("INDUSTRY_NAME"));
                    } else {
                        sindustryname.setText("No Data  for industry");
                    }

                    //client type
                    if (jsonObject.isNull("CLIENT_TYPE") == false) {
                        String temp = (String) jsonObject.get("CLIENT_TYPE");
                        if (temp.equals("P")) {
                            sclientType.setText("Existing");
                        } else if (temp.equals("E")) {
                            sclientType.setText("Preceding");
                        }

                    } else {
                        sclientType.setText("No Data  for Client Type");
                    }


                    if (jsonObject.isNull("CONTACT_PERSON") == false) {
                        scontactPerson.setText((String) jsonObject.get("CONTACT_PERSON"));
                    } else {
                        scontactPerson.setText("No Data  for Contact Person");
                    }


                    if (jsonObject.isNull("ADDRESS_LINE_1") == false) {
                        saddress.setText((String) jsonObject.get("ADDRESS_LINE_1"));
                    } else {
                        saddress.setText("No Data  for Address");
                    }


                    if (jsonObject.isNull("CONT_NUMBER") == false) {
                        sphone.setText((String) jsonObject.get("CONT_NUMBER"));
                    } else {
                        sphone.setText("0000000");
                    }


                    if (jsonObject.isNull("OFFICE_PHONE") == false) {
                        sofficephone.setText((String) jsonObject.get("OFFICE_PHONE"));
                    } else {
                        sofficephone.setText("0000000");
                    }


                    if (jsonObject.isNull("EMAIL") == false) {
                        semail.setText((String) jsonObject.get("EMAIL"));
                    } else {
                        semail.setText("No Data  for Email");
                    }


                    if (jsonObject.isNull("DECISION_MAKER") == false) {

                        sdecisionMaker.setText((String) jsonObject.get("DECISION_MAKER"));
                    } else {
                        sdecisionMaker.setText("No Data  for Decision Maker");
                    }


                    if (jsonObject.isNull("DEC_NUMBER") == false) {
                        sdecisionMakerNumber.setText((String) jsonObject.get("DEC_NUMBER"));
                    } else {
                        sdecisionMakerNumber.setText("0000000");
                    }


                    if (jsonObject.isNull("MIDDLE_MAN") == false) {
                        smiddleMan.setText((String) jsonObject.get("MIDDLE_MAN"));
                    } else {
                        smiddleMan.setText("No Data  for Middle Man");
                    }


                    if (jsonObject.isNull("CONSULTANT") == false) {
                        sconsultant.setText((String) jsonObject.get("CONSULTANT"));
                    } else {
                        sconsultant.setText("No Data  for Consultant");
                    }


                    if (jsonObject.isNull("FINANCE_FROM") == false) {
                        sfinance.setText((String) jsonObject.get("FINANCE_FROM"));
                    } else {
                        sfinance.setText("No Data  for Finance");
                    }


                    if (jsonObject.isNull("REMARKS") == false) {
                        sremarks.setText((String) jsonObject.get("REMARKS"));
                    } else {
                        sremarks.setText("No Data  for Remarks");
                    }


                    if (jsonObject.isNull("POSSIBLE_REQUIRMENT") == false) {
                        spossibleRequirement.setText((String) jsonObject.get("POSSIBLE_REQUIRMENT"));
                    } else {
                        spossibleRequirement.setText("No Data  for Possible Requirements");
                    }
                    //Log.e("check", "this is what we get " + (String) jsonObject.get("CLIENT_NAME"));


//                    }
                } catch (JSONException e) {
                    Log.e("error array conversion", e.toString());
                    e.printStackTrace();
                }

                Log.e("Response", response.toString());
//                Log.e("CONTACT_PERSON", "this is"+contactPerson);

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
                params.put("clientName", clientName);
                return params;
            }
        };


        requestQueue.add(stringRequest);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest sendUpdateData = new StringRequest(Request.Method.POST, serverAddressforupdate, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray jsonArray = null;
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            Log.e("send Json", String.valueOf(jsonObject));

                            Log.e("Return Json", String.valueOf(jsonObject));
                            Log.e("Return Json", response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.getStackTraceString(e);
                        }
                        ;

                        Toast.makeText(clientUpdate.this, "webiste: - > " + swebsite.getText() + sclientName.getText() + sclientType.getText() + sconsultant.getText() + sphone.getText() + spossibleRequirement.getText() + sremarks.getText(), Toast.LENGTH_SHORT).show();
                        ;
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("clientNameU", String.valueOf(sclientName.getText()));
                        params.put("clientName", clientName);
                        params.put("website", String.valueOf(swebsite.getText()));
                        params.put("companyName", "Dim");
                        params.put("contactPerson", String.valueOf(scontactPerson.getText()));
                        params.put("phone", String.valueOf(sphone.getText()));
                        params.put("address", String.valueOf(saddress.getText()));
                        params.put("email", String.valueOf(semail.getText()));
                        params.put("officephone", String.valueOf(sofficephone.getText()));
                        params.put("decisionMaker", String.valueOf(sdecisionMaker.getText()));
                        params.put("decisionMakerNumber", String.valueOf(sdecisionMakerNumber.getText()));
                        params.put("middleMan", String.valueOf(smiddleMan.getText()));
                        params.put("consultant", String.valueOf(sconsultant.getText()));
                        params.put("finance", String.valueOf(sfinance.getText()));
                        params.put("possibleRequirement", String.valueOf(spossibleRequirement.getText()));
                        params.put("remarks", String.valueOf(sremarks.getText()));
                        params.put("industry", "Alur Dom");
                        params.put("addressType", "Zonal");
                        params.put("clientType", "Existing");
                        params.put("userlogin", "S-001");
                        return params;
                    }
                };
                requestQueue.add(sendUpdateData);
            }
        });
    }
}
