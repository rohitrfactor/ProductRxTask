package com.rohit.garorasu.productrxtask.Result;

import com.rohit.garorasu.productrxtask.Schema;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by garorasu on 6/4/17.
 */

public interface FilledFormView {
        void showProgress();
        void hideProgress();
        void showFailure();
        void addForms(ArrayList<HashMap<String,String>> forms);
        void getSchema();
        void renderViews(ArrayList<Schema> schemas);
}

