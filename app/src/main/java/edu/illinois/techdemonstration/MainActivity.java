package edu.illinois.techdemonstration;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private EventAdapter mEventAdapter;
    private MainActivity mMainActivity;
    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Old Code kept because application is still work in progress.
        ArrayList<Event> eventsList = new ArrayList<>();
        //Loop through all the events in the database and parse them into an event
        eventsList.add(new Event("Party", "Sieble Center", "Cheese and crackers", "867509", "1230 PM"));
        eventsList.add(new Event("Group meeting", "Grainger", "Studying for 173 Examlet", "5435435", "130 PM"));
        //setupRecyclerViewForLocal(eventsList);
        setupRecyclerViewForDataBase();
        setupNotifications();
    }


    /**
     * Helper method for setting up notifications to work on android.
     * This default implementation simply displays a message that when clicked will open up the main activity of the app.
     * This will be changed later on to open up a specific detail of an event nearby.
     * Code copied from the android developer guide.
     * https://developer.android.com/guide/topics/ui/notifiers/notifications.html
     */
    private void setupNotifications() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your app to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // mNotificationId is a unique integer your app uses to identify the
        // notification. For example, to cancel the notification, you can pass its ID
        // number to NotificationManager.cancel().
        mNotificationManager.notify(this.getTaskId(), mBuilder.build());
    }

    /**
     * Helper method for configuring the recyclerview to work with a FirebaseRecyclerAdapter.
     */
    private void setupRecyclerViewForDataBase() {
        mRecyclerView = (RecyclerView) findViewById(R.id.activty_recycler_view);
        mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference eventsRef = mDatabase.getReference("eventsNew");
        FirebaseRecyclerAdapter<Event, EventViewHolder> eventAdapter = new FirebaseRecyclerAdapter<Event, EventViewHolder>
                (Event.class, R.layout.food_event_list_item, EventViewHolder.class, eventsRef) {

            @Override
            protected void populateViewHolder(EventViewHolder viewHolder,
                                              Event model, int position) {
                viewHolder.bindEvent(model);
                Log.v("POP", "populateViewHolderCalled");
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(eventAdapter);
        Log.v("POP", "setupRecyclerViewForDataBaseCalled");
    }



    /**
     * This method will eventually be deprecated from the project.
     * This method sets up a local RecyclerView with an Adapter. This
     * RecyclerView implementation works if we can download all the events
     * and parse them into an array list.
     * @param eventsList The events to display in the recyclerview.
     */
    private void setupRecyclerViewForLocal(ArrayList<Event> eventsList) {
        mRecyclerView = (RecyclerView) findViewById(R.id.activty_recycler_view);

        //Need to initialize the events now!!!
        mEventAdapter = new EventAdapter(eventsList);
        mRecyclerView.setAdapter(mEventAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
