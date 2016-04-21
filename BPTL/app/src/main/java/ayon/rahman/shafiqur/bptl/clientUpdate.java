package ayon.rahman.shafiqur.bptl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

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
    String clientName = "empty", serverAddress = "http://103.229.84.171/clientUpdate.php";
    String website, contactPerson, phone, address, email, officephone, clientType, industryname,
            decisionMaker, decisionMakerNumber, middleMan, consultant, finance, possibleRequirement, remarks;
    EditText sclientName, swebsite, scontactPerson, sphone, saddress, semail, sofficephone, sclientType, sindustryname,
            sdecisionMaker, sdecisionMakerNumber, smiddleMan, sconsultant, sfinance, spossibleRequirement, sremarks;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_update);
        clientName = getIntent().getExtras().getString("item");
        requestQueue = Volley.newRequestQueue(clientUpdate.this);

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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, serverAddress, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
                try {
//                    jsonArray = new JSONArray(response);
                    JSONObject jsonObject = new JSONObject(response);
//                    category = object.getString("status");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//
//                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    String clientNameget;
                    sclientName.setText("Client Name : " + (String) jsonObject.get("CLIENT_NAME"));

                    if (jsonObject.isNull("WEB_ADDRESS") == false) {
                        swebsite.setText("Web Address : " + (String) jsonObject.get("WEB_ADDRESS"));
                    } else {
                        swebsite.setText("No Data for website");
                    }


                    if (jsonObject.isNull("INDUSTRY_NAME") == false) {
                        sindustryname.setText("Industry Name : " + (String) jsonObject.get("INDUSTRY_NAME"));
                    } else {
                        sindustryname.setText("No Data  for industry");
                    }

                    //client type
                    if (jsonObject.isNull("CLIENT_TYPE") == false) {
                        String temp = (String) jsonObject.get("CLIENT_TYPE");
                        if (temp.equals("P")) {
                            sclientType.setText("Client Type : Existing");
                        } else if (temp.equals("E")) {
                            sclientType.setText("Client Type : Preceding");
                        }

                    } else {
                        sclientType.setText("No Data  for Client Type");
                    }


                    if (jsonObject.isNull("CONTACT_PERSON") == false) {
                        scontactPerson.setText("Contact Person : " + (String) jsonObject.get("CONTACT_PERSON"));
                    } else {
                        scontactPerson.setText("No Data  for Contact Person");
                    }


                    if (jsonObject.isNull("ADDRESS_LINE_1") == false) {
                        saddress.setText("Address Line : " + (String) jsonObject.get("ADDRESS_LINE_1"));
                    } else {
                        saddress.setText("No Data  for Address");
                    }


                    if (jsonObject.isNull("CONT_NUMBER") == false) {
                        sphone.setText("Contact Number : " + (String) jsonObject.get("CONT_NUMBER"));
                    } else {
                        sphone.setText("No Data  for Contact Number");
                    }


                    if (jsonObject.isNull("OFFICE_PHONE") == false) {
                        sofficephone.setText("Office Phone : " + (String) jsonObject.get("OFFICE_PHONE"));
                    } else {
                        sofficephone.setText("No Data  for Office Phone");
                    }


                    if (jsonObject.isNull("EMAIL") == false) {
                        semail.setText("Email : " + (String) jsonObject.get("EMAIL"));
                    } else {
                        semail.setText("No Data  for Email");
                    }


                    if (jsonObject.isNull("DECISION_MAKER") == false) {

                        sdecisionMaker.setText("Decision Maker : " + (String) jsonObject.get("DECISION_MAKER"));
                    } else {
                        sdecisionMaker.setText("No Data  for Decision Maker");
                    }


                    if (jsonObject.isNull("DEC_NUMBER") == false) {
                        sdecisionMakerNumber.setText("Decision Maker Number : " + (String) jsonObject.get("DEC_NUMBER"));
                    } else {
                        sdecisionMakerNumber.setText("No Data  for Decision Maker phone number");
                    }


                    if (jsonObject.isNull("MIDDLE_MAN") == false) {
                        smiddleMan.setText("Middle Man : " + (String) jsonObject.get("MIDDLE_MAN"));
                    } else {
                        smiddleMan.setText("No Data  for Middle Man");
                    }


                    if (jsonObject.isNull("CONSULTANT") == false) {
                        sconsultant.setText("Consultant : " + (String) jsonObject.get("CONSULTANT"));
                    } else {
                        sconsultant.setText("No Data  for Consultant");
                    }


                    if (jsonObject.isNull("FINANCE_FROM") == false) {
                        sfinance.setText("Finance : " + (String) jsonObject.get("FINANCE_FROM"));
                    } else {
                        sfinance.setText("No Data  for Finance");
                    }


                    if (jsonObject.isNull("REMARKS") == false) {
                        sremarks.setText("Remarks : " + (String) jsonObject.get("REMARKS"));
                    } else {
                        sremarks.setText("No Data  for Remarks");
                    }


                    if (jsonObject.isNull("POSSIBLE_REQUIRMENT") == false) {
                        spossibleRequirement.setText("Possible Requirements : " + (String) jsonObject.get("POSSIBLE_REQUIRMENT"));
                    } else {
                        spossibleRequirement.setText("No Data  for Possible Requirements");
                    }
                    Log.e("check", "this is what we get " + (String) jsonObject.get("CLIENT_NAME"));


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
                params.put("customername", clientName);
                return params;
            }
        };


        requestQueue.add(stringRequest);

    }
}
