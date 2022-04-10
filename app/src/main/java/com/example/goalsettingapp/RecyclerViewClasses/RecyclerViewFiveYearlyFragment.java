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

/**
 * A fragment representing a list of Items.
 */
public class RecyclerViewFiveYearlyFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private static ArrayList<Goals> mFiveYearlyGoals = new ArrayList<>();
    private MainViewModel mMainViewModel;


    public RecyclerViewFiveYearlyFragment() { //empty constructer
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RecyclerViewFiveYearlyFragment newInstance(int columnCount) {
        RecyclerViewFiveYearlyFragment fragment = new RecyclerViewFiveYearlyFragment();
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
        View view = inflater.inflate(R.layout.fragment_five_yearly_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.five_yearly_list);
        mMainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            GoalRecyclerViewAdapter adapter;
            int currentTabPosition = PageAdapter.getCurrentTab();
            Log.d("tabPos", Integer.toString(currentTabPosition));

            adapter = new GoalRecyclerViewAdapter(mMainViewModel.getFiveYearlyGoals());
            recyclerView.setAdapter(adapter);

        }
        return view;
    }


    public static void addFiveYearlyGoal(Goals goal) {
        mFiveYearlyGoals.add(goal);
    }

    public void changeGoalCurrentValue(int tabIndicator, int currentValue, int position) {
        Goals newGoalsItem;

        switch (tabIndicator) {

            case 2:
                newGoalsItem = mFiveYearlyGoals.get(position);
                newGoalsItem.setCurrentValue(currentValue);
                mFiveYearlyGoals.set(position, newGoalsItem);
                break;
            default:
                break;
        }
    }

}
