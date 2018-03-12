package com.luv2code.springdemo.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Bean
    public ConversionService conversionService() {
        return new DefaultConversionService();
    }

    @Value("#{countryOptions}")
    private Map<String, String> countryOptions;

    @Value("#{valuesLists.languages}")
    private String[] languageOptions;

    @Value("#{valuesLists.operatingSystems}")
    private String[] operatingSystemsOptions;


    @RequestMapping("/showForm")
    public String showForm(Model model){

        // create a new student object
        Student student = new Student();

        // add student object to the model
        model.addAttribute("student", student);

        // add the country options to the model
        model.addAttribute("countryOptions", countryOptions);

        // add the language options to the model
        model.addAttribute("languageOptions", languageOptions);

        // add the OS options to the model
        model.addAttribute("operatingSystemsOptions", operatingSystemsOptions);

        return "student-form";
    }

    @RequestMapping("/processForm")
    public String processForm(@ModelAttribute("student") Student student) {

        // log the input data
        System.out.println("Student: " + student.getFirstName() + " " + student.getLastName());

        return "student-confirmation";
    }

}
