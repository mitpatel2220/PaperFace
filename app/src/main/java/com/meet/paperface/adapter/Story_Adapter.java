 package com.meet.paperface.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meet.paperface.R;
import com.meet.paperface.model.Story_Model;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Story_Adapter extends RecyclerView.Adapter<Story_Adapter.ViewHolder> {
    
    private Context context;
    private List<Story_Model> list;

    public Story_Adapter(Context context, List<Story_Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.story_list, parent, false );
        return new ViewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Story_Model storyModel = list.get( position );
        
        holder.textView.setText( storyModel.getThesis() );
        
        Picasso.get().load( storyModel.getPictures()).networkPolicy( NetworkPolicy.OFFLINE ).into( holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load( storyModel.getPictures() ).fit().centerInside().into( holder.imageView );

            }
        } );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            imageView = itemView.findViewById( R.id.story );
            textView = itemView.findViewById( R.id.poem );
        }
    }
}
