package com.rohit.garorasu.productrxtask.Result;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.rohit.garorasu.productrxtask.FilledForm;
import com.rohit.garorasu.productrxtask.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment implements FilledFormView {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeContainer;
    private FormAdapter adapter;



    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new FormAdapter(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        final FilledFormsPresenter presenter = new FilledFormsPresenterImp(this);
        presenter.getFilledForms();

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getFilledForms();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showFailure() {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                swipeContainer.setRefreshing(false);
            }
        });
    }

    @Override
    public void addForms(final ArrayList<FilledForm> forms) {


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                adapter.addForms(forms);
                swipeContainer.setRefreshing(false);
            }
        });

    }
}
