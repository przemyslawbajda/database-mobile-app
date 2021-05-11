package com.example.lab2_database_mobile_app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/*
ViewModel is responsible for the storage of the data that are
presented to user. Role of ViewModel is to storage and process the data
needed by the user interface
 */

public class PhoneViewModel extends AndroidViewModel {
    private final PhoneRepository repository;
    private final LiveData<List<Phone>> allPhones;


    public PhoneViewModel(@NonNull Application application) {
        super(application);
        repository = new PhoneRepository(application);
        allPhones = repository.getAllPhones();
    }

    LiveData<List<Phone>> getAllPhones(){
        return repository.getAllPhones();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void insert(Phone phone){ repository.insert(phone);}

}
