package com.example.a18801.music;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 18801 on 2019/11/18.
 */

public class MusicAdapter extends ArrayAdapter<Music>
{
    private int resourceid;

    public MusicAdapter(Context context, int textViewResourced, List<Music> objects)
    {
       super(context,textViewResourced,objects);
        resourceid=textViewResourced;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Music music=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceid,parent,false);
        TextView textView=(TextView)view.findViewById(R.id.music_name);
        textView.setText(music.getName());
        return view;
    }
}
