package com.example.lab2_database_mobile_app;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "phone")
public class Phone {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "manufacturer")
    private String manufacturer;

    @NonNull
    @ColumnInfo(name = "model")
    private String model;

    @NonNull
    @ColumnInfo(name = "androidVersion")
    private String androidVersion;

    @NonNull
    @ColumnInfo(name = "website")
    private String website;

    public Phone(@NonNull String manufacturer, @NonNull String model,@NonNull String androidVersion, @NonNull String website){
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.androidVersion = androidVersion;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(@NonNull String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @NonNull
    public String getModel() {
        return model;
    }

    public void setModel(@NonNull String model) {
        this.model = model;
    }

    @NonNull
    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(@NonNull String androidVersion) {
        this.androidVersion = androidVersion;
    }

    @NonNull
    public String getWebsite() {
        return website;
    }

    public void setWebsite(@NonNull String website) {
        this.website = website;
    }
}
