package org.launchcode.techjobs.persistent.models;


import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// Part 2.2: Models (Employer) - Add @Entity to mark it as an entity/table.
@Entity
public class Employer extends AbstractEntity {

    // Part 2.1: Models (Employer) - Create location field & Add validation to require input & limit string length. //
    @NotBlank(message="Location is required.")
    @Size(min = 1, max = 100, message = "Please limit location to 100 characters.")
    private String location;

    // Part 2.2: Models (Employer) - Add no arg constructor since an @Entity, so Hibernate can create table in MySQL database. //
    public Employer() {
    }

    // Part 2.1: Models (Employer) - Add getters & setters for public access. //
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
