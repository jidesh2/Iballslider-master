package com.onroute.iballslider;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.VideoView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jidesh on 06-12-2015.
 */
public class fragmentone extends Fragment {
    VideoView vn;
    String ids12;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       View viewroot= inflater.inflate(R.layout.fragment_one_layout,container,false);
        final ViewTreeObserver viewTreeObserver = viewroot.getViewTreeObserver();
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    /*    viewTreeObserver.addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
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
        });*/
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        getslider();



        //   Uri vidFile = Uri.parse(

        return  viewroot;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);


    }
    public void getslider()
    {
        String tag_string_req = "req_login";
        String content = null;
        // final String persona = parameters[0];


        content = "http://onroute.co.in/testserver/slider_add2.php";


        //  pDialog.setMessage("loading your favourites! ...");
        // showDialog();

        StringRequest strReq = new StringRequest(com.android.volley.Request.Method.POST,
                content, new com.android.volley.Response.Listener<String>() {


            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(String response) {
                Log.d("t2", "8889999 " + response);
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
                    //  JSONArray st = jr.getJSONArray("slider");
                    ids12 = jr.getString("slider");
                  //  if (!getActivity().isDestroyed()|| !getActivity().isFinishing()) {
                        ImageView im = (ImageView) getView().findViewById(R.id.imageView2);
                        Glide.with(getActivity()).load(ids12).into(im);
                   // }
                    // ids2=st.getString(1);
                    // Toast.makeText(getApplicationContext(), st.toString(), Toast.LENGTH_LONG).show();
                    //ImageView op = (ImageView) findViewById(R.id.imageView29);
                    // Glide.with(getBaseContext()).load(CONTENTBRO.get(0)).into(op);
                    //  Toast.makeText(getApplicationContext(),CONTENTBRO.get(0),Toast.LENGTH_SHORT).show();
                    //      Intent intent = new Intent(OTPactivity.this,
                    //        DashboardActivity_.class);
                    // intent.putExtra("check","showotp");
                    //  startActivity(intent);
                    // new GetVideo().execute(CONTENTBRO);

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    //   Toast.makeText(getActivity(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }


        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();

                params.put("category", Settings.Secure.getString(getActivity().getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID));

                return params;
            }


        };


        // Adding request to request queue
        App.getInstance().addToRequestQueue(strReq, tag_string_req);



    }
    }
