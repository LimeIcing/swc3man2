package laace.swc3man2.controllers;

import laace.swc3man2.models.StudentModel;
import laace.swc3man2.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public String studentPage(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("students", studentService.listAll(page) );
        model.addAttribute("currentPage",page);
        return "students/index";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public StudentModel findOne(Integer id){
        return studentService.findStudentById(id);
    }


}
