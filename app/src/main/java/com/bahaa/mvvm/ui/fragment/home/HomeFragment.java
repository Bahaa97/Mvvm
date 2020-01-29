package com.bahaa.mvvm.ui.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bahaa.mvvm.R;
import com.bahaa.mvvm.databinding.FragmentHomeBinding;
import com.bahaa.mvvm.models.Movie;
import com.bahaa.mvvm.ui.fragment.adapter.movieAdapter.MovieAdapter;
import com.bahaa.mvvm.ui.fragment.base.BaseFragment;
import com.bahaa.mvvm.util.AppUtils;

import java.util.List;

public class HomeFragment extends BaseFragment implements MovieAdapter.OnMovieClick {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding fragmentHomeBinding;
    private MovieAdapter movieAdapter;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        fragmentHomeBinding.setHomeViewModle(homeViewModel);
        AppUtils.initVerticalRV(getContext(),fragmentHomeBinding.recyclerMovies, 1, 10);
        movieAdapter = new MovieAdapter(this,R.layout.item_movie);
        fragmentHomeBinding.recyclerMovies.setAdapter(movieAdapter);
        homeViewModel.moviesResponseMutableLiveData.observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                fragmentHomeBinding.recyclerMovies.setAdapter(null);
                movieAdapter.setCartList(movies);
                fragmentHomeBinding.recyclerMovies.setAdapter(movieAdapter);
            }
        });
        return fragmentHomeBinding.getRoot();

    }

    @Override
    public void onMovieClickListener(Movie movie) {
        Bundle bundle = new Bundle();
        bundle.putString("id",movie.getId().toString());
        navigateWithBundle(fragmentHomeBinding.getRoot(),bundle,R.id.action_navHome_to_movieDetailsFragment);
    }
}