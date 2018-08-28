package skillsandservices.main.com.skillsandservices;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class paintingRequest extends AppCompatActivity {
     Button submit;
    DatabaseReference databaseReference;
    EditText typeName;
    EditText nooforders;
    EditText date;
    private Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting_request);
        typeName = (EditText) findViewById(R.id.typename);
        nooforders = (EditText) findViewById(R.id.nooforders);
        submit = (Button) findViewById(R.id.submitreq);
        date = (EditText) findViewById(R.id.date);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference = FirebaseDatabase.getInstance().getReference("Categories").child("Painting");
                String id = databaseReference.push().getKey();
                PaintObject paint = new PaintObject();
                if (!TextUtils.isEmpty(typeName.getText()) && !TextUtils.isEmpty(nooforders.getText()) && !TextUtils.isEmpty(date.getText())) {
                    paint.setTypeOfPainting(String.valueOf(typeName.getText()));
                    paint.setDateToBeDelivered(String.valueOf(date.getText()));
                    paint.setNoOfOrders(String.valueOf(nooforders.getText()));
                    databaseReference.child(id).setValue(paint);

                    Toast.makeText(paintingRequest.this, "Thanks for posting the request", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(paintingRequest.this, "please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
        final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                date.setText(sdf.format(myCalendar.getTime()));
            }
        };
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(paintingRequest.this,datePickerListener,
                        myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

    }




        }



