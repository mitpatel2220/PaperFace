package com.meet.paperface.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.meet.paperface.MainLayout;
import com.meet.paperface.R;
public class RegisterActivity extends AppCompatActivity {

    EditText Username, Register_email, Register_Password;
    FirebaseAuth mAuth;
    Button Sign_Up, Google_sign;
    TextView text;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "GoogleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );
        Username = findViewById( R.id.userName );
        Register_email = findViewById( R.id.emailRegister );
        Register_Password = findViewById( R.id.passwordRegister );
        Sign_Up = findViewById( R.id.signUpButton );
        Google_sign = findViewById( R.id.gmailButton );
        text = findViewById( R.id.or );
        mAuth = FirebaseAuth.getInstance();
        Sign_Up.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name1 = Username.getText().toString();
                String email = Register_email.getText().toString();
                String password = Register_Password.getText().toString();
                if (name1.isEmpty()) {
                    Toast.makeText( RegisterActivity.this, "Please enter your name", Toast.LENGTH_SHORT ).show();
                } else if (email.isEmpty()) {
                    Toast.makeText( RegisterActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT ).show();
                } else if (password.isEmpty()) {
                    Toast.makeText( RegisterActivity.this, "Please enter password", Toast.LENGTH_SHORT ).show();
                } else {
                    mAuth.createUserWithEmailAndPassword( email, password ).addOnCompleteListener( RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent in = new Intent( RegisterActivity.this, MainLayout.class );
                                startActivity( in );
                                finish();
                                Toast.makeText( RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT ).show();
                            } else {
                                Toast.makeText( RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT ).show();
                            }
                        }
                    } );
                }
            }
        } );
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder()
                .requestIdToken( getString( R.string.default_web_client_id ) )
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient( this, gso );
        Google_sign.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        } );
        if (mAuth.getCurrentUser() != null) {
            FirebaseUser user = mAuth.getCurrentUser();
            updateUI( user );
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult( signInIntent, RC_SIGN_IN );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent( data );
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult( ApiException.class );
                firebaseAuthWithGoogle( account );
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w( TAG, "Google sign in failed", e );
                Toast.makeText( this, e.getMessage(), Toast.LENGTH_SHORT ).show();
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d( TAG, "firebaseAuthWithGoogle:" + acct.getId() );
        AuthCredential credential = GoogleAuthProvider.getCredential( acct.getIdToken(), null );
        mAuth.signInWithCredential( credential )
                .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d( TAG, "signInWithCredential:success" );
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI( user );
                            Toast.makeText( RegisterActivity.this, "Authentication Successful", Toast.LENGTH_SHORT ).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w( TAG, "signInWithCredential:failure", task.getException() );
                            updateUI( null );
                            Toast.makeText( RegisterActivity.this, "Authentication failed", Toast.LENGTH_SHORT ).show();
                        }
                        // ...
                    }
                } );
    }

    private void updateUI(FirebaseUser user) {
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount( getApplicationContext() );
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
//            String photo = String.valueOf( user.getPhotoUrl() );
            text.setText( "Info:\n" );
            text.setText( name + "\n" );
//            text.setText( name );
            text.setText( email );

        } else {
            text.setText( "Sign_Out_Successfully" );
        }
    }
    
}