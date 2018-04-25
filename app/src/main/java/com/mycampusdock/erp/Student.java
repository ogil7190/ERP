package com.mycampusdock.erp;

public class Student {
    private String name;
    private String roll;
    private boolean marked;
    public Student() {
    }

    public Student(String name, String roll) {
        this.name = name;
        this.roll = roll;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getName() {

        return name;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public boolean isMarked() {

        return marked;
    }

    public String getRoll() {
        return roll;
    }
}
