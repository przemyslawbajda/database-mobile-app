package com.example.lab2_database_mobile_app;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertionActivity extends AppCompatActivity {

    EditText manufacturerEditText;
    EditText modelEditText;
    EditText androidVersionEditText;
    EditText websiteEditText;

    Button cancelButton;
    Button saveButton;
    Button webSiteButton;

    int phoneID=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        setEditTextReferences();
        setCancelButton();
        setWebSiteEdit();

        //check if intend came with data to edit phone fields
        Intent intent = getIntent();
        if(intent.hasExtra("manufacturer_data")){
            phoneID = intent.getIntExtra("id_data", -1);
            manufacturerEditText.setText(intent.getStringExtra("manufacturer_data"));
            modelEditText.setText(intent.getStringExtra("model_data"));
            androidVersionEditText.setText(intent.getStringExtra("android_data"));
            websiteEditText.setText(intent.getStringExtra("website_data"));
            setUpdateButton();
        }else{
            setSaveButton();
        }
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
            setIntend();
        });
    }

    void setUpdateButton(){
        saveButton = findViewById(R.id.buttonSave);
        saveButton.setText(R.string.button_update);
        saveButton.setOnClickListener(v -> {
            setIntend();
        });
    }

    void setWebSiteEdit(){
        webSiteButton = findViewById(R.id.buttonWebsite);

        webSiteButton.setOnClickListener(v -> {
            String address = websiteEditText.getText().toString();

            if(address.startsWith("https://")){
                //create an intent with url
                Intent intentWebsite = new Intent("android.intent.action.VIEW", Uri.parse(address));
                startActivity(intentWebsite);
            }else{
                Toast.makeText(InsertionActivity.this, R.string.website_error, Toast.LENGTH_SHORT).show();
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

    private void setIntend() {
        if(areAllFieldsValid()){
            //if all input fields are not empty, create an intent
            Intent intent = new Intent();
            intent.putExtra("id_data", phoneID);
            intent.putExtra("manufacturer_input", manufacturerEditText.getText().toString());
            intent.putExtra("model_input", modelEditText.getText().toString());
            intent.putExtra("android_input", androidVersionEditText.getText().toString());
            intent.putExtra("website_input", websiteEditText.getText().toString());
            setResult(RESULT_OK, intent);
            finish(); //close current activity
        }
    }
}
