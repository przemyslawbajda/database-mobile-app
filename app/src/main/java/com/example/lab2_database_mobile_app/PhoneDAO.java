package com.example.lab2_database_mobile_app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/*
DAO interface maps methods to SQL queries.
Allows to read and write data from database.
Is implemented automatically
 */
@Dao
public interface PhoneDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Phone phone);

    @Query("DELETE FROM phone")
    void deleteAll();

    @Query("SELECT * FROM phone ORDER BY manufacturer ASC")
    LiveData<List<Phone>> getAlphabetizedElements();
}
