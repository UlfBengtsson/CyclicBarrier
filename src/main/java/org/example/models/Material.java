package org.example.models;

public class Material {
    private static int idCounter = 0;
    private int id;
    private MaterialState state;

    private String name;

    public Material(MaterialState state, String name) {
        this.id = ++idCounter;
        this.state = state;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public MaterialState getState() {
        return state;
    }

    public void setState(MaterialState state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id: " + id +
                " name: " + name +
                " state: " + state + '\'' +
                '}';
    }
}