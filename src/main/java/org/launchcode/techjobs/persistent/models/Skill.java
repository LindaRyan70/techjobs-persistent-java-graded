package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// Part 2.4: Models (Skill) - Add @Entity to mark it as an entity/table. Note: Skill replaces CoreCompetency. //
@Entity
public class Skill extends AbstractEntity {

    // Part 2.3: Models (Skill) - Create location field & Add validation to require input & limit string length. //
    @NotBlank(message="Skill description may not be left blank.")
    @Size(min = 1, max = 750, message = "Please limit skill description to 750 characters.")
    private String description;

    // Part 2.4: Models (Skill) - Add no arg constructor since an @Entity, so Hibernate can create table in MySQL database. //
    public Skill() {
    }

    // Part 2.3: Models (Skill) - Add getters & setters for public access. //
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}