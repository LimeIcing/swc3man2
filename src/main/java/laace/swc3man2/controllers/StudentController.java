package laace.swc3man2.controllers;

import laace.swc3man2.models.StudentModel;
import laace.swc3man2.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        StudentModel studentModel = studentService.findStudentByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + studentModel.getName() + " (" + studentModel.getEmail() + ")");
        modelAndView.addObject("userRole", studentModel.getRoles());

        modelAndView.addObject("studentMessage", "Content Available Only for Students with STUDENT Role");
        modelAndView.setViewName("/students/home");
        return modelAndView;
    }

    @GetMapping("/findOne")
    @ResponseBody
    public StudentModel findOne(Integer id){
        return studentService.findStudentById(id);
    }


}
