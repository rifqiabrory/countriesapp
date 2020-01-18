package com.albiesoft.countriesapp.service;

import com.albiesoft.countriesapp.di.DaggerApiComponent;
import com.albiesoft.countriesapp.model.CountryModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class CountriesService {

     private static CountriesService instance;

     // dagger not allowed to private
     @Inject
     public CountriesApi api;

     private CountriesService(){
         DaggerApiComponent.create().injectCountry(this);
     }

     public static  CountriesService getInstance(){
         if(instance == null){
             instance = new CountriesService();
         }
         return instance;
     }

     public Single<List<CountryModel>> getCountries(){
         return api.getCountries();
     }
}
