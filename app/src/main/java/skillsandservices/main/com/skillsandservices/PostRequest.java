package skillsandservices.main.com.skillsandservices;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostRequest extends Activity
{
	DatabaseConnector dConnector = new DatabaseConnector(this);
    DatePickerDialog.OnDateSetListener datePickerListener;
     Button submit;
     DatabaseReference databaseReference;
     EditText itemName;
     EditText quantity;
     EditText date;



    private Calendar myCalendar = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postrequest);

        itemName = (EditText) findViewById(R.id.itemname);
        quantity = (EditText) findViewById(R.id.quantity);
        date = (EditText) findViewById(R.id.date);
        submit = (Button) findViewById(R.id.submitReq);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    if (validate()) {


                        databaseReference = FirebaseDatabase.getInstance().getReference("Categories").child("Cooking");

                //dConnector.addRecordRequest(itemName.getEditableText().toString(), quantity.getEditableText().toString(), phoneno.getEditableText().toString(), date.getEditableText().toString());
                      InformationObject object = new InformationObject();
                       object.setDate(String.valueOf(date.getText()));
                       object.setItemName(String.valueOf(itemName.getText()));
                       object.setQuantity((String.valueOf(quantity.getText())));
                         FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                       databaseReference.child(user.getEmail()).setValue(object);

                    Toast.makeText(PostRequest.this, "Thanks for posting your request.",
                            Toast.LENGTH_LONG).show();

               }
            //}
        });


         datePickerListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
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
                new DatePickerDialog(PostRequest.this, datePickerListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

}

	    public  boolean validate(){
        if(date.toString().isEmpty() )
	    {
	        Toast.makeText(PostRequest.this,"please enter date",Toast.LENGTH_LONG).show();
	        return false;
        }
        else if(itemName.toString().isEmpty())
        {
            Toast.makeText(PostRequest.this,"please enter item name",Toast.LENGTH_LONG).show();
            return false;

        } else if(myCalendar.toString().isEmpty())
        {
            Toast.makeText(PostRequest.this,"please enter date",Toast.LENGTH_LONG).show();
            return false;

        }


        return true;
    }

//what is the prob ?

}


