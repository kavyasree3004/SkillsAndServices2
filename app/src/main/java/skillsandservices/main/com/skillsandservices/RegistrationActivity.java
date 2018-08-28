package skillsandservices.main.com.skillsandservices;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class RegistrationActivity extends Activity {

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText phoneNumber;
    private EditText password;
    private EditText confirmpassword;
    private Button submit;
    private RadioGroup rdgp;
    String emailId,pass,phone,confpass,firstnm,lastnm,radio;
    UserDetails user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        user = new UserDetails();
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.email);
        phoneNumber = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.confirmpassword);
        submit = (Button)findViewById(R.id.submit);
        rdgp = (RadioGroup)findViewById(R.id.radio_group);
        rdgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(radioGroup.getCheckedRadioButtonId()== R.id.radio1)
                    radio = "client";
                if(radioGroup.getCheckedRadioButtonId() == R.id.radio2)
                    radio = "dealer";
            }
        });
        emailId = String.valueOf(email.getText());
        pass = String.valueOf(password.getText());
        phone = String.valueOf(phoneNumber.getText());
        confpass = String.valueOf(confirmpassword.getText());
        firstnm = String.valueOf(firstName.getText());
        lastnm = String.valueOf(lastName.getText());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    FirebaseAuth auth =FirebaseAuth.getInstance();
                    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
                    user.setTypeOfUser(radio);
                    user.setEmailID(emailId);
                    user.setPhoneNo(phone);
                    user.setFirstName(firstnm);
                    user.setLastName(lastnm);
                    auth.createUserWithEmailAndPassword(emailId,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                ref.child(emailId).setValue(user);
                                Toast.makeText(getBaseContext(),"Registered",Toast.LENGTH_LONG).show();
                            }else
                            {
                                Toast.makeText(getBaseContext(),"Error Registering,Please check your internet",Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                }
            }
        });
    }

    private boolean validate() {
        if(TextUtils.isEmpty(emailId) || Patterns.EMAIL_ADDRESS.matcher(emailId).matches())
        {
            Toast.makeText(getBaseContext(),"invalid mail",Toast.LENGTH_LONG).show();
            return false;
        }
        if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(getBaseContext(),"invalid pass",Toast.LENGTH_LONG).show();
            return false;
        }
        if(!TextUtils.isEmpty(phone))
        {
            try {
                int num = Integer.parseInt(phone);


            } catch (NumberFormatException e) {
                Toast.makeText(getBaseContext(),"Not a correct number",Toast.LENGTH_LONG).show();
                return false;
            }
        }
        if(TextUtils.isEmpty(phone))
        {

            Toast.makeText(getBaseContext(),"invalid phone no",Toast.LENGTH_LONG).show();
            return false;
        }


        if(TextUtils.isEmpty(confpass))
        {
            Toast.makeText(getBaseContext(),"invalid confirm pass",Toast.LENGTH_LONG).show();
            return false;
        }
        if(TextUtils.isEmpty(firstnm))
        {
            Toast.makeText(getBaseContext(),"invalid first name",Toast.LENGTH_LONG).show();
            return false;
        }
        if(TextUtils.isEmpty(lastnm))
        {
            Toast.makeText(getBaseContext(),"invalid last name",Toast.LENGTH_LONG).show();
            return false;
        }
        if(TextUtils.isEmpty(radio))
        {
            Toast.makeText(getBaseContext(),"Select either dealer or client",Toast.LENGTH_LONG).show();
            return false;
        }
        if(!confpass.equals(pass)){
            Toast.makeText(getBaseContext(),"Enter Same Password for both Confirm password and Password",Toast.LENGTH_LONG).show();
            return false;

        }
        return true;
    }
}
