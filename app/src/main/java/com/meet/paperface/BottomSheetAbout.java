package com.meet.paperface;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
public class BottomSheetAbout extends BottomSheetDialogFragment {

    private BottomSheetListenerAbout listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view3 = inflater.inflate( R.layout.about_us, container, false );
        
        return view3;
    }
    

    public interface BottomSheetListenerAbout {

        
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach( context );
        try {
            listener = (BottomSheetListenerAbout) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + " must implement BottomSheetListenerPass" );
        }
    }
}
