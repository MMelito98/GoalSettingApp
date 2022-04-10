package com.example.goalsettingapp;

import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;


public class PlugActivity extends AppCompatActivity {
    private static final String LinkedinURL
            = "https://www.linkedin.com/in/michael-melito-914a18149/";
    private static final String TwitterURL
            ="https://twitter.com/MelitoMichael";
    private final String LOG_TAG = PlugActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plug);
     //   Toolbar toolbar = findViewById(R.id.toolbar_plug);      //Set up toolbar yet again.  Has to be a better way to do this
      //  setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemID = item.getItemId();
        switch (itemID) {
            case R.id.plug:
                Intent i = new Intent(this, PlugActivity.class);
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openLinkedIn(View view) {
        Log.d(LOG_TAG, "Linkedin Icon Clicked");
        Uri webpage = Uri.parse(LinkedinURL);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);        //Logic to open linkedin account on the icon click
        startActivity(intent);
    }

    public void openTwitter(View view) {
        Log.d(LOG_TAG, "Twitter Icon Clicked");
        Uri webpage = Uri.parse(TwitterURL);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);        //Logic to open twitter account on the icon click
        startActivity(intent);
    }
}