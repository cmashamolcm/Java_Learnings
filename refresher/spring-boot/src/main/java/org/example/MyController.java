package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
// with basic spring mvc
@Controller
public class MyController {
    @RequestMapping("/home")
    //@ResponseBody - gives string. To show jsp page, this is not required.
    public String home(HttpServletRequest req){
        var name = req.getParameter("name");
        req.getSession().setAttribute("name", name);
        return "home.jsp";
    }

    @RequestMapping("/mvc")
    public ModelAndView homeWithMvc(@RequestParam("name") String name){
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", name);
        mv.setViewName("home.jsp");
        return mv;
    }

    @RequestMapping("/mvcWithModel")
    public ModelAndView homeWithMvc(Model model){
        ModelAndView mv = new ModelAndView();
        mv.addObject("obj", model);
        mv.setViewName("home.jsp");
        return mv;
    }
}
