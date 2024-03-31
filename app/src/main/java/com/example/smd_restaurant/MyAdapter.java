package com.example.smd_restaurant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private final OnItemClickListener listener;
    ArrayList<restaurant_model> restaurants = new ArrayList<>();


    public interface OnItemClickListener {
        void onItemClick(restaurant_model item);
    }
    public MyAdapter(ArrayList<restaurant_model> restaurants,OnItemClickListener listener)
    {
        this.restaurants=restaurants;
        this.listener=listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_restaurant, parent, false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvName.setText(restaurants.get(position).getName());
        holder.tvSubItem.setText(restaurants.get(position).getCategory());
        holder.tvLocation.setText(restaurants.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvSubItem, tvLocation;
        ImageView ivImage;

        public ViewHolder(@NonNull View view) {
            super(view);

            tvName = itemView.findViewById(R.id.tvName);
            tvSubItem = itemView.findViewById(R.id.tvSubItem);
            tvLocation = itemView.findViewById(R.id.tvLocation);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        restaurant_model clickedItem = restaurants.get(position);
                        listener.onItemClick(clickedItem);
                    }
                }
            });
        }
    }
}
