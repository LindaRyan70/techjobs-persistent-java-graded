package org.launchcode.techjobs.persistent.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

/*   Part 4.2 - ManyToMany - Refactor Job.skills field to reflect the Many-To-Many relationship with Skill.jobs.
        Originally I had it map to "jobs", but that threw an error that it could not do that, so I removed the mappedBy
        from the @ManyToMany annotation on the Job.skills field, and it worked. I also refactored parameterized
        constructor to allow for a List of someSkills, and added a getter/setter. */
//    private String skills;
    @ManyToMany
    private List<Skill> skills = new ArrayList<>();

    public Job() {
    }

    //  Part 3.2.2: Update Job Model class - Refactor constructor to reflect change in anEmployer type from String to Employer.
    /*    Part 4.2 - Setting up a Many-To-Many (Refactoring Job.skills) I refactored the type for the existing variable
                    someSkills in the constructor. (Created similar variable someJobs in the Job constructor.) */
    public Job(Employer anEmployer, List<Skill> someSkills) {
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


//    public String getSkills() {
//        return skills;
//    }
//
//    public void setSkills(String skills) {
//        this.skills = skills;
//    }

    /*  Part 4.2 Setting up Many-To-Many - Refactor Job.skills - Refactor get/set types above to List<Skill>  */
    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
