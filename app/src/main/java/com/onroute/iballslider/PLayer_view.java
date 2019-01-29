package com.onroute.iballslider;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

import im.delight.android.webview.AdvancedWebView;

public class PLayer_view extends AppCompatActivity {
    TinyDB tb;
    Integer time;
    RelativeLayout View_1,View_2;
    ImageView im_1,im_2;
    VideoView v1,v2;
    App a;
    AdvancedWebView av1,av2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        final WindowManager manager = ((WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE));


         a = (App)getApplication();


        tb=new TinyDB(this);
        if(tb.getString("orientation").equals("null") || tb.getString("orientation").isEmpty())
        {
            Intent mainIntent = new Intent(PLayer_view.this,settings_new.class );

            startActivity(mainIntent);
            finish();
        }
        else
        {
            final Handler handler = new Handler();
            final int delay = 180000; //milliseconds

            handler.postDelayed(new Runnable(){
                public void run(){
                    //do something
                    getad();
                    handler.postDelayed(this, delay);
                }
            }, delay);
        }
        if(tb.getString("orientation").equals("landscape"))
        {
            setContentView(R.layout.activity_player_view_land);

        }
        else if(tb.getString("orientation").equals("portrait"))
        {

            setContentView(R.layout.activity_player_view);

        }
        View_1=findViewById(R.id.View1);
        View_2=findViewById(R.id.View2);
        im_1=findViewById(R.id.landimg1);
        im_2=findViewById(R.id.landimg2);
        v1=findViewById(R.id.landvideo1);
        v2=findViewById(R.id.landvideo2);
        av1=findViewById(R.id.landweb1);
        av2=findViewById(R.id.landweb2);

        getad();

    }
    public void getad()
    {
        final String view2ddd="2";
        String tag_string_req = "req_login";
        String content = null;
        // final String persona = parameters[0];
        final String[] ids1 = new String[1];
        final String[] ids2 = new String[1];
        if(tb.getString("orientation").equals("landscape")) {
            content =   "http://zoomscreens.com/cms/landscape.php";
        }
        else if(tb.getString("orientation").equals("portrait"))
        {

            content = "http://zoomscreens.com/cms/potrait.php";
        }



        //  pDialog.setMessage("loading your favourites! ...");
        // showDialog();

        StringRequest strReq = new StringRequest(com.android.volley.Request.Method.POST,
                content, new com.android.volley.Response.Listener<String>() {


            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(String response) {
                Log.d("t", "Login Response: " + response);
                // hideDialog();
             //   {"view1":"active","type_1":"video", "url_1":"http:\/\/zoomscreens.com\/cms\/uploads\/fsamazon.mp4","view2":"active","type_2":"image",
                //        "url_2":"http:\/\/zoomscreens.com\/cms\/uploads\/ssScreenshot_1538136558.png"}
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

                   String view1 = jr.getString("view1");

                    String view2 = jr.getString("view2");

                    if(view1.equals("active"))
                    {
                        String type_1 = jr.getString("type_1");
                        String url_1 = jr.getString("url_1");
                        View_1.setVisibility(View.VISIBLE);
                        if(type_1.equals("video"))
                        {

                            v1.setVisibility(View.VISIBLE);
                            av1.setVisibility(View.GONE);
                            im_1.setVisibility(View.GONE);
                            if(!v1.isPlaying()) {
                                assert v1 != null;
                                //Uri vidFile = Uri.parse(ids1[0]);
                                v1.setVideoPath(url_1);
                                //ids1[0]);
                                v1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                    public void onPrepared(MediaPlayer mp) {

                                        //  vn.start();
                                        v1.start();
                                        //vn.seekTo(100);
                                        // vn.pause();
                                    }
                                });
                                v1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        // v1.setVisibility(View.GONE);
                                        v1.start();
                                    }
                                });
                            }
                        }
                        else if(type_1.equals("image"))
                        {
                            v1.setVisibility(View.GONE);
                            av1.setVisibility(View.GONE);
                            im_1.setVisibility(View.VISIBLE);
                            if(!isDestroyed())
                            Glide.with(PLayer_view.this).load(url_1).into(im_1);


                        }
                        else if(type_1.equals("website"))
                        {
                            v1.setVisibility(View.GONE);
                            av1.setVisibility(View.VISIBLE);
                            im_1.setVisibility(View.GONE);

                           av1.loadUrl(url_1,true);
                        }
                        else
                        {
                            v1.setVisibility(View.GONE);
                            av1.setVisibility(View.GONE);
                            im_1.setVisibility(View.GONE);
                        }




                    }else if (view1.equals("null")) {
                        View_1.setVisibility(View.GONE);




                    }
                    if(view2.equals("active")) {
                        String type_2 = jr.getString("type_2");
                        String url_2 = jr.getString("url_2");
                        View_2.setVisibility(View.VISIBLE);

                        if (type_2.equals("video")) {

                            v2.setVisibility(View.VISIBLE);
                            av2.setVisibility(View.GONE);
                            im_2.setVisibility(View.GONE);
                            if (!v2.isPlaying()) {
                                assert v2 != null;
                                //Uri vidFile = Uri.parse(ids1[0]);
                                v2.setVideoPath(url_2);
                                //ids1[0]);
                                v2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                    public void onPrepared(MediaPlayer mp) {
                                        //  vn.start();
                                        v2.start();
                                        //vn.seekTo(100);
                                        // vn.pause();
                                    }
                                });
                                v2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        // v1.setVisibility(View.GONE);
                                        v2.start();
                                    }
                                });

                            }
                        }else if (type_2.equals("image")) {
                                v2.setVisibility(View.GONE);
                                av2.setVisibility(View.GONE);
                                im_2.setVisibility(View.VISIBLE);
                                if(!isDestroyed()) {
                                    Glide.with(PLayer_view.this).load(url_2).into(im_2);
                                }


                            } else if (type_2.equals("website")) {
                                v2.setVisibility(View.GONE);
                                av2.setVisibility(View.VISIBLE);
                                im_2.setVisibility(View.GONE);

                                av2.loadUrl(url_2, true);
                            } else {
                                v2.setVisibility(View.GONE);
                                av2.setVisibility(View.GONE);
                                im_2.setVisibility(View.GONE);
                            }
                        } else if (view2.equals("null")) {
                            View_2.setVisibility(View.GONE);




                    }

                  //  JSONArray st = jr.getJSONArray("view1");
               //     Toast.makeText(getApplicationContext(), view1, Toast.LENGTH_LONG).show();
                 //   ids1[0] =st.getString(0);
                 //   ids2[0] =st.getString(1);







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
                params.put("number", "");
                params.put("category", android.provider.Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));

                return params;
            }


        };


        // Adding request to request queue
        App.getInstance().addToRequestQueue(strReq, tag_string_req);




    }
}
