package com.example.goalsettingapp;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.goalsettingapp.RecyclerViewClasses.RecyclerViewFiveYearlyFragment;
import com.example.goalsettingapp.RecyclerViewClasses.RecyclerViewQuarterFragment;
import com.example.goalsettingapp.RecyclerViewClasses.RecyclerViewYearlyFragment;
import com.example.goalsettingapp.adapters.PageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import android.widget.TextView;


import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager2.widget.ViewPager2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static int REQUEST_CODE = 1;
    private MainViewModel viewModel;
    int tabPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);     //Inflating main Layout
        toolbarSetup();

        TextView currentDateText = findViewById(R.id.current_date);

        currentDateText.setText(GoalDateHandler.getCurrentDate());
        TextView goalDateText = findViewById(R.id.goal_date);

        goalDateText.setText(GoalDateHandler.getGoalDate(0));

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);


        TabLayout tabLayout = findViewById(R.id.tab_layout);    //Setting up the tab layout - tabs done in XML
        ViewPager2 viewPager = findViewById(R.id.pager);

        PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), getLifecycle());


        viewPager.setAdapter(adapter);
        adapter.addFragment(new RecyclerViewQuarterFragment());
        adapter.addFragment(new RecyclerViewYearlyFragment());
        adapter.addFragment(new RecyclerViewFiveYearlyFragment());
        new TabLayoutMediator(tabLayout, viewPager,(tab, position) -> {
            String[] goalTypeNames = getResources().getStringArray(R.array.goal_group_list);
            String currentName = goalTypeNames[position];
            String massagedName = currentName.substring(0, currentName.length()-5);
            tab.setText(massagedName);
        }).attach();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabPosition = tab.getPosition();
                goalDateText.setText(GoalDateHandler.getGoalDate(tabPosition));
                viewModel.setTabPosition(tabPosition);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void toolbarSetup() {
        Toolbar toolbar = findViewById(R.id.toolbar_main);  //Setting up toolbar
        setSupportActionBar(toolbar);
    }

    public void goToPlugPage(View view) {
        Log.d(LOG_TAG, "Plug page clicked");
        Intent intent = new Intent(MainActivity.this, PlugActivity.class);  //Opening plug activity
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemID = item.getItemId();      //options method - Fill other items later
        switch (itemID) {
            case R.id.plug:
                Intent i = new Intent(this, PlugActivity.class);
                startActivity(i);
                break;
            case R.id.add:
                addGoal();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    public void addGoal() {
        Intent addIntent = new Intent(this, AddDialogActivity.class);
        startActivityForResult(addIntent, REQUEST_CODE);
        //be set to the right goal group

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            Goals goal = new Goals(data.getStringExtra(AddDialogActivity.TITLE_CODE),
                    data.getStringExtra(AddDialogActivity.DESC_CODE),
                    data.getIntExtra(AddDialogActivity.VALUE_CODE, 0));
            int goalGroup = data.getIntExtra(AddDialogActivity.GROUP_CODE,0);

            switch (goalGroup){
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
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}