package com.meet.paperface.fragment;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

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
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class Past_Order_Fragment extends Fragment {

    private final List<Past_Order_Model> listdata = new ArrayList<>();
    private RecyclerView rv;
    private Past_Order_Adapter adaptor;
    private FirebaseAuth firebaseAuth;

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
        DatabaseReference mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Pastorder");
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv = view.findViewById( R.id.recycle_view );
        rv.setHasFixedSize( true );
        rv.setLayoutManager( new LinearLayoutManager( getContext() ) );
        mUsersDatabase.keepSynced( true );
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        final String myuid = Objects.requireNonNull(firebaseUser).getUid();

        Objects.requireNonNull(getActivity()).setTitle("Past Orders");
        View view1=getActivity().getCurrentFocus();

        if(view1 !=null){
            InputMethodManager imm=(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(imm).hideSoftInputFromWindow(view1.getWindowToken(),0);
        }


        mUsersDatabase.child( myuid ).addValueEventListener(new ValueEventListener() {
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
