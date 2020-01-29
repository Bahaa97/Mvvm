package com.bahaa.mvvm.ui.fragment.home;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bahaa.mvvm.data.network.RetrofitClient;
import com.bahaa.mvvm.models.Movie;
import com.bahaa.mvvm.util.AppTools;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    MutableLiveData<List<Movie>> moviesResponseMutableLiveData;
    public ObservableField<Boolean> isTopRated;
    public ObservableField<Boolean> isPopular;
    public ObservableField<Boolean> isLatest;
    public ObservableField<Boolean> isUpComing;

    public HomeViewModel() {
        moviesResponseMutableLiveData = new MutableLiveData<>();
        isTopRated = new ObservableField<>();
        isPopular = new ObservableField<>();
        isLatest = new ObservableField<>();
        isUpComing = new ObservableField<>();

        isTopRated.set(false);
        isPopular.set(false);
        isLatest.set(false);
        isUpComing.set(false);
        onTopRatedClicked();
    }


    public void onTopRatedClicked() {
        if (!isTopRated.get()) {
            getTopRated();
            isTopRated.set(true);
            isPopular.set(false);
            isLatest.set(false);
            isUpComing.set(false);
        }
    }


    public void onPopularClicked() {
        if (!isPopular.get()) {
            getPopular();
            isPopular.set(true);
            isTopRated.set(false);
            isLatest.set(false);
            isUpComing.set(false);
        }
    }

    public void onLatestClicked() {
        if (!isLatest.get()) {
            getLatest();
            isTopRated.set(false);
            isPopular.set(false);
            isLatest.set(true);
            isUpComing.set(false);
        }
    }

    public void onUpComingClicked() {
        if (!isUpComing.get()) {
            getUpComing();
            isTopRated.set(false);
            isPopular.set(false);
            isLatest.set(false);
            isUpComing.set(true);
        }
    }


    @SuppressLint("CheckResult")
    void getTopRated() {
        RetrofitClient.webService().getMovies(AppTools.Network.TOP_RATED, AppTools.Network.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Log.e("NETWORK", result.getPage().toString());
                    moviesResponseMutableLiveData.postValue(result.getResults());
                }, throwable -> {
                    Log.e("NETWORK ERROR", throwable.getMessage());
                });
    }

    @SuppressLint("CheckResult")
    void getPopular() {
        RetrofitClient.webService().getMovies(AppTools.Network.POPULAR, AppTools.Network.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Log.e("NETWORK", result.getPage().toString());
                    moviesResponseMutableLiveData.postValue(result.getResults());
                }, throwable -> {
                    Log.e("NETWORK ERROR", throwable.getMessage());
                });

    }

    @SuppressLint("CheckResult")
    void getLatest() {
        RetrofitClient.webService().getMovies(AppTools.Network.LATEST, AppTools.Network.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Log.e("NETWORK", result.getPage().toString());
                    moviesResponseMutableLiveData.postValue(result.getResults());
                }, throwable -> {
                    Log.e("NETWORK ERROR", throwable.getMessage());
                });

    }

    @SuppressLint("CheckResult")
    void getUpComing() {
        RetrofitClient.webService().getMovies(AppTools.Network.UP_COMING, AppTools.Network.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Log.e("NETWORK", result.getPage().toString());
                    moviesResponseMutableLiveData.postValue(result.getResults());
                }, throwable -> {
                    Log.e("NETWORK ERROR", throwable.getMessage());
                });

    }

    protected void onCleared() {
        super.onCleared();
    }


}