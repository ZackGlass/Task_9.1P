package com.example.lostandfoundapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class Advert implements Parcelable {

    private int ad_id;
    private String adName;
    private String phone;
    private String description;
    private String date;
    private String locationName;
    private String lat;
    private String lng;

    public Advert(String adName, String phone, String description, String date, String locationName, LatLng latlng) {
        this.adName = adName;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.locationName = locationName;
        this.lat = Double.toString(latlng.latitude);
        this.lng = Double.toString(latlng.longitude);
    }

    public Advert(String adName, String phone, String description, String date, String locationName, String lat, String lng) {
        this.adName = adName;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.locationName = locationName;
        this.lat = lat;
        this.lng = lng;
    }

    public Advert() {
    }

    protected Advert(Parcel in) {
        ad_id = in.readInt();
        adName = in.readString();
        phone = in.readString();
        description = in.readString();
        date = in.readString();
        locationName = in.readString();
        lat = in.readString();
        lng = in.readString();
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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String location) {
        this.locationName = location;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
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
        parcel.writeString(locationName);
        parcel.writeString(lat);
        parcel.writeString(lng);
    }
}
