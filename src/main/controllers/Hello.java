package main.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Hello {

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public ModelAndView welcomePage() {

        ModelAndView model = new ModelAndView();
         model.setViewName("login");
        return model;

    }

    @RequestMapping(value = { "/hello" }, method = RequestMethod.GET)
    public ModelAndView welcomePage12() {

        ModelAndView model = new ModelAndView();
        model.setViewName("hello");
        return model;

    }

    @RequestMapping(value = { "/error2" }, method = RequestMethod.GET)
    public ModelAndView welcomePage123() {

        ModelAndView model = new ModelAndView();
        model.setViewName("error");
        return model;

    }
    @RequestMapping(value = { "/" }, method = RequestMethod.POST)
    public ModelAndView welcomePage1() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is welcome page!");
        model.setViewName("redirect:passenger");
        return model;

    }

}