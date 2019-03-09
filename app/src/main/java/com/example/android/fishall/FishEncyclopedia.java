package com.example.android.fishall;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class FishEncyclopedia extends AppCompatActivity
        implements EncyclopediaAdapter.ListItemClickListener{

    private final String TAG = FishEncyclopedia.class.getSimpleName();

    //RecyclerView and Adapter
    RecyclerView mRecyclerView;
    EncyclopediaAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_encyclopedia);

        // Reference the recycler view with a call to findViewById
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_encyclopedia);

        // The linear layout manager will position list items in a vertical list
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // The adapter is responsible for displaying each item in the list
        mAdapter = new EncyclopediaAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

    }

    /**
     * This is where we receive our callback from the clicklistener in the adapter
     * This callback is invoked when you click on an item in the list.
     *
     * @param clickedItemIndex Index in the list of the item that was clicked.
     */
    @Override
    public void onListItemClick(int clickedItemIndex) {

        // In here, method handles the scenario when a list item is clicked

        Log.v(TAG, "List item clicked at index: " + clickedItemIndex);

        Context context = FishEncyclopedia.this;

        /* This is the class that we want to start (and open) when the button is clicked. */
        Class destinationActivity = FishDetail.class;

        Intent startChildActivityIntent = new Intent(context, destinationActivity);

        startChildActivityIntent.putExtra(Intent.EXTRA_TEXT, Integer.toString(clickedItemIndex));

        startActivity(startChildActivityIntent);


    }
}
