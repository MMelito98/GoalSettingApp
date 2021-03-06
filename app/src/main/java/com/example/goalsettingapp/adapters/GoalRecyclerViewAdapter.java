package com.example.goalsettingapp.adapters;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.goalsettingapp.Goals;
import com.example.goalsettingapp.R;
import com.example.goalsettingapp.UpdateDialogFragment;
import com.example.goalsettingapp.databinding.FragmentListItemBinding;

import java.util.ArrayList;


public class GoalRecyclerViewAdapter extends RecyclerView.Adapter<GoalRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Goals> mGoalsList;
    private Context mContext;
    private final String LOGCAT = "GoalrecyclerViewAdapter";
    public static int curVal = 0;

    public static final String TAB_CODE= "SUPER SECRET CODE FOR TAB POSITION";
    public static final String POSITION_CODE= "SUPER SECRET CODE FOR ARRAY POSITION";


    public GoalRecyclerViewAdapter(ArrayList<Goals> items) {
        mGoalsList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(FragmentListItemBinding
                .inflate(LayoutInflater.from(mContext),
                        parent,
                        false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mGoalsList.get(position);
        holder.mTitleText.setText(mGoalsList.get(position).getTitle());
        holder.mSubtitleText.setText(mGoalsList.get(position).getDescription());
        int currentValue = mGoalsList.get(position).getCurrentValue();
        int goalValue = mGoalsList.get(position).getGoalValue();
        holder.mProgressText.setText("" + currentValue + "/" +
                goalValue + "");
        holder.mProgressBar.
                setProgress(getProgressBarNumber(currentValue, goalValue));

        holder.mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new UpdateDialogFragment(position).show(
                        ((AppCompatActivity) mContext)
                                .getSupportFragmentManager(),
                        UpdateDialogFragment.FRAGMENT_TAG);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (mGoalsList == null){
            return 0;
        }
        else {
            return mGoalsList.size();
        }
    }

    private int getProgressBarNumber(int current, int goal){
        int value;
        try {
            value = (int)(((double) current / (double) goal) * 100);
        } catch (ArithmeticException e) {
            value = 34;
        }
        if (value > 100)
            value =  100;
        return value;
    }

    public void addGoal(ArrayList<Goals> goals) {
        mGoalsList = goals;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{

        public final TextView mTitleText;
        public final TextView mSubtitleText;
        public final TextView mProgressText;
        public final ProgressBar mProgressBar;
        public final CardView mCardview;
        public final Button mUpdateButton;
        public Goals mItem;

        public ViewHolder(FragmentListItemBinding binding) {
            super(binding.getRoot());
            mTitleText = binding.goalTitle;
            mSubtitleText = binding.goalDescription;
            mProgressText = binding.progressText;
            mUpdateButton = binding.updateButton;
            mProgressBar = binding.progressBar;
            mCardview = binding.card;

            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mSubtitleText.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            Log.d(LOGCAT, "View CLicked");
            int position = getAdapterPosition();

            switch (mCardview.findViewById(R.id.expandable).getVisibility()){
                case View.GONE:
                    mCardview.findViewById(R.id.expandable)
                            .setVisibility(View.VISIBLE);
                    break;
                case View.VISIBLE:
                    mCardview.findViewById(R.id.expandable)
                            .setVisibility(View.GONE);
                    break;
            }
        }
    }
}