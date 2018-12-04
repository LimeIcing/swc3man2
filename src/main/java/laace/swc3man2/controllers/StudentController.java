package laace.swc3man2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students/")
public class StudentController
{
    @GetMapping
    public String studentPage()
    {
        //model til courses
        //requestparam til at se sider
    }
}
