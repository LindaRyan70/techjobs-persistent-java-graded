package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    /* Part 2.1.1: Controllers - Add a private field of EmployerRepository type called employerRepository to
                                EmployerController and give this field an @Autowired annotation. */
    @Autowired
    private EmployerRepository employerRepository;

    /* Part 2.1.2: Controllers - Added an index method that responds at /employers with a list of all employers in database.
                Returned template employers/index. Got model attribute "employers" from the index.html and model value
                "employerRepository" from field variable above. Used findAll() Iterable type method to get all entities
                saved to database. */
    @GetMapping("") // Test required the empty ("") to pass by showing the path this method responds from (but I thought it defaults to root.) //
    public String index(Model model) {
        model.addAttribute("employers", employerRepository.findAll());
        return "employers/index";
    }

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    /* Part 2.1.3: Controllers - Add code to save a valid Employer object below using employerRepository and .save() method.
                    Pass in the newEmployer variable from method param list above.
                    [NOTE: Using employerRepository.save(new Employer()) also works, but not needed since have a variable.] */
    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer, Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "employers/add";
        }
        employerRepository.save(newEmployer); // Added code line here to save valid Employer objects to the database, using param variable above. //
        return "redirect:";
    }

    /* Part 2.1.4: Controllers - Replaced the null initialization line below to use employerRepository to interact with our
                    database, along with .findById() method passing employerId variable from controller method param list above */
    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

        Optional optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("employer", employer);
            return "employers/view";
        } else {
            return "redirect:../";
        }
    }
}
