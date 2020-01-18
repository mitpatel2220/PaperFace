package com.meet.paperface.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meet.paperface.MainLayout;
import com.meet.paperface.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class Payment_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText name_Payment, Room_number_Payment, others_Payment, mobileNo_payment;
    RadioButton online_Payment_Radio, cod_Radio, clicked_radio;
    Button Place_order;
    RadioGroup radioGroup;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference, drAvail;
    DatabaseReference dr;
    Spinner hostel_name_Payment;

    private final String[] HOSTEL_NAME = new String[]{"RT hall polytechnic", "MV hall polytechnic", "SJ hall polytechnic", "Diamond Jubilee Boys Hostel", "Meghamani parivar diamond boys hostel", "Others"};

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
        others_Payment = findViewById(R.id.others_payment);
        online_Payment_Radio = findViewById(R.id.payOnline_Payment_Radio);
        cod_Radio = findViewById(R.id.cod_Payment_Radio);
        radioGroup = findViewById(R.id.radio_grp);
        Place_order = findViewById(R.id.place_Order_Payment);

        hostel_name_Payment = findViewById(R.id.hostel_name_payment);

        drAvail = FirebaseDatabase.getInstance().getReference().child("Available");


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        final String myuid = firebaseUser.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Orders");
        dr = FirebaseDatabase.getInstance().getReference().child("YourOrder");

        hostel_name_Payment.setTag("Please select hostel");
        hostel_name_Payment.setPrompt("Please select hostel");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.HostelName, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hostel_name_Payment.setAdapter(adapter);
        hostel_name_Payment.setOnItemSelectedListener(this);


        Place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                drAvail.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        String x = dataSnapshot.child("yes").getValue().toString();
                        String pages = dataSnapshot.child("pages").getValue().toString();

                        if (x.equals("yes")) {

                            // dialoge

                            showDialogeforpaper();



                        } else {

                            String name = name_Payment.getText().toString();
                            String mobilenumber = mobileNo_payment.getText().toString();
                            String Room_no = Room_number_Payment.getText().toString();
                            String hostelName = hostel_name_Payment.getTag().toString();
                            String otherPayment = others_Payment.getText().toString();
                            int id = radioGroup.getCheckedRadioButtonId();
                            clicked_radio = findViewById(id);
                            String click = clicked_radio.getText().toString();
                            if (otherPayment.isEmpty()) {
                                otherPayment = "--";
                            }

                            if (name.isEmpty()) {
                                name_Payment.setError("Please enter Your Name");
                                Toast.makeText(Payment_Activity.this, "Please enter Your Name", Toast.LENGTH_SHORT).show();
                            } else if (mobilenumber.isEmpty()) {
                                mobileNo_payment.setError("Please enter Your Mobile No.");
                                Toast.makeText(Payment_Activity.this, "Please enter Your Mobile No.", Toast.LENGTH_SHORT).show();

                            } else if (Room_no.isEmpty()) {
                                Room_number_Payment.setError("Please enter Your Room No.");
                                Toast.makeText(Payment_Activity.this, "Please enter Your Room No.", Toast.LENGTH_SHORT).show();

                            } else if (hostelName.isEmpty()) {

                                Toast.makeText(Payment_Activity.this, "Please enter Your Hostel Name", Toast.LENGTH_SHORT).show();

                            } else {
                                if (click.equals("Pay Online")) {


                                    String s1 = rs;
                                    String transactionNote = name + " pay " + rs + " Done ";
                                    String currencyUnit = "INR";
                                    Uri uri = Uri.parse("upi://pay?pa=" + "jbbram681@okicici" + "&pn=" + "Meet " + "&tn=" + transactionNote +
                                            "&am=" + s1 + "&cu=" + currencyUnit);
                                    Intent intent = new Intent();
                                    intent.setData(uri);
                                    intent.setPackage("com.google.android.apps.nbu.paisa.user");
                                    Intent chooser = Intent.createChooser(intent, "Pay with...");
                                    startActivityForResult(chooser, 1, null);


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
                                    map.put("uid", myuid);
                                    final String currentDate = DateFormat.getDateTimeInstance().format(new Date());
                                    HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put("page", page);
                                    hashMap.put("rs", rs);
                                    hashMap.put("date", currentDate);
                                    dr.child(myuid).push().setValue(hashMap).addOnCompleteListener(Payment_Activity.this, new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {


                                                Toast.makeText(Payment_Activity.this, "Your Order is successfully Placed", Toast.LENGTH_SHORT).show();

                                            } else {
                                                Toast.makeText(Payment_Activity.this, "Something Error", Toast.LENGTH_SHORT).show();

                                            }

                                        }
                                    });
                                    databaseReference.push().setValue(map).addOnCompleteListener(Payment_Activity.this, new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                                Intent in = new Intent(Payment_Activity.this, MainLayout.class);
                                                startActivity(in);
                                                finish();
                                                Toast.makeText(Payment_Activity.this, "Your Order is successfully Placed", Toast.LENGTH_SHORT).show();

                                            } else {
                                                Toast.makeText(Payment_Activity.this, "Something Error", Toast.LENGTH_SHORT).show();

                                            }

                                        }
                                    });

                                }

                            }


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(Payment_Activity.this, text, Toast.LENGTH_SHORT).show();
        hostel_name_Payment.setTag(text);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//&& data != null
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String res = data.getStringExtra("response");
            String search = "SUCCESS";
            if (Objects.requireNonNull(res).toLowerCase().contains(search.toLowerCase())) {

                String name = name_Payment.getText().toString();
                String mobilenumber = mobileNo_payment.getText().toString();
                String Room_no = Room_number_Payment.getText().toString();
                String hostelName = hostel_name_Payment.getTag().toString();
                String otherPayment = others_Payment.getText().toString();

                Intent intent = getIntent();
                final String page = intent.getStringExtra("total_page");
                final String rs = intent.getStringExtra("total_rs");

                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                final String myuid = firebaseUser.getUid();

                databaseReference = FirebaseDatabase.getInstance().getReference().child("Orders");
                dr = FirebaseDatabase.getInstance().getReference().child("YourOrder");


                HashMap<String, String> map = new HashMap<>();
                map.put("hostelname", hostelName);
                map.put("mobileno", mobilenumber);
                map.put("name", name);
                map.put("other", otherPayment);
                map.put("payment", "Payment Done");
                map.put("roomno", Room_no);
                map.put("totalpage", page);
                map.put("totalrs", rs);
                map.put("uid", myuid);

                final String currentDate = DateFormat.getDateTimeInstance().format(new Date());
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("page", page);
                hashMap.put("rs", rs);
                hashMap.put("date", currentDate);

                dr.child(myuid).push().setValue(hashMap).addOnCompleteListener(Payment_Activity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {


                            Toast.makeText(Payment_Activity.this, "Your Order is successfully Placed", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(Payment_Activity.this, "Something Error", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
                databaseReference.push().setValue(map).addOnCompleteListener(Payment_Activity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Intent in = new Intent(Payment_Activity.this, MainLayout.class);
                            startActivity(in);
                            finish();
                            Toast.makeText(Payment_Activity.this, "Your Order is successfully Placed", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(Payment_Activity.this, "Something Error", Toast.LENGTH_SHORT).show();

                        }

                    }
                });


                Intent in = new Intent(Payment_Activity.this, MainLayout.class);
                startActivity(in);
                finish();


                Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void showDialogeforpaper() {
        AlertDialog.Builder builderDia = new AlertDialog.Builder(this);
        // builderDia.setTitle("No Internet Connection");
        builderDia.setMessage("Pages are not available right now\n\nPress OK to Exit");
        builderDia.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builderDia.show();
    }
}

