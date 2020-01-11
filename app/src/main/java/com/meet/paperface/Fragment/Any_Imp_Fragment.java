package com.meet.paperface.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.meet.paperface.R;
/**
 * A simple {@link Fragment} subclass.
 */
public class Any_Imp_Fragment extends Fragment {

    Button b1;

    public Any_Imp_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_any_impruvment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Uri hii = Uri.parse("smsto:" + "+919904278734");
        Intent i = new Intent(Intent.ACTION_SENDTO, hii);
        i.setPackage("com.whatsapp");
        startActivity(i);



    }
}
