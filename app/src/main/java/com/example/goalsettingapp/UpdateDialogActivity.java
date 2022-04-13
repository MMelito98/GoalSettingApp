package com.example.goalsettingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.ViewModel;

import com.example.goalsettingapp.RecyclerViewClasses.RecyclerViewQuarterFragment;
import com.example.goalsettingapp.adapters.GoalRecyclerViewAdapter;

public class UpdateDialogActivity extends Activity {

    public static final String CURRENT_VAL_CODE= "SUPER SECRET CODE FOR GROUP";
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dialog);
        EditText mNewCurrentValue = findViewById(R.id.current_Goal_current_input);



        Button enterGoalsButton = findViewById(R.id.update_goal_button);
        enterGoalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int goalValue = 0;
                String newCurrVal = mNewCurrentValue.getText().toString();


                if ((newCurrVal != null) && newCurrVal.length() > 0){
                    try{
                        goalValue = Integer.parseInt(newCurrVal);
                    } catch (Exception e){
                        goalValue = -1;
                    }
                }
                Intent getIntent = getIntent();
                int tabValue = getIntent.getIntExtra(GoalRecyclerViewAdapter.TAB_CODE,0);
                int posValue = getIntent.getIntExtra(GoalRecyclerViewAdapter.POSITION_CODE,0);

                new RecyclerViewQuarterFragment().changeQuarterlyGoalValue(tabValue, goalValue, posValue);

                finish();
            }
        });
    }

}