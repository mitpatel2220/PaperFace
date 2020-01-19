package com.meet.paperface.activity;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
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
import com.meet.paperface.BottomSheetPassword;
import com.meet.paperface.MainLayout;
import com.meet.paperface.R;

import java.util.regex.Pattern;
public class Login_Activity extends AppCompatActivity implements BottomSheetPassword.BottomSheetListenerPass {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile( "^" +
                             //"(?=.*[0-9])" +         //at least 1 digit
                             //"(?=.*[a-z])" +         //at least 1 lower case letter
                             //"(?=.*[A-Z])" +         //at least 1 upper case letter
                             //"(?=.*[a-zA-Z])" +      //any letter
                             // "(?=.*[@#$%^&+=])" +    //at least 1 special character
                             "(?=\\S+$)" +           //no white spaces
                             ".{6,8}" +               //at least 4 characters
                             "$" );
    TextView signUp, shopKeeper, forgot_Password;
    FirebaseAuth fa;
    EditText email, password;
    Button log_in;
    SharedPreferences sp;
    int click = 1;
    public static final String mypreference = "mypreference";
    public static final String hello = "login";
    private ProgressDialog mRegProgress;

    @RequiresApi(api = Build.VERSION_CODES.M)
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
        mRegProgress = new ProgressDialog( this );
        sp = getSharedPreferences( mypreference,
                                   Context.MODE_PRIVATE );
        checkConnection();
        final FirebaseUser fbu = fa.getCurrentUser();
        if (fbu != null) {
            Intent in = new Intent( Login_Activity.this, MainLayout.class );
            startActivity( in );
            finish();

        }
        if (click == 1) {
            password.setTransformationMethod( new PasswordTransformationMethod() );
            click++;
        }
        password.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click == 2) {
                    password.setTransformationMethod( null );
                    click--;
                } else if (click == 1) {
                    password.setTransformationMethod( new PasswordTransformationMethod() );
                    click++;
                }
            }
        } );
        signUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Login_Activity.this, Register_Activity.class );
                startActivity( intent );
            }
        } );
        shopKeeper.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Login_Activity.this, Shopkeeper_Activity.class );
                startActivity( intent );
            }
        } );
        log_in.setOnLongClickListener( new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDialoge();
                return false;
            }
        } );
        log_in.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_s = email.getText().toString();
                String password_s = password.getText().toString();
                if (email_s.isEmpty()) {
                    Toast.makeText( Login_Activity.this, "Field can't be empty", Toast.LENGTH_SHORT ).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher( email_s ).matches()) {
                    Toast.makeText( Login_Activity.this, "Please enter a valid email address", Toast.LENGTH_SHORT ).show();
                } else if (password_s.isEmpty()) {
                    Toast.makeText( Login_Activity.this, "Please enter your password", Toast.LENGTH_SHORT ).show();
                } else if (!PASSWORD_PATTERN.matcher( password_s ).matches()) {
                    Toast.makeText( Login_Activity.this, "Password length should be minimum 6 and not more than 8. It may have characters with digits", Toast.LENGTH_LONG ).show();
                } else {
                    mRegProgress.setTitle( "Loging In" );
                    mRegProgress.setMessage( "Please wait while we create your account !" );
                    mRegProgress.setCanceledOnTouchOutside( false );
                    mRegProgress.show();
                    fa.signInWithEmailAndPassword( email_s, password_s ).addOnCompleteListener( Login_Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString( hello, "log-in" );
                                editor.commit();
                                mRegProgress.dismiss();
                                Intent in = new Intent( Login_Activity.this, MainLayout.class );
                                startActivity( in );
                                finish();
                                Toast.makeText( Login_Activity.this, "Login successful", Toast.LENGTH_SHORT ).show();
                            } else {
                                mRegProgress.hide();
                                Toast.makeText( Login_Activity.this, "Login unsuccessful", Toast.LENGTH_SHORT ).show();
                            }
                        }
                    } );
                }
            }
        } );
        forgot_Password.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetPassword bottomSheetPassword = new BottomSheetPassword();
                bottomSheetPassword.show( getSupportFragmentManager(), "bottomSheetPass" );

            }
        } );
    }

    @Override
    public void onButtonclicked(String text) {
        fa.sendPasswordResetEmail( text ).addOnCompleteListener( new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText( Login_Activity.this, "Reset link has been sent to your gmail account", Toast.LENGTH_SHORT ).show();

                }

            }
        } );

    }

    public void checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService( Context.CONNECTIVITY_SERVICE );
        NetworkInfo network = connectivityManager.getActiveNetworkInfo();
        if (network != null) {
            if (network.getType() == ConnectivityManager.TYPE_WIFI) {
//                Toast.makeText( getApplicationContext(), "WIFI ENABLED", Toast.LENGTH_SHORT ).show();
            } else if (network.getType() == ConnectivityManager.TYPE_MOBILE) {
//                Toast.makeText( getApplicationContext(), "Mob ENABLED", Toast.LENGTH_SHORT ).show();
            }
        } else {
            showDialoge();
        }
    }

    public void showDialoge() {
        AlertDialog.Builder builderDia = new AlertDialog.Builder( this );
        builderDia.setTitle( "No Internet Connection" );
        builderDia.setMessage( "You need to have Mobile Internet Connection or Wifi to access this.\n\nPress OK to Exit" );
        builderDia.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        } );
        builderDia.show();
    }
}