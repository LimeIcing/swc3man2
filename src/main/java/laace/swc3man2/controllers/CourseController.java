package laace.swc3man2.controllers;

import laace.swc3man2.models.CourseModel;
import laace.swc3man2.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/courses")
    public String coursePage(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("courses", courseRepository.findAll(new PageRequest(page,10)) );
        model.addAttribute("currentPage",page);
        return "courses";
    }

    @GetMapping("courses/findOne")
    @ResponseBody
    public CourseModel findOne(Integer id){
        return courseRepository.getOne(id);
    }
}
