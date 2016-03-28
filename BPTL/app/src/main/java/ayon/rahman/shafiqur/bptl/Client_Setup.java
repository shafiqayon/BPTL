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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client_Setup extends AppCompatActivity {
    final String servername = "http://http://192.168.1.105/clientsetup.php";
    public RequestQueue queue, requestQueue;
    public String category = null, selectedInSpinner = "nothing selected", industrySelected = null, addressTypeSelected = null, clientTypeSelected = null, selectedInSpinnerAddresstype = null, selectedInSpinnerClientType = null;

    String temp, temp2, industryServerforspinner = "http://192.168.1.105/industryspinner.php";
    EditText clientName, website, companyName, contactPerson, phone, address, email, officephone,
            decisionMaker, decisionMakerNumber, middleMan, consultant, finance, possibleRequirement, remarks;
    String sclientName, swebsite, scompanyName, scontactPerson, sphone, saddress, semail, sofficephone,
            sdecisionMaker, sdecisionMakerNumber, smiddleMan, sconsultant, sfinance, spossibleRequirement, sremarks;
    Spinner industrylistspinner;
    ArrayAdapter<String> industryAdapter;
    Button save;
    private List<String> industryListforSpinner = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client__setup);
        save = (Button) findViewById(R.id.save);

        industrylistspinner = (Spinner) findViewById(R.id.industry);
        clientName = (EditText) findViewById(R.id.clientName);
        website = (EditText) findViewById(R.id.website);
        companyName = (EditText) findViewById(R.id.companyName);
        contactPerson = (EditText) findViewById(R.id.contactPerson);
        phone = (EditText) findViewById(R.id.phoneNumber);
        address = (EditText) findViewById(R.id.address);
        email = (EditText) findViewById(R.id.email);
        officephone = (EditText) findViewById(R.id.officePhone);
        decisionMaker = (EditText) findViewById(R.id.decisionMakerName);
        decisionMakerNumber = (EditText) findViewById(R.id.decisionMakerNumber);
        middleMan = (EditText) findViewById(R.id.middleManName);
        consultant = (EditText) findViewById(R.id.consultant);
        finance = (EditText) findViewById(R.id.finance);
        possibleRequirement = (EditText) findViewById(R.id.requirement);
        remarks = (EditText) findViewById(R.id.remarks);


        queue = Volley.newRequestQueue(this);
        requestQueue = Volley.newRequestQueue(this);

        //getting industry for spinner industry
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(industryServerforspinner, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        temp = null;
                        JSONObject industry = (JSONObject) jsonArray.get(i);
                        temp = (String) industry.get("INDUSTRY_NAME");
                        industryListforSpinner.add(temp);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                industryAdapter = new ArrayAdapter<String>(Client_Setup.this, android.R.layout.simple_spinner_item, industryListforSpinner);
                industryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                industrylistspinner.setAdapter(industryAdapter);
                industryAdapter.notifyDataSetChanged();
                industrylistspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // Log.e("item", (String) parent.getItemAtPosition(position));
                        selectedInSpinner = parent.getItemAtPosition(position).toString();
                       /* temp2 = String.valueOf(parent.getItemAtPosition(position));*/
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
        String[] addressTypeItems = new String[]{"Local Office ", "Head Office", "Factory", "Regional", "Zonal Office", "Other Address"};
        String[] clientType = new String[]{"Prospecting", "Existing"};

        //setting up the address type spinner
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, addressTypeItems);
        Spinner addresstype = (Spinner) findViewById(R.id.addressType);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addresstype.setAdapter(adapter2);
        addresstype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("item", (String) parent.getItemAtPosition(position));
                selectedInSpinnerAddresstype = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //setting up the client type spinner
        ArrayAdapter<String> adapterforClientType = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, clientType);
        adapterforClientType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner ClientTypeSpinner = (Spinner) findViewById(R.id.clientType);
        ClientTypeSpinner.setAdapter(adapterforClientType);
        ClientTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("item", (String) parent.getItemAtPosition(position));
                selectedInSpinnerClientType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sclientName = String.valueOf(clientName.getText());
                swebsite = String.valueOf(website.getText());
                saddress = String.valueOf(address.getText());
                scompanyName = String.valueOf(companyName.getText());
                scontactPerson = String.valueOf(contactPerson.getText());
                sphone = String.valueOf(phone.getText());
                semail = String.valueOf(email.getText());
                sofficephone = String.valueOf(officephone.getText());
                sdecisionMaker = String.valueOf(decisionMaker.getText());
                sdecisionMakerNumber = String.valueOf(decisionMakerNumber.getText());
                smiddleMan = String.valueOf(middleMan.getText());
                sconsultant = String.valueOf(consultant.getText());
                sfinance = String.valueOf(finance.getText());
                spossibleRequirement = String.valueOf(possibleRequirement.getText());
                sremarks = String.valueOf(remarks.getText());
                industrySelected = selectedInSpinner;
                addressTypeSelected = selectedInSpinnerAddresstype;
                clientTypeSelected = selectedInSpinnerClientType;

                Toast.makeText(Client_Setup.this, sclientName + swebsite + scompanyName + scontactPerson + sphone + saddress + semail + sofficephone +
                        sdecisionMaker + sdecisionMakerNumber + smiddleMan + sconsultant + sfinance + spossibleRequirement + sremarks + industrySelected + addressTypeSelected + clientTypeSelected, Toast.LENGTH_LONG).show();
                Log.e("Sending Items", sclientName + swebsite + scompanyName + scontactPerson + sphone + saddress + semail + sofficephone +
                        sdecisionMaker + sdecisionMakerNumber + smiddleMan + sconsultant + sfinance + spossibleRequirement + sremarks + industrySelected + addressTypeSelected + clientTypeSelected);


                // selected industry from spinner

                StringRequest stringRequest = new StringRequest(Request.Method.POST, servername, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       /* try {

                            JSONObject object = new JSONObject(response);
                            category = object.getString("status");
                        } catch (JSONException e) {
                            Log.e("Server Reply", e.toString());
                            e.printStackTrace();
                        }

                        Toast.makeText(Client_Setup.this,category,Toast.LENGTH_LONG).show();*/
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("clientName", sclientName);
                        params.put("website", swebsite);
                        params.put("companyName", scompanyName);
                        params.put("contactPerson", scontactPerson);
                        params.put("phone", sphone);
                        params.put("address", saddress);
                        params.put("email", semail);
                        params.put("officephone", sofficephone);
                        params.put("decisionMaker", sdecisionMaker);
                        params.put("decisionMakerNumber", sdecisionMakerNumber);
                        params.put("middleMan", smiddleMan);
                        params.put("consultant", sconsultant);
                        params.put("finance", sfinance);
                        params.put("possibleRequirement", spossibleRequirement);
                        params.put("remarks", sremarks);
                        params.put("industry", industrySelected);
                        params.put("addressType", addressTypeSelected);
                        params.put("clientType", clientTypeSelected);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

        //getting client list for client spinner
        /*JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(clientServerforspinner, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        temp = null;
                        JSONObject industry = (JSONObject) jsonArray.get(i);
                        temp = (String) industry.get("CLIENT_NAME");
                        clientListForSpinner.add(temp);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });*/

        queue.add(jsonArrayRequest);
        //queue.add(jsonArrayRequest2);
    }
}
