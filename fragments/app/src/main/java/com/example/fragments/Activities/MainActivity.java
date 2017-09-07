package com.example.fragments.Activities;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fragments.Fragments.DataFragment;
import com.example.fragments.Fragments.DetailsFragment;
import com.example.fragments.R;

public class MainActivity extends FragmentActivity implements DataFragment.DataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void SendData(String text) {
        // Call Details Fragment render text function

        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
        detailsFragment.renderText(text);
    }
}
