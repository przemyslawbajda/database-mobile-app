package com.example.lab2_database_mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private PhoneViewModel phoneViewModel;
    private PhoneListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting adapter on a list
        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        adapter = new PhoneListAdapter(this);
        recyclerView.setAdapter(adapter);

        //setting layout for row elements
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //read model from the provider
        phoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);

        phoneViewModel.getAllPhones().observe(this, phones -> {
            adapter.setPhoneList(phones);
        });
    }
}