package edu.illinois.techdemonstration;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Chris on 11/15/2017.
 */

public class EventViewHolder extends RecyclerView.ViewHolder {
    public View itemView;
    public TextView nameTextView;
    public TextView locationTextView;
    public TextView dateTextView;
    public TextView timeTextView;

    public EventViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.nameTextView = (TextView) itemView.findViewById(R.id.event_name_list);
        this.locationTextView = (TextView) itemView.findViewById(R.id.event_location_list);
        this.dateTextView = (TextView) itemView.findViewById(R.id.event_date_list);
        this.timeTextView = (TextView) itemView.findViewById(R.id.event_time_list);
    }

    public void bindEvent(final Event event) {
        nameTextView.setText(event.getName());
        locationTextView.setText(event.getLocation());
        dateTextView.setText(event.getDate());
        timeTextView.setText(event.getTime());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context = itemView.getContext();
                Intent detailedIntent = new Intent(context, DetailActivity.class);

                // rather than passing individual values, pass the whole event (as a Parcelable)
                detailedIntent.putExtra("event", event);

                context.startActivity(detailedIntent);
            }
        });
    }
}
