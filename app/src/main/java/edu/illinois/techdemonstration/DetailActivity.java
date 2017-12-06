package edu.illinois.techdemonstration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_event_detail);

        // Get a restaurant out of extras passed with intent
        final Intent intent = getIntent();
        final Event event = intent.getParcelableExtra("event");

        // fill the text attributes
        final TextView nameTextView = (TextView) findViewById(R.id.event_name_detail);
        final TextView locationTextView = (TextView) findViewById(R.id.event_location_detail);
        final TextView timeTextView = (TextView) findViewById(R.id.event_time_detail);
        final TextView dateTextView = (TextView) findViewById(R.id.event_date_detail);
        final TextView descriptionTextView = (TextView) findViewById(R.id.event_description_detail);

        nameTextView.setText(event.getName());
        locationTextView.setText(event.getLocation());
        timeTextView.setText(event.getTime());
        dateTextView.setText(event.getDate());
        descriptionTextView.setText(event.getDescription());

        //Initialize on click listener for mapsImageButton.
    }

}
