package com.example.goalsettingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.ResultSet;

public class AddDialogActivity extends Activity {
    public static final String VALUE_CODE= "SUPER SECRET CODE FOR VALUE";
    public static final String TITLE_CODE= "SUPER SECRET CODE FOR TITLE";
    public static final String DESC_CODE= "SUPER SECRET CODE FOR DESC";
    public static final String GROUP_CODE= "SUPER SECRET CODE FOR GROUP";


    EditText mGoalTitle;
    EditText mGoalDesc;
    EditText mGoalValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dialog);
        Intent replyIntent = getIntent();

        Spinner spinner = findViewById(R.id.goal_group_spinner);
       // spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.goal_group_list,
                        android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        mGoalTitle = findViewById(R.id.Goals_title_input);
        mGoalDesc = findViewById(R.id.Goals_desc_input);
        mGoalValue = findViewById(R.id.Goals_value_input);
        Button enterGoalsButton = findViewById(R.id.update_goal_button);
        enterGoalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double goalValue = null;

                String goalTitle = mGoalTitle.getText().toString();
                String goalDesc = mGoalDesc.getText().toString();
                String goalStrNum = mGoalValue.getText().toString();


                if ((goalStrNum != null) && goalStrNum.length() > 0){
                    try{
                        goalValue = Double.parseDouble(goalStrNum);
                    } catch (Exception e){
                        goalValue = Double.valueOf(-1);
                    }
                }
                int goalGroupint = spinner.getSelectedItemPosition();

                Intent intent = new Intent();
                intent.putExtra(GROUP_CODE, goalGroupint);
                intent.putExtra(TITLE_CODE, goalTitle);
                intent.putExtra(DESC_CODE, goalDesc);
                intent.putExtra(VALUE_CODE,goalValue);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void setUpSpinner(int goalGroup) {
        Spinner spinner = (Spinner) findViewById(R.id.goal_group_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.goal_group_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        String[] goalGroupArray = getResources().getStringArray(R.array.goal_group_list);
        spinner.setPrompt(goalGroupArray[goalGroup]);
    }
}