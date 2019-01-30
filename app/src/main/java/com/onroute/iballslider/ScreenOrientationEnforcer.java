package com.onroute.iballslider;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.view.View;
import android.view.WindowManager;

public class ScreenOrientationEnforcer {

    private final View view;
    private final WindowManager windows;

    public ScreenOrientationEnforcer(Context context) {
        windows = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        view = new View(context);
    }

    public void start() {
        WindowManager.LayoutParams layout = generateLayout();
        windows.addView(view, layout);
        view.setVisibility(View.VISIBLE);
    }

    public void stop() {
        windows.removeView(view);
    }

    private WindowManager.LayoutParams generateLayout() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

        //So we don't need a permission or activity
        //Note that this won't work on some devices running MIUI
        layoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;

        //Just in case the window type somehow doesn't enforce this
        layoutParams.flags =
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        //Prevents breaking apps that detect overlying windows enabling
        //(eg UBank app, or enabling an accessibility service)
        layoutParams.width = 0;
        layoutParams.height = 0;

        //Try to make it completely invisible
        layoutParams.format = PixelFormat.TRANSPARENT;
        layoutParams.alpha = 0f;

        //The orientation to force
        layoutParams.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;

        return layoutParams;
    }

}
