package com.bahaa.mvvm.ui.fragment.adapter.movieAdapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bahaa.mvvm.models.Movie;
import com.bahaa.mvvm.util.AppTools;
import com.squareup.picasso.Picasso;

public class MovieViewModel extends ViewModel {
    private Movie movie;
    public ObservableField<String> imageObservable;
    public ObservableField<String> movieNameObservable;
    public ObservableField<String> movieOverViewObservable;
    public ObservableField<String> moviePublishDate;
    public MutableLiveData<String> action;

    public MovieViewModel(Movie movie) {
        this.movie = movie;
        imageObservable = new ObservableField<>();
        movieNameObservable = new ObservableField<>();
        movieOverViewObservable = new ObservableField<>();
        moviePublishDate = new ObservableField<>();
        action = new MutableLiveData<>();
        updateUI();
    }

    private void updateUI() {
        imageObservable.set(movie.getPosterPath());
        movieNameObservable.set(movie.getTitle());
        movieOverViewObservable.set(movie.getOverview());
        moviePublishDate.set(movie.getReleaseDate());
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(AppTools.Network.IMAGE_URL + url).into(imageView);
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }


    public void onItemClick() {
        action.setValue(AppTools.OPEN_MOVIE);
    }
}
