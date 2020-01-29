package com.bahaa.mvvm.ui.fragment.movieDetails;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bahaa.mvvm.R;
import com.bahaa.mvvm.databinding.FragmentFavoriteBinding;
import com.bahaa.mvvm.databinding.FragmentMovieDetailsBinding;
import com.bahaa.mvvm.models.Movie;
import com.bahaa.mvvm.ui.fragment.adapter.movieAdapter.MovieAdapter;
import com.bahaa.mvvm.ui.fragment.base.BaseFragment;
import com.bahaa.mvvm.util.AppTools;
import com.bahaa.mvvm.util.AppUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsFragment extends BaseFragment implements MovieAdapter.OnMovieClick{
    private MovieDetailsViewModel movieDetailsViewModel;
    private FragmentMovieDetailsBinding fragmentMovieDetailsBinding;
    private MovieAdapter movieAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMovieDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false);
        if (getArguments() != null) {
            movieDetailsViewModel = new MovieDetailsViewModel(getArguments().getString("id"));
        } else {
            movieDetailsViewModel = new MovieDetailsViewModel("0");
        }
        movieDetailsViewModel.action.observe(getViewLifecycleOwner(), action -> {
            switch (action){
                case AppTools.BACK:
                    customBackClick();
                    break;
            }
        });
        initRecycler();
        movieDetailsViewModel.movieMutableLiveData.observe(getViewLifecycleOwner(), movies -> {
            movieAdapter.setCartList(movies);
        });
        return fragmentMovieDetailsBinding.getRoot();
    }


    private void initRecycler() {
        movieAdapter = new MovieAdapter(this,R.layout.item_movie_gride);
        fragmentMovieDetailsBinding.setDetailsViewModel(movieDetailsViewModel);
        AppUtils.initHorizontalRV(getContext(),fragmentMovieDetailsBinding.recyclerMovies,1);
        fragmentMovieDetailsBinding.recyclerMovies.setAdapter(movieAdapter);
    }

    @Override
    public void onMovieClickListener(Movie movie) {
        Bundle bundle = new Bundle();
        bundle.putString("id",movie.getId().toString());
        navigateWithBundle(fragmentMovieDetailsBinding.getRoot(),bundle,R.id.movieDetailsFragment);
    }
}
