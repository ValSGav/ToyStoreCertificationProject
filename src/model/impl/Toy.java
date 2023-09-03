package model.impl;

import model.Raffled;
import model.Saveable;

import java.util.Random;

public class Toy implements Raffled, Saveable, Comparable<Toy> {

    static Random rnd;

    static {
        rnd = new Random();
    }

    private int weight;
    final String name;

    final int id;

    public Toy(String name) {
        this.name = name;
        this.weight = 0;
        this.id = rnd.nextInt(0, 210000000);
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getRepresentation() {
        return String.format("%s(id:%d)", this.name, this.id);
    }

    @Override
    public int compareTo(Toy o) {
        return this.id - o.id;
    }
}
