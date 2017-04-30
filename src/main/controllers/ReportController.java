package main.controllers;

import main.Exception.TaxiException;
import main.services.TripServiceInterface;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for report page
 */
@Controller
@SessionAttributes("loginSession")
@RequestMapping("/report")
public class ReportController {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(MainController.class);
    @Autowired
    private TripServiceInterface tripServiceInterface;// = new TripServiceImplementation();

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView sayReport(@ModelAttribute("loginSession") String loginSession,
                                  @RequestParam(value = "trips_pkey", required = false) String trips_pkey){
        ModelAndView mav = new ModelAndView();
        mav.addObject("trips_pkey", trips_pkey);
        mav.setViewName("report");
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST) //ВМЕСТО ЭТОГО ИДЕТ В POST passenger'a! прописано в jsp
    public ModelAndView sendReport(@ModelAttribute("loginSession") String loginSession,
                                    @RequestParam(value = "message", required = false) String message,
                                    @RequestParam(value = "trips_pkey", required = false) String trips_pkey){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:passenger");
        try {
            tripServiceInterface.updateReport(Long.valueOf(trips_pkey), message);
        } catch (TaxiException e) {
            mav.getModelMap().addAttribute("message", e.getMessage());
            mav.setViewName("redirect:error");
            return mav;
        }
        return mav;
    }
}
