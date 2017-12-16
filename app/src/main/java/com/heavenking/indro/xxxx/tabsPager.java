package com.heavenking.indro.xxxx;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by INDRO on 12/12/2017.
 */

public class tabsPager extends FragmentStatePagerAdapter {

    String[] titles = new String[]{"News","Profile","Ideas"};

    public tabsPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                BlankFragment newsFragment = new BlankFragment();
                return newsFragment;
            case 1:
                BlankFragment2 profileFragment = new BlankFragment2();
                return profileFragment;
            case 2:
                BlankFragment3 ideasFragment = new BlankFragment3();
                return ideasFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
