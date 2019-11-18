package com.example.a18801.music;

/**
 * Created by 18801 on 2019/11/17.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class minefregment extends Fragment
{
    private List<musiclist> musiclists=new ArrayList<>();
    MusiclistAdapter musiclistAdapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        musiclist list=new musiclist();
        list.setMusicname("英文歌曲");
        list.setNum("1");
        musiclists.add(list);
        View view=inflater.inflate(R.layout.mine_page,container,false);
        musiclistAdapter=new MusiclistAdapter(getContext(),R.layout.musiclist_item,musiclists);
        ListView listView=(ListView)view.findViewById(R.id.mine_musiclist);
        listView.setAdapter(musiclistAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                musiclist list=musiclists.get(position);
                Intent intent=new Intent(getContext(),gedan.class);
                intent.putExtra("listname",list.getMusicname());
                startActivity(intent);
            }
        });
        return view;
    }
}