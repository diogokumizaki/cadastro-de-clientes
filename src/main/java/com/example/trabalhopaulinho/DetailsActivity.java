package com.example.trabalhopaulinho;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

/*
 * Details activity class that loads LeanbackDetailsFragment class
 */
public class DetailsActivity extends FragmentActivity {
    public static final String SHARED_ELEMENT_NAME = "hero";
    public static final String MOVIE = "Movie";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(androidx.leanback.R.id.details_background_view, new VideoDetailsFragment())
                    .commitNow();
        }
    }

}