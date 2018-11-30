package laace.swc3man2.controllers;

import laace.swc3man2.models.CourseModel;
import laace.swc3man2.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {


    @Autowired
    CourseService courseService;

    @GetMapping
    public String coursePage(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("courses", courseService.listAll(page) );
        model.addAttribute("currentPage",page);
        return "courses/index";
    }
    @GetMapping("/create")
    public String courseCreatePage(Model model){
        model.addAttribute("course", new CourseModel());
        return "courses/create";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute CourseModel courseModel)
    {
        courseService.addCourse(courseModel);
        return "redirect:/";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public CourseModel findOne(Integer id){
        return courseService.findCourseById(id);
    }
}
