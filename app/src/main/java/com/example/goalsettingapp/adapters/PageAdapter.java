package com.example.goalsettingapp.adapters;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.goalsettingapp.RecyclerViewClasses.RecyclerViewFiveYearlyFragment;
import com.example.goalsettingapp.RecyclerViewClasses.RecyclerViewQuarterFragment;
import com.example.goalsettingapp.RecyclerViewClasses.RecyclerViewYearlyFragment;

import java.util.ArrayList;


public class PageAdapter extends FragmentStateAdapter {
    int mNumOfTabs;

    private static int mCurrentTab;
    final String LOGTAG = PageAdapter.class.getSimpleName();

    ArrayList<Fragment> fragmentList = new ArrayList<>();


    public PageAdapter(@NonNull FragmentManager fm, Lifecycle lifecycle) {
        super(fm, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int i) {
        Log.d(LOGTAG, "PageAdapter i: "+ Integer.toString(i));
        return fragmentList.get(i);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment){
        fragmentList.add(fragment);
    }


    public static int getCurrentTab() {

        Log.d("pos", String.valueOf(mCurrentTab));
        return mCurrentTab;
    }
}
