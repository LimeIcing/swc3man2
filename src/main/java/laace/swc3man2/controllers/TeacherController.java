package laace.swc3man2.controllers;

import laace.swc3man2.models.TeacherModel;
import laace.swc3man2.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/findOne")
    @ResponseBody
    public TeacherModel findOne(Integer id){
        return teacherService.findTeacherById(id);
    }
}