package com.meet.paperface;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetName extends BottomSheetDialogFragment {

    private BottomSheetListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.bottom_reset_name, container, false);
        final EditText textView = view1.findViewById(R.id.reset_name);
        Button button = view1.findViewById(R.id.reset_done);
        button.setOnClickListener(new View.OnClickListener() {
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
        });
        return view1;
    }


    public interface BottomSheetListener {

        void onButtonclicked(String text);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement BottomSheetListenerPass");
        }
    }
}
