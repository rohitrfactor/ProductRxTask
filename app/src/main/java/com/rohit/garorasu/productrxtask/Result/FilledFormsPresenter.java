package com.rohit.garorasu.productrxtask.Result;



import com.rohit.garorasu.productrxtask.FilledForm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garorasu on 6/4/17.
 */

public interface FilledFormsPresenter {
        void getFilledForms();
        void addForms(ArrayList<FilledForm> forms);
        void showFailure();
}
