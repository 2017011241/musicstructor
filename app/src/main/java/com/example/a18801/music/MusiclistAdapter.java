package com.example.a18801.music;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 18801 on 2019/11/17.
 */

public class MusiclistAdapter extends ArrayAdapter<musiclist> {
    private int resourceid;

    public MusiclistAdapter(Context context, int textViewResourced, List<musiclist> objects) {
        super(context, textViewResourced, objects);
        resourceid = textViewResourced;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        musiclist list=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceid,parent,false);
        TextView musiclistname=(TextView)view.findViewById(R.id.musiclist_name);
        TextView musiclistnum=(TextView)view.findViewById(R.id.musiclist_num);
        musiclistname.setText(list.getMusicname());
        musiclistnum.setText(list.getNum()+"首");
        return view;
    }
}
