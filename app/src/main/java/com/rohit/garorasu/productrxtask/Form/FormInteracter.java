package com.rohit.garorasu.productrxtask.Form;


import java.util.HashMap;

/**
 * Created by garorasu on 6/4/17.
 */

public interface FormInteracter {
    void submitForm(HashMap<String,String> params);
    void requestFormSchema();
}
