package com.hb.todo.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class Todo implements Parcelable {

    // attributs
    private long id;
    private String name;
    private String urgency;

    // constructeurs
    public Todo() {
    }

    public Todo(String name, String urgency) {
        this.name = name;
        this.urgency = urgency;
    }

    public Todo(long id, String name, String urgency) {
        this.id = id;
        this.name = name;
        this.urgency = urgency;
    }

    public Todo(Parcel src) {
        this.id = src.readLong();
        this.name = src.readString();
        this.urgency = src.readString();
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    // getters & setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    // toString
    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", urgency='" + urgency + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // enregistre dans la parcel tout les attributs
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(urgency);
    }
}
