package com.meet.paperface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    TextView signUp, shopKeeper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signUp = findViewById( R.id.signUp );
        shopKeeper = findViewById( R.id.shopkeeper );
        
        signUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, RegisterActivity.class );
                startActivity( intent );
            }
        } );
        
        shopKeeper.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, ShopkeeperActivity.class );
                startActivity( intent );
            }
        } );
        
    }
}
