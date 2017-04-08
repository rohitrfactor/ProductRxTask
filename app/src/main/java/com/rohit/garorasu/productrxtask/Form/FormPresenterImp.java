package com.rohit.garorasu.productrxtask.Form;

import com.rohit.garorasu.productrxtask.Schema;
import com.rohit.garorasu.productrxtask.Survey;

import java.util.ArrayList;
import java.util.HashMap;

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
    public void submitForm(HashMap<String,String> params) {
            if(view != null){
                view.showProgress();
            }
            interacter.submitForm(params);
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

    @Override
    public void getSchema() {
            interacter.requestFormSchema();
    }

    @Override
    public void addSchema(ArrayList<Schema> schemas) {
            if(view != null){
                view.renderViews(schemas);
            }
    }

    @Override
    public void schemaFailure() {

    }
}
