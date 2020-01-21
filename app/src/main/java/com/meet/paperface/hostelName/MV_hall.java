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
import com.meet.paperface.adapter.Mv_hall_adeptor;
import com.meet.paperface.model.Task_Class;
import com.meet.paperface.model.Users;
import com.meet.paperface.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MV_hall extends AppCompatActivity {

    private final List<Users> list_data = new ArrayList<>();
    private final List<Task_Class> list = new ArrayList<>();
    private RecyclerView rv;
    private Mv_hall_adeptor adaptor;
    private String keyValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mv_hall);
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        DatabaseReference mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Orders");
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv = findViewById( R.id.recycler_one );
        rv.setHasFixedSize( true );
        rv.setLayoutManager( new LinearLayoutManager( this ) );
        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ss : dataSnapshot.getChildren()) {
                    if(Objects.requireNonNull(ss.child("hostelname").getValue()).equals("MV hall polytechnic")){
                        Users user = ss.getValue( Users.class );
                        Task_Class task = ss.getValue( Task_Class.class );
                        keyValue = ss.getKey();
                        Objects.requireNonNull(task).setKey( keyValue );
                        list.add( task );
                        list_data.add( user );


                    }

                }
                adaptor = new Mv_hall_adeptor( MV_hall.this, list_data, list );
                rv.setAdapter( adaptor );

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        } );

    }
}
