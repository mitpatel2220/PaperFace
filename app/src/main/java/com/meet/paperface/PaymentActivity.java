package com.meet.paperface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

public class PaymentActivity extends AppCompatActivity {

    EditText name_Payment, Room_number_Payment, hostel_name_Payment, others_Payment, mobileNo_payment;

    RadioButton online_Payment_Radio, cod_Radio, clicked_radio;

    Button Place_order;

    RadioGroup radioGroup;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    DatabaseReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        Intent intent = getIntent();
        final String page = intent.getStringExtra("total_page");
        final String rs = intent.getStringExtra("total_rs");


        name_Payment = findViewById(R.id.name_payment);
        mobileNo_payment = findViewById(R.id.mobile_no_payment);
        Room_number_Payment = findViewById(R.id.room_number_Payment);
        hostel_name_Payment = findViewById(R.id.hostel_name_payment);
        others_Payment = findViewById(R.id.others_payment);

        online_Payment_Radio = findViewById(R.id.payOnline_Payment_Radio);
        cod_Radio = findViewById(R.id.cod_Payment_Radio);

        radioGroup = findViewById(R.id.radio_grp);

        Place_order = findViewById(R.id.place_Order_Payment);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Orders");
        dr = FirebaseDatabase.getInstance().getReference().child("YourOrder");


        final String myuid = firebaseUser.getUid().toString();


        Place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name = name_Payment.getText().toString();
                String mobilenumber = mobileNo_payment.getText().toString();
                String Room_no = Room_number_Payment.getText().toString();
                String hostelName = hostel_name_Payment.getText().toString();
                String otherPayment = others_Payment.getText().toString();

                int id = radioGroup.getCheckedRadioButtonId();
                clicked_radio = findViewById(id);

                String click = clicked_radio.getText().toString();

                if (otherPayment.isEmpty()) {

                    otherPayment = "--";
                }

                if (name.isEmpty()) {

                    Toast.makeText(PaymentActivity.this, "Please enter Your Name", Toast.LENGTH_SHORT).show();
                } else if (mobilenumber.isEmpty()) {
                    Toast.makeText(PaymentActivity.this, "Please enter Your Mobile No.", Toast.LENGTH_SHORT).show();


                } else if (Room_no.isEmpty()) {
                    Toast.makeText(PaymentActivity.this, "Please enter Your Room No.", Toast.LENGTH_SHORT).show();


                } else if (hostelName.isEmpty()) {

                    Toast.makeText(PaymentActivity.this, "Please enter Your Hostel Name", Toast.LENGTH_SHORT).show();

                } else {

                    if (click.equals("Pay Online")) {


                    } else {

                        HashMap<String, String> map = new HashMap<>();
                        map.put("hostelname", hostelName);
                        map.put("mobileno", mobilenumber);
                        map.put("name", name);
                        map.put("other", otherPayment);
                        map.put("payment", "Payment Left");
                        map.put("roomno", Room_no);
                        map.put("totalpage", page);
                        map.put("totalrs", rs);
                        map.put("uid",myuid);

                        final String currentDate = DateFormat.getDateTimeInstance().format(new Date());

                        HashMap<String, String> hashMap = new HashMap<>();

                        hashMap.put("page", page);
                        hashMap.put("rs", rs);
                            hashMap.put("date",currentDate);


                            dr.child(myuid).push().setValue(hashMap).addOnCompleteListener(PaymentActivity.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {

                                        Toast.makeText(PaymentActivity.this, "Your Order is successfully Placed", Toast.LENGTH_SHORT).show();


                                    } else {

                                        Toast.makeText(PaymentActivity.this, "Something Error", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                        databaseReference.push().setValue(map).addOnCompleteListener(PaymentActivity.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {

                                    Toast.makeText(PaymentActivity.this, "Your Order is successfully Placed", Toast.LENGTH_SHORT).show();


                                } else {

                                    Toast.makeText(PaymentActivity.this, "Something Error", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });


                    }


                }


            }
        });


    }


}
