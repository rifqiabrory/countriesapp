package com.albiesoft.countriesapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.albiesoft.countriesapp.service.CountriesService;
import com.albiesoft.countriesapp.model.CountryModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {
    public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<>();
    public MutableLiveData<Boolean> countryLoadedError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

    // add service (retrofit)
    private CountriesService service = CountriesService.getInstance();

    // add guard
    private CompositeDisposable disposable = new CompositeDisposable();

    public void refresh(){
        fetchCountries();
    }

    private void fetchCountries() {
//        CountryModel country1 = new CountryModel("Jakarta", "Indonesia", "");
//        CountryModel country2 = new CountryModel("Bandung", "Indonesia","");
//        CountryModel country3 = new CountryModel("Surabaya", "Indonesia", "");
//
//        List<CountryModel> list = new ArrayList<>();
//        list.add(country1);
//        list.add(country2);
//        list.add(country3);
//        list.add(country1);
//        list.add(country2);
//        list.add(country3);
//        list.add(country1);
//        list.add(country2);
//        list.add(country3);
//        list.add(country1);
//        list.add(country2);
//        list.add(country3);
//        list.add(country1);
//        list.add(country2);
//        list.add(country3);
//        list.add(country1);
//        list.add(country2);
//        list.add(country3);
//        list.add(country1);
//        list.add(country2);
//        list.add(country3);
//
//        countries.setValue(list);
//        countryLoadedError.setValue(false);
//        loading.setValue(false);
        loading.setValue(true);
        disposable.add(
                service.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {
                    @Override
                    public void onSuccess(List<CountryModel> countryModels) {
                        countries.setValue(countryModels);
                        countryLoadedError.setValue(false);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        countryLoadedError.setValue(true);
                        loading.setValue(false);
                        e.printStackTrace();
                    }
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
