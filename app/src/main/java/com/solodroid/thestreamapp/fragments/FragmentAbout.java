package com.solodroid.thestreamapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.solodroid.thestreamapp.R;
import com.solodroid.thestreamapp.activities.MainActivity;
import com.solodroid.thestreamapp.adapters.AdapterAbout;

import java.util.ArrayList;
import java.util.List;

public class FragmentAbout extends Fragment {

    View root_view, parent_view;
    RecyclerView recyclerView;
    private Toolbar toolbar;
    AdapterAbout adapterAbout;
    private MainActivity mainActivity;

    public FragmentAbout() {
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
        root_view = inflater.inflate(R.layout.fragment_about, null);
        parent_view = getActivity().findViewById(R.id.main_content);

        toolbar = (Toolbar) root_view.findViewById(R.id.toolbar);
        setupToolbar();
        setHasOptionsMenu(true);

        recyclerView = (RecyclerView) root_view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterAbout = new AdapterAbout(getDataInformation(), getActivity());
        recyclerView.setAdapter(adapterAbout);

        return root_view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.refresh_btn).setVisible(false);
    }

    private List<Data> getDataInformation() {

        List<Data> data = new ArrayList<>();

        data.add(new Data(
                R.drawable.ic_other_appname,
                getResources().getString(R.string.about_app_name),
                getResources().getString(R.string.app_name)
        ));

        data.add(new Data(
                R.drawable.ic_other_build,
                getResources().getString(R.string.about_app_version),
                getResources().getString(R.string.sub_about_app_version)
        ));

        data.add(new Data(
                R.drawable.ic_other_email,
                getResources().getString(R.string.about_app_email),
                getResources().getString(R.string.sub_about_app_email)
        ));

        data.add(new Data(
                R.drawable.ic_other_copyright,
                getResources().getString(R.string.about_app_copyright),
                getResources().getString(R.string.sub_about_app_copyright)
        ));

        data.add(new Data(
                R.drawable.ic_other_rate,
                getResources().getString(R.string.about_app_rate),
                getResources().getString(R.string.sub_about_app_rate)
        ));

        data.add(new Data(
                R.drawable.ic_other_more,
                getResources().getString(R.string.about_app_more),
                getResources().getString(R.string.sub_about_app_more)
        ));

        data.add(new Data(
                R.drawable.ic_other_privacy,
                getResources().getString(R.string.about_app_privacy_policy),
                getResources().getString(R.string.sub_about_app_privacy_policy)
        ));

        return data;
    }

    public class Data {
        private int image;
        private String title;
        private String sub_title;

        public int getImage() {
            return image;
        }

        public String getTitle() {
            return title;
        }

        public String getSub_title() {
            return sub_title;
        }

        public Data(int image, String title, String sub_title) {
            this.image = image;
            this.title = title;
            this.sub_title = sub_title;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity.setupNavigationDrawer(toolbar);
    }

    private void setupToolbar() {
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setSubtitle(getString(R.string.drawer_about));
        mainActivity.setSupportActionBar(toolbar);
    }

}