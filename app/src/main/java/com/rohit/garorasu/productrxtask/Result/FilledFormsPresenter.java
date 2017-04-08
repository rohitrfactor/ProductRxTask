package com.rohit.garorasu.productrxtask.Result;



import com.rohit.garorasu.productrxtask.FilledForm;
import com.rohit.garorasu.productrxtask.Schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by garorasu on 6/4/17.
 */

public interface FilledFormsPresenter {
        void getFilledForms();
        void addForms(ArrayList<HashMap<String,String>> forms);
        void showFailure();
        void getSchema();
        void addSchema(ArrayList<Schema> schemas);
        void schemaFailure();
}
