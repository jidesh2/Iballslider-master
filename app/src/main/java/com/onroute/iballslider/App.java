package com.onroute.iballslider;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;



//import android.support.multidex.MultiDex;


public class App extends Application {
   // public static final String TAG = AppController.class.getSimpleName();
    public static SharedPreferences preferences;
    private RequestQueue mRequestQueue;
    private static Context mContext;
    private static App mInstance;
   // RefWatcher refWatcher;
    private String phone_number;
    private boolean act;
    private String persona;
    private String slat;
    private String slong;
    String idd;
    private static boolean a;
    public static String YOUTUBE_API_KEY;
    private String starttimee;
    ArrayList<String> URLOPEN=new ArrayList<String>();
    ArrayList<String> IDOPEN=new ArrayList<String>();
    ArrayList<String> TITLEOPEN=new ArrayList<String>();


    public ArrayList<String> get_URL() {
        return URLOPEN;
    }
    public ArrayList<String> get_TITLE() {
        return TITLEOPEN;
    }
    public ArrayList<String> get_ID() {
        return IDOPEN;
    }
    public String getSlat() {
        return slat;
    }
    public boolean getact() {
        return act;
    }
    public String getSlong() {
        return slong;
    }
    public String getPersona() {
        return persona;
    }
    public String getPhone_number() {
        return phone_number;
    }
    //public String getPhone_number() {
   //     return phone_number;

   // }
    public static String if_it_contains_me()
    {


       {
           YOUTUBE_API_KEY = "AIzaSyDKi3za0bU_sRbMTow3n2qnaGludVR_XO8";

       }
return YOUTUBE_API_KEY;
    }
    public void setPhone_number(String someVariable) {
        this.phone_number = someVariable;
    }
    public void setPersona(String personamilla) {
        this.persona = personamilla;
    }
    public void setLat(String startlat) {
        this.slat=startlat;
    }
    public void setLong(String startlong) {
        this.slong=startlong;
    }
    public void setact(boolean act) {
        this.act=act;
    }
    public void set_URL(String someVariable) {
        this.URLOPEN.add(someVariable);
    }
    public void set_TITLE(String someVariable) {
        this.TITLEOPEN.add(someVariable);
    }
    public void set_IDOPEN(String someVariable) {
        this.IDOPEN.add(someVariable);
    }




    public static boolean getreadytofallback()
    {
        return a;

    }
    public static void  setreadytofallback(boolean m)
    {
        a=m;

    }



    public void delete()
    {
        this.TITLEOPEN.clear();
        this.URLOPEN.clear();
        this.IDOPEN.clear();

    }
    private String[] column = {"2", "2", "3", "3", "3", "21", "21", "22", "22", "23", "23", "23", "24", "24",
            "31", "31", "25", "25", "25", "26", "26", "27", "27", "28", "28", "28", "32", "32", "29", "29", "201", "201", "201", "202", "202",
            "203", "203", "204", "204", "204", "205", "205"};
    public String getStarttime() {
        return starttimee;
    }


    public void setStarttime(String someVariable) {
        this.starttimee = someVariable;
    }






    @Override
    public void onCreate() {
        super.onCreate();
     //   refWatcher = LeakCanary.install(this);
        preferences = getSharedPreferences( getPackageName() + "_preferences", MODE_PRIVATE);
        // Setup the application graph
        mContext = getApplicationContext();
     //   applicationGraph = ObjectGraph.create().plus(new ActivityModule(this));
        mInstance = this;
   // }tion graph
        mContext = getApplicationContext();
      //  applicationGraph = ObjectGraph.create().plus(new ActivityModule(this));
        mInstance = this;
    }
    public static Context getContext() {
        return mContext;
    }
    public static synchronized App getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(com.android.volley.Request<T> req, String tag) {
       // req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(com.android.volley.Request<T> req) {
       // req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
      //  MultiDex.install(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }



}