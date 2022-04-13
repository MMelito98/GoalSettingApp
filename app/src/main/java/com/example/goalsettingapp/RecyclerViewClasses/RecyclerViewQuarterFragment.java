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


public class RecyclerViewQuarterFragment extends Fragment {

    private static ArrayList<Goals> mQuarterlyGoals = new ArrayList<>();
    private MainViewModel viewModel;


    public RecyclerViewQuarterFragment() { /*empty constructer*/}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quarter_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.quarter_list);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);


        GoalRecyclerViewAdapter adapter;
        int currentTabPosition = PageAdapter.getCurrentTab();

        adapter = new GoalRecyclerViewAdapter(viewModel.getQuarterlyGoals().getValue());
        viewModel.getQuarterlyGoals().observe(getViewLifecycleOwner(), goals -> {
            adapter.updateGoals(goals);
        });

        recyclerView.setAdapter(adapter);

        return view;
    }

    public void changeQuarterlyGoalValue(int tabIndicator, int currentValue, int position) {
        Goals newGoalsItem = mQuarterlyGoals.get(position);
        newGoalsItem.setCurrentValue(currentValue);
        mQuarterlyGoals.set(position, newGoalsItem);

    }

}
