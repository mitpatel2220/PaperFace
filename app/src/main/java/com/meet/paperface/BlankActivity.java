package com.meet.paperface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meet.paperface.activity.Payment_Activity;
import com.meet.paperface.adapter.View_Pager_Adapter;
import com.meet.paperface.model.View_Pager_Model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class BlankActivity extends AppCompatActivity {
    private Button ok;
    private Button done;
    private EditText edit_how;
    private EditText edit_extra;
    private TextView edit_page;
    private TextView edit_rs;
    private TextView textview1;
    private ViewPager viewPager;
    private int sec = 0;
    private DatabaseReference dr,dr1;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);

        ok = findViewById(R.id.ok);
        done = findViewById(R.id.done);
        edit_how = findViewById(R.id.edit_How);
        edit_extra = findViewById(R.id.edit_Extra);
        edit_page = findViewById(R.id.edit_Pages);
        edit_rs = findViewById(R.id.edit_Rs);
        viewPager = findViewById(R.id.pager);
        textview1 = findViewById(R.id.textView1);
        List<View_Pager_Model> view_pager_models = new ArrayList<>();
        View_Pager_Adapter view_pager_adapter = new View_Pager_Adapter(view_pager_models, getApplicationContext());
        view_pager_models.add(new View_Pager_Model(R.drawable.sheet_third));
        view_pager_models.add(new View_Pager_Model(R.drawable.sheet_first));
        view_pager_models.add(new View_Pager_Model(R.drawable.sheet_sec));
        view_pager_models.add(new View_Pager_Model(R.drawable.sheet_tlt));
        viewPager.setAdapter(view_pager_adapter);
        runnable.run();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        final String myuid = Objects.requireNonNull(firebaseUser).getUid();


         dr1 = FirebaseDatabase.getInstance().getReference().child("Price");

        dr = FirebaseDatabase.getInstance().getReference().child("YourOrderPage");


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);




//        dr.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                textview1.setText(Objects.requireNonNull(dataSnapshot.getValue()).toString() + " Rs of 100 Pages");
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String pages = edit_how.getText().toString();
                String extrapages = edit_extra.getText().toString();

                String pages1 = edit_how.getText().toString();

                String pages2 = edit_how.getText().toString();

                if (pages2.isEmpty() || pages2.equals("0") || pages2.equals("00") || pages2.equals("000") || pages2.equals("0000")) {
                    Toast.makeText(BlankActivity.this, "Please enter Bunch of pages", Toast.LENGTH_SHORT).show();

                    edit_how.setError("Please enter Bunch of pages");

                } else {


                    dr1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String pages = edit_how.getText().toString();
                            String extrapages = edit_extra.getText().toString();
                            int total_pages;
                            float total_rs;
                            int i = Integer.parseInt(dataSnapshot.getValue().toString());

                            if (extrapages.isEmpty()) {
                                extrapages = "0";

                            }
                            int pages_int = Integer.parseInt(pages);
                            int extra_int = Integer.parseInt(extrapages);
                            total_pages = (pages_int * 100) + extra_int;
                            edit_page.setText(total_pages + "");
                            total_rs = (float) ((total_pages * i) / 100.0);
                            String x = total_rs + "0";
                            edit_rs.setText(x);



//                            Intent intent = new Intent(SinglePageActivity.this, Payment_Activity.class);
//                            intent.putExtra("total_page", total_pages + "");
//                            intent.putExtra("total_rs", x);


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }

            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dr1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        float total_rs;
                        String pages = edit_how.getText().toString();
                        String extrapages = edit_extra.getText().toString();
                        if (extrapages.isEmpty()) {
                            extrapages = "0";

                        }
                        String pages1 = edit_how.getText().toString();
                        int total_pages;
                        if (pages1.isEmpty()) {
                            Toast.makeText(BlankActivity.this, "Please enter Bunch of pages", Toast.LENGTH_SHORT).show();

                        } else if (edit_page.getText().toString().isEmpty()) {
                            Toast.makeText(BlankActivity.this, "Please Touch on ok", Toast.LENGTH_SHORT).show();

                        } else {
                            int pages_int = Integer.parseInt(pages);
                            int extra_int = Integer.parseInt(extrapages);
                            total_pages = (pages_int * 100) + extra_int;

                            int i = Integer.parseInt(Objects.requireNonNull(dataSnapshot.getValue()).toString());


                            total_rs = (float) ((total_pages * i) / 100.0);
                            String x = total_rs + "0";
                            final String currentDate = DateFormat.getDateTimeInstance().format(new Date());

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("page", total_pages + "");
                            hashMap.put("rs", x);
                            hashMap.put("date", currentDate);
                            hashMap.put("type","Blank Pages");

                            dr.child(myuid).push().setValue(hashMap).addOnCompleteListener(BlankActivity.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        Intent intent = new Intent(BlankActivity.this, MainLayout.class);
                                        startActivity(intent);
                                        finish();

                                        Toast.makeText(BlankActivity.this, "Your Order is successfully Placed", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(BlankActivity.this, "Something Error", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });


//                            Intent intent = new Intent(SinglePageActivity.this, Payment_Activity.class);
//                            intent.putExtra("total_page", total_pages + "");
//                            intent.putExtra("total_rs", x);
//                            startActivity(intent);


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (sec <= 4) {
                if (sec == 4) {
                    sec = 0;
                }
                viewPager.setCurrentItem(sec);
                sec++;
                handler.postDelayed(this, 2000);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks( runnable );

    }
}