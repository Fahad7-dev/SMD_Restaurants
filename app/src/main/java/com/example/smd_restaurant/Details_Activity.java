package com.example.smd_restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Details_Activity extends AppCompatActivity {
    TextView tvName,tvCategory,tvLocation,tvDescription,tvPhone,tvRating;
    Button btnExit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_show_details);

        init();

        Intent intent = getIntent();
        String name = intent.getStringExtra("restaurant_name");
        String category = intent.getStringExtra("restaurant_category");
        String location = intent.getStringExtra("restaurant_location");
        String phone = intent.getStringExtra("restaurant_phone");
        String description = intent.getStringExtra("restaurant_description");
        int rating = intent.getIntExtra("restaurant_rating", 0);


        tvName.setText(name);
        tvCategory.setText(category);
        tvLocation.setText(location);
        tvPhone.setText(phone);
        tvDescription.setText(description);
        tvRating.setText(String.valueOf(rating));

        btnExit=findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Details_Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    void init()
    {
        tvName = findViewById(R.id.tvName);
        tvCategory = findViewById(R.id.tvCategory);
        tvLocation = findViewById(R.id.tvLocation);
        tvPhone = findViewById(R.id.tvPhone);
        tvDescription = findViewById(R.id.tvDescription);
        tvRating=findViewById(R.id.tvRating);
        btnExit=findViewById(R.id.btnExit);
    }
}
