package com.example.sushrut.sscp_owner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Bill extends AppCompatActivity {

    private TextView tv1;
    int tot = 0;
    DatabaseReference ref ;
    String str = "";
    FloatingActionButton btn1;

    static  String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        getSupportActionBar().hide();

        Bundle extras = getIntent().getExtras();
        name = extras.getString("Food Item");

        tv1 = findViewById(R.id.tv_bill);
         btn1 = findViewById(R.id.floatingActionButton);



        ref = FirebaseDatabase.getInstance().getReference().child("BILL").child(name);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    str = str + "\n" + ds.child("Name").getValue().toString();
                    str = str + "\nQuantity :- " + ds.child("Quantity").getValue().toString();
                    str = str + "\t\tTotal Price :- " + ds.child("Total").getValue().toString() + "\n";
                    tv1.setText(str);
                    tot = tot + Integer.parseInt(ds.child("Total").getValue().toString());
                }
                str = str + "\nGRAND TOTAL :- " + tot;
                tv1.setText(str);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       btn1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(Bill.this,Password.class);
               startActivity(intent);
           }
       });


    }


//    public Bitmap takeScreenshot() {
//        View rootView = findViewById(android.R.id.content).getRootView();
//        rootView.setDrawingCacheEnabled(true);
//        return rootView.getDrawingCache();
//    }
//
//    public void saveBitmap(Bitmap bitmap) {
//        File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
//        FileOutputStream fos;
//        try {
//            fos = new FileOutputStream(imagePath);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
//            fos.flush();
//            fos.close();
//        } catch (FileNotFoundException e) {
//            Log.e("GREC", e.getMessage(), e);
//        } catch (IOException e) {
//            Log.e("GREC", e.getMessage(), e);
//        }
//    }

}
