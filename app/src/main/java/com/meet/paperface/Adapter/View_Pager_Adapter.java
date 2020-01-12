package com.meet.paperface.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.meet.paperface.Model.View_Pager_Model;
import com.meet.paperface.R;

import java.util.List;
public class View_Pager_Adapter extends PagerAdapter {
    
    List<View_Pager_Model> list;
    Context context;

    public View_Pager_Adapter(List<View_Pager_Model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals( object );
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from( context );
        View view = layoutInflater.inflate( R.layout.pager, container, false );
        ImageView imageView;
        imageView = view.findViewById( R.id.image_view );
        imageView.setImageResource( list.get( position ).getImage() );
        container.addView( view, 0 );
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView( (View) object );
    }
}
