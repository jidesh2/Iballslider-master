package com.onroute.iballslider;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * Created by jidesh on 06-12-2015.
 */
public class fragmentthree extends Fragment {
    VideoView vn;
    SeekBar volumeSeekBar;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       View viewroot= inflater.inflate(R.layout.fragment_three_layout,container,false);
        final ViewTreeObserver viewTreeObserver = viewroot.getViewTreeObserver();
        viewTreeObserver.addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
            @Override
            public void onWindowFocusChanged(final boolean hasFocus) {
                if (hasFocus) {

                    Window w = getActivity().getWindow();
                    w.getDecorView().setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
            }
        });

        volumeSeekBar = (SeekBar) viewroot.findViewById(R.id.seekBar);
        setupVolumeBar();

    vn = (VideoView) viewroot.findViewById(R.id.videoView3);
        //   Uri vidFile = Uri.parse(

        return  viewroot;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
          if(vn!=null) {
              //do sth..
              if (vn.isPlaying())
                  vn.stopPlayback();
          }
        }
        else
        {
            if(vn!=null) {
                Uri vidFile = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.akancha);

                assert vn != null;
                vn.setVideoURI(Uri.parse(String.valueOf(vidFile)));
                //   Environment.getExternalStorageDirectory().getAbsolutePath()+"couchmark.mp4");
                vn.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    public void onPrepared(MediaPlayer mp) {
                        //  vn.start();
                        vn.start();
                        //vn.seekTo(100);
                        // vn.pause();
                    }
                });
                vn.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if(!vn.isPlaying())
                            vn.start();

                    }
                });
            }

        }
    }
    private void setupVolumeBar() {
        final AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        volumeSeekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeSeekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
            }


            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }


            @Override
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                        progress, 0);
            }
        });
    }
    }
