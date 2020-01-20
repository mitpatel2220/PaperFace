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

import com.meet.paperface.R;
import com.meet.paperface.adapter.Others_adeptor;
import com.meet.paperface.model.Task_Class;
import com.meet.paperface.model.Users;

import java.util.ArrayList;
import java.util.List;

public class Others_Address extends AppCompatActivity {
    List<Users> list_data = new ArrayList<>();
    List<Task_Class> list = new ArrayList<>();
    RecyclerView rv;
    Others_adeptor adaptor;
    private DatabaseReference mUsersDatabase;
    private LinearLayoutManager mLayoutManager;
    String keyValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others__address);
        setContentView(R.layout.activity_mv_hall);
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
                    if(ss.child("hostelname").getValue().equals("Others")){
                        Users user = ss.getValue( Users.class );
                        Task_Class task = ss.getValue( Task_Class.class );
                        keyValue = ss.getKey();
                        task.setKey( keyValue );
                        list.add( task );
                        list_data.add( user );


                    }

                }
                adaptor = new Others_adeptor( Others_Address.this, list_data, list );
                rv.setAdapter( adaptor );

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        } );

    }
}
