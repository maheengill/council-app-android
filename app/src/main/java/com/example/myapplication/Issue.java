package com.example.myapplication;

public class Issue {
    private int id;
    private String name;

    public Issue(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Issue() {
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

    public void setName(String name) {
        this.name = name;
    }
}
