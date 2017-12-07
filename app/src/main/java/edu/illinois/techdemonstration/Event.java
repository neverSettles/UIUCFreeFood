package edu.illinois.techdemonstration;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chris on 11/13/2017.
 */

public class Event implements Parcelable {
    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
    private String Name;
    private String Location;
    private String Description;
    private String UID;
    private String Time;


    Event(String name, String location, String desc, String uid, String time) {
        Name = name;
        Location = location;
        Description = desc;
        UID = uid;
        Time = time;
    }

    public Event() {
    }

    protected Event(Parcel in) {
        this.Name = in.readString();
        this.Location = in.readString();
        this.Description = in.readString();
        this.UID = in.readString();
        this.Time = in.readString();
    }

    public String getName() {
        return Name;
    }

    public String getLocation() {
        return Location;
    }

    public String getDescription() {
        return Description;
    }

    public String getUID() {
        return UID;
    }

    public String getTime() {
        try {
            return Time.substring(Time.indexOf("T") + 1, Time.lastIndexOf("-"));
        }
        catch(Exception e){}
        return Time;
    }

    public String getDate() {
        try {
            return Time.substring(0, Time.indexOf("T"));
        } catch(Exception e){}
        return Time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Name);
        dest.writeString(this.Location);
        dest.writeString(this.Description);
        dest.writeString(this.UID);
        dest.writeString(this.Time);
    }
}
