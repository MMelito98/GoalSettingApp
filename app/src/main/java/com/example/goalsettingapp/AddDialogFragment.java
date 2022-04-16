package com.example.goalsettingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

public class AddDialogFragment extends DialogFragment {

    public static final String FRAGMENT_TAG = AddDialogFragment
            .class.getSimpleName();

    public static final String VALUE_CODE= "SUPER SECRET CODE FOR VALUE";
    public static final String TITLE_CODE= "SUPER SECRET CODE FOR TITLE";
    public static final String DESC_CODE= "SUPER SECRET CODE FOR DESC";
    public static final String GROUP_CODE= "SUPER SECRET CODE FOR GROUP";

    public AddDialogFragment(){//Empty Contructor
        }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_dialog, container);
        MainViewModel viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        Spinner spinner = (Spinner) view.findViewById(R.id.goal_group_spinner);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(getContext(), R.array.goal_group_list,
                        android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        EditText mGoalTitle = view.findViewById(R.id.Goals_title_input);
        EditText mGoalDesc = view.findViewById(R.id.Goals_desc_input);
        EditText mGoalValue = view.findViewById(R.id.Goals_value_input);
        Button enterGoalsButton = view.findViewById(R.id.update_goal_button);
        enterGoalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String goalTitle = mGoalTitle.getText().toString();
                String goalDesc = mGoalDesc.getText().toString();
                String goalStrNum = mGoalValue.getText().toString();

                int goalValue = UpdateDialogFragment.checkStringIntegerValue(goalStrNum);

                int goalGroupInt = spinner.getSelectedItemPosition();
                Goals goal = new Goals(goalTitle, goalDesc, goalValue);
                switch (goalGroupInt){
                    case (0):
                        viewModel.addQuarterlyGoal(goal);
                        break;
                    case (1):
                        viewModel.addYearlyGoal(goal);
                        break;
                    case (2):
                        viewModel.addFiveYearlyGoal(goal);
                        break;
                    default:
                        break;
                }

                dismiss();
            }
        });

        return view;
    }


    private void setUpSpinner(int goalGroup, View view) {
        Spinner spinner = (Spinner) view.findViewById(R.id.goal_group_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.goal_group_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        String[] goalGroupArray = getResources().getStringArray(R.array.goal_group_list);
        spinner.setPrompt(goalGroupArray[goalGroup]);
    }
}