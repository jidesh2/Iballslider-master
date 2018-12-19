package com.onroute.iballslider;


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

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public int getCount() {
        return 1;
    }



    @Override
    public Fragment getItem(int arg0) {

        switch (arg0) {
            case 0:
               // return new FragmentSimpleLoginButton();
                return new Godrej_page();

       //     return new netflix();
            case 1:
                return new fragmenttwo();
             //   return new fragmentfour();


        case 2:

            return new firstpage();

     case 3:
             return new fragmentaka();


         /*   case 4:
                //

                */

        }
        return null;
    }
    @Override
    public void destroyItem(ViewGroup viewPager, int position, Object object) {

    }
}
