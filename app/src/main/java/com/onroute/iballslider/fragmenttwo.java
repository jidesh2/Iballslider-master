package com.onroute.iballslider;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by jidesh on 06-12-2015.
 */
public class fragmenttwo extends Fragment {
    VideoView vn;
    SeekBar volumeSeekBar;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View viewroot = inflater.inflate(R.layout.fragment_three_layout, container, false);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        final ViewTreeObserver viewTreeObserver = viewroot.getViewTreeObserver();
/*        viewTreeObserver.addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
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
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                }
            }
        });
*/
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        vn = (VideoView) viewroot.findViewById(R.id.videoView3);
        Toast.makeText(getActivity(),"played_visible_2",Toast.LENGTH_LONG).show();
        volumeSeekBar = (SeekBar) viewroot.findViewById(R.id.seekBar);
        setupVolumeBar();
        //   Environment.getExternalStorageDirectory().getAbsolutePath()+"couchmark.mp4");



        return viewroot;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            if (vn != null) {
                //do sth..
                if (vn.isPlaying())
                    vn.stopPlayback();
            }
        } else {
            if (vn != null) {
vn.setVisibility(View.VISIBLE);
             //   Uri vidFile = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.akancha);
                getad(getView());

            }
        }

    }
    public void getad(final View v)
    {
        String tag_string_req = "req_login";
        String content = null;
        // final String persona = parameters[0];
        final String[] ids1 = new String[1];
        final String[] ids2 = new String[1];

        content = "http://zoomscreens.com/china/adothers.php";


        //  pDialog.setMessage("loading your favourites! ...");
        // showDialog();

        StringRequest strReq = new StringRequest(com.android.volley.Request.Method.POST,
                content, new com.android.volley.Response.Listener<String>() {


            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(String response) {
                Log.d("t", "Login Response: " + response);
                // hideDialog();
Integer time;
                try {
                /*    JSONObject jObj = new JSONObject(response);
                    JSONArray result = jObj.getJSONArray(response);
                 //   Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                    // Check for error node in json
                    for (int i=0; i<result.length(); i++) {
                        TinyDB tinydb = new TinyDB(OTPactivity.this);
                        tinydb.putListString("datas", TITLESS4);
                    }*/
                    JSONObject jr = new JSONObject(response);
                    //JSONObject jb = (JSONObject)jr.getJSONObject(0);
                    JSONArray st = jr.getJSONArray("ad");
                    ids1[0] =st.getString(0);
                 //   ids2[0] =st.getString(1);


                    if(!getActivity().isDestroyed()) {
//                        if (!CONTENTBROT.get(0).equals("none")) {
                        {
                            //TinyDB tb = new TinyDB(Youtube_activity.this);



                            if (ids1!=null) {
                                assert vn != null;
                                Uri vidFile = Uri.parse(ids1[0]);
                                vn.setVideoPath(ids1[0]);
                                //ids1[0]);
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
                                        vn.setVisibility(View.GONE);
                                        ((MainActivity) getActivity()).start_scroll();
                                    }
                                });
                          /*      final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                    }
                                },  TimeUnit.SECONDS.toMillis(time));

*/

                            }

                        }
                    }

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    //  Toast.makeText(Youtube_activity.this, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("this", "Login Error: " + error.getMessage());
                //  Toast.makeText(yt,
                //          error.getMessage(), Toast.LENGTH_LONG).show();
                //  hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);

                // TinyDB tb = new TinyDB(Youtube_activity.this);
                // String persona = tb.getString("persona");
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("persona", "work");
                params.put("lat","");
                params.put("lon", "");
                params.put("time", "");
                params.put("ti", "");
                // String number3 = ((App) Youtube_activity.this.getApplication()).getPhone_number();
                params.put("number", "9158917014");
                params.put("category", Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID));

                return params;
            }


        };


        // Adding request to request queue
        App.getInstance().addToRequestQueue(strReq, tag_string_req);




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
