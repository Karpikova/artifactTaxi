package main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.enterprise.inject.Model;

/**
 * Controller for error page
 */
@Controller
public class ErrorController {

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView sayError(@RequestParam(value = "message", required = false) String message) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", message);
        mav.setViewName("error");
        return mav;
    }

}
