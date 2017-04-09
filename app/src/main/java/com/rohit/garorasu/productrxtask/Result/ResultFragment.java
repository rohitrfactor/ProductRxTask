package com.rohit.garorasu.productrxtask.Result;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rohit.garorasu.productrxtask.R;
import com.rohit.garorasu.productrxtask.Schema;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment implements FilledFormView {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeContainer;
    private FormAdapter adapter;
    private ProgressBar mProgressBar;
    private LinearLayout mError;
    private FilledFormsPresenter presenter;



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
        mProgressBar = (ProgressBar) view.findViewById(R.id.result_progress);
        mError = (LinearLayout) view.findViewById(R.id.result_error);
        Button retry = (Button) view.findViewById(R.id.retry_result_schema);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSchema();
            }
        });
        adapter = new FormAdapter(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        presenter = new FilledFormsPresenterImp(this);
        getSchema();

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getSchema();
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
            mProgressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
            mError.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    mError.setVisibility(View.INVISIBLE);
                }
            });
    }

    @Override
    public void showFailure() {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                swipeContainer.setRefreshing(false);
                mProgressBar.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
                mError.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void addForms(final ArrayList<HashMap<String,String>> forms) {


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                adapter.addForms(forms);
                swipeContainer.setRefreshing(false);
            }
        });

    }

    @Override
    public void getSchema() {
        presenter.getSchema();
    }

    @Override
    public void renderViews(final ArrayList<Schema> schemas) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.addSchemas(schemas);
            }
        });
    }
}
