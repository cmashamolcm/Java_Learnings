package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeveloperController {

    @Autowired
    DeveloperRepo repo;

    @RequestMapping("/dev")
    public ModelAndView saveDeveloper(DevModel model){
        var mv = new ModelAndView();
        mv.setViewName("dev.jsp");
        mv.addObject("obj", repo.save(model));
        return mv;
    }

    @RequestMapping("/id")
    public ModelAndView getDeveloper(@RequestParam(required = false) Long id){// controller expects to return  ModelAndView or jsp file name always. Cannot send data without @ResponseBody
        var mv = new ModelAndView();
        mv.setViewName("dev.jsp");
        mv.addObject("obj", repo.findById(id).get());
        System.out.println(repo.getDataByNameWithQuery("asha"));
        return mv;
    }
}
