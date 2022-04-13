package com.example.goalsettingapp;

import android.app.LoaderManager;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.goalsettingapp.RecyclerViewClasses.RecyclerViewFiveYearlyFragment;
import com.example.goalsettingapp.RecyclerViewClasses.RecyclerViewQuarterFragment;
import com.example.goalsettingapp.RecyclerViewClasses.RecyclerViewYearlyFragment;
import com.example.goalsettingapp.adapters.PageAdapter;
import com.example.goalsettingapp.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
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
    private MainViewModel mMainViewModel;
    int tabPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);     //Inflating main Layout
        toolbarSetup();

        TextView currentDateText = findViewById(R.id.current_date);
        currentDateText.setText(getCurrentDate());
        TextView goalDateText = findViewById(R.id.goal_date);
        goalDateText.setText(getGoalDate(0));

        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);


        TabLayout tabLayout = findViewById(R.id.tab_layout);    //Setting up the tab layout - tabs done in XML
        ViewPager2 viewPager = findViewById(R.id.pager);

        PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), getLifecycle());


        viewPager.setAdapter(adapter);
        adapter.addFragment(new RecyclerViewQuarterFragment());
        adapter.addFragment(new RecyclerViewYearlyFragment());
        adapter.addFragment(new RecyclerViewFiveYearlyFragment());
        new TabLayoutMediator(tabLayout, viewPager,(tab, position) -> {
            tab.setText("Tab");
        }).attach();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabPosition = tab.getPosition();
                goalDateText.setText(getGoalDate(tabPosition));
                viewPager.setCurrentItem(tabPosition);
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
        //setSupportActionBar(toolbar);
    }

    public void goToPlugPage(View view) {
        Log.d(LOG_TAG, "Plug page clicked");
        Intent intent = new Intent(MainActivity.this, PlugActivity.class);  //Opening plug activity
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //return super.onCreateOptionsMenu(menu);
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

    private String getCurrentDate() {
        Date today = new Date();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        return sdf.format(today);
    }

    private String getGoalDate(int position) {
        Date today = new Date();
        Date goalDate = new Date();

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        switch (position){
            case 0:
                int month = calendar.get(Calendar.MONTH);
                int newMonth = month + ((month)%3);

                calendar.set(Calendar.MONTH, newMonth);

                int newDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // Makes day end of month

                calendar.set(Calendar.DAY_OF_MONTH, newDay);

                goalDate = calendar.getTime();
                break;
            case 1:
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.MONTH, 0);
                calendar.add(Calendar.YEAR, 1);
                goalDate = calendar.getTime();
                break;
            case 2:
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.MONTH, 0);
                calendar.add(Calendar.YEAR, 5);
                goalDate = calendar.getTime();
                break;

        }
        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(goalDate);
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
                    mMainViewModel.addQuarterlyGoal(goal);
                    break;
                case (1):
                    mMainViewModel.addYearlyGoal(goal);
                    break;
                case (2):
                    mMainViewModel.addFiveYearlyGoal(goal);
                    break;
                default:
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}