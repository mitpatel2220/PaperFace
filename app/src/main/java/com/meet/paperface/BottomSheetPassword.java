package com.meet.paperface;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
public class BottomSheetPassword extends BottomSheetDialogFragment {

    private BottomSheetListenerPass listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view2 = inflater.inflate( R.layout.bottom_reset_password, container, false );
        final EditText textView = view2.findViewById( R.id.reset_pass );
        Button button = view2.findViewById( R.id.reset_password );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String forpass = textView.getText().toString();

                if(forpass.isEmpty()){

                    textView.setError("Please enter your name");
                }else {
                    listener.onButtonclicked(textView.getText().toString());

                }
                dismiss();
            }
        } );
        return view2;
    }
    

    public interface BottomSheetListenerPass {

        void onButtonclicked(String text);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach( context );
        try {
            listener = (BottomSheetListenerPass) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + " must implement BottomSheetListenerPass" );
        }
    }
}
