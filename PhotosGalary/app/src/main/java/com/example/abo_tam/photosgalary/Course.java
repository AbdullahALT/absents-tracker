package com.example.abo_tam.photosgalary;

/**
 * Created by abo_tam on 8/19/2015.
 */
public class Course {
    private int id;
    private String name;
    private int absents;

    public Course(String name) {
        this.name = name;
        this.id = this.absents = 0;
    }

    public Course(int id, String name, int absents) {
        this.name = name;
        this.id = id;
        this.absents = absents;
    }

    public int getAbsents() {
        return absents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

}
