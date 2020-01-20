package com.meet.paperface;

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
import com.meet.paperface.hostelName.Diamond_jubile;
import com.meet.paperface.model.Task_Class;
import com.meet.paperface.model.Users;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Allorder_adapter extends RecyclerView.Adapter<Allorder_adapter.ViewHolder>{

    private Context fcontext;
    private List<Users> fupload = new ArrayList<>();

    public void add(Users s) {
        fupload.add( s );
    }

    public Allorder_adapter(Context context, List<Users> user) {
        fcontext = context;
        fupload = user;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( fcontext ).inflate( R.layout.data_list, parent, false );
        return new ViewHolder( view );

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
          holder.hostelname.setText( fupload.get( position ).getHostelname() );
        holder.mobileno.setText( fupload.get( position ).getMobileno() );
        holder.name.setText( fupload.get( position ).getName() );
        holder.other.setText( fupload.get( position ).getOther() );
        holder.payment.setText( fupload.get( position ).getPayment() );
        holder.roomno.setText( fupload.get( position ).getRoomno() );
        holder.totalpage.setText( fupload.get( position ).getTotalpage() );
        holder.totalrs.setText( fupload.get( position ).getTotalrs() );

    }

    @Override
    public int getItemCount() {
        return fupload.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView hostelname, mobileno, name, other, payment, roomno, totalpage, totalrs;

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
        }
    }
}

