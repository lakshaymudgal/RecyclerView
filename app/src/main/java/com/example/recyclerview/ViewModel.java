package com.example.recyclerview;

public class ViewModel {

    private String name;
    private int id;

    public ViewModel(String name, int id){

        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
