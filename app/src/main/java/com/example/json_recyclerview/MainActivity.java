package com.example.json_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<Result> countriesList;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countriesList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        EditText editText =findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());

            }
        });


        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);


        Call<CountryList> call12 = apiInterface.getCountries();

        call12.enqueue(new Callback<CountryList>() {
            @Override
            public void onResponse(Call<CountryList> call, Response<CountryList> response) {
                countriesList = response.body().getResult();
                recyclerAdapter = new RecyclerAdapter(MainActivity.this, countriesList);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onFailure(Call<CountryList> call, Throwable t) {

            }
        });

    }

    private void filter(String text){
        ArrayList<Result> filteredList = new ArrayList<>();
        for (Result item : countriesList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        recyclerAdapter.filterList(filteredList);
    }


}