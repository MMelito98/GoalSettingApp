package com.example.goalsettingapp.RecyclerViewClasses;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.goalsettingapp.Goals;
import com.example.goalsettingapp.MainViewModel;
import com.example.goalsettingapp.R;
import com.example.goalsettingapp.adapters.GoalRecyclerViewAdapter;
import com.example.goalsettingapp.adapters.PageAdapter;

import java.util.ArrayList;


public class RecyclerViewFiveYearlyFragment extends Fragment {

    private static ArrayList<Goals> mFiveYearlyGoals = new ArrayList<>();
    private MainViewModel mMainViewModel;


    public RecyclerViewFiveYearlyFragment() { /*empty constructer*/}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_five_yearly_list, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.five_yearly_list);

        mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        GoalRecyclerViewAdapter adapter;
        int currentTabPosition = PageAdapter.getCurrentTab();

        adapter = new GoalRecyclerViewAdapter(mMainViewModel.getFiveYearlyGoals().getValue());
        mMainViewModel.getFiveYearlyGoals().observe(getViewLifecycleOwner(), goals -> {
            adapter.updateGoals(goals);
        });
        recyclerView.setAdapter(adapter);

        return view;
    }


}
