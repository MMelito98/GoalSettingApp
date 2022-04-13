package com.example.goalsettingapp.RecyclerViewClasses;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.goalsettingapp.Goals;
import com.example.goalsettingapp.MainViewModel;
import com.example.goalsettingapp.R;
import com.example.goalsettingapp.adapters.GoalRecyclerViewAdapter;
import com.example.goalsettingapp.adapters.PageAdapter;

import java.util.ArrayList;

public class RecyclerViewYearlyFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    private static ArrayList<Goals> mYearlyGoals = new ArrayList<>();

    private MainViewModel mMainViewModel;



    public RecyclerViewYearlyFragment() { //empty constructer
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RecyclerViewYearlyFragment newInstance(int columnCount) {
        RecyclerViewYearlyFragment fragment = new RecyclerViewYearlyFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yearly_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.yearly_list);
        mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            int currentTabPosition = PageAdapter.getCurrentTab();

            GoalRecyclerViewAdapter adapter = new GoalRecyclerViewAdapter (
                    mMainViewModel.getYearlyGoals().getValue());

            mMainViewModel.getYearlyGoals().observe(getViewLifecycleOwner(), goals -> {
                updateGoals(goals,adapter);
            });
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    private void updateGoals(ArrayList<Goals> goals, GoalRecyclerViewAdapter adapter) {
        adapter.updateGoals(goals);
    }


    public static void addYearlyGoal(Goals goal) {
        mYearlyGoals.add(goal);
    }


    public void changeGoalCurrentValue(int tabIndicator, int currentValue, int position) {
        Goals newGoalsItem;

        switch (tabIndicator) {

            case 1:
                newGoalsItem = mYearlyGoals.get(position);
                newGoalsItem.setCurrentValue(currentValue);
                mYearlyGoals.set(position, newGoalsItem);
                break;
        }
    }

}
