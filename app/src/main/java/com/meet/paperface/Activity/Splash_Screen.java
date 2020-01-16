package com.meet.paperface.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.meet.paperface.Fragment.Home_Fragment;
import com.meet.paperface.Fragment.Story_Fragment;
import com.meet.paperface.MainLayout;
import com.meet.paperface.R;
public class Splash_Screen extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                              WindowManager.LayoutParams.FLAG_FULLSCREEN );
        
        setContentView( R.layout.activity_splash__screen );
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep( 2000 );
                    if (firebaseUser == null) {
                        Intent intent = new Intent( Splash_Screen.this, Login_Activity.class );
                        startActivity( intent );
                    } else {
                        Intent intent = new Intent( Splash_Screen.this, MainLayout.class );
                        startActivity( intent );
                    }
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}