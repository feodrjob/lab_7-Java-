package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {
    private String lastname;
    private String firstname;
    private String surname;
    private String birthsday;


    @JsonCreator
    public Person(@JsonProperty("lastname") String lastname,
                  @JsonProperty("firstname") String firstname,
                  @JsonProperty("surname") String surname,
                  @JsonProperty("birthsday") String birthsday) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.surname = surname;
        this.birthsday = birthsday;
    }


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthsday() {
        return birthsday;
    }

    public void setBirthsday(String birthsday) {
        this.birthsday = birthsday;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(lastname, person.lastname) && Objects.equals(firstname, person.firstname) && Objects.equals(surname, person.surname) && Objects.equals(birthsday, person.birthsday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastname, firstname, surname, birthsday);
    }
}
