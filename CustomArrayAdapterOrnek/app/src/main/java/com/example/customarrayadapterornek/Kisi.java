package com.example.customarrayadapterornek;

public class Kisi {

    private int photo;
    private String name;


    public Kisi(String name, int photo) {
        this.photo = photo;
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
