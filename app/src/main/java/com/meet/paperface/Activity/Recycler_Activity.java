package com.meet.paperface.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meet.paperface.Adapter.Adeptorforrecycle;
import com.meet.paperface.R;
import com.meet.paperface.Model.TaskClass;
import com.meet.paperface.Model.Users;

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.recycle_menu,menu);
        MenuItem item=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) item.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

            //    adaptor.getFilter().filter(s);

                return false;
            }
        });
        return true;


    }
}
