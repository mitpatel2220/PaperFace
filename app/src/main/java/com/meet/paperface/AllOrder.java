package com.meet.paperface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meet.paperface.adapter.Diamond_jubily_adptor;
import com.meet.paperface.hostelName.Diamond_jubile;
import com.meet.paperface.model.Task_Class;
import com.meet.paperface.model.Users;

import java.util.ArrayList;
import java.util.List;

public class AllOrder extends AppCompatActivity {

    List<Users> list_data = new ArrayList<>();
    List<Task_Class> list = new ArrayList<>();
    RecyclerView rv;
    Allorder_adapter adaptor;
    private DatabaseReference mUsersDatabase;
    private LinearLayoutManager mLayoutManager;
    String keyValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order);
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child( "Allorders" );
        mLayoutManager = new LinearLayoutManager( this );
        rv = findViewById( R.id.recycler_one );
        rv.setHasFixedSize( true );
        rv.setLayoutManager( new LinearLayoutManager( this ) );
        mUsersDatabase.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ss : dataSnapshot.getChildren()) {
                         Users user = ss.getValue( Users.class );
                              list_data.add( user );




                }
                adaptor = new Allorder_adapter( AllOrder.this, list_data);
                rv.setAdapter( adaptor );

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        } );

    }
}

