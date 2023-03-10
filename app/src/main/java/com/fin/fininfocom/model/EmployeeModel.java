package com.fin.fininfocom.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Regeti Jhansi Rani  on 09-03-2023.
 */
public class EmployeeModel extends RealmObject {
    private long id;
    private String employee_name, employee_city;
    private int employee_age;

    public EmployeeModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_city() {
        return employee_city;
    }

    public void setEmployee_city(String employee_city) {
        this.employee_city = employee_city;
    }

    public int getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(int employee_age) {
        this.employee_age = employee_age;
    }
}
