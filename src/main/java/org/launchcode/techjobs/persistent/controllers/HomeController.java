package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    /* Part 3.3 Updating HomeController: Add employerRepository field that is AutoWired - looks for an EmployerRepository object
    to manage and create an instance of it to populate the employerRepository field variable with the CRUD data from our ORM. */
    @Autowired
    private EmployerRepository employerRepository;


    // Part 3 At End - Had to add jobRepository to save newJob to job table in MySQL
    @Autowired
    private JobRepository jobRepository;

    // This handles the TechJobs template/index.html display (Shows bullet list of hyperlinked jobs href to job.id,
    // but displaying job.name.
    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        // Part 3 End/No Directions - Version 1: Had to add code to display all jobs from the jobRepository database.
        model.addAttribute("jobs", jobRepository.findAll()); // "jobs" from index.html ${jobs}.

        // OR Version 2: make a collection/ArrayList of jobs from the JobRepository table to display.
//        List jobs = (List<Job>) jobRepository.findAll();
//        model.addAttribute("jobs", jobs);

        return "index";
    }

    // Handles displaying the Add Job page. Shows required Name field and selections for Employer and Skills.
    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        /* Part 3.3.2 Update HomeController - check templates/add.html to get "employers" attribute and use it to add
        Employer data from the employerRepository to the model below. */
        model.addAttribute("employers", employerRepository.findAll());
        return "add";
    }



    // Handles submitting the Add Job page user input and storing it in the database. Display "Add Job" along with
    //          how required Name text field and drop down selections for Employer and Skills.
//    ******NOTE: Temporarily made skills @RequestParam(required=false) so I could test program w/o skills working yet.
    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId, @RequestParam(required=false) List<Integer> skills) {
        // Part 3 End/No Directions Version 1: Check for validation. If passes, skip ahead to Optional<>. If not, route back to add page.
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Job");
//            // Part 3.3.4.Note: Added code to relist options if validation fails.
//            model.addAttribute("employers", employerRepository.findAll());
//            return "add";
//        }
//        /* Part 3.3.4 - Added this per coding_events project to check if an employerId value is present/submitted and
//                      then save to the jobRepository. Optional provides a temporary container to hold the data by ID.
//                      Then it checks to see if a value is actually present. Then creates an Employer object to get()
//                      the values. Then set those values as the new value for the employer. Then save that newJob to the
//                      jobRepository. */
//        Optional<Employer> optionalEmployer = employerRepository.findById(employerId);
//            if (optionalEmployer.isPresent()) {
//                Employer employer = optionalEmployer.get();
//                newJob.setEmployer(employer);
//            }
//        // *Part 3 End/No Directions Version 1 - Had to add jobRepository to save newJob to job table in MySQL.
//        jobRepository.save(newJob);
//        return "redirect:"; // returns to main index page for TechJobs templates/index.html w/ updated bullet list of Jobs by name.
//    }

        /* Part 3 End/No Directions Version 2: Establish optionalEmployer (using the Optional container). Then check if
                either there are Validation errors or if the input submitted does not contain a value, relist the
                employers and return to Add page. THIS SEEMS DRY-ER TO ME! */
        Optional<Employer> optionalEmployer = employerRepository.findById(employerId); // Have to declare/initialize optionalEmployer before using it.

        if (errors.hasErrors() || optionalEmployer.isEmpty()) {
            model.addAttribute("title", "Add Job");
            // Part 3.3.4.Note: Added code to repopulate drop-down Employer options if validation or isEmpty fails.
            model.addAttribute("employers", employerRepository.findAll());
            return "add";
        }
//        If no errors and DOES have data, then get the values of that temporary object and assign/save that job in jobRepository.
            newJob.setEmployer(optionalEmployer.get());
        /* NOTE: Part 3 At End/No Directions - Had to add jobRepository to save newJob to job table in MySQL. */
            jobRepository.save(newJob);
            return "redirect:"; // returns to main index page for TechJobs templates/index.html w/ updated bullet list of Jobs by name.
    }



    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

       // *Part 3 At End/No Directions - Had to add code to hold an Optional Job instance based on id input.
        Optional<Job> optionalJob = jobRepository.findById(jobId);
       // If the jobId entered as a pathVariable is present/exists in the table, then that job data prints to screen via view.html.
        // Otherwise, it re-routes user back to existing TechJobs hyper-linked list.
        if(optionalJob.isPresent()) {
            Job job = (Job) optionalJob.get();
            model.addAttribute("job", job);
            return "view";
        }

        return "redirect:..";  // This routes user back to main TechJobs hyper-linked list of Jobs.
    }


}
