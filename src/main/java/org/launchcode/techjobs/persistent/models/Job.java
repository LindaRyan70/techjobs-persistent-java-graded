package org.launchcode.techjobs.persistent.models;

import javax.persistence.*;

@Entity
public class Job extends AbstractEntity{

    // Part 3.2.1: Update Job Model class - No longer need id and name fields since they extend from AbstractEntity.
//    @Id
//    @GeneratedValue
//    private int id;
//
//    private String name;

    // Part 3.2.2: Update Job Model class - Refactor (String employer) to be of type (Employer employer).
    // Part 3.2.3: Add @ManyToOne annotation to establish a persistent relationship with Employer.
    @ManyToOne
    private Employer employer;

    private String skills;

    public Job() {
    }

    //  Part 3.2.2: Update Job Model class - Refactor constructor to reflect change in anEmployer type from String to Employer.
    public Job(Employer anEmployer, String someSkills) {
        super();
        this.employer = anEmployer;
        this.skills = someSkills;
    }

    // Part 3.2.1: Update Job Model class - No longer need id and name get/sets since Job extends from AbstractEntity.

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    //  Part 3.2.2: Update Job Model class - Refactor get/sets to reflect change in employer type from String to Employer.
    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
