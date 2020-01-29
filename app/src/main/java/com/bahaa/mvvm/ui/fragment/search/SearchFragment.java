package com.bahaa.mvvm.ui.fragment.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bahaa.mvvm.R;
import com.bahaa.mvvm.databinding.FragmentNotificationsBinding;
import com.bahaa.mvvm.ui.fragment.base.BaseFragment;

public class SearchFragment extends BaseFragment {

    private SearchViewModel notificationsViewModel;
    private FragmentNotificationsBinding fragmentNotificationsBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentNotificationsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_notifications,container,false);
        notificationsViewModel =
                ViewModelProviders.of(this).get(SearchViewModel.class);
        fragmentNotificationsBinding.setNotificationModel(notificationsViewModel);
        return fragmentNotificationsBinding.getRoot();
    }


}