package org.launchcode.techjobs.persistent.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

/* Part 2.1: AbstractEntity - This class replaces JobField & holds fields/methods common across Job class and the classes
   it has as fields. Add @MappedSuperclass annotation to allow tables by subclasses but NOT for this parent class itself. */
@MappedSuperclass
public abstract class AbstractEntity {

/*  Part 2.2 AbstractEntity - Add @Id and @GeneratedValue annotations to id field since all subclasses of AbstractEntity
           will also be entities. */
    @Id
    @GeneratedValue
    private int id;

//  Part 2.3.a AbstractEntity - Added (@NotBlank) as validation to prevent user from leaving name field blank. //
    @NotBlank(message = "This field is required.")
/* Part 2.3.b AbstractEntity - Added (@Size) as reasonable limit to size of name string to be used for Job, Employer,
                and Skill (formerly CoreCompetency). I found some documentation recommending around 160 characters. */
    @Size(min = 1, max = 160, message = "This field must be no more than 160 characters.")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}