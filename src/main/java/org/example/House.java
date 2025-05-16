package org.example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class House implements Serializable {
    private String houseNumber;
    private String aderess;
    private Person owners;
    private Flat[] flatsList;

    public House(String houseNumber, String aderess, Person owners, Flat[] flatsList) {
        this.houseNumber = houseNumber;
        this.aderess = aderess;
        this.owners = owners;
        this.flatsList = flatsList;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAderess() {
        return aderess;
    }

    public void setAderess(String aderess) {
        this.aderess = aderess;
    }

    public Person getOwners() {
        return owners;
    }

    public void setOwners(Person owners) {
        this.owners = owners;
    }

    public Flat[] getFlatsList() {
        return flatsList;
    }

    public void setFlatsList(Flat[] flatsList) {
        this.flatsList = flatsList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(houseNumber, house.houseNumber) && Objects.equals(aderess, house.aderess) && Objects.equals(owners, house.owners) && Objects.deepEquals(flatsList, house.flatsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseNumber, aderess, owners, Arrays.hashCode(flatsList));
    }
}
