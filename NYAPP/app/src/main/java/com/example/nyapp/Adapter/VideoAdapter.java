package com.example.nyapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nyapp.R;

import java.util.List;

/**
 * Created by xuxiaojin on 2019/4/2.
 */

public class VideoAdapter extends ArrayAdapter<Videos>{
    private int resourceId;
    public VideoAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<Videos> objects) {
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Videos video=getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.videoNameTextview=(TextView)view.findViewById(R.id.videoname_Tv);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.videoNameTextview.setText(video.getVideoName());
        return view;
    }
    class ViewHolder{
        TextView videoNameTextview;
    }
}
