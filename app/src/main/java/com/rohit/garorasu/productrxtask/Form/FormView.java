package com.rohit.garorasu.productrxtask.Form;

import android.view.View;

import com.rohit.garorasu.productrxtask.Schema;

import java.util.ArrayList;

/**
 * Created by garorasu on 6/4/17.
 */

public interface FormView {
    void submitForm();
    void submitSuccess();
    void submitFailure();
    void showProgress();
    void getSchema();
    void renderViews(ArrayList<Schema> schemas);
}
