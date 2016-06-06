package ayon.rahman.shafiqur.bptl;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class clientViewByDate extends AppCompatActivity {
    Button startDate, endDate, viewDate;
    String startDateString, endDateString, usernamepassed;
    Calendar c;
    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView endTV, startTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view_by_date);
        usernamepassed = getIntent().getExtras().getString("username");
        startDate = (Button) findViewById(R.id.bydate);
        endDate = (Button) findViewById(R.id.viewAll);
        viewDate = (Button) findViewById(R.id.viewDates);
        endTV = (TextView) findViewById(R.id.endtv);
        startTV = (TextView) findViewById(R.id.starttv);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(clientViewByDate.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String dateString = formatDate(year, monthOfYear, dayOfMonth);
                                startDateString = dateString;
                               /* Toast.makeText(clientViewByDate.this, "Inside End date", Toast.LENGTH_SHORT).show();*/
                                startTV.setText("Selected Start Date : " + dateString);
                              /*  Toast.makeText(clientViewByDate.this, dateString, Toast.LENGTH_SHORT).show();*/

                            }
                        }, mYear, mMonth, mDay);
                /*dpd.setButton(DialogInterface.BUTTON_NEUTRAL,"Select Start Date", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(clientViewByDate.this,"Mother Toast Clent",Toast.LENGTH_SHORT).show();
                    }
                });*/
                dpd.show();

            }

        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog enddpd = new DatePickerDialog(clientViewByDate.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String dateString = formatDate(year, monthOfYear, dayOfMonth);
                        endDateString = dateString;
                        endTV.setText("Selected End Date: " + dateString);
                      /*  Toast.makeText(clientViewByDate.this, dateString, Toast.LENGTH_SHORT).show();*/
                    }
                }, mYear, mMonth, mDay);

               /* enddpd.setButton(DialogInterface.BUTTON_NEUTRAL,"Select End Date", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(clientViewByDate.this,"Mother Toast Client end ",Toast.LENGTH_SHORT).show();
                    }
                });*/
                enddpd.show();
            }
        });


        viewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(clientViewByDate.this, workviewbydatebetween.class);
                i.putExtra("startDate", startDateString);
                i.putExtra("endDate", endDateString);
                i.putExtra("username", usernamepassed);
                startActivity(i);
              /*  Toast.makeText(clientViewByDate.this,startDateString+"\n"+endDateString,Toast.LENGTH_LONG).show();*/
            }
        });

    }

    private static String formatDate(int year, int month, int day) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month, day);
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        return sdf.format(date);
    }

}
