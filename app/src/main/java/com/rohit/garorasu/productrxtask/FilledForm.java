package com.rohit.garorasu.productrxtask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by garorasu on 6/4/17.
 */

public class FilledForm {
    String id,emp_id,name,sex;
    public FilledForm(String id, String emp_id, String name, String sex){
        this.id = id;
        this.emp_id = emp_id;
        this.name  = name;
        this.sex   = sex;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public static FilledForm jsonToFilledForm(JSONObject jsonObject){

        try {
            String id = jsonObject.getString("id");
            String emp_id = jsonObject.getString("emp_id");
            String name = jsonObject.getString("name");
            String sex = jsonObject.getString("sex");
            return new FilledForm(id,emp_id,name,sex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
