package com.example.redstar.ctourbeta.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;

import com.example.redstar.ctourbeta.R;

/**
 * Created by RedStar on 27.03.2016.
 */
public class MyTourFragment extends AbstractFragment {

    private static final  int LAYOUT = R.layout.fragment_example;

    public static MyTourFragment getInstance(Context context) {
        Bundle args = new Bundle();
        MyTourFragment fragment = new MyTourFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_my_tour));

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
