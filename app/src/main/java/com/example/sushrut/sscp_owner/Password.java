package com.example.sushrut.sscp_owner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Password extends AppCompatActivity {
    EditText et1;
    Button btn1;
    DatabaseReference ref;
    final String str1 = "sscp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        String a = Bill.name;

        if(a.equals("Tno_1" ) || a.equals("Tno_2" ) || a.equals("Tno_3" ) || a.equals("Tno_4" ))
        {

            ref = FirebaseDatabase.getInstance().getReference().child("TABLES").child("2").child(a).child("Status");
        }

        else if(a.equals("Tno_5" ) || a.equals("Tno_6" ) || a.equals("Tno_7" ) || a.equals("Tno_8" ) || a.equals("Tno_9"))
        {

            ref = FirebaseDatabase.getInstance().getReference().child("TABLES").child("4").child(a).child("Status");
        }
        else if(a.equals("Tno_10")|| a.equals("Tno_11" ) || a.equals("Tno_12" ) || a.equals("Tno_13" ) || a.equals("Tno_14") || a.equals("Tno_15"))
        {
            ref = FirebaseDatabase.getInstance().getReference().child("TABLES").child("8").child(a).child("Status");

        }

        else{
            ref = FirebaseDatabase.getInstance().getReference().child("TAKEAWAY").child(a).child("Status");
        }
        et1 = findViewById(R.id.et_password);
        btn1 = findViewById(R.id.btn_proceed);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String str = et1.getText().toString();
              if(str.equals(str1)){
                  ref.setValue("0");
                  Intent i1 = new Intent(Password.this,choice.class);
                  startActivity(i1);
              }
              else {
                  Toast.makeText(Password.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
              }
            }
        });
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Enter Valid Password", Toast.LENGTH_SHORT).show();
    }
}
