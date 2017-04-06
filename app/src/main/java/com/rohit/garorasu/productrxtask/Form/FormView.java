package com.rohit.garorasu.productrxtask.Form;

import android.view.View;

/**
 * Created by garorasu on 6/4/17.
 */

public interface FormView {
    void submitForm();
    void submitSuccess();
    void submitFailure();
    void showProgress();
}
