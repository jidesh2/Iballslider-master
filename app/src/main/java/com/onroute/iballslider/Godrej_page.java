package com.onroute.iballslider;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.widget.ImageView;
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

import im.delight.android.webview.AdvancedWebView;

/**
 * Created by jidesh on 06-12-2015.
 */
public class Godrej_page extends Fragment implements AdvancedWebView.Listener {
    VideoView vn;
    View viewroot;
    Integer time;
    private AdvancedWebView mWebView;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      viewroot= inflater.inflate(R.layout.godrej,container,false);
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
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
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
        mWebView = (AdvancedWebView) viewroot.findViewById(R.id.webview);
        mWebView.setListener(this, this);
        mWebView.setInitialScale(1);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        Toast.makeText(getActivity(),"played_visible_3",Toast.LENGTH_LONG).show();
        boolean preventCaching = true;
        getad(viewroot);



        //   Uri vidFile = Uri.parse(

        return  viewroot;
    }

    @Override
    public void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    public void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ((MainActivity) getActivity()).start_scroll();
                }
            },  TimeUnit.SECONDS.toMillis(time));



        }
        else
        {



        }


    }
    public void getad(final View v)
    {
        String tag_string_req = "req_login";
        String content = null;
        // final String persona = parameters[0];
        final String[] ids1 = new String[1];
        final String[] ids2 = new String[1];

        content = "http://zoomscreens.com/china/banner.php";


        //  pDialog.setMessage("loading your favourites! ...");
        // showDialog();

        StringRequest strReq = new StringRequest(com.android.volley.Request.Method.POST,
                content, new com.android.volley.Response.Listener<String>() {


            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(String response) {
                Log.d("t", "Login Response: " + response);
                // hideDialog();

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
                    ids2[0] =st.getString(1);

                    if(ids2[0]==null)
                    {
                        time = Integer.parseInt("15");
                    }
                    else {
                        time = Integer.parseInt(st.getString(1));
                    }

                    if(!getActivity().isDestroyed()) {
//                        if (!CONTENTBROT.get(0).equals("none")) {
                        {
                            //TinyDB tb = new TinyDB(Youtube_activity.this);



                            if (ids1[0]!=null) {
                              //
                                time=Integer.parseInt(st.getString(1));
                                mWebView.loadUrl(ids1[0],true);



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
                params.put("number", "");
                params.put("category", Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID));

                return params;
            }


        };


        // Adding request to request queue
        App.getInstance().addToRequestQueue(strReq, tag_string_req);




    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) { }

    @Override
    public void onPageFinished(String url) { }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) { }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) { }

    @Override
    public void onExternalPageRequest(String url) { }

}

