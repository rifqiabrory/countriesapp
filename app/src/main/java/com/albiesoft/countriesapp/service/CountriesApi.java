package com.albiesoft.countriesapp.service;

import com.albiesoft.countriesapp.model.CountryModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CountriesApi {
    @GET("/DevTides/countries/master/countriesV2.json")
    Single<List<CountryModel>> getCountries();
}
