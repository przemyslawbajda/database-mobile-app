package com.example.lab2_database_mobile_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InsertionActivity extends AppCompatActivity {

    EditText manufacturerEditText;
    EditText modelEditText;
    EditText androidVersionEditText;
    EditText websiteEditText;

    Button cancelButton;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        setEditTextReferences();
        setCancelButton();

    }

    //read references to inputs
    void setEditTextReferences(){
        manufacturerEditText = findViewById(R.id.editTextManufacturer);
        modelEditText = findViewById(R.id.editTextModel);
        androidVersionEditText = findViewById(R.id.editTextAndroidVersion);
        websiteEditText = findViewById(R.id.editTextWebsite);
    }

    void setCancelButton(){
        cancelButton = findViewById(R.id.buttonCancel);

        cancelButton.setOnClickListener(v -> {

            setResult(1);
            finish();
        });
    }



}
