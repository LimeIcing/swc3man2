package laace.swc3man2.controllers;

import laace.swc3man2.models.TeacherModel;
import laace.swc3man2.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        TeacherModel teacherModel = new TeacherModel();
        modelAndView.addObject("teacher", teacherModel);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewTeacher(@Valid TeacherModel teacherModel,
                                         BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        TeacherModel teacherExists = teacherService.findTeacherByEmail(teacherModel.getEmail());

        if (teacherExists != null) {
            bindingResult
                    .rejectValue("email", "error.teacher",
                            "There is already a teacher registered with the email provided");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            teacherService.saveTeacher(teacherModel);
            modelAndView.addObject("successMessage", "Teacher has been registered successfully");
            modelAndView.addObject("teacher", new TeacherModel());
            modelAndView.setViewName("registration");
        }

        return modelAndView;
    }
}