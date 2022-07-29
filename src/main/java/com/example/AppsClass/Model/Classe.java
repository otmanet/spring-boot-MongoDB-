package com.example.AppsClass.Model;

import java.util.List;
import java.util.Map;

public class Classe {

    private Map<String, Object> global;

    private List<Object> Student;

    private Boolean System;

    private Boolean active;

    public Classe() {
        super();
    }

    public Classe(Map<String, Object> global, List<Object> student, Boolean system, Boolean active) {
        this.global = global;
        Student = student;
        System = system;
        this.active = active;
    }

    public Map<String, Object> getGlobal() {
        return global;
    }

    public void setGlobal(Map<String, Object> global) {
        this.global = global;
    }

    public List<Object> getStudent() {
        return Student;
    }

    public void setStudent(List<Object> student) {
        Student = student;
    }

    public Boolean getSystem() {
        return System;
    }

    public void setSystem(Boolean system) {
        System = system;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Classe{" +
                "global=" + global +
                ", Student=" + Student +
                ", System=" + System +
                ", active=" + active +
                '}';
    }
}
