package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*  Part 2.2 Data Layer (SKillRepository) - Create a data access interface (repository) similar to existing JobRepository.
             Extend CrudRepository interface to map our objects. */
@Repository
public interface SkillRepository extends CrudRepository<Skill, Integer> {
}
