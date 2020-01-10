package com.meet.paperface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.UUID;
public class Story_Activity extends AppCompatActivity {

    ImageView send_pictures;
    EditText send_thesis;
    Button send_data;
    DatabaseReference databaseReference;
    HashMap<String, String> hashMap = new HashMap<>();
    StorageReference storageReference;
    ProgressBar progressBar;
    Uri uri;
    private StorageTask upload_task;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_story_ );
        send_pictures = findViewById( R.id.send_pictures );
        send_thesis = findViewById( R.id.send_thesis );
        send_data = findViewById( R.id.send_data );
        progressBar = findViewById( R.id.progressBar );
        storageReference = FirebaseStorage.getInstance().getReference();
        

        send_pictures.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType( "image/*" );
                intent.setAction( Intent.ACTION_GET_CONTENT );
                startActivityForResult( intent, 1 );
            }
        } );
        send_data.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String thesis = send_thesis.getText().toString();
                databaseReference = FirebaseDatabase.getInstance().getReference().child( "Story" ).child( send_thesis.getText().toString() );

                hashMap.put( "pictures", uri.toString() );
                hashMap.put( "thesis", thesis );
                sendData();
                if (upload_task != null && upload_task.isInProgress()) {
                    Toast.makeText( Story_Activity.this, "File is being uploaded. Please wait a moment!", Toast.LENGTH_SHORT ).show();
                } else {    
                    uploadFile();
                }
            }
        } );
    }

    private void sendData() {
        databaseReference.setValue( hashMap ).addOnCompleteListener( new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText( Story_Activity.this, "Story text has been sent on server", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( Story_Activity.this, "Something error", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();
            send_pictures.setImageURI( uri );
        }
    }
    
    private void uploadFile() {
        if (uri != null) {
            StorageReference storageReference1 = storageReference.child("uploads").child( send_thesis.getText().toString()+".png" );
            upload_task = storageReference1.putFile( uri ).addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed( new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress( 0 );
                        }
                    }, 500 );
                    Toast.makeText( Story_Activity.this, "upload successful", Toast.LENGTH_SHORT ).show();

                }
            } ).addOnFailureListener( new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText( getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT ).show();
                }
            } ).addOnProgressListener( new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressBar.setProgress( (int) progress );
                }
            } );
        } else {
            Toast.makeText( this, "No file selected", Toast.LENGTH_SHORT ).show();
        }
    }
}