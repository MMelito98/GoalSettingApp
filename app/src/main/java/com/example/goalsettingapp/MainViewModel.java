package com.example.goalsettingapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    private static MutableLiveData<ArrayList<Goals>> mQuarterlyGoals = new MutableLiveData<>();
    private static ArrayList<Goals> mQuarterlyGoalsList = new ArrayList<>();

    static MutableLiveData<ArrayList<Goals>> mYearlyGoals = new MutableLiveData<>();
    private static ArrayList<Goals> mYearlyGoalsList = new ArrayList<>();

    private static MutableLiveData<ArrayList<Goals>> mFiveYearlyGoals = new MutableLiveData<ArrayList<Goals>>();
    private static ArrayList<Goals> mFiveYearlyGoalsList = new ArrayList<>();

    private static int tabPosition;


    public LiveData<ArrayList<Goals>> getQuarterlyGoals(){ return mQuarterlyGoals; }
    public LiveData<ArrayList<Goals>> getYearlyGoals() {
        return mYearlyGoals;
    }
    public LiveData<ArrayList<Goals>> getFiveYearlyGoals(){ return mFiveYearlyGoals; }

    public void addQuarterlyGoal(Goals goal){
        mQuarterlyGoalsList.add(goal);

        mQuarterlyGoals.postValue(mQuarterlyGoalsList);
    }

    public void addYearlyGoal(Goals goal){
        mYearlyGoalsList.add(goal);
        mYearlyGoals.postValue(mYearlyGoalsList);
    }

    public void addFiveYearlyGoal(Goals goal){
        mFiveYearlyGoalsList.add(goal);
        mFiveYearlyGoals.postValue(mFiveYearlyGoalsList);
    }

    public void setTabPosition(int i){
        tabPosition = i;
    }

    public int getTabPosition(){
        return tabPosition;
    }

    public void updateGoalValue(int currentValue, int recyclerPos){
        Goals newGoalsItem;
        switch (tabPosition){
            case 0:
                newGoalsItem = mQuarterlyGoalsList.get(recyclerPos);
                newGoalsItem.setCurrentValue(currentValue);
                mQuarterlyGoalsList.set(recyclerPos, newGoalsItem);
                mQuarterlyGoals.postValue(mQuarterlyGoalsList);
                break;
            case 1:
                newGoalsItem = mYearlyGoalsList.get(recyclerPos);
                newGoalsItem.setCurrentValue(currentValue);
                mYearlyGoalsList.set(recyclerPos, newGoalsItem);
                mYearlyGoals.postValue(mYearlyGoalsList);
                break;
            case 2:
                newGoalsItem = mFiveYearlyGoalsList.get(recyclerPos);
                newGoalsItem.setCurrentValue(currentValue);
                mFiveYearlyGoalsList.set(recyclerPos, newGoalsItem);
                mFiveYearlyGoals.postValue(mFiveYearlyGoalsList);
                break;

        }

    }
}
