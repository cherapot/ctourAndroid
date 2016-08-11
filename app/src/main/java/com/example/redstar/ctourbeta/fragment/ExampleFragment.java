package com.example.redstar.ctourbeta.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;

import com.example.redstar.ctourbeta.R;


public class ExampleFragment extends AbstractFragment {


    private static final  int LAYOUT = R.layout.fragment_example;

    public static ExampleFragment getInstance(Context context) {
        Bundle args = new Bundle();
        ExampleFragment fragment = new ExampleFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_arr_tour));

        return fragment;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        return view;//super.onCreateView(inflater, container, savedInstanceState);
    }
}
