package com.example.android.fishall;

/**
 * Created by ALLDe on 14-May-18.
 */

public class Fish {

    private String name;
    private String latinName;
    private String description;
    private String size;
    private int image;
    private int map;

    public Fish()
    {
        latinName = null;
        description=null;
        name=null;
        image=0;
        map=0;
        size=null;
    }

    public String getSize() {
        return size;
    }

    public int getMap() {
        return map;
    }

    public String getLatinName() {
        return latinName;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public void setMap(int map) {
        this.map = map;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

