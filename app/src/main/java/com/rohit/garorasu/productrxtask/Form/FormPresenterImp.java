package com.rohit.garorasu.productrxtask.Form;

import com.rohit.garorasu.productrxtask.Survey;

/**
 * Created by garorasu on 6/4/17.
 */

public class FormPresenterImp implements FormPresenter {
    private FormInteracter interacter;
    private FormView view;
    public FormPresenterImp(FormView view){
            this.view = view;
            this.interacter = new FormInteracterImp(this);
    }

    @Override
    public void submitForm(Survey survey) {
            if(view != null){
                view.showProgress();
            }
            interacter.submitForm(survey);
    }

    @Override
    public void submitSuccess() {
            if(view != null){
                view.submitSuccess();
            }
    }

    @Override
    public void submitFailure() {
            if(view != null){
                view.submitFailure();
            }
    }
}
