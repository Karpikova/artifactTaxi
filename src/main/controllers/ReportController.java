package main.controllers;

import main.Exception.TaxiException;
import main.services.TripServiceInterface;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

/**
 * Controller for report page
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private TripServiceInterface tripServiceInterface;// = new TripServiceImplementation();//TODO а как бы так быстро определять

    /**
     * To open report page
     * @param trips_pkey
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView sayReport(@RequestParam(value = "trips_pkey", required = false) String trips_pkey){
        ModelAndView mav = new ModelAndView();
        mav.addObject("trips_pkey", trips_pkey);
        mav.setViewName("report");
        return mav;
    }

    /**
     * Sends passenger's report about executed trip
     * @param message
     * @param trips_pkey
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView sendReport(@RequestParam(value = "message", required = false) String message,
                                    @RequestParam(value = "trips_pkey", required = false) String trips_pkey) throws TaxiException, InterruptedException, ExecutionException, SQLException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:passenger");
        tripServiceInterface.updateReport(Long.valueOf(trips_pkey), message);
        return mav;
    }
}
