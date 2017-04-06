package com.rohit.garorasu.productrxtask.Result;

import com.rohit.garorasu.productrxtask.FilledForm;

import java.util.ArrayList;

/**
 * Created by garorasu on 6/4/17.
 */

public interface FilledFormView {
        void showProgress();
        void hideProgress();
        void showFailure();
        void addForms(ArrayList<FilledForm> forms);
}

