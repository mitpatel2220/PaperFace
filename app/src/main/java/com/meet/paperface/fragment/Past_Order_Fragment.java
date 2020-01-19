package com.meet.paperface.fragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meet.paperface.adapter.Past_Order_Adapter;
import com.meet.paperface.model.Past_Order_Model;
import com.meet.paperface.R;

import java.util.ArrayList;
import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 */
public class Past_Order_Fragment extends Fragment {

    List<Past_Order_Model> listdata = new ArrayList<>();
    RecyclerView rv;
    Past_Order_Adapter adaptor;
    private DatabaseReference mUsersDatabase;
    private LinearLayoutManager mLayoutManager;
    FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_past_order, container, false );
        //        View root = inflater.inflate(R.layout.fragment_past_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child( "Pastorder" );
        mLayoutManager = new LinearLayoutManager( getContext() );
        rv = view.findViewById( R.id.recycle_view );
        rv.setHasFixedSize( true );
        rv.setLayoutManager( new LinearLayoutManager( getContext() ) );
        mUsersDatabase.keepSynced( true );
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        final String myuid = firebaseUser.getUid();
        mUsersDatabase.child( myuid ).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ss : dataSnapshot.getChildren()) {
                    Past_Order_Model user = ss.getValue( Past_Order_Model.class );
                    listdata.add( user );

                }
                adaptor = new Past_Order_Adapter( getActivity(), listdata );
                rv.setAdapter( adaptor );

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        } );

    }
}
