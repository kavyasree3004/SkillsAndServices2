package skillsandservices.main.com.skillsandservices;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeMenu extends Activity {
	   Button b1,b2,b3;
	   static String type;

	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);

	      setContentView(R.layout.menuitems);
	      b1=(Button)findViewById(R.id.cooking);
	      b2=(Button)findViewById(R.id.painting);
	      b3=(Button)findViewById(R.id.others);
	      
	      b1.setOnClickListener(new View.OnClickListener() {
	          @Override
	          public void onClick(View v) {
	          	Intent i;
                   if(type.equals( "client")){
					i = new Intent(HomeMenu.this, PostRequest.class);
					   startActivity(i);
                   }
                   if(type.equals("dealer")){
					   i = new Intent(HomeMenu.this, ListViewCookingActivity.class);
                   //  Toast.makeText(HomeMenu.this,"hello",Toast.LENGTH_LONG).show();
					startActivity(i);}

	          }
	       });

	      b2.setOnClickListener(new View.OnClickListener() {
			  @Override
			  public void onClick(View view) {
			  	Intent i;
				  if(type.equals( "client")) {
					  i = new Intent(HomeMenu.this, paintingRequest.class);
					  startActivity(i);
				  }
				  //  Toast.makeText(HomeMenu.this,"hello",Toast.LENGTH_LONG).show();
				  if(type.equals("dealer")){
					i = new Intent(HomeMenu.this, ListViewPaintingActivity.class);
					  startActivity(i);}
			  }
		  });
	   }
}
