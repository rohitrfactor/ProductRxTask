package com.rohit.garorasu.productrxtask.Form;

import com.rohit.garorasu.productrxtask.Survey;

/**
 * Created by garorasu on 6/4/17.
 */

public interface FormPresenter {
    void submitForm(Survey survey);
    void submitSuccess();
    void submitFailure();
}
