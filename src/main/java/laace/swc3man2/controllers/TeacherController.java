package laace.swc3man2.controllers;

import laace.swc3man2.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping
    public String teachersPage(Model model) {
        model.addAttribute("teachers", teacherRepository.findAll() );
        return "teachers/index";
    }

    @GetMapping("/one/{id}")
    public String teacherDetailpage(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("teacher", teacherRepository.getOne(id));
        return "teachers/one";
    }
}
