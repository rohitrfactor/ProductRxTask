package com.rohit.garorasu.productrxtask.Form;

import com.rohit.garorasu.productrxtask.Schema;
import com.rohit.garorasu.productrxtask.Survey;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by garorasu on 6/4/17.
 */

public interface FormPresenter {
    void submitForm(HashMap<String,String> params);
    void submitSuccess();
    void submitFailure();
    void getSchema();
    void addSchema(ArrayList<Schema> schemas);
    void schemaFailure();
}
