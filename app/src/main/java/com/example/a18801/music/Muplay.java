package com.example.a18801.music;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class Muplay extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    public static final int UPDATE=1;
private Button play;
    private Button next;
    private Button last;
    private Button change;
    private MusicAdapter musicAdapter;
    private SeekBar seekBar;
    private TextView start;
    private TextView end;
    private Handler handler=new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case UPDATE:
                    try
                    {
                        seekBar.setProgress(mediaPlayer.getCurrentPosition());
                        int i=mediaPlayer.getCurrentPosition()/1024;
                        int fen=i/60;
                        int miao=i-fen*60;
                        int tenfen=fen/10;
                        fen=fen-tenfen*10;
                        int tenmiao=miao/10;
                        miao=miao-tenmiao*10;
                        String time=String.valueOf(tenfen)+String.valueOf(fen)+":"+String.valueOf(tenmiao)+String.valueOf(miao);
                        start.setText(time);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessageDelayed(UPDATE,1000);
                    break;
            }
        }
    };
    int i;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muplay);
        play=(Button)findViewById(R.id.play);
        next=(Button)findViewById(R.id.next);
        last=(Button)findViewById(R.id.last);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        start=(TextView)findViewById(R.id.starttime);
        end=(TextView)findViewById(R.id.endtime);
        if(ContextCompat.checkSelfPermission(Muplay.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Muplay.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        else {
            initMediaPlayer();
        }
        seekBar.setMax(mediaPlayer.getDuration());
        int i=mediaPlayer.getDuration()/1024;
        int fen=i/60;
        int miao=i-fen*60;
        int tenfen=fen/10;
        fen=fen-tenfen*10;
        int tenmiao=miao/10;
        miao=miao-tenmiao*10;
        String time=String.valueOf(tenfen)+String.valueOf(fen)+":"+String.valueOf(tenmiao)+String.valueOf(miao);
        end.setText(time);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(play.getText().equals("播放"))
                {
                    play.setText("播放中");
                    if(!mediaPlayer.isPlaying())
                    {
                        mediaPlayer.start();
                        handler.sendEmptyMessage(UPDATE);
                    }
                }
                else if(play.getText().equals("播放中"))
                {
                    play.setText("播放");
                    if(mediaPlayer.isPlaying())
                    {
                        mediaPlayer.pause();
                    }
                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                {
                    if(mediaPlayer!=null&&mediaPlayer.isPlaying())
                    {
                        mediaPlayer.seekTo(progress);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private void initMediaPlayer()
    {
        try{
           mediaPlayer=MediaPlayer.create(Muplay.this,R.raw.firstmusic);
            mediaPlayer.prepare();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults)
    {
        switch (requestCode)
        {
            case 1:
                if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    initMediaPlayer();
                }
                else
                {
                    Toast.makeText(this,"拒绝权限将无法运行程序",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }
    protected void onDestroy()
    {
        super.onDestroy();
        if(mediaPlayer!=null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
