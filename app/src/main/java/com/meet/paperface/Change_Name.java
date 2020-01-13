package com.meet.paperface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Change_Name extends AppCompatActivity {

    EditText name;
    Button done;
    SharedPreferences sp;
    public static final String mypreference = "mypreference";
    public static final String Name = "nameKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__name);


        name=findViewById(R.id.name);
        done=findViewById(R.id.done);
        sp = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor=sp.edit();
                editor.putString(Name,name.getText().toString());
                editor.commit();

                Intent intent=new Intent(Change_Name.this,MainLayout.class);
                startActivity(intent);
                finish();
                Toast.makeText(Change_Name.this, "Name changed", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
