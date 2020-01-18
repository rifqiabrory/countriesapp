package com.albiesoft.countriesapp.di;

import com.albiesoft.countriesapp.service.CountriesService;

import dagger.Component;

@Component(modules = {ApiModul.class})
public interface ApiComponent {

    void injectCountry(CountriesService service);
}
