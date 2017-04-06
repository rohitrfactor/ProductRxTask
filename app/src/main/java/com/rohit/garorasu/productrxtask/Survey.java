package com.rohit.garorasu.productrxtask;

/**
 * Created by garorasu on 6/4/17.
 */

public class Survey {
    int emp_id;
    String name;
    String sex;

    public Survey(int emp_id,String name,String sex){
        this.emp_id = emp_id;
        this.name   = name;
        this.sex    = sex;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }
}
