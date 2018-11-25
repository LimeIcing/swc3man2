package laace.swc3man2.controllers;

import laace.swc3man2.interfaces.TeacherRepositoryInterface;
import laace.swc3man2.models.TeacherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TeacherController {

    @Autowired
    TeacherRepositoryInterface TRI;

    @GetMapping("/teachers")
    public String teachersPage(Model model) {
        model.addAttribute("teachers", TRI.findAll() );
        return "teachers";
    }

    @GetMapping("/teacher/{id}")
    public String teacherDetailpage(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("teacher", TRI.getOne(id));
        return "teacher";
    }
}
