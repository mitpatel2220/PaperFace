package com.meet.paperface;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meet.paperface.Activity.Login_Activity;
import com.meet.paperface.Fragment.Home_Fragment;
import com.meet.paperface.Fragment.Past_Order_Fragment;
import com.meet.paperface.Fragment.Story_Fragment;
import com.meet.paperface.Fragment.Your_Order_Fragment;
public class MainLayout extends AppCompatActivity implements BottomSheetName.BottomSheetListener, BottomSheetAbout.BottomSheetListenerAbout {

    private AppBarConfiguration mAppBarConfiguration;
    ActionBarDrawerToggle mtoggle;
    private FirebaseAuth mAuth;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference, dr;
    private GoogleSignInClient mGoogleSignInClient;
    SharedPreferences sharedPreferences;
    public static final String mypreference = "mypreference";
    public static final String Name = "nameKey";
    public static final String hello = "login";
    TextView name;
    FragmentTransaction fragmentTransaction;
    int i = 1;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_layout );
        Toolbar toolbar = findViewById( R.id.toolbar );
        view = findViewById( R.id.nsb );
        setSupportActionBar( toolbar );
        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        mtoggle = new ActionBarDrawerToggle( this, drawer, R.string.open, R.string.close );
        drawer.addDrawerListener( mtoggle );
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        mAuth = FirebaseAuth.getInstance();
        checkConnection();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder( GoogleSignInOptions.DEFAULT_SIGN_IN )
                .requestIdToken( getString( R.string.default_web_client_id ) )
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient( this, gso );
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace( R.id.frame, new Home_Fragment() );
        fragmentTransaction.commit();
//                String myuid = firebaseUser.getUid().toString();
        NavigationView navigationView = findViewById( R.id.nav_view );
        updatenavHolder();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        dr=FirebaseDatabase.getInstance().getReference().child("Available");

        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String x=dataSnapshot.child("yes").getValue().toString();
                String pages=dataSnapshot.child("pages").getValue().toString();

                if(x.equals("yes")){

                    showDialogeforpaper();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        
        
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_Yourorder, R.id.nav_pastorder, R.id.nav_Aboutus, R.id.nav_AnyImpruvment, R.id.nav_share, R.id.nav_Story )
                .setDrawerLayout( drawer )
                .build();
//        NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment );
//        NavigationUI.setupActionBarWithNavController( this, navController, mAppBarConfiguration );
//        NavigationUI.setupWithNavController( navigationView, navController );
        navigationView.setNavigationItemSelectedListener( new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.nav_home) {
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace( R.id.frame, new Home_Fragment() );
                    fragmentTransaction.commit();
                } else if (id == R.id.nav_Yourorder) {
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace( R.id.frame, new Your_Order_Fragment() );
                    fragmentTransaction.commit();
                    i = 0;
                } else if (id == R.id.nav_pastorder) {
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace( R.id.frame, new Past_Order_Fragment() );
                    fragmentTransaction.commit();
                    i = 0;
                } else if (id == R.id.nav_Aboutus) {
                    BottomSheetAbout bottomSheetAbout= new BottomSheetAbout();
                    bottomSheetAbout.show( getSupportFragmentManager(), "bottomSheetAbout" );
                } else if (id == R.id.nav_AnyImpruvment) {
                    Uri hii = Uri.parse( "smsto:" + "+917698209853" );
                    Intent intent = new Intent( Intent.ACTION_SENDTO, hii );
                    intent.setPackage( "com.whatsapp" );
                    startActivity( intent );
                } else if (id == R.id.nav_share) {
                    Intent intent = new Intent( Intent.ACTION_SEND );
                    intent.setType( "text/plain" );
                    intent.putExtra( Intent.EXTRA_SUBJECT, "subject here" );
                    intent.putExtra( Intent.EXTRA_TEXT, "body" );
                    startActivity( Intent.createChooser( intent, "Share via.." ) );
                } else if (id == R.id.nav_Story) {
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace( R.id.frame, new Story_Fragment() );
                    fragmentTransaction.commit();
                    i = 0;
                }
                DrawerLayout drawer = findViewById( R.id.drawer_layout );
                drawer.closeDrawer( GravityCompat.START );
                return true;
            }
        } );
    }
    
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else if (i == 0) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.frame, new Home_Fragment() );
            fragmentTransaction.commit();
            i = 1;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main_layout, menu );
        return true;
    }
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment );
//        return NavigationUI.navigateUp( navController, mAppBarConfiguration )
//               || super.onSupportNavigateUp();
//        return false;
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected( item );
        if (mtoggle.onOptionsItemSelected( item )) {
            return true;
        }
        if (item.getItemId() == R.id.action_Logout) {
            if (sharedPreferences.getString( hello, "" ).equals( "register" ) || sharedPreferences.getString( hello, "" ).equals( "log-in" )) {
                FirebaseAuth.getInstance().signOut();
                Intent in = new Intent( MainLayout.this, Login_Activity.class );
                startActivity( in );
                finish();
            }
            if (sharedPreferences.getString( hello, "" ).equals( "google" )) {
                mAuth.signOut();
                // Google sign out
                mGoogleSignInClient.signOut().addOnCompleteListener( this,
                                                                     new OnCompleteListener<Void>() {
                                                                         @Override
                                                                         public void onComplete(@NonNull Task<Void> task) {
                                                                             Intent in = new Intent( MainLayout.this
                                                                                     , Login_Activity.class );
                                                                             startActivity( in );
                                                                             finish();
                                                                             Toast.makeText( MainLayout.this, "Done"
                                                                                     , Toast.LENGTH_SHORT ).show();
                                                                         }
                                                                     } );

            }
        }
        return true;

    }

    public void updatenavHolder() {
        NavigationView navigationView = findViewById( R.id.nav_view );
        View headerview = navigationView.getHeaderView( 0 );
        name = headerview.findViewById( R.id.person_name );
        final TextView order = headerview.findViewById( R.id.textView );
        ImageView userNameEdit = headerview.findViewById( R.id.edit );
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child( "Users" );
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String myuid = firebaseUser.getUid();
        databaseReference.child( myuid ).addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ss : dataSnapshot.getChildren()) {
                    String order1 = ss.getValue().toString();
                    order.setText( order1 + " Orders" );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        } );
        sharedPreferences = getSharedPreferences( mypreference, Context.MODE_PRIVATE );
        if (sharedPreferences.contains( "myName" )) {
            name.setText( sharedPreferences.getString( "myName", "" ) );
        }
        if (sharedPreferences.contains( hello )) {
            Toast.makeText( this, sharedPreferences.getString( hello, "" )
                    , Toast.LENGTH_SHORT ).show();
        }
        userNameEdit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetName bottomSheetName = new BottomSheetName();
                bottomSheetName.show( getSupportFragmentManager(), "bottomSheet" );
            }
        } );
        name.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetName bottomSheetName = new BottomSheetName();
                bottomSheetName.show( getSupportFragmentManager(), "bottomSheet" );
            }
        } );
    }

    @Override
    public void onButtonclicked(String text) {
        name.setText( text );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString( "myName", text );
        editor.commit();
        Toast.makeText( MainLayout.this, "Name changed", Toast.LENGTH_SHORT ).show();
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

    public void showDialogeforpaper() {
        AlertDialog.Builder builderDia = new AlertDialog.Builder(this);
        // builderDia.setTitle("No Internet Connection");
        builderDia.setMessage("Pages are not available right now\n\nPress OK to Exit");
        builderDia.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builderDia.show();
    }
}