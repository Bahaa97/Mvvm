package com.bahaa.mvvm.ui.fragment.movieDetails;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bahaa.mvvm.data.network.RetrofitClient;
import com.bahaa.mvvm.models.Movie;
import com.bahaa.mvvm.models.movieDetails.MovieDetails;
import com.bahaa.mvvm.util.AppTools;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailsViewModel extends ViewModel {
    private CompositeDisposable disposables = new CompositeDisposable();
    private MovieDetails movie;
    private String id;
    public ObservableField<String> imageObservable;
    public MutableLiveData<List<Movie>> movieMutableLiveData;
    public ObservableField<String> nameObservable;
    public ObservableField<String> overViewObservable;
    public ObservableField<String> voteCountObservable;
    public ObservableField<String> voteAverageObservable;
    public ObservableField<String> releaseDateObservable;
    public MutableLiveData<String> action;
    public MovieDetailsViewModel(String id) {
        this.id = id;
        imageObservable = new ObservableField<>();
        nameObservable = new ObservableField<>();
        overViewObservable = new ObservableField<>();
        voteAverageObservable = new ObservableField<>();
        voteCountObservable = new ObservableField<>();
        releaseDateObservable = new ObservableField<>();
        movieMutableLiveData = new MutableLiveData<>();
        action = new MutableLiveData<>();
        getMovieDetails();
        getMovieRelated();
    }

    void getMovieDetails(){
        Disposable disposable = RetrofitClient.webService().getMovieDetails(id, AppTools.Network.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    movie = result;
                    updataUI();
                }, throwable -> {
                    Log.e("NETWORK ERROR", throwable.getMessage());
                });
        disposables.add(disposable);
    }
    void getMovieRelated(){
        Disposable disposable = RetrofitClient.webService().getRelated(id, AppTools.Network.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    movieMutableLiveData.postValue(result.getResults());
                }, throwable -> {
                    Log.e("NETWORK ERROR", throwable.getMessage());
                });
        disposables.add(disposable);
    }

    private void updataUI() {
        imageObservable.set(movie.getPosterPath());
        nameObservable.set(movie.getOriginalTitle());
        overViewObservable.set(movie.getOverview());
        voteCountObservable.set(movie.getVoteCount().toString());
        voteAverageObservable.set(movie.getVoteAverage().toString());
        releaseDateObservable.set(movie.getReleaseDate());

    }

    public void onBackPressed(){
        action.setValue(AppTools.BACK);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(AppTools.Network.IMAGE_URL + url).into(imageView);
    }
}