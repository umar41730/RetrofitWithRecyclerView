package com.example.json_recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    private Context context;
    private List<Result> countries;

    public RecyclerAdapter(Context context, List<Result> countries) {
        this.context = context;
        this.countries = countries;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_layout,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.countriesName.setText(countries.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void filterList(ArrayList<Result> filteredList) {
        countries = filteredList;
        notifyDataSetChanged();


    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView countriesName;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            countriesName=(TextView)itemView.findViewById(R.id.text);
        }
    }
}
