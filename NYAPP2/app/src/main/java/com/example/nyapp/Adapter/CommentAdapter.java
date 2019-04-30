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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by xuxiaojin on 2019/4/30.
 */

public class CommentAdapter extends ArrayAdapter<Comment> {
    private int resourceId;
    public CommentAdapter(@NonNull Context context, int resource, @NonNull List<Comment> objects) {
        super(context, resource, objects);
        this.resourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Comment comment=getItem(position);
        ViewHolder viewHolder;
        View view;
        if (convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new CommentAdapter.ViewHolder();
            viewHolder.context=view.findViewById(R.id.contextTv);
            viewHolder.commenttime=view.findViewById(R.id.commenttimeTv);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= ((CommentAdapter.ViewHolder) view.getTag());
        }
        viewHolder.context.setText(comment.getContext());
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            Date dt=df.parse(String.valueOf(comment.getCommenttime()));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date=format.format(dt);
            viewHolder.commenttime.setText(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return view;
    }

    class ViewHolder{
        TextView context;
        TextView commenttime;
    }
}
