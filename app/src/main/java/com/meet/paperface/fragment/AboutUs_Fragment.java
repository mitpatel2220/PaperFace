package com.meet.paperface.fragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.meet.paperface.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUs_Fragment extends Fragment {
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;

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
        Objects.requireNonNull(getActivity()).setTitle("About us");

        View view1=getActivity().getCurrentFocus();

        if(view1 !=null){
            InputMethodManager imm=(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(imm).hideSoftInputFromWindow(view1.getWindowToken(),0);
        }

        imageView1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri=Uri.parse("http://instagram.com/_u/kartik_patel_0");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.instagram.android");
                try {

                    startActivity(intent);
                }catch (ActivityNotFoundException e){

                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://instagram.com/_u/kartik_patel_0")));

                }


            }
        } );

        imageView2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("http://instagram.com/_u/cmgohil.10");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.instagram.android");
                try {

                    startActivity(intent);
                }catch (ActivityNotFoundException e){

                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://instagram.com/_u/cmgohil.10")));

                }
            }
        } );

        imageView3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("http://instagram.com/_u/adil.parmar.13");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.instagram.android");
                try {

                    startActivity(intent);
                }catch (ActivityNotFoundException e){

                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://instagram.com/_u/adil.parmar.13")));

                }
            }
        } );

        imageView4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("http://instagram.com/_u/meet__patel_2220");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.instagram.android");
                try {

                    startActivity(intent);
                }catch (ActivityNotFoundException e){

                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://instagram.com/_u/meet__patel_2220")));

                }
            }
        } );


    }
}
