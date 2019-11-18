package com.example.a18801.music;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
        import android.media.Image;
        import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
        import android.widget.ImageView;
        import android.support.v4.app.FragmentManager;
import android.widget.ListView;
import android.Manifest;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView home;
    private ImageView mine;
    private List<musiclist> musiclists=new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home=(ImageView)findViewById(R.id.main_image);
        home.setOnClickListener(this);
        mine=(ImageView)findViewById(R.id.mine_page);
        mine.setOnClickListener(this);
        replaceFragment(new mainfregment());
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.hide();
        }
    }
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.main_image:
                replaceFragment(new mainfregment());
                break;
            case R.id.mine_page:
                replaceFragment(new minefregment());
                break;
            default:
                break;
        }
    }
    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.main_layout,fragment);
        transaction.commit();
    }
}
