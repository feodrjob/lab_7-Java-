package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Flat implements Serializable {
    private int number;
    private double square;
    private Person[] data;


    @JsonCreator
    public Flat(@JsonProperty("number") int number,
                @JsonProperty("square") double square,
                @JsonProperty("data") Person[] data) {
        this.number = number;
        this.square = square;
        this.data = data;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public Person[] getData() {
        return data;
    }

    public void setData(Person[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return number == flat.number && Double.compare(square, flat.square) == 0 && Objects.deepEquals(data, flat.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, square, Arrays.hashCode(data));
    }
}
