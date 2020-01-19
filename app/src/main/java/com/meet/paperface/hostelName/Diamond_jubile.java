package com.meet.paperface.hostelName;

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
import com.meet.paperface.model.Task_Class;
import com.meet.paperface.model.Users;
import com.meet.paperface.R;

import java.util.ArrayList;
import java.util.List;

public class Diamond_jubile extends AppCompatActivity {
    List<Users> list_data = new ArrayList<>();
    List<Task_Class> list = new ArrayList<>();
    RecyclerView rv;
    Diamond_jubily_adptor adaptor;
    private DatabaseReference mUsersDatabase;
    private LinearLayoutManager mLayoutManager;
    String keyValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diamond_jubile);

        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child( "Orders" );
        mLayoutManager = new LinearLayoutManager( this );
        rv = findViewById( R.id.recycler_one );
        rv.setHasFixedSize( true );
        rv.setLayoutManager( new LinearLayoutManager( this ) );
        mUsersDatabase.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ss : dataSnapshot.getChildren()) {
                    if(ss.child("hostelname").getValue().equals("Diamond Jubilee Boys Hostel")){
                        Users user = ss.getValue( Users.class );
                        Task_Class task = ss.getValue( Task_Class.class );
                        keyValue = ss.getKey();
                        task.setKey( keyValue );
                        list.add( task );
                        list_data.add( user );


                    }

                }
                adaptor = new Diamond_jubily_adptor( Diamond_jubile.this, list_data, list );
                rv.setAdapter( adaptor );

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        } );

    }
    }

