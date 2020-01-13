package com.meet.paperface;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meet.paperface.Activity.MainActivity;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



import java.util.HashMap;
public class MainLayout extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    ActionBarDrawerToggle mtoggle;

    private FirebaseAuth mAuth;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    private GoogleSignInClient mGoogleSignInClient;

    SharedPreferences sp;
    public static final String mypreference = "mypreference";
    public static final String Name = "nameKey";
    public static final String hello = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_layout );
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        mtoggle = new ActionBarDrawerToggle( this, drawer, R.string.open, R.string.close );
        drawer.addDrawerListener( mtoggle );
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient( this, gso );

        sp = getSharedPreferences(mypreference,
                                  Context.MODE_PRIVATE);



//                String myuid = firebaseUser.getUid().toString();

        NavigationView navigationView = findViewById( R.id.nav_view );
        updatenavHolder();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_Yourorder, R.id.nav_pastorder, R.id.nav_Aboutus, R.id.nav_AnyImpruvment,
                R.id.nav_share, R.id.nav_Help, R.id.nav_Story )
                .setDrawerLayout( drawer )
                .build();
        NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment );
        NavigationUI.setupActionBarWithNavController( this, navController, mAppBarConfiguration );
        NavigationUI.setupWithNavController( navigationView, navController );
        navController.addOnDestinationChangedListener( new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main_layout, menu );
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment );
        return NavigationUI.navigateUp( navController, mAppBarConfiguration )
               || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected( item );
        if (mtoggle.onOptionsItemSelected( item )) {
            return true;

        }
        if (item.getItemId() == R.id.action_Logout) {


//            if (sp.contains(Name)) {
//
//                Toast.makeText(this, sp.getString(Name, ""), Toast.LENGTH_SHORT).show();
//            }

            if(sp.getString(hello, "").equals("register") || sp.getString(hello, "").equals("log-in")){


                FirebaseAuth.getInstance().signOut();
                Intent in = new Intent( MainLayout.this, MainActivity.class );
                startActivity( in );
                finish();
            }
            if(sp.getString(hello, "").equals("google")){


                mAuth.signOut();

                // Google sign out
                mGoogleSignInClient.signOut().addOnCompleteListener(this,
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent in = new Intent( MainLayout.this, MainActivity.class );
                                startActivity( in );
                                finish();
                                Toast.makeText(MainLayout.this, "Done", Toast.LENGTH_SHORT).show();
                            }
                        });


            }


        }
        return true;
    }
    public void updatenavHolder(){

        NavigationView navigationView = findViewById( R.id.nav_view );
        View headerview=navigationView.getHeaderView(0);

        TextView name=headerview.findViewById(R.id.person_name);
        final TextView order=headerview.findViewById(R.id.textView);
        TextView edit=headerview.findViewById(R.id.edit);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Users");
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        String myuid=firebaseUser.getUid();

        databaseReference.child(myuid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ss:dataSnapshot.getChildren()){
                    String order1=ss.getValue().toString();

                    order.setText(order1+" Orders");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        sp = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sp.contains(hello)) {

            Toast.makeText(this, sp.getString(hello, ""), Toast.LENGTH_SHORT).show();

        }



        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Intent in=new Intent(MainLayout.this,Change_Name.class);
              startActivity(in);


            }
        });

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in=new Intent(MainLayout.this,Change_Name.class);
                startActivity(in);


            }
        });

    }
}