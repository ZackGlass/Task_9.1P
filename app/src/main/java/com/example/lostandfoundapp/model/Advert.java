package com.example.lostandfoundapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Advert implements Parcelable {

    private int ad_id;
    private String adName;
    private String phone;
    private String description;
    private String date;
    private String location;

    public Advert(String adName, String phone, String description, String date, String location) {
        this.adName = adName;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;
    }

    public Advert() {
    }

    protected Advert(Parcel in) {
        ad_id = in.readInt();
        adName = in.readString();
        phone = in.readString();
        description = in.readString();
        date = in.readString();
        location = in.readString();
    }

    public static final Creator<Advert> CREATOR = new Creator<Advert>() {
        @Override
        public Advert createFromParcel(Parcel in) {
            return new Advert(in);
        }

        @Override
        public Advert[] newArray(int size) {
            return new Advert[size];
        }
    };

    public int getAd_id() {
        return ad_id;
    }

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ad_id);
        parcel.writeString(adName);
        parcel.writeString(phone);
        parcel.writeString(description);
        parcel.writeString(date);
        parcel.writeString(location);
    }
}
