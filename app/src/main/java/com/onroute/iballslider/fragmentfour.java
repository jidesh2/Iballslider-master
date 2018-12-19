package com.onroute.iballslider;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.VideoView;

/**
 * Created by jidesh on 06-12-2015.
 */
public class fragmentfour extends Fragment {
    VideoView vn;
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




    vn = (VideoView) viewroot.findViewById(R.id.videoView3);
        //   Uri vidFile = Uri.parse(
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
        return  viewroot;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            //do sth..
//           if(vn.isPlaying())
        //    vn.stopPlayback();
        }
        else
        {


        }
    }

    }
