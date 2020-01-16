package com.meet.paperface.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.meet.paperface.Activity.Payment_Activity;
import com.meet.paperface.Activity.Story_Activity;
import com.meet.paperface.Adapter.View_Pager_Adapter;
import com.meet.paperface.Model.View_Pager_Model;
import com.meet.paperface.R;

import java.util.ArrayList;
import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Fragment extends Fragment {

    Button ok, done;
    EditText edit_how, edit_extra;
    TextView edit_page, edit_rs;
    ViewPager viewPager;
    int sec = 0;

    public Home_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_home, container, false );
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        ok = view.findViewById( R.id.ok );
        done = view.findViewById( R.id.done );
        edit_how = view.findViewById( R.id.edit_How );
        edit_extra = view.findViewById( R.id.edit_Extra );
        edit_page = view.findViewById( R.id.edit_Pages );
        edit_rs = view.findViewById( R.id.edit_Rs );
        viewPager = view.findViewById( R.id.pager );
        List<View_Pager_Model> view_pager_models = new ArrayList<>();
        View_Pager_Adapter view_pager_adapter = new View_Pager_Adapter( view_pager_models, getContext() );
        view_pager_models.add( new View_Pager_Model( R.drawable.printer ) );
        view_pager_models.add( new View_Pager_Model( R.drawable.shading ) );
        view_pager_models.add( new View_Pager_Model( R.drawable.profile ) );
        view_pager_models.add( new View_Pager_Model( R.drawable.user ) );
        viewPager.setAdapter( view_pager_adapter );
        runnable.run();
        
        ok.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float total_rs;
                String pages = edit_how.getText().toString();
                String extrapages = edit_extra.getText().toString();
                if (extrapages.isEmpty()) {
                    extrapages = "0";

                }
                String pages1 = edit_how.getText().toString();
                int total_pages;
                if (pages1.isEmpty()) {
                    Toast.makeText( getActivity(), "Please enter Bunch of pages", Toast.LENGTH_SHORT ).show();

                } else {
                    int pages_int = Integer.parseInt( pages );
                    int extra_int = Integer.parseInt( extrapages );
                    total_pages = (pages_int * 100) + extra_int;
                    edit_page.setText( total_pages + "" );
                    total_rs = (float) ((total_pages * 55) / 100.0);
                    String x = total_rs + "0";
                    edit_rs.setText( x );
                    Intent intent = new Intent( getContext(), Payment_Activity.class );
                    intent.putExtra( "total_page", total_pages + "" );
                    intent.putExtra( "total_rs", x );

                }

            }
        } );
        done.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float total_rs;
                String pages = edit_how.getText().toString();
                String extrapages = edit_extra.getText().toString();
                if (extrapages.isEmpty()) {
                    extrapages = "0";

                }
                String pages1 = edit_how.getText().toString();
                int total_pages;
                if (pages1.isEmpty()) {
                    Toast.makeText( getActivity(), "Please enter Bunch of pages", Toast.LENGTH_SHORT ).show();

                } else if (edit_page.getText().toString().isEmpty()) {
                    Toast.makeText( getActivity(), "Please Touch on ok", Toast.LENGTH_SHORT ).show();

                } else {
                    int pages_int = Integer.parseInt( pages );
                    int extra_int = Integer.parseInt( extrapages );
                    total_pages = (pages_int * 100) + extra_int;
                    total_rs = (float) ((total_pages * 55) / 100.0);
                    String x = total_rs + "0";
                    Intent intent = new Intent( getContext(), Payment_Activity.class );
                    intent.putExtra( "total_page", total_pages + "" );
                    intent.putExtra( "total_rs", x );
                    startActivity( intent );
                }
            }
        } );
    }
    
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (sec <= 4) {
                if (sec == 4) {
                    sec = 0;
                }
                viewPager.setCurrentItem( sec );
                sec++;
                handler.postDelayed( this, 2000 );
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks( runnable );
    }
}