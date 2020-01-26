package com.bahaa.mvvm.ui.activities.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bahaa.mvvm.R;
import com.bahaa.mvvm.util.MyPreference;
import com.bahaa.mvvm.util.SpacesItemDecoration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

    public Gson gson;
    public MyPreference preference;
    private static BaseActivity instance;
    private ProgressDialog mProgressDialog;
    public static BaseActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new GsonBuilder().create();
        preference = new MyPreference(gson);
        setContentView(getLayout());
        initActivity(savedInstanceState);
    }
    public abstract int getLayout();
    public abstract void initActivity(Bundle savedInstanceState);
    public void hideView(View view){
        if (view != null) view.setVisibility(View.GONE);
    }
    public void showView(View view){
        if (view != null) view.setVisibility(View.VISIBLE);
    }
    public void hideToolbar() {
        if (getSupportActionBar() != null) getSupportActionBar().hide();
    }
    public void hideView(List<View> views){
        for (View view :
                views) {
            if (view != null) view.setVisibility(View.GONE);
        }
    }
    public void showView(List<View> views){
        for (View view :
                views) {
            if (view != null) view.setVisibility(View.VISIBLE);
        }
    }
    public void fillSpinner(List list, Spinner spinner) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }
    public void hideKeyboard() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (getCurrentFocus() != null)
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.e("MultiBackStack", "Failed to add fragment to back stack", e);
        }
    }
    public void goToFragment(Fragment fragment, int frame, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(tag) == null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(frame, fragment, tag).commit();
        }
        List<Fragment> fragments = fragmentManager.getFragments();
        Log.e("Fragments Quantity", String.valueOf(fragments.size()));
        removeFragments(fragments);
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                .show(fragment)
                .commit();
    }
    private void removeFragments(List<Fragment> fragments) {
        for (Fragment fragment :
                fragments) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    public void openActivity(Class<?> activity, Bundle bundle, boolean finish) {
        if (finish) finish();
        Intent intent = new Intent(this, activity);
        if (bundle != null) intent.putExtras(bundle);
        startActivity(intent);
    }
    public void openActivityWithAnim(Class<?> activity, Bundle bundle, Boolean finish, int startAnime, int endAnime) {
        if (finish) finish();
        Intent intent = new Intent(this, activity);
        if (bundle != null) intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(startAnime, endAnime);
    }
    public ProgressDialog showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
        }

        if (!mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(getString(R.string.message_loading));
            mProgressDialog.show();
        }

        return mProgressDialog;
    }
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
    public GridLayoutManager initVerticalRV(RecyclerView recyclerView, int spanCount, int space) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(space, spanCount, true));
        recyclerView.setNestedScrollingEnabled(false);
        return gridLayoutManager;
    }
    @Override
    protected void onResume() {
        super.onResume();
        instance = this;
    }
    public GridLayoutManager initHorizontalRV(RecyclerView recyclerView, int spanCount) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10, spanCount, false));
        recyclerView.setNestedScrollingEnabled(false);
        return gridLayoutManager;
    }
    public LinearLayoutManager initVerticalRVLinear(RecyclerView recyclerView, int spanCount) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        // recyclerView.addItemDecoration(new SpacesItemDecoration(10, spanCount, false));
        recyclerView.setNestedScrollingEnabled(false);
        return linearLayoutManager;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hideProgressDialog();

    }
    public void showDialog(String title, String message) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_alert);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView txtTitle;
        TextView txtMessage;
        Button btnOK;
        Button btnNO;

        txtTitle = dialog.findViewById(R.id.txtTitle);
        txtTitle.setText(title);

        txtMessage = dialog.findViewById(R.id.txtMessage);
        txtMessage.setText(message);

        btnOK = dialog.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnNO = dialog.findViewById(R.id.btnNO);
        btnNO.setVisibility(View.GONE);

        dialog.show();

    }
    public Dialog showDialog(String title, String message, String buttonOK, String buttonNO, int image, View.OnClickListener onOkClickListener) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_alert);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        ImageView imageIcon;
        TextView txtTitle;
        TextView txtMessage;
        Button btnOK;
        Button btnNO;


        txtTitle = dialog.findViewById(R.id.txtTitle);
        txtTitle.setText(title);

        txtMessage = dialog.findViewById(R.id.txtMessage);
        txtMessage.setText(message);

        imageIcon = dialog.findViewById(R.id.imageIcon);
        if (image != 0) {
            imageIcon.setImageDrawable(getDrawable(image));
        } else {
            imageIcon.setVisibility(View.GONE);
        }


        btnOK = dialog.findViewById(R.id.btnOK);
        btnOK.setText(buttonOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onOkClickListener != null) onOkClickListener.onClick(v);
            }
        });

        btnNO = dialog.findViewById(R.id.btnNO);
        if (buttonNO != null) {
            btnNO.setText(buttonNO);
            btnNO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        } else {
            btnNO.setVisibility(View.GONE);
        }

        dialog.show();
        return dialog;
    }
    public void openFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow();
    }


}
