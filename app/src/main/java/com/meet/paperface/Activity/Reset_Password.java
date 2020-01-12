package com.meet.paperface.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.meet.paperface.R;

public class Reset_Password extends AppCompatActivity {
    FirebaseAuth fa;
     EditText email;
     Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset__password);

        fa = FirebaseAuth.getInstance();

        email=findViewById(R.id.edittext);
        done=findViewById(R.id.done);


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fa.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(Reset_Password.this, "Reset link sended in Your Gmail account", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }
        });

    }
}
