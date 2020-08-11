package com.example.sushrut.sscp_owner;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Chef1 extends AppCompatActivity {

    private TextView tv1 ;

    DatabaseReference ref ,ref1;
    String str1 = " ORDER SERVED ";
    String str = "";
    private Button b;
    static  String name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef1);
        getSupportActionBar().hide();

        Bundle extras = getIntent().getExtras();
        name = extras.getString("Food Item");

        tv1 = findViewById(R.id.chefitm);
        tv1.setMovementMethod(new ScrollingMovementMethod());

        b = findViewById(R.id.enter);

        ref = FirebaseDatabase.getInstance().getReference().child("BILL").child(name);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setText(str1);

                b.setVisibility(View.GONE);

                if(name.equals("Tno_1" ) || name.equals("Tno_2" ) || name.equals("Tno_3" ) || name.equals("Tno_4" ))
                {

                    ref = FirebaseDatabase.getInstance().getReference().child("TABLES").child("2").child(name).child("Bill");
                }

                else if(name.equals("Tno_5" ) || name.equals("Tno_6" ) || name.equals("Tno_7" ) || name.equals("Tno_8" ) || name.equals("Tno_9"))
                {

                    ref = FirebaseDatabase.getInstance().getReference().child("TABLES").child("4").child(name).child("Bill");
                }
                else if(name.equals("Tno_10")|| name.equals("Tno_11" ) || name.equals("Tno_12" ) || name.equals("Tno_13" ) || name.equals("Tno_14") || name.equals("Tno_15"))
                {
                    ref = FirebaseDatabase.getInstance().getReference().child("TABLES").child("8").child(name).child("Bill");

                }

                else{
                    ref = FirebaseDatabase.getInstance().getReference().child("TAKEAWAY").child(name).child("Bill");
                }

                ref.setValue("0");
                Intent i1 = new Intent(Chef1.this,choice.class);
                startActivity(i1);

            }
        });


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    str = str + "\n" + ds.child("Name").getValue().toString();
                    str = str + "\nQuantity :- " + ds.child("Quantity").getValue().toString()+"\n";
                    tv1.setText(str);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
