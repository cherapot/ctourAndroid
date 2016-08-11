package com.example.redstar.ctourbeta.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by RedStar on 27.03.2016.
 */
public class AbstractFragment extends Fragment{


    private String title;
    protected Context context;
    protected View view;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
