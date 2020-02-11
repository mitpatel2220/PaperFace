package com.meet.paperface.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meet.paperface.AllOrder;
import com.meet.paperface.R;
import com.meet.paperface.hostelName.Diamond_jubile;
import com.meet.paperface.hostelName.MV_hall;
import com.meet.paperface.hostelName.Meghamani_Parivar;
import com.meet.paperface.hostelName.Others_Address;
import com.meet.paperface.hostelName.RT_Hall;
import com.meet.paperface.hostelName.SJ_hall;

import java.util.Objects;

public class Recycler_Activity extends AppCompatActivity {


    private CardView Mv_hall;
    private CardView RT_hall;
    private CardView SJ_hall;
    private CardView Diamond_jubily;
    private CardView Meghani_parivar;
    private CardView others;
    private CardView all_orders;

    TextView countRThall, countMVhall, countSJhall, countDiamondJubily, countMeghaniParivar, countOthers, countAllorder;

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_);

        countRThall = findViewById(R.id.countRThall);
        countMVhall = findViewById(R.id.countMVhall);
        countSJhall = findViewById(R.id.countSJhall);
        countDiamondJubily = findViewById(R.id.countDiamondJubily);
        countMeghaniParivar = findViewById(R.id.countMeghaniParivar);
        countOthers = findViewById(R.id.countOthers);
        countAllorder = findViewById(R.id.countAllorder);


        Mv_hall = findViewById(R.id.MV_hall);
        RT_hall = findViewById(R.id.RT_hall);
        SJ_hall = findViewById(R.id.SJ_hall);
        Diamond_jubily = findViewById(R.id.Diamond_Jubilee);
        Meghani_parivar = findViewById(R.id.Meghani_Parivar);
        others = findViewById(R.id.others);
        floatingActionButton = findViewById(R.id.fab);
        all_orders = findViewById(R.id.all_orders);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Recycler_Activity.this, Story_Activity.class);
                startActivity(intent);
            }
        });
        Mv_hall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Recycler_Activity.this, MV_hall.class);
                startActivity(in);

            }
        });
        RT_hall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Recycler_Activity.this, RT_Hall.class);
                startActivity(in);

            }
        });
        SJ_hall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Recycler_Activity.this, SJ_hall.class);
                startActivity(in);

            }
        });
        Diamond_jubily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Recycler_Activity.this, Diamond_jubile.class);
                startActivity(in);

            }
        });
        Meghani_parivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Recycler_Activity.this, Meghamani_Parivar.class);
                startActivity(in);

            }
        });
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Recycler_Activity.this, Others_Address.class);
                startActivity(in);

            }
        });

        all_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(Recycler_Activity.this, AllOrder.class);
                startActivity(in);

                Toast.makeText(Recycler_Activity.this, "Clicked on All Orders", Toast.LENGTH_SHORT).show();
            }
        });


        DatabaseReference mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Orders");
        DatabaseReference mUsersDatabase1 = FirebaseDatabase.getInstance().getReference().child("Allorders");
        mUsersDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int i=0;
                for (DataSnapshot ss : dataSnapshot.getChildren()) {

                    i++;


                }

                countAllorder.setText(i+"");
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int diamondj = 0;
                int sj = 0;
                int mv = 0;
                int meghani = 0;
                int rt = 0;
                int other = 0;
                int all = 0;

                for (DataSnapshot ss : dataSnapshot.getChildren()) {
                    if (Objects.requireNonNull(ss.child("hostelname").getValue()).equals("Diamond Jubilee Boys Hostel")) {

                        diamondj++;

                    }
                    if (Objects.requireNonNull(ss.child("hostelname").getValue()).equals("Meghamani parivar diamond boys hostel")) {
                        meghani++;
                    }
                    if (Objects.requireNonNull(ss.child("hostelname").getValue()).equals("MV hall polytechnic")) {
                        mv++;

                    }
                    if (Objects.requireNonNull(ss.child("hostelname").getValue()).equals("Others")) {
                        other++;
                    }
                    if (Objects.requireNonNull(ss.child("hostelname").getValue()).equals("RT hall polytechnic")) {
                        rt++;
                    }

                    if (Objects.requireNonNull(ss.child("hostelname").getValue()).equals("SJ hall polytechnic")) {
                        sj++;
                    }


                }

                countDiamondJubily.setText(diamondj+"");
                countMeghaniParivar.setText(meghani+"");
                countMVhall.setText(mv+"");
                countOthers.setText(other+"");
                countRThall.setText(rt+"");
                countSJhall.setText(sj+"");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
