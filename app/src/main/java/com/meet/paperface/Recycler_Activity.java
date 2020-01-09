package com.meet.paperface;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Recycler_Activity extends AppCompatActivity {

    List<Users> listdata = new ArrayList<>();

    RecyclerView rv;
    Adeptorforrecycle adaptor;
    private DatabaseReference mUsersDatabase;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_recycler_ );


        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Orders");

        mLayoutManager = new LinearLayoutManager(this);

        rv =findViewById(R.id.recycler_one);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));


        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ss : dataSnapshot.getChildren()) {
                    Users user = ss.getValue(Users.class);
                    listdata.add(user);

                }
                adaptor = new Adeptorforrecycle(Recycler_Activity.this, listdata);
                rv.setAdapter(adaptor);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
