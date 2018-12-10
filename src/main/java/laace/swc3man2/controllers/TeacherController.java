package laace.swc3man2.controllers;

import laace.swc3man2.models.TeacherModel;
import laace.swc3man2.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.security.Security;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Resource
    public boolean isAdmin = teacherService.isAdmin(teacherService.findTeacherByEmail(
            SecurityContextHolder.getContext().getAuthentication().getName()));

    public static UserDetails currentUserDetails(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication  != null) {
            Object object = authentication.getPrincipal();
            return object instanceof UserDetails? (UserDetails)  object : null;
        }
        return null;
    }



    @GetMapping
    public String teacherPage(Model model, @RequestParam(defaultValue = "0") int page) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        TeacherModel teacherModel = teacherService.findTeacherByEmail(auth.getName());
        model.addAttribute("teachers", teacherService.listAll(page) );
        model.addAttribute("currentPage",page);
        model.addAttribute("userRole", currentUserDetails().getAuthorities());
        model.addAttribute("userDetails", currentUserDetails());
        model.addAttribute("adminStatus", teacherService.isAdmin(teacherModel));
        return "teachers/index";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public TeacherModel findOne(Integer id){
        return teacherService.findTeacherById(id);
    }
}