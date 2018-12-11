package laace.swc3man2.controllers;

import laace.swc3man2.models.CourseModel;
import laace.swc3man2.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
        model.addAttribute("courseModel", new CourseModel());

        return "courses/create";
    }

    @GetMapping("/edit")
    public CourseModel courseEditPage(int id){
        return courseService.findCourseById(id);
    }

    @PostMapping("/edit/save/")
    public String saveEditCourse (@ModelAttribute CourseModel courseModel, int id) {
        courseService.editCourse(courseModel, id);

        return "redirect:/courses/";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute CourseModel courseModel) {
        courseService.addCourse(courseModel);

        return "redirect:/courses/";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public CourseModel findOne(Integer id){
        return courseService.findCourseById(id);
    }

    /*@GetMapping("/findAllByName")
    @ResponseBody
    public List<CourseModel> findAllByName(String searchQuery) {
        return courseService.findCoursesByName(searchQuery);
    }*/
}