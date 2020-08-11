package com.example.sushrut.sscp_owner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Chef extends AppCompatActivity {

    private ListView livee;

    private ArrayList<String> arrayList = new ArrayList<>();
    private DatabaseReference ref1 , ref2 , ref3, ref4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef);
        getSupportActionBar().hide();

        livee = (ListView)findViewById(R.id.cheflist);
        ref1 = FirebaseDatabase.getInstance().getReference().child("TABLES").child("2");
        ref2 = FirebaseDatabase.getInstance().getReference().child("TABLES").child("4");
        ref3 = FirebaseDatabase.getInstance().getReference().child("TABLES").child("8");
        ref4 = FirebaseDatabase.getInstance().getReference().child("TAKEAWAY");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        livee.setAdapter(arrayAdapter);

        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    if(Integer.parseInt(ds.child("Bill").getValue().toString()) == 1)
                    {
                        String val = ds.child("Name").getValue(String.class);
                        arrayList.add(val);
                    }
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    if(Integer.parseInt(ds.child("Bill").getValue().toString()) == 1)
                    {
                        String val = ds.child("Name").getValue(String.class);
                        arrayList.add(val);
                    }
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {

                    if(Integer.parseInt(ds.child("Bill").getValue().toString()) == 1)
                    {
                        String val = ds.child("Name").getValue(String.class);
                        arrayList.add(val);
                    }
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        ref4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {

                    if(Integer.parseInt(ds.child("Bill").getValue().toString()) == 1)
                    {
                        String val = ds.child("Name").getValue(String.class);
                        arrayList.add(val);
                    }
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        livee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(Chef.this , Chef1.class);
                intent.putExtra("Food Item" , (String) livee.getItemAtPosition(position));
                startActivity(intent);

            }

        });

    }
}
