package com.example.goalsettingapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    private static MutableLiveData<ArrayList<Goals>> mQuarterlyGoals = new MutableLiveData<>();
    private static ArrayList<Goals> mQuarterlyGoalsList = new ArrayList<>();

    private static MutableLiveData<ArrayList<Goals>> mYearlyGoals = new MutableLiveData<>();
    private static ArrayList<Goals> mYearlyGoalsList = new ArrayList<>();

    private static MutableLiveData<ArrayList<Goals>> mFiveYearlyGoals = new MutableLiveData<ArrayList<Goals>>();
    private static ArrayList<Goals> mFiveYearlyGoalsList = new ArrayList<>();


    public ArrayList<Goals> getQuarterlyGoals(){
        return mQuarterlyGoals.getValue();
    }
    public ArrayList<Goals> getYearlyGoals(){
        return mYearlyGoals.getValue();
    }
    public ArrayList<Goals> getFiveYearlyGoals(){
        return mFiveYearlyGoals.getValue();
    }

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
}
