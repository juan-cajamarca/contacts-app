package com.example.contacts;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {

    private String name;
    private String phone;
    private String group;

    public Contact(String name, String phone, String group) {
        super();

        this.name = name;
        this.phone = phone;
        this.group = group;
    }

    protected Contact(Parcel in) {
        name = in.readString();
        phone = in.readString();
        group = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getGroup() {
        return this.group;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(group);
    }

}
