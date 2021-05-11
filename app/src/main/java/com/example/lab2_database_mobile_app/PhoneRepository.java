package com.example.lab2_database_mobile_app;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

/*
PhoneRepository is a single source of data for the application.
Gets data from DAO.
 */

public class PhoneRepository {
    private PhoneDAO phoneDAO;
    private LiveData<List<Phone>> allPhoneElements;

    PhoneRepository(Application application){
        PhoneRoomDatabase phoneRoomDatabase = PhoneRoomDatabase.getDatabase(application);

        //retriving DAO to handle queries
        phoneDAO = phoneRoomDatabase.phoneDAO();
        allPhoneElements = phoneDAO.getAlphabetizedElements();
    }

    LiveData<List<Phone>> getAllPhones(){
        return allPhoneElements;
    }

    void deleteAll(){
        PhoneRoomDatabase.databaseWriteExecutor.execute(() ->{
            phoneDAO.deleteAll();
        });
    }

    void insert(Phone phone){
        PhoneRoomDatabase.databaseWriteExecutor.execute(() ->{
           phoneDAO.insert(phone);
        });
    }

}
