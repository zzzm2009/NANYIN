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
 * Created by xuxiaojin on 2019/4/9.
 */

public class ClubAdapter extends ArrayAdapter<Club> {
    private int resourceId;
    public ClubAdapter(@NonNull Context context, int resource, @NonNull List<Club> objects) {
        super(context, resource, objects);
        this.resourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Club club=getItem(position);
        ViewHolder viewHolder;
        View view;
        if (convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.name=view.findViewById(R.id.name_tv);
            viewHolder.address=view.findViewById(R.id.address_tv);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= ((ViewHolder) view.getTag());
        }
        viewHolder.name.setText(club.getClubname());
        viewHolder.address.setText(club.getClubaddress());
        return view;
    }

    class ViewHolder{
        TextView name;
        TextView address;
    }
}
