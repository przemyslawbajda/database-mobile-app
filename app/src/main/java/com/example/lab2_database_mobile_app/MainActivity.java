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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Element;

public class MainActivity extends AppCompatActivity implements PhoneListAdapter.OnItemClickListener {

    private PhoneViewModel phoneViewModel;
    private PhoneListAdapter adapter;
    private FloatingActionButton floatingActionButton;

    private static final int INSERT_ACTIVITY_REQUEST_CODE = 1;
    private static final int UPDATE_REQUEST_CODE = 2;

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

            Phone newPhone = createPhone(result);

            //and new phone to database
            phoneViewModel.insert(newPhone);
        }

        if(resultCode == RESULT_OK && requestCode == UPDATE_REQUEST_CODE){
            Phone newPhone = updatePhone(result);

            Toast.makeText(this, "", Toast.LENGTH_LONG).show();
            phoneViewModel.update(newPhone);
        }
    }

    private Phone createPhone(Intent result) {
        //create new phone based on received intent data
        return new Phone(
                result.getStringExtra("manufacturer_input"),
                result.getStringExtra("model_input"),
                result.getStringExtra("android_input"),
                result.getStringExtra("website_input")
        );
    }

    private Phone updatePhone(Intent result) {
        //create new phone based on received intent data
        return new Phone(
                result.getIntExtra("id_data", -1),
                result.getStringExtra("manufacturer_input"),
                result.getStringExtra("model_input"),
                result.getStringExtra("android_input"),
                result.getStringExtra("website_input")
        );
    }

    @Override
    public void OnItemClickListener(Phone phone) {
        //starts new activity when row is clicked
        Intent intent = new Intent(MainActivity.this, InsertionActivity.class);

        intent.putExtra("id_data", phone.getId());
        intent.putExtra("manufacturer_data", phone.getManufacturer());
        intent.putExtra("model_data", phone.getModel());
        intent.putExtra("android_data", phone.getAndroidVersion());
        intent.putExtra("website_data", phone.getWebsite());

        startActivityForResult(intent, UPDATE_REQUEST_CODE );
    }
}