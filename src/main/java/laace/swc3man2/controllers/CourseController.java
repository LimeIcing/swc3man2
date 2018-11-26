package laace.swc3man2.controllers;

import laace.swc3man2.interfaces.CourseRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController
{
    @Autowired
    CourseRepositoryInterface CRI;

    @GetMapping("/courses")
    public String coursePage(Model model) {
        model.addAttribute("courses", CRI.findAll() );
        return "courses";
    }
}
