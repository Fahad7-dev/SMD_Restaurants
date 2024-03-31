package com.example.smd_restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class Add_Activity extends AppCompatActivity {

    TextInputEditText etName, etCategory, etLocation, etPhone, etDescription, etRating;
    Button btnSubmitDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
        btnSubmitDetails.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                String name = etName.getText().toString();
                String category = etCategory.getText().toString();
                String location = etLocation.getText().toString();
                String phone = etPhone.getText().toString().trim();
                String description = etDescription.getText().toString();
                int ratingToInt = 0;
                ratingToInt = Integer.parseInt(etRating.getText().toString());

                intent.putExtra("restaurant_name", name);
                intent.putExtra("restaurant_category", category);
                intent.putExtra("restaurant_description", description);
                intent.putExtra("restaurant_location", location);
                intent.putExtra("restaurant_phone", phone);
                intent.putExtra("restaurant_rating", ratingToInt);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
    void init()
    {
        etName=findViewById(R.id.etName);
        etPhone=findViewById(R.id.etPhone);
        etDescription=findViewById(R.id.etDescription);
        etCategory=findViewById(R.id.etCategory);
        etLocation=findViewById(R.id.etLocation);
        etRating=findViewById(R.id.etRating);
        btnSubmitDetails=findViewById(R.id.btnSubmitDetails);
    }
}
