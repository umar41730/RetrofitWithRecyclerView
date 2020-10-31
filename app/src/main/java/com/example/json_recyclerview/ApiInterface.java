package com.example.json_recyclerview;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("countries/")
    Call<CountryList> getCountries();
}
