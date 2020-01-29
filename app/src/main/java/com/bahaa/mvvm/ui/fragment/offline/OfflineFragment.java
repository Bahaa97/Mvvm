package com.bahaa.mvvm.ui.fragment.offline;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bahaa.mvvm.MyApplication;
import com.bahaa.mvvm.R;
import com.bahaa.mvvm.data.network.ConnectivityStatus;
import com.bahaa.mvvm.databinding.FragmentOfflineBinding;
import com.bahaa.mvvm.ui.fragment.base.BaseFragment;

import static com.bahaa.mvvm.util.AppTools.TRY_AGAIN;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfflineFragment extends BaseFragment {
    private OfflineViewModel offlineViewModel;
    private FragmentOfflineBinding fragmentOfflineBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        offlineViewModel = ViewModelProviders.of(this).get(OfflineViewModel.class);
        fragmentOfflineBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_offline, container, false);
        fragmentOfflineBinding.setOfflineViewModle(offlineViewModel);
        offlineViewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer) {
                    case TRY_AGAIN:
                        onBtnCloseClicked();
                        break;
                }
            }
        });
        return fragmentOfflineBinding.getRoot();
    }

    public void onBtnCloseClicked() {
        if (!ConnectivityStatus.isConnected(getActivity())) {
            showToast(MyApplication.getContext().getResources().getString(R.string.label_check_internet));
        } else {
            navigate(R.id.action_offlineFragment_to_navHome);
        }
    }


}
