package com.rohit.garorasu.productrxtask.Result;

import com.rohit.garorasu.productrxtask.FilledForm;
import com.rohit.garorasu.productrxtask.Schema;

import java.util.ArrayList;
import java.util.HashMap;

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
        interacter.getFilledForms();
    }

    @Override
    public void addForms(ArrayList<HashMap<String,String>> forms) {
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

    @Override
    public void getSchema() {
        interacter.requestFormSchema();
    }

    @Override
    public void addSchema(ArrayList<Schema> schemas) {
        if(view!=null){
            view.renderViews(schemas);
            getFilledForms();
        }
    }

    @Override
    public void schemaFailure() {

    }
}
