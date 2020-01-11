package com.meet.paperface.Activity;
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
import com.meet.paperface.Adapter.Adeptorforrecycle;
import com.meet.paperface.R;
import com.meet.paperface.TaskClass;
import com.meet.paperface.Users;

import java.util.ArrayList;
import java.util.List;

public class Recycler_Activity extends AppCompatActivity {

    List<Users> listdata = new ArrayList<>();

    List<TaskClass> list=new ArrayList<>();
    RecyclerView rv;
    Adeptorforrecycle adaptor;
    private DatabaseReference mUsersDatabase;
    private LinearLayoutManager mLayoutManager;
    String keyValue;

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
                    TaskClass task=ss.getValue(TaskClass.class);
                    keyValue=ss.getKey();
                    task.setKey(keyValue);
                    list.add(task);
                    listdata.add(user);

                }
                adaptor = new Adeptorforrecycle(Recycler_Activity.this, listdata,list);
                rv.setAdapter(adaptor);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


}
