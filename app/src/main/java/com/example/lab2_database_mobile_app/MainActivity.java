package com.example.lab2_database_mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private PhoneViewModel phoneViewModel;
    private PhoneListAdapter adapter;
    private FloatingActionButton floatingActionButton;

    private static final int INSERT_ACTIVITY_REQUEST_CODE = 1;


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

        //set new phoneList in the adapter when data has been changed
        phoneViewModel.getAllPhones().observe(this, phones -> {
            adapter.setPhoneList(phones);
        });

        setFABListener();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //create menu based on xml file
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        //get id of menu item
        int id = item.getItemId();

        if(id == R.id.deleteAllOption){
            phoneViewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setFABListener(){
        floatingActionButton = findViewById(R.id.fabMain);

        floatingActionButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainActivity.this, InsertionActivity.class );
            startActivityForResult(intent, INSERT_ACTIVITY_REQUEST_CODE);

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        super.onActivityResult(requestCode, resultCode, result);

        //if result code is ok and intent came from InsertActivity
        if(resultCode == RESULT_OK && requestCode == INSERT_ACTIVITY_REQUEST_CODE){

            //create new phone
            Phone newPhone = new Phone(
                    result.getStringExtra("manufacturer_input"),
                    result.getStringExtra("model_input"),
                    result.getStringExtra("android_input"),
                    result.getStringExtra("website_input")
            );

            //and new phone to database
            phoneViewModel.insert(newPhone);

        }
    }
}