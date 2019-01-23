package com.onroute.iballslider;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jidesh on 06-12-2015.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    String ids12;
TinyDB tb;
    public PagerAdapter(FragmentManager fm, Activity activity) {

        super(fm);
        tb=new TinyDB(activity);
    }



    @Override
    public int getCount() {

        return 3;
    }



    @Override
    public Fragment getItem(int arg0) {

if(tb.getString("orientation").equals("portrait"))
{
    switch (arg0) {
        case 0:
            return new fragmentone();
        // return new FragmentSimpleLoginButton();

        //     return new netflix();
        case 1:
            return new fragmenttwo();
        //   return new fragmentfour();


        case 2:

            return new Godrej_page();


    }
}
else {

    switch (arg0) {
        case 0:
            return new fragmentone();
        // return new FragmentSimpleLoginButton();

        //     return new netflix();
        case 1:
            return new fragmenttwo();
        //   return new fragmentfour();


        case 2:

            return new Godrej_page();



         /*   case 4:
                //

                */


    }
}
        return null;
    }
    @Override
    public void destroyItem(ViewGroup viewPager, int position, Object object) {

    }
}
