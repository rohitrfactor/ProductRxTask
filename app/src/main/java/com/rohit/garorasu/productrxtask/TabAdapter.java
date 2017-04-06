package com.rohit.garorasu.productrxtask;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.rohit.garorasu.productrxtask.Form.FormFragment;
import com.rohit.garorasu.productrxtask.Result.ResultFragment;

/**
 * Created by garorasu on 6/4/17.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 2;

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return new FormFragment();
            case 1:
                return new ResultFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Form";
            case 1:
                return "Results";
            default:
                return null;
        }
    }
}
