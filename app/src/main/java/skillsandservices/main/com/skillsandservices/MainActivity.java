
package skillsandservices.main.com.skillsandservices;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    // DatabaseConnector dConnector = new DatabaseConnector(this);

    FirebaseAuth mAuth;

    private Button forgot;
    private EditText emailU, passwordU;
    private UserDetails user;
    private Button register;
    private Button login;
    String mail, passw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("test");
        user = new UserDetails();

        emailU = findViewById(R.id.et_email);
        passwordU = findViewById(R.id.et_password);
        user.setEmailID(emailU.getText().toString());
//        mail = String.valueOf(email.getText().toString());
//        passw = String.valueOf(password.getText().toString());

        register = (Button) findViewById(R.id.b_register);
        login = (Button) findViewById(R.id.b_login);
        forgot = (Button) findViewById(R.id.b_sendpass);
        register.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent r = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(r);
            }

        });


        forgot.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                final EditText edittext = new EditText(MainActivity.this);
                alert.setMessage("Enter Your Email");
                alert.setTitle("Forgot Password");

                alert.setView(edittext);

                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value
                        //OR
                        String YouEditTextValue = edittext.getText().toString();

                        // String to=YouEditTextValue;
                        String subject = "Your Pasword Details";
                        // String message="Your password : "+forgotpass;


                        Intent email = new Intent(Intent.ACTION_SEND);
                        //email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                        email.putExtra(Intent.EXTRA_SUBJECT, subject);
                        // email.putExtra(Intent.EXTRA_TEXT, message);

                        //need this to prompts email client only
                        email.setType("message/rfc822");

                        startActivity(Intent.createChooser(email, "Choose an Email client :"));

                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.
                        dialog.dismiss();
                    }
                });

                alert.show();
            }

        });


        login.setOnClickListener(new OnClickListener()  {

            @Override
            public void onClick(View view) {

//                String mail = String.valueOf(email.getText());
                String UserPass = passwordU.getText().toString();
                String UserMail = emailU.getText().toString();

                FirebaseAuth auth = FirebaseAuth.getInstance();

                if (UserMail != null && !(UserMail.equals(null)) ) {
                    if (UserMail != "" && !(UserMail.equals("")) ){
                        if (UserPass != null && !(UserPass.equals(null))) {
                            if (UserPass != "" && !(UserMail.equals(""))) {
                                Toast.makeText(MainActivity.this, "Email: "+UserMail+"\nPassword: "+UserPass, Toast.LENGTH_SHORT).show();
                                Log.i("hogger", "mail is " + UserMail + " password is " + UserPass);

//                                final Boolean[] log = new Boolean[1];
                        auth.signInWithEmailAndPassword(UserMail, UserPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isComplete()) {
//                                    log[0] = true;
//                                    String mailId = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                                    try {
//                                        Toast.makeText(MainActivity.this, auth.getCurrentUser().getUid()+"", Toast.LENGTH_SHORT).show();
//                                        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(auth.getCurrentUser().getUid());


//                                        Toast.makeText(getBaseContext(), "Not registered", Toast.LENGTH_LONG).show();
//                                        final UserDetails[] user = {new UserDetails()};
//                                        db.addValueEventListener(new ValueEventListener() {
//                                            @Override
//                                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                                user[0] = dataSnapshot.getValue(UserDetails.class);
//                                            }
//
//                                            @Override
//                                            public void onCancelled(DatabaseError databaseError) {
//                                                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_LONG).show();
//                                            }
//                                        });
//                                        if (user[0].getTypeOfUser().equals("client")) {
//                                            HomeMenu.type = "client";
//                                            startActivity(new Intent(MainActivity.this, HomeMenu.class));
//                                        } else {
//                                            HomeMenu.type = "dealer";
//                                            startActivity(new Intent(MainActivity.this, HomeMenu.class));
//                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Toast.makeText(getBaseContext(), "some error", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(getBaseContext(), "Error signing in", Toast.LENGTH_LONG).show();
                                }

                            }

                        });


                            Toast.makeText(MainActivity.this, "auth: "+auth.getCurrentUser().getUid()+"", Toast.LENGTH_SHORT).show();
                        


                            } else {
                                Toast.makeText(MainActivity.this, "Check the password again!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Check the password again!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Check the email id!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Check the email id!", Toast.LENGTH_SHORT).show();
                }

                /*auth.signInWithEmailAndPassword(mail, passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()) {
                            String mailId = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                            try {
                                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mailId);


                                Toast.makeText(getBaseContext(), "Not registered", Toast.LENGTH_LONG).show();
                                final UserDetails[] user = {new UserDetails()};
                                db.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        user[0] = dataSnapshot.getValue(UserDetails.class);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_LONG).show();
                                    }
                                });
                                if (user[0].getTypeOfUser().equals("client")) {
                                    HomeMenu.type = "client";
                                    startActivity(new Intent(MainActivity.this, HomeMenu.class));
                                } else {
                                    HomeMenu.type = "dealer";
                                    startActivity(new Intent(MainActivity.this, HomeMenu.class));
                                }
                            } catch (Exception e) {
                                Toast.makeText(getBaseContext(), "some error", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getBaseContext(), "Error signing in", Toast.LENGTH_LONG).show();
                        }
                    }
                });*/
            }
        });
    }
}


