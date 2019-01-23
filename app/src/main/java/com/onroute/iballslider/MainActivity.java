package com.onroute.iballslider;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.TimeUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


import com.swipper.library.Swipper;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class MainActivity extends Swipper  {
    private AutoScrollViewPager viewPager;
    customViewGroup view;
    TinyDB tb;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

        setContentView(R.layout.activity_main);
        set(this);
        tb=new TinyDB(this);
        if(tb.getString("orientation").equals("null") || tb.getString("orientation").isEmpty())
        {
            Intent mainIntent = new Intent(MainActivity.this,settings_new.class );
            //   mainIntent.putExtra("hi", a);mainIntent.putExtra("news","null");
            // mainIntent.putExtra("title", x1.getText().toString());
            startActivity(mainIntent);
finish();
        }
        WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
        localLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        localLayoutParams.gravity = Gravity.TOP;
        localLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|

                // this is to enable the notification to recieve touch events
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |

                // Draws over status bar
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;

        localLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        localLayoutParams.height = (int) (30* getResources()
                .getDisplayMetrics().scaledDensity);
        localLayoutParams.format = PixelFormat.TRANSPARENT;
        Brightness(Orientation.VERTICAL);
        enableBrightness();
        enableVolume();
        view = new customViewGroup(this);
        DepthTransformation depthTransformation = new DepthTransformation();
//      manager.addView(view, localLayoutParams);
      //  startLockTask();
        viewPager = (AutoScrollViewPager)findViewById(R.id.view_pager);
        PagerAdapter padapter=new PagerAdapter(getFragmentManager(),this);
        viewPager.setAdapter(padapter);
        viewPager.setInterval(920000000);
        viewPager.setPageTransformer(true, depthTransformation);
     //   viewPager.setOffscreenPageLimit(2);
        viewPager.stopAutoScroll();
        viewPager.setBorderAnimation(false);
     //   viewPager.setScrollDurationFactor(6);
        Volume(Orientation.CIRCULAR);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        windowCloseHandler.post(windowCloserRunnable);

    }
    @Override
    protected void onPause() {
        super.onPause();

        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);

        activityManager.moveTaskToFront(getTaskId(), 0);
        // handler.removeCallbacks(myRunnable);
    }
    private Handler windowCloseHandler = new Handler();
    private Runnable windowCloserRunnable = new Runnable() {@Override public void run() {
        ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        if (cn != null && cn.getClassName().equals("com.android.systemui.recent.RecentsActivity")) {
            toggleRecents();
        }
    }
    };
    private void toggleRecents() {
        Intent closeRecents = new Intent("com.android.systemui.recent.action.TOGGLE_RECENTS");
        closeRecents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        ComponentName recents = new ComponentName("com.android.systemui", "com.android.systemui.recent.RecentsActivity");
        closeRecents.setComponent(recents);
        this.startActivity(closeRecents);
    }
    public class customViewGroup extends ViewGroup {

        public customViewGroup(Context context) {
            super(context);
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            Log.v("customViewGroup", "**********Intercepted");
            return true;
        }
    }
    public void start_scroll()
    {
        viewPager.scrollOnce();

    }

}

