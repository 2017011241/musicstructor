package com.example.a18801.music;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class gedan extends AppCompatActivity {
   private TextView listname;
    private List<Music> musicList=new ArrayList<>();
    private  ListView listView;
    private MusicAdapter musicAdapter;
    private Button play;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gedan);
        Intent intent=getIntent();
        String name=intent.getStringExtra("listname");
        Music music=new Music();
        music.setName("lasllla");
        musicList.add(music);
        listname=(TextView)findViewById(R.id.list_name);
        listname.setText(name);
        musicAdapter=new MusicAdapter(gedan.this,R.layout.music_item,musicList);
        listView=(ListView)findViewById(R.id.music_list);
        listView.setAdapter(musicAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Music music1=musicList.get(position);
                Intent intent1=new Intent(gedan.this,Muplay.class);
                intent1.putExtra("name",music1.getName());
                startActivity(intent1);
            }
        });
    }
}
