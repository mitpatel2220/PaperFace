package com.meet.paperface.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meet.paperface.Adapter.Recycle_Adapter;
import com.meet.paperface.HostelName.Diamond_jubile;
import com.meet.paperface.HostelName.MV_hall;
import com.meet.paperface.HostelName.Meghamani_Parivar;
import com.meet.paperface.HostelName.RT_Hall;
import com.meet.paperface.HostelName.SJ_hall;
import com.meet.paperface.R;
import com.meet.paperface.Model.Task_Class;
import com.meet.paperface.Model.Users;

import java.util.ArrayList;
import java.util.List;
public class Recycler_Activity extends AppCompatActivity {

    //    List<Users> list_data = new ArrayList<>();
//    List<Task_Class> list = new ArrayList<>();
//    RecyclerView rv;
//    Recycle_Adapter adaptor;
//    private DatabaseReference mUsersDatabase;
//    private LinearLayoutManager mLayoutManager;
//    String keyValue;
    CardView Mv_hall, RT_hall, SJ_hall, Diamond_jubily, Meghani_parivar, others;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_recycler_ );
//        Toolbar toolbar = findViewById( R.id.toolbar );
//        setSupportActionBar( toolbar );
//        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child( "Orders" );
//        mLayoutManager = new LinearLayoutManager( this );
//        rv = findViewById( R.id.recycler_one );
//        rv.setHasFixedSize( true );
//        rv.setLayoutManager( new LinearLayoutManager( this ) );
//        mUsersDatabase.addValueEventListener( new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot ss : dataSnapshot.getChildren()) {
//                    if(ss.child("hostelname").getValue().equals("meet")){
//                        Users user = ss.getValue( Users.class );
//                        Task_Class task = ss.getValue( Task_Class.class );
//                        keyValue = ss.getKey();
//                        task.setKey( keyValue );
//                        list.add( task );
//                        list_data.add( user );
//
//
//                    }
//
//                }
//                adaptor = new Recycle_Adapter( Recycler_Activity.this, list_data, list );
//                rv.setAdapter( adaptor );
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        } );
        Mv_hall = findViewById( R.id.MV_hall );
        RT_hall = findViewById( R.id.RT_hall );
        SJ_hall = findViewById( R.id.SJ_hall );
        Diamond_jubily = findViewById( R.id.Diamond_Jubilee );
        Meghani_parivar = findViewById( R.id.Meghani_Parivar );
        others = findViewById( R.id.others );
        floatingActionButton = findViewById( R.id.fab );
        
        floatingActionButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Recycler_Activity.this, Story_Activity.class );
                startActivity( intent );
            }
        } );
        Mv_hall.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent( Recycler_Activity.this, MV_hall.class );
                startActivity( in );

            }
        } );
        RT_hall.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent( Recycler_Activity.this, RT_Hall.class );
                startActivity( in );

            }
        } );
        SJ_hall.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent( Recycler_Activity.this, SJ_hall.class );
                startActivity( in );

            }
        } );
        Diamond_jubily.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent( Recycler_Activity.this, Diamond_jubile.class );
                startActivity( in );

            }
        } );
        Meghani_parivar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent( Recycler_Activity.this, Meghamani_Parivar.class );
                startActivity( in );

            }
        } );
        others.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent( Recycler_Activity.this, Others_Address.class );
                startActivity( in );

            }
        } );

    }
}
