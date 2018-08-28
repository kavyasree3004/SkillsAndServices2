package skillsandservices.main.com.skillsandservices;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends Activity {
    String[] language ={"San francisco, California","Los angeles,california","Philadelphia,Pennsylvania",
    		"Houston,Texas","Atlanta , Georgia","Las Vegas,Nevada","Miami,Florida","Orlando,Florida"}; 
    Button b1;
    String selectedtext;

    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.homelayout);  

        //Creating the instance of ArrayAdapter containing list of language names  
           ArrayAdapter<String> adapter = new ArrayAdapter<String>  
            (this,android.R.layout.select_dialog_item,language);  
        //Getting the instance of AutoCompleteTextView  
           final AutoCompleteTextView actv= (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);  
           actv.setThreshold(1);//will start working from first character  
           actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView  
           actv.setTextColor(Color.WHITE);  
           
           b1=(Button)findViewById(R.id.button);
           actv.setOnItemClickListener(new OnItemClickListener() {

               public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                   Log.i("SELECTED TEXT WAS-->", actv.getText().toString());
                   selectedtext=actv.getText().toString();
               }
           });
           b1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
         	      System.out.println("***********************************"+actv.getText().toString());

   				Intent i = new Intent(getBaseContext(), HomeMenu.class);
   				i.putExtra("location",selectedtext);
   				startActivity(i);
   				finish();

               }
            });

//error ekkada ostundu ?actually painting activity nen create chesa already..but adi stop avtundi nd login ki eredirect avtundi..
      //  chupista aagu
                          
    }  
  
  
}  
