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


public class PageAdapter extends FragmentStateAdapter {
    int mNumOfTabs;

    private static int mCurrentTab;


    public PageAdapter(@NonNull FragmentManager fm, Lifecycle lifecycle) {
        super(fm, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int i) {
        Log.d("HERE", "got here");
        switch (i) {
            case (0):
                return new RecyclerViewQuarterFragment();

            case (1):
                return new RecyclerViewYearlyFragment();

            case (2):
                return new RecyclerViewFiveYearlyFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static int getCurrentTab() {

        Log.d("pos", String.valueOf(mCurrentTab));
        return mCurrentTab;
    }
}
