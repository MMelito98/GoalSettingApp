package com.example.goalsettingapp.RecyclerViewClasses;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.goalsettingapp.Goals;
import com.example.goalsettingapp.MainViewModel;
import com.example.goalsettingapp.R;
import com.example.goalsettingapp.adapters.GoalRecyclerViewAdapter;

import java.util.ArrayList;

public class RecyclerViewYearlyFragment extends Fragment {

    private MainViewModel mMainViewModel;

    public RecyclerViewYearlyFragment() { /*empty constructer*/}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yearly_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.yearly_list);
        mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);


        GoalRecyclerViewAdapter adapter = new GoalRecyclerViewAdapter(
                mMainViewModel.getYearlyGoals().getValue());

        mMainViewModel.getYearlyGoals().observe(getViewLifecycleOwner(), goals -> {
            adapter.addGoal(goals);
        });
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void updateGoals(ArrayList<Goals> goals, GoalRecyclerViewAdapter adapter) {
        adapter.addGoal(goals);
    }

}
