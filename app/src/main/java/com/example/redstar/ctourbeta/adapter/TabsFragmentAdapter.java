package com.example.redstar.ctourbeta.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.redstar.ctourbeta.R;
import com.example.redstar.ctourbeta.dto.TourDTO;
import com.example.redstar.ctourbeta.fragment.AbstractFragment;
import com.example.redstar.ctourbeta.fragment.AllTourFragment;
import com.example.redstar.ctourbeta.fragment.ExampleFragment;
import com.example.redstar.ctourbeta.fragment.MyTourFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RedStar on 27.03.2016.
 */
public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractFragment> tabs;
    private Context context;

    private AllTourFragment allTourFragment;
    private List<TourDTO> data;


    public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);

        this.context = context;
        this.data = new ArrayList<>();
        initTabMap(context);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabMap(Context context) {

        allTourFragment = AllTourFragment.getInstance(context, data);

        tabs = new HashMap<>();
        tabs.put(0, allTourFragment);
        tabs.put(1, MyTourFragment.getInstance(context));
        tabs.put(2, ExampleFragment.getInstance(context));
    }

    public void setData(List<TourDTO> data) {
        this.data = data;
        allTourFragment.refreshData(data);
    }

}
