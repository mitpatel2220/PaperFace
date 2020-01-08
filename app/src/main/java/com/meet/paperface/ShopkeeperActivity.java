package com.meet.paperface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShopkeeperActivity extends AppCompatActivity {

    EditText id_Shopkeeper,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopkeeper);

        id_Shopkeeper=findViewById(R.id.email_ShopKeeper);
        password=findViewById(R.id.password_ShopKeeper);

        login=findViewById(R.id.log_in_ShopKeeper);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email_sk=id_Shopkeeper.getText().toString();
                String password_sk=password.getText().toString();


                if(email_sk.isEmpty()){
                    Toast.makeText(ShopkeeperActivity.this, "Please your id", Toast.LENGTH_SHORT).show();

                }else if(password_sk.isEmpty()){

                    Toast.makeText(ShopkeeperActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                }else {


                    if(email_sk.equals("12345678")){
                        if(password_sk.equals("12345678")){

                            Toast.makeText(ShopkeeperActivity.this, "Done bhai", Toast.LENGTH_SHORT).show();



                        }
                        else {
                            Toast.makeText(ShopkeeperActivity.this, "Something Error", Toast.LENGTH_SHORT).show();
                        }



                    }



                }


            }
        });

    }
}
