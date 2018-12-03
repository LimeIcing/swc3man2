package laace.swc3man2.controllers;

import laace.swc3man2.repositories.TeacherRepository;
import laace.swc3man2.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping
    public String teacherPage(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("teachers", teacherService.listAll(page) );
        model.addAttribute("currentPage",page);
        return "teachers/index";
    }


    /*
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
    */
}
