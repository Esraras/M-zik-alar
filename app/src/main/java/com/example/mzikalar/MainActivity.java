package com.example.mzikalar;


import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btPlay, btPause, btNext, btList;
    MediaPlayer mediaPlayer;
    ArrayList<String> musicList;
    int currentMusic = 0;
    TextView mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btPlay = findViewById(R.id.btPlay);
        btPause = findViewById(R.id.btPause);
        btNext = findViewById(R.id.btNext);
        btList = findViewById(R.id.btList);
        mTitle = findViewById(R.id.tvTitle);
        musicList = new ArrayList<>();
        ListRaw();
        btPlay.setOnClickListener(this);
        btPause.setOnClickListener(this);
        btNext.setOnClickListener(this);
        btList.setOnClickListener(this);
        mTitle.setText(musicList.get(0));
        mediaPlayer = MediaPlayer.create(this, R.raw.batsinbudunya);

    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.btPlay:{
                 mediaPlayer.start();
                 break;
             }
             case R.id.btPause:{
                 mediaPlayer.pause();
                 break;
             }
             case R.id.btNext:{
                 if(mediaPlayer.isPlaying()){
                     mediaPlayer.stop();
                 }
                 if(currentMusic < musicList.size() -1){
                     currentMusic+=1;
                 }else{
                     currentMusic = 0;
                 }
                 String musicUri = "android.resource://" + getPackageName() + "/raw/" + musicList.get(currentMusic);
                 mediaPlayer = MediaPlayer.create(this, Uri.parse(musicUri));
                 mediaPlayer.start();
                 mTitle.setText(musicList.get(currentMusic));

                 Log.d("butonKesfet", "onClick: Next");
                 break;
             }
             case R.id.btList:{
                 Log.d("butonKesfet", "onCLick: ;MusicList" );
                 break;
             }
             default: {
                 Log.d("butonKesfet", "onClick: unKnow");
                 break;
             }
         }
    }

    public void ListRaw(){
        Field[] fields = R.raw.class.getFields();
        for (int i= 0; i<fields.length; i++){
            musicList.add(fields[i].getName());
        }
       // for(Field f: fields){
       //     musicList.add(f.getName());
       //}
    }
}