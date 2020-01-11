package com.meet.paperface.Fragment;

import android.content.Intent;
import android.os.Bundle;
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

import com.meet.paperface.Activity.PaymentActivity;
import com.meet.paperface.Activity.Story_Activity;
import com.meet.paperface.R;
/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Button edit, ok, done;

    EditText edit_how, edit_extra;

    TextView edit_page, edit_rs;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edit = view.findViewById(R.id.edit);
        ok = view.findViewById(R.id.ok);
        done = view.findViewById(R.id.done);

        edit_how = view.findViewById(R.id.edit_How);
        edit_extra = view.findViewById(R.id.edit_Extra);

        edit_page = view.findViewById(R.id.edit_Pages);
        edit_rs = view.findViewById(R.id.edit_Rs);
        
        edit.setOnLongClickListener( new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent( getContext(), Story_Activity.class );
                startActivity( intent );
                return false;
            }
        } );


        ok.setOnClickListener(new View.OnClickListener() {
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

                    Toast.makeText(getActivity(), "Please enter Bunch of pages", Toast.LENGTH_SHORT).show();

                } else {
                    int pages_int = Integer.parseInt(pages);
                    int extra_int = Integer.parseInt(extrapages);


                    total_pages = (pages_int * 100) + extra_int;

                    edit_page.setText(total_pages + "");

                    total_rs = (float) ((total_pages * 55) / 100.0);

                    String x = total_rs + "0";
                    edit_rs.setText(x);


                    Intent intent = new Intent(getContext(), PaymentActivity.class);
                    intent.putExtra("total_page", total_pages + "");
                    intent.putExtra("total_rs", x);


                }


            }
        });

        done.setOnClickListener(new View.OnClickListener() {
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

                    Toast.makeText(getActivity(), "Please enter Bunch of pages", Toast.LENGTH_SHORT).show();

                }
                else if(edit_page.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please Touch on ok", Toast.LENGTH_SHORT).show();


                }
                    else
                 {
                    int pages_int = Integer.parseInt(pages);
                    int extra_int = Integer.parseInt(extrapages);


                    total_pages = (pages_int * 100) + extra_int;


                    total_rs = (float) ((total_pages * 55) / 100.0);

                    String x = total_rs + "0";


                    Intent intent = new Intent(getContext(), PaymentActivity.class);
                    intent.putExtra("total_page", total_pages + "");
                    intent.putExtra("total_rs", x);
                      startActivity(intent);


                }


            }
        });

    }


}
