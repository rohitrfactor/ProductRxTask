package com.rohit.garorasu.productrxtask.Result;

import com.rohit.garorasu.productrxtask.FilledForm;

import java.util.ArrayList;

/**
 * Created by garorasu on 6/4/17.
 */

public class FilledFormsPresenterImp implements FilledFormsPresenter {
    private FilledFormInteracter interacter;
    private FilledFormView view;

    public FilledFormsPresenterImp(FilledFormView view){
        this.view = view;
        this.interacter = new FilledFormInteracterImp(this);
    }
    @Override
    public void getFilledForms() {
        if(view !=null){
            view.showProgress();
        }
        interacter.getFilledForms();
    }

    @Override
    public void addForms(ArrayList<FilledForm> forms) {
        if(view != null){
            view.addForms(forms);
            view.hideProgress();
        }
    }

    @Override
    public void showFailure() {
        if(view != null){
            view.hideProgress();
            view.showFailure();
        }
    }
}
