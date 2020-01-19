package com.meet.paperface.fragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.meet.paperface.R;
/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUs_Fragment extends Fragment {
    ImageView imageView1, imageView2, imageView3, imageView4;

    public AboutUs_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_about_us_, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        imageView1 = view.findViewById( R.id.team_one );
        imageView2 = view.findViewById( R.id.team_two );
        imageView3 = view.findViewById( R.id.team_three );
        imageView4 = view.findViewById( R.id.team_four );
        
        imageView1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( getContext(), "Clicked on Kartik", Toast.LENGTH_SHORT ).show();
            }
        } );

        imageView2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( getContext(), "Clicked on Chirag", Toast.LENGTH_SHORT ).show();
            }
        } );

        imageView3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( getContext(), "Clicked on Adil", Toast.LENGTH_SHORT ).show();
            }
        } );

        imageView4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( getContext(), "Clicked on MD", Toast.LENGTH_SHORT ).show();
            }
        } );


    }
}
