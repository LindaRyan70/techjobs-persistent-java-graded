package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
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

    /*  Part 4.3 ManyToMany - Updating HomeController, Again - Similar to what I did with EmployerRepository, I added
    *  appropriate SkillRepository field and model.addAttributes to controller methods to add skills objects to
    *   the newJob param variable in processAddJobForm().   */
    @Autowired
    private SkillRepository skillRepository;

    // This handles the TechJobs template/index.html display (Shows bullet list of hyperlinked jobs href to job.id,
    // but displaying job.name.
    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        // Part 3 End/No Directions - Version 1: Add code to display all jobs from the jobRepository database.
        model.addAttribute("jobs", jobRepository.findAll()); // "jobs" from index.html ${jobs}.

        // OR Version 2: Make collection/List of jobs and assign to JobRepository table to display (cast JobRepository to List<Job> type).
//        List<Job> jobs = (List<Job>) jobRepository.findAll();
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
        /* Part 4.3 ManyToMany - Updating HomeController - check templates/add.html to get "skills" attribute and use it
        to add Skills data from the skillRepository to the model below.  */
        model.addAttribute("skills", skillRepository.findAll());
        return "add";
    }



    // Handles submitting the Add Job page user input and storing it in the database. Display "Add Job" along with
    //          required Name text field and drop down selections for Employer and Skills.
/*    ******NOTE: I had to make @RequestParam(required=false) for the (List<Integer> skills) param, to allow skills
                selection to be optional on the Add Job Form. */
    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob, Errors errors, Model model,
                                    @RequestParam int employerId, @RequestParam(required = false) List<Integer> skills) {

        /* Part 3 End/No Directions: Check for validation. If passes, skip ahead to create an Optional<Employer>
                  instance. If not, route back to add page. */
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            // Part 3.3.4. Note: Added code to relist Employer options if validation fails.
            model.addAttribute("employers", employerRepository.findAll());
            // Part 4.3 ManyToMany - Update HomeController - Added code to repopulate Skills options if validation fails.
            model.addAttribute("skills", skillRepository.findAll());
            return "add";
        }

        /* Part 3.3.4 - Added this per coding_events project to check if an employerId value is present/submitted and
                      then save to the jobRepository. Optional provides a temporary container to hold the data by ID.
                      Then it checks to see if a value is actually present and sets those values to attach to the newJob.
                       At the very bottom of this method, I added code to save the newJob to the jobRepository. */
        Optional<Employer> optionalEmployer = employerRepository.findById(employerId);
            if (optionalEmployer.isPresent()) {
                newJob.setEmployer(optionalEmployer.get());
            }

        /*  NOTE: Per IntelliJ, this 'functional style expression' works in place of the if(optionalEmployer.isPresent())
                  statement above by invoking the (className :: methodName). In this case, newJob is Job type.  */
//        optionalEmployer.ifPresent(newJob::setEmployer);

        /*  This condensed statement does NOT work b/c passing an Optional<Employer> type where it expects Employer type. */
//                newJob.setEmployer(employerRepository.findById(employerId));

        /*  Part 4.3 HomeController - Added a List of Skill type named someSkills and setting it to equal skillRepository
            at a certain ID (skills). The skills param is converted to an int based on the Integer wrapper in method param
            (List<Integer> skills). */
        /*  NOTE: I realized belatedly that the 2 lines of code were given to us in the directions....AFTER I spent
                   several hours figuring it out on my own! So I changed my variable to match the textbook. */
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        /*  Part 4.3 HomeController - Setting the various skills to attach to the newJob being added.  */
        newJob.setSkills(skillObjs);
        /* Part 3 End/No Directions - Had to add jobRepository to save newJob to job table in MySQL. */
        jobRepository.save(newJob);
        return "redirect:"; // returns to main index page for TechJobs templates/index.html w/ updated bullet list of Jobs by name.
    }



    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

       /* Part 3 At End/No Directions - Had to add code to hold an Optional Job instance based on jobRepository jobId input. */
        Optional optionalJob = jobRepository.findById(jobId);

       /* If the optionalJob with the jobId entered as a pathVariable is present/exists in the table, then that job data
            prints to screen via view.html. Otherwise, it re-routes user back to existing TechJobs hyper-linked list.  */
            if(optionalJob.isPresent()) {
            model.addAttribute("job", optionalJob.get());
            return "view";
        }
        /* This routes user back to main '/' TechJobs hyper-linked list of Jobs from '/view/{jobId}'.
        The first dot goes back to '/view'  and the second dot goes back to '/' (AKA localhost:8080) */
        return "redirect:..";
    }


}
