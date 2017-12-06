package edu.illinois.techdemonstration;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Chris on 10/31/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {
    private List<Event> mEvents = new ArrayList<>();

    public EventAdapter(ArrayList<Event> events) {
        this.mEvents.addAll(events);
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View restarauntItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_event_list_item, parent, false);
        return new EventViewHolder(restarauntItem);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        final Event event = mEvents.get(position);
        holder.nameTextView.setText(event.getName());
        holder.locationTextView.setText(event.getLocation());
        holder.dateTextView.setText(event.getDate());
        holder.timeTextView.setText(event.getTime());

        final Context context = holder.itemView.getContext();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = v.getContext();
                Intent detailedIntent = new Intent(context, DetailActivity.class);

                // rather than passing individual values, pass the whole cat (as a Parcelable)
                detailedIntent.putExtra("event", event);

                context.startActivity(detailedIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    /**
     * @param eventsToPopulate events to set the adapter equal to.
     */
    public void updateDataSet(ArrayList<Event> eventsToPopulate) {
        mEvents.clear();
        mEvents.addAll(eventsToPopulate);
        notifyDataSetChanged();
    }


}
