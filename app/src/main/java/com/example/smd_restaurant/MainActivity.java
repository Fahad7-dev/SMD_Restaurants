package com.example.smd_restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ArrayList<restaurant_model> restaurants;
    RecyclerView rvList;
    MyAdapter adapter;
    Button btnAdd,btnSearch;
    private static final String SHARED_PREFS_KEY = "shared_prefs_key";
    private static final String ITEM_LIST_KEY = "item_list_key";
    TextInputEditText etSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loadRestaurants();
        adapter = new MyAdapter(restaurants, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(restaurant_model rm) {
                Intent intent=new Intent(MainActivity.this,Details_Activity.class);
                intent.putExtra("restaurant_name",rm.getName());
                intent.putExtra("restaurant_category",rm.getCategory());
                intent.putExtra("restaurant_location",rm.getLocation());
                intent.putExtra("restaurant_phone",rm.getPhone());
                intent.putExtra("restaurant_description",rm.getDescription());
                intent.putExtra("restaurant_rating",rm.getRating());
                startActivity(intent);
            }
        });
        rvList.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Add_Activity.class);
                startActivityForResult(intent, 1);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                String filter = Objects.requireNonNull(etSearch.getText()).toString().trim().toLowerCase();
                if (filter.equals("rating")) {
                    restaurants.sort(new Comparator<restaurant_model>() {
                        @Override
                        public int compare(restaurant_model res1, restaurant_model res2) {
                            return Integer.compare(res2.getRating(), res1.getRating());
                        }
                    });
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
    void init()
    {
        btnAdd=findViewById(R.id.btnAdd);
        etSearch=findViewById(R.id.etSearch);
        btnSearch=findViewById(R.id.btnSearch);
        rvList=findViewById(R.id.rvList);
        rvList.setLayoutManager(new LinearLayoutManager(this));

        restaurants=new ArrayList<>();
        restaurants.add(new restaurant_model("Burger lab","fast food","02156845115","something","idk",5));
        restaurants.add(new restaurant_model("Pizza hut","fast food","03335415615","something","idk",4));
        restaurants.add(new restaurant_model("deli daily","burger","02164894516","something","idk",5));
        restaurants.add(new restaurant_model("cheezious","pizza","02316541561","something","idk",2));
        restaurants.add(new restaurant_model("cafe","cafe","021454151","something","idk",0));
        restaurants.add(new restaurant_model("paratha","desi","02154451210","something","idk",3));
        restaurants.add(new restaurant_model("Butt karaahi","desi","545415120","something","idk",5));
        restaurants.add(new restaurant_model("desi food","desi","1248541202","something","idk",4));

       }

    @SuppressLint("NotifyDataSetChanged")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra("restaurant_name");
            String category = data.getStringExtra("restaurant_category");
            String location = data.getStringExtra("restaurant_location");
            String phone = data.getStringExtra("restaurant_phone");
            String description = data.getStringExtra("restaurant_description");
            String rating = String.valueOf(data.getIntExtra("restaurant_rating",0));
            int ratings=Integer.parseInt(rating);
            restaurants.add(new restaurant_model(name, category, phone, location, description,ratings));
            adapter.notifyDataSetChanged();
            saveRestaurants();
        }
    }

    private void saveRestaurants() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(restaurants);
        editor.putString(ITEM_LIST_KEY, json);
        editor.apply();
    }

    private void loadRestaurants() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(ITEM_LIST_KEY, "");
        Type type = new TypeToken<ArrayList<restaurant_model>>() {}.getType();
        restaurants = gson.fromJson(json, type);

        if (restaurants == null) {
            restaurants = new ArrayList<>();
        }
    }

}