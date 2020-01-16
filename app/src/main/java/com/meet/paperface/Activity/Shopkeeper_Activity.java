package com.meet.paperface.Activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.meet.paperface.R;
public class Shopkeeper_Activity extends AppCompatActivity {

    EditText id_Shopkeeper, password;
    Button login;
    int click = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_shopkeeper );
        id_Shopkeeper = findViewById( R.id.email_ShopKeeper );
        password = findViewById( R.id.password_ShopKeeper );

        if (click == 1 ){
            password.setTransformationMethod( new PasswordTransformationMethod() );
            click++;
        }
        password.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click==2){
                    password.setTransformationMethod( null );
                    click--;
                }else if (click==1){
                    password.setTransformationMethod( new PasswordTransformationMethod() );
                    click++;
                }
            }
        } );
        
        login = findViewById( R.id.log_in_ShopKeeper );
        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_sk = id_Shopkeeper.getText().toString();
                String password_sk = password.getText().toString();
                if (email_sk.isEmpty()) {
                    Toast.makeText( Shopkeeper_Activity.this, "Field can't be empty", Toast.LENGTH_SHORT ).show();
                } else if (password_sk.isEmpty()) {
                    Toast.makeText( Shopkeeper_Activity.this, "Field can't be empty", Toast.LENGTH_SHORT ).show();
                } else if (!email_sk.equals( "Md@123" ) || !password_sk.equals( "Md@123" )) {
                    Toast.makeText( Shopkeeper_Activity.this, "Credential does not match", Toast.LENGTH_SHORT ).show();
                } else {
                    Intent intent = new Intent( Shopkeeper_Activity.this, Recycler_Activity.class );
                    startActivity( intent );
                }

            }
        } );

    }
}
