package com.meet.paperface.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.meet.paperface.hostelName.Meghamani_Parivar;
import com.meet.paperface.model.Task_Class;
import com.meet.paperface.model.Users;
import com.meet.paperface.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Meghani_parivar_adptor extends RecyclerView.Adapter<Meghani_parivar_adptor.ViewHolder>{

    private Context fcontext;
    private List<Users> fupload = new ArrayList<>();
    private List<Task_Class> fupload1 = new ArrayList<>();
    DatabaseReference dr;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;

    public void add(Users s) {
        fupload.add( s );
    }

    public Meghani_parivar_adptor(Context context, List<Users> user, List<Task_Class> user1) {
        fcontext = context;
        fupload = user;
        fupload1 = user1;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( fcontext ).inflate( R.layout.data_list, parent, false );
        return new ViewHolder( view );

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        dr = FirebaseDatabase.getInstance().getReference().child( "Orders" );
        databaseReference = FirebaseDatabase.getInstance().getReference().child( "Pastorder" );
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child( "YourOrder" );
        final String ss = dr.getRef().getKey();
        holder.hostelname.setText( fupload.get( position ).getHostelname() );
        holder.mobileno.setText( fupload.get( position ).getMobileno() );
        holder.name.setText( fupload.get( position ).getName() );
        holder.other.setText( fupload.get( position ).getOther() );
        holder.payment.setText( fupload.get( position ).getPayment() );
        holder.roomno.setText( fupload.get( position ).getRoomno() );
        holder.totalpage.setText( fupload.get( position ).getTotalpage() );
        holder.totalrs.setText( fupload.get( position ).getTotalrs() );
        holder.x.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String currentDate = DateFormat.getDateTimeInstance().format( new Date() );
                HashMap<String, String> map = new HashMap<>();
                map.put( "date", currentDate );
                map.put( "pages", fupload.get( position ).getTotalpage() );
                map.put( "rs", fupload.get( position ).getTotalrs() );
                databaseReference.child( fupload.get( position ).getUid() ).push().setValue( map ).addOnCompleteListener( new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText( fcontext, "Done", Toast.LENGTH_SHORT ).show();

                        } else {
                        }

                    }
                } );
                databaseReference1.child( fupload.get( position ).getUid() ).removeValue().addOnCompleteListener( new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText( fcontext, "Removed", Toast.LENGTH_SHORT ).show();

                    }
                } );
                dr.child( fupload1.get( position ).getKey() ).removeValue().addOnCompleteListener( new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText( fcontext, "Removed", Toast.LENGTH_SHORT ).show();
                        dr.keepSynced( true );
                        fcontext.startActivity( new Intent( fcontext, Meghamani_Parivar.class ) );
                        ((Activity)fcontext).finish();

                    }
                } );


            }
        } );

    }

    @Override
    public int getItemCount() {
        return fupload.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView hostelname, mobileno, name, other, payment, roomno, totalpage, totalrs;
        ImageView x;

        ViewHolder(@NonNull View itemView) {
            super( itemView );
            hostelname = itemView.findViewById( R.id.card_hostelNum_blank );
            mobileno = itemView.findViewById( R.id.card_mobileno_blank );
            name = itemView.findViewById( R.id.card_name_blank );
            other = itemView.findViewById( R.id.card_other_blank );
            payment = itemView.findViewById( R.id.card_payment_blank );
            roomno = itemView.findViewById( R.id.card_room_blank );
            totalpage = itemView.findViewById( R.id.card_Pages_Blank );
            totalrs = itemView.findViewById( R.id.card_Rupees_Blank );
            x = itemView.findViewById( R.id.imageview );
        }
    }
}

