package com.example.goalsettingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

public class UpdateDialogFragment extends DialogFragment {

    public static final String FRAGMENT_TAG = UpdateDialogFragment
            .class.getSimpleName();
    int recyclerViewPosition;

    public UpdateDialogFragment(int position){
        this.recyclerViewPosition = position;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_dialog, container);
        MainViewModel viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        EditText mNewCurrentValue = view.findViewById(R.id.current_Goal_current_input);
        Button enterGoalsButton = view.findViewById(R.id.update_goal_button);
        enterGoalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCurrVal = mNewCurrentValue.getText().toString();
                int goalValue = checkStringIntegerValue(newCurrVal);

                viewModel.updateGoalValue(goalValue, recyclerViewPosition);

                dismiss();
            }
        });
        return view;

    }

    public static int checkStringIntegerValue(String newCurrVal) {
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


}