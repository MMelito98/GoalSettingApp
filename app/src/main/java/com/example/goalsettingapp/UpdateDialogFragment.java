package com.example.goalsettingapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.goalsettingapp.RecyclerViewClasses.RecyclerViewQuarterFragment;
import com.example.goalsettingapp.adapters.GoalRecyclerViewAdapter;

public class UpdateDialogFragment extends DialogFragment {

    public static final String CURRENT_VAL_CODE= "SUPER SECRET CODE FOR GROUP";
    public static final String FRAGMENT_TAG = UpdateDialogFragment.class.getSimpleName();
    int recyclerViewPosition;

    public UpdateDialogFragment(int position){
        this.recyclerViewPosition = position;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_update_dialog, container);
        MainViewModel viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        EditText mNewCurrentValue = view.findViewById(R.id.current_Goal_current_input);
        Button enterGoalsButton = view.findViewById(R.id.update_goal_button);
        enterGoalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCurrVal = mNewCurrentValue.getText().toString();
                int goalValue = checkCurrentValue(newCurrVal);

                viewModel.updateGoalValue(goalValue, recyclerViewPosition);

                dismiss();
            }
        });
        return view;

    }

    private int checkCurrentValue(String newCurrVal) {
        int goalValue = 0;
        if ((newCurrVal != null) && newCurrVal.length() > 0){
            try{
                goalValue = Integer.parseInt(newCurrVal);
            } catch (ArithmeticException e){
                goalValue = 1;
            }
        }
        return goalValue;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_update_dialog);
//
//
//
//        Button enterGoalsButton = findViewById(R.id.update_goal_button);
//        enterGoalsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int goalValue = 0;
//                String newCurrVal = mNewCurrentValue.getText().toString();
//
//
//                if ((newCurrVal != null) && newCurrVal.length() > 0){
//                    try{
//                        goalValue = Integer.parseInt(newCurrVal);
//                    } catch (Exception e){
//                        goalValue = -1;
//                    }
//                }
//                Intent getIntent = getIntent();
//                int tabValue = getIntent.getIntExtra(GoalRecyclerViewAdapter.TAB_CODE,0);
//                int posValue = getIntent.getIntExtra(GoalRecyclerViewAdapter.POSITION_CODE,0);
//
//                new RecyclerViewQuarterFragment().changeQuarterlyGoalValue(tabValue, goalValue, posValue);
//
//                finish();
//            }
//        });
//    }

}