package com.bahaa.mvvm.ui.fragment.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bahaa.mvvm.R;
import com.bahaa.mvvm.databinding.FragmentFavoriteBinding;
import com.bahaa.mvvm.ui.fragment.base.BaseFragment;

public class FavoriteFragment extends BaseFragment {

    private FavoriteViewModel dashboardViewModel;
    private FragmentFavoriteBinding fragmentFavoriteBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentFavoriteBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_favorite,container,false);
        dashboardViewModel =
            ViewModelProviders.of(this).get(FavoriteViewModel.class);
        fragmentFavoriteBinding.setFavoriteViewModle(dashboardViewModel);
        return fragmentFavoriteBinding.getRoot();
    }
}