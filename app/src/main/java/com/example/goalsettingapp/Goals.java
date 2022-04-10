package com.example.goalsettingapp;

public class Goals {

    private String Title;
    private String description;
    private int goalValue;
    private int currentValue = 0;
    private int goalType;       //Intializing the goals Varaibles

    public final int QUARTERLY_GOAL = 1;
    public final int YEARLY_GOAL = 2;
    public final int FIVE_YEARLY_GOAL = 3;  //Creating public final ints for which type of goal it is

    public Goals(String title, String description, int goalValue) {
        this.Title = title;
        this.description = description;
        this.goalValue = goalValue;
    }

    public String getTitle() {return Title;}

    public String getDescription() {return description;}

    public int getGoalValue() {return goalValue;}

    public int getCurrentValue() {return currentValue;}

    public int getGoalType() {return goalType;}         //All of the Getters

    public void setCurrentValue(int currentValue) {      //Only Setter, as this is the only vallue that can really change
        this.currentValue = currentValue;
    }
}
