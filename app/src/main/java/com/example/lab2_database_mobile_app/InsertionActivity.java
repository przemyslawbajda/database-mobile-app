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
        setSaveButton();

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

    void setSaveButton(){
        saveButton = findViewById(R.id.buttonSave);

        saveButton.setOnClickListener(v -> {

            if(areAllFieldsValid()){
                //if all input fields are not empty, create an intent
                Intent intent = new Intent();
                intent.putExtra("manufacturer_input", manufacturerEditText.getText().toString());
                intent.putExtra("model_input", modelEditText.getText().toString());
                intent.putExtra("android_input", androidVersionEditText.getText().toString());
                intent.putExtra("website_input", websiteEditText.getText().toString());
                setResult(RESULT_OK, intent);
                finish(); //close current activity
            }
        });
    }

    boolean areAllFieldsValid(){

        if(manufacturerEditText.getText().toString().isEmpty()){
            manufacturerEditText.setError(getString(R.string.empty_input_error));
            return false;
        }

        if(modelEditText.getText().toString().isEmpty()){
            modelEditText.setError(getString(R.string.empty_input_error));
            return false;
        }
        if(androidVersionEditText.getText().toString().isEmpty()){
            androidVersionEditText.setError(getString(R.string.empty_input_error));
            return false;
        }
        if(websiteEditText.getText().toString().isEmpty()){
            websiteEditText.setError(getString(R.string.empty_input_error));
            return false;
        }
        return true;
    }

}
