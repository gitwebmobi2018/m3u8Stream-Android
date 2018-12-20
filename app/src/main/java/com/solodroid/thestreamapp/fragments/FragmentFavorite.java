package com.solodroid.thestreamapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.solodroid.thestreamapp.R;
import com.solodroid.thestreamapp.activities.MainActivity;
import com.solodroid.thestreamapp.adapters.AdapterFavorite;
import com.solodroid.thestreamapp.databases.DatabaseHandlerFavorite;
import com.solodroid.thestreamapp.models.Channel;

import java.util.ArrayList;
import java.util.List;

public class FragmentFavorite extends Fragment {

    private MainActivity mainActivity;
    private Toolbar toolbar;
    private List<Channel> data = new ArrayList<Channel>();
    View root_view, parent_view;
    AdapterFavorite adapterPostList;
    DatabaseHandlerFavorite databaseHandler;
    RecyclerView recyclerView;
    LinearLayout linearLayout;

    public FragmentFavorite() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root_view = inflater.inflate(R.layout.fragment_favorite, null);
        parent_view = getActivity().findViewById(R.id.main_content);

        toolbar = (Toolbar) root_view.findViewById(R.id.toolbar);
        setupToolbar();
        setHasOptionsMenu(true);

        linearLayout = (LinearLayout) root_view.findViewById(R.id.lyt_no_favorite);
        recyclerView = (RecyclerView) root_view.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        databaseHandler = new DatabaseHandlerFavorite(getActivity());
        data = databaseHandler.getAllData();

        adapterPostList = new AdapterFavorite(getActivity(), recyclerView, data);
        recyclerView.setAdapter(adapterPostList);

        if (data.size() == 0) {
            linearLayout.setVisibility(View.VISIBLE);
        } else {
            linearLayout.setVisibility(View.INVISIBLE);
        }

        return root_view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.refresh_btn).setVisible(false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity.setupNavigationDrawer(toolbar);
    }

    private void setupToolbar() {
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setSubtitle(getString(R.string.tab_favorite));
        mainActivity.setSupportActionBar(toolbar);
    }

    @Override
    public void onResume() {

        super.onResume();

        data = databaseHandler.getAllData();
        adapterPostList = new AdapterFavorite(getActivity(), recyclerView, data);
        recyclerView.setAdapter(adapterPostList);

        if (data.size() == 0) {
            linearLayout.setVisibility(View.VISIBLE);
        } else {
            linearLayout.setVisibility(View.INVISIBLE);
        }
    }

}
