package com.example.lab2_database_mobile_app;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Phone.class}, version = 1, exportSchema = false)
public abstract class PhoneRoomDatabase extends RoomDatabase {
    //abstract method that returns DAO
    public abstract PhoneDAO phoneDAO();

    //singleton implementation
    private static volatile PhoneRoomDatabase INSTANCE;

    static PhoneRoomDatabase getDatabase(final Context context){
        //create new object if there is none
        if(INSTANCE == null){
            synchronized (PhoneRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PhoneRoomDatabase.class, "PhoneDatabase")
                            //setting event handler (?)
                            .addCallback(sRoomDatabaseCallback)
                            //migration, delete and create database
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }


    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //object handles callbacks associated with database events e.g. (onCreate, onOpen)
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        //Initalization of database (if not exists) with default content
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                PhoneDAO dao = INSTANCE.phoneDAO();

                dao.insert(new Phone("Samsung", "K10", "5.1.1 Lollipop", "https://www.lg.com/pl/wszystkie-smartfony/lg-K10"));
                dao.insert(new Phone("HTC", "Desire 620", "4.4.4 KitKat", "https://www.htc.com/pl/support/htc-desire-620/howto/596344.html"));
                dao.insert(new Phone("Xiaomi", "Redmi 9C", "10", "https://mi-home.pl/redmi-9c"));
                dao.insert(new Phone("Xiaomi", "POCO X3", "10", "https://mi-home.pl/telefony-poco/poco-x3-nfc-6gb-64gb-cobalt-blue"));



            });
        }
    };


}
