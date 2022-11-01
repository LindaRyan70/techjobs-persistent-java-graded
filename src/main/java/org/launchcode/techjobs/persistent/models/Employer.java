package org.launchcode.techjobs.persistent.models;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

// Part 2.2: Models (Employer) - Add @Entity to mark it as an entity/table.
@Entity
public class Employer extends AbstractEntity {

    // Part 2.1: Models (Employer) - Create location field & Add validation to require input & limit string length. //
    @NotBlank(message="Location is required.")
    @Size(min = 1, max = 100, message = "Please limit location to 100 characters.")
    private String location;

    // Part 3.1 One-To-Many - Add jobs Field to Employer and join the column with a foreign key employer_id.
    @OneToMany  // Establishes a persistent relationship between one employer and many jobs.
    @JoinColumn(name = "employer_id") // Generates a column (foreign key) in Job table which will point to Employer class primary key.
    private final List<Job> jobs = new ArrayList<>();


   // Not sure if I need a parameterized constructor here or not. Currently, it works w/o it but leaving on for now.
    public Employer(String location) {
        this.location = location;
    }


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
