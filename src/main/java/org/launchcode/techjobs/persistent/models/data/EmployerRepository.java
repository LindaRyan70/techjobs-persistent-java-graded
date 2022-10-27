package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*  Part 2.1 Data Layer (EmployerRepository) - Create a data access interface (repository) similar to existing JobRepository.
             Extend CrudRepository interface to map our objects. */
@Repository
public interface EmployerRepository extends CrudRepository<Employer, Integer> {
}
