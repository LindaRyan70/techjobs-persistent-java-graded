package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

// Part 2.4: Models (Skill) - Add @Entity to mark it as an entity/table. Note: Skill replaces CoreCompetency. //
@Entity
public class Skill extends AbstractEntity {

    // Part 2.3: Models (Skill) - Create location field & Add validation to require input & limit string length. //
    @NotBlank(message="Skill description may not be left blank.")
    @Size(min = 1, max = 750, message = "Please limit skill description to 750 characters.")
    private String description;

    /* Part 4.1 - ManyToMany - Skill.jobs - Add a jobs field with appropriate annotation that maps to "skills".
    *              Add getter/setter and refactor parameterized constructor to reflect a list of someJobs. I added FINAL
    *               to make the list permanent but the content inside can be changed. I think b/c skills do not need to
    *               be able to modify or setJobs in a setter. Need to research this more.... */
    @ManyToMany(mappedBy = "skills")
    private final List<Job> jobs = new ArrayList<>();


    // Not sure if I need a parameterized constructor here or not. Currently, it works w/o it but leaving on for now.
/*    Part 4.1 - Setting up a Many-To-Many (Refactoring Skill.jobs) I added a variable someJobs to the constructor b/c
    the existing starter code for Job constructor had a similar name for someSkills. */
    public Skill(String description) {
        this.description = description;
    }


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

    /* Part 4.1 - ManyToMany - Refactor Skill.jobs - Only added getter for new jobs field, since skills does not need to
                    modify the jobs list. Also, cannot setJobs b/c the field has been declared FINAL. */
    public List<Job> getJobs() {
        return jobs;
    }

}