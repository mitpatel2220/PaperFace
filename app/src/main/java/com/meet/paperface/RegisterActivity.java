package com.meet.paperface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText Username, Register_email, Register_Password;
    FirebaseAuth fa;


    Button Sign_Up, Google_sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Username = findViewById(R.id.userName);
        Register_email = findViewById(R.id.emailRegister);
        Register_Password = findViewById(R.id.passwordRegister);

        Sign_Up = findViewById(R.id.signUpButton);
        Google_sign = findViewById(R.id.gmailButton);

        fa = FirebaseAuth.getInstance();


        Sign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name1 = Username.getText().toString();

                String email = Register_email.getText().toString();
                String password = Register_Password.getText().toString();

                if (name1.isEmpty()) {

                    Toast.makeText(RegisterActivity.this, "Please enter Your Name", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty()) {

                    Toast.makeText(RegisterActivity.this, "Please enter E-mail", Toast.LENGTH_SHORT).show();


                } else if (password.isEmpty()) {

                    Toast.makeText(RegisterActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();


                } else {

                    fa.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                Intent in = new Intent(RegisterActivity.this, MainLayout.class);
                                startActivity(in);
                                finish();
                                Toast.makeText(RegisterActivity.this, "Ragistration Completed", Toast.LENGTH_SHORT).show();


                            } else {
                                Toast.makeText(RegisterActivity.this, "Ragistration Failed", Toast.LENGTH_SHORT).show();

                            }


                        }
                    });

                }
            }
        });

        Google_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


    }
}
