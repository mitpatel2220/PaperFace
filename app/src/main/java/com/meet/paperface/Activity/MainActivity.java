package com.meet.paperface.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.meet.paperface.MainLayout;
import com.meet.paperface.R;

import java.util.regex.Pattern;
public class MainActivity extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN = 
            Pattern.compile("^" +
                            //"(?=.*[0-9])" +         //at least 1 digit
                            //"(?=.*[a-z])" +         //at least 1 lower case letter
                            //"(?=.*[A-Z])" +         //at least 1 upper case letter
                            "(?=.*[a-zA-Z])" +      //any letter
                           // "(?=.*[@#$%^&+=])" +    //at least 1 special character
                            "(?=\\S+$)" +           //no white spaces
                            ".{6,8}" +               //at least 4 characters
                            "$");

    TextView signUp, shopKeeper, forgot_Password;
    FirebaseAuth fa;
    EditText email, password;
    Button log_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        signUp = findViewById( R.id.signUp );
        shopKeeper = findViewById( R.id.shopkeeper );
        forgot_Password = findViewById( R.id.forgot );
        email = findViewById( R.id.email );
        password = findViewById( R.id.password );
        log_in = findViewById( R.id.log_in );
        fa = FirebaseAuth.getInstance();
        final FirebaseUser fbu = fa.getCurrentUser();
        if (fbu != null) {
            Intent in = new Intent( MainActivity.this, MainLayout.class );
            startActivity( in );
            finish();

        }
        signUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, Register_Activity.class );
                startActivity( intent );
            }
        } );
        shopKeeper.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, Shopkeeper_Activity.class );
                startActivity( intent );
            }
        } );
        log_in.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_s = email.getText().toString();
                String password_s = password.getText().toString();
                if (email_s.isEmpty()) {
                    Toast.makeText( MainActivity.this, "Field can't be empty", Toast.LENGTH_SHORT ).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher( email_s).matches()){
                    Toast.makeText( MainActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT ).show();
                }else if (password_s.isEmpty()) {
                    Toast.makeText( MainActivity.this, "Please enter your password", Toast.LENGTH_SHORT ).show();
                }else if (!PASSWORD_PATTERN.matcher( password_s ).matches()){
                    Toast.makeText( MainActivity.this, "Password length should be minimum 6 and not more than 8. It may have characters with digits", Toast.LENGTH_LONG ).show();
                }else {
                    fa.signInWithEmailAndPassword( email_s, password_s ).addOnCompleteListener( MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent in = new Intent( MainActivity.this, MainLayout.class );
                                startActivity( in );
                                finish();
                                Toast.makeText( MainActivity.this, "Login successful", Toast.LENGTH_SHORT ).show();
                            } else {
                                Toast.makeText( MainActivity.this, "Login unsuccessful", Toast.LENGTH_SHORT ).show();
                            }
                        }
                    } );
                }
            }
        } );
        forgot_Password.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        } );
    }
}