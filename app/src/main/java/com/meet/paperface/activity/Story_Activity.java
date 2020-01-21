package com.meet.paperface.activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.meet.paperface.model.Story_Model;
import com.meet.paperface.R;
public class Story_Activity extends AppCompatActivity {

    private ImageView send_pictures;
    private EditText send_thesis;
    private EditText send_title;
    private Button send_data;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private ProgressBar progressBar;
    private Uri uri;
    private StorageTask upload_task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_story_ );
        send_pictures = findViewById( R.id.send_pictures );
        send_thesis = findViewById( R.id.send_thesis );
        send_title = findViewById( R.id.send_title );
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
                databaseReference = FirebaseDatabase.getInstance().getReference().child( "Story" );
                if (upload_task != null && upload_task.isInProgress()) {
                    Toast.makeText( Story_Activity.this, "File is being uploaded. Please wait a moment!", Toast.LENGTH_SHORT ).show();
                } else if (send_title.getText().toString().isEmpty()){
                    Toast.makeText( Story_Activity.this, "Please give it a short story title", Toast.LENGTH_SHORT ).show();
                }else if (send_title.getText().toString().length()<=4){
                    Toast.makeText( Story_Activity.this, "Story title should have minimum 5 letters", Toast.LENGTH_SHORT ).show();
                } else if (send_thesis.getText().toString().isEmpty()){
                    Toast.makeText( Story_Activity.this, "Please mention your story", Toast.LENGTH_SHORT ).show();
                } else {
                    uploadFile();
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
            final StorageReference storageReference1 = storageReference.child( "uploads" )
                    .child( send_title.getText().toString() + ".png" );
           
            upload_task = storageReference1.putFile( uri )
                    .addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                    StorageMetadata storageMetadata = taskSnapshot.getMetadata();
                    Task<Uri> downloadUri = storageReference1.getDownloadUrl()
                            .addOnSuccessListener( new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String imageRef = uri.toString();
                            databaseReference.child( send_title.getText().toString() )
                                    .child( "pictures" ).setValue( imageRef );
                            databaseReference.child(send_title.getText().toString()).child("name").setValue(send_title.getText().toString() );
                        }
                    } );
                    Handler handler = new Handler();
                    handler.postDelayed( new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress( 0 );
                        }
                    }, 500 );
                    
                    Toast.makeText( Story_Activity.this, "Your story has been successfully updated", Toast.LENGTH_LONG ).show();
                    Story_Model story_model = new Story_Model( "", send_thesis.getText().toString().trim(),"" );
                    databaseReference.child( send_title.getText().toString() ).setValue( story_model );
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
            Toast.makeText( this, "Please choose your story image", Toast.LENGTH_SHORT ).show();
        }
    }


}