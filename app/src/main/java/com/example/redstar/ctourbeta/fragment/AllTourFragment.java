package com.example.redstar.ctourbeta.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;

import com.example.redstar.ctourbeta.R;
import com.example.redstar.ctourbeta.adapter.TourListAdapter;
import com.example.redstar.ctourbeta.dto.TourDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RedStar on 27.03.2016.
 */
public class AllTourFragment extends AbstractFragment {

    private static final  int LAYOUT = R.layout.tmp;
    private List<TourDTO> data;
    private TourListAdapter tourListAdapter;

    public static AllTourFragment getInstance(Context context, List<TourDTO> data) {
        Bundle args = new Bundle();
        AllTourFragment fragment = new AllTourFragment();
        fragment.setArguments(args);
        fragment.setData(data);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_all_tour));

        return fragment;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycleView);
        rv.setLayoutManager(new LinearLayoutManager(context));
        tourListAdapter = new TourListAdapter(data);
        rv.setAdapter(tourListAdapter);

        return view;//super.onCreateView(inflater, container, savedInstanceState);
    }


    public void setData(List<TourDTO> data) {
        this.data = data;
    }

    public void refreshData(List<TourDTO> data){
        tourListAdapter.setData(data);
        tourListAdapter.notifyDataSetChanged();
    }
}
