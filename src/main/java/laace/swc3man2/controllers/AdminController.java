package laace.swc3man2.controllers;

import laace.swc3man2.models.TeacherModel;
import laace.swc3man2.services.CourseService;
import laace.swc3man2.services.StudentService;
import laace.swc3man2.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        TeacherModel teacherModel = teacherService.findTeacherByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + teacherModel.getName() + " (" + teacherModel.getEmail() + ")");
        modelAndView.addObject("userRole", teacherModel.getRoles());

        modelAndView.addObject("adminMessage", "Content Available Only for Teachers with Admin Role");
        modelAndView.setViewName("/admin/home");
        return modelAndView;
    }

    @GetMapping("/studentCourses")
    public String studentCoursePage(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("courses", courseService.listAll(page) );
        model.addAttribute("students", studentService.listAll(page) );
        model.addAttribute("currentPage",page);
        return "admin/studentCourses";
    }

    @GetMapping("/fetch")
    public String fetchFromAPI() {
        teacherService.fetchFromAPI();
        studentService.fetchFromAPI();
        courseService.fetchFromAPI();

        return "redirect:/";
    }
}
