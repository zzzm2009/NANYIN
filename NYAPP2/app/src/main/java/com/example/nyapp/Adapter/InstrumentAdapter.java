package com.example.nyapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nyapp.R;

import java.util.List;

/**
 * Created by xuxiaojin on 2019/4/9.
 */

public class InstrumentAdapter extends ArrayAdapter<Instrument> {
    private int reourceId;
    public InstrumentAdapter(@NonNull Context context, int resource, @NonNull List<Instrument> objects) {
        super(context, resource, objects);
        this.reourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Instrument instrument=getItem(position);
        View view;
        ViewHolder viewHolder;

        if (convertView==null){
                view= LayoutInflater.from(getContext()).inflate(reourceId,parent,false);
                viewHolder=new ViewHolder();
                viewHolder.imageView= ((ImageView) view.findViewById(R.id.instrumentImg));
                viewHolder.name= ((TextView) view.findViewById(R.id.instrumentnameTv));
                view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder= ((ViewHolder) view.getTag());
        }
        viewHolder.imageView.setImageResource(instrument.getImageId());
        viewHolder.name.setText(instrument.getName());
        return view;
    }

    class ViewHolder{
        ImageView imageView;
        TextView name;
    }
}
