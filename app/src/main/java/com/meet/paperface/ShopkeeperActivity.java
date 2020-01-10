package com.meet.paperface;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class ShopkeeperActivity extends AppCompatActivity {

    EditText id_Shopkeeper, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_shopkeeper );
        id_Shopkeeper = findViewById( R.id.email_ShopKeeper );
        password = findViewById( R.id.password_ShopKeeper );
        login = findViewById( R.id.log_in_ShopKeeper );
        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_sk = id_Shopkeeper.getText().toString();
                String password_sk = password.getText().toString();


                if(email_sk.equals("Md@123") && password_sk.equals("Md@123")){
                    Intent intent=new Intent(ShopkeeperActivity.this,Recycler_Activity.class);
                    startActivity(intent);

                }



            }
        } );

    }
}
