package main.controllers;

import main.Exception.TaxiException;
import main.pojo.Status;
import main.pojo.Trip;
import main.services.DriverServiceInterface;
import main.services.TripServiceInterface;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * Controller for driver
 */
@Controller
public class DriverController {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(DriverController.class);

    @Autowired
    private TripServiceInterface tripServiceInterface;// = new TripServiceImplementation();
    @Autowired
    private DriverServiceInterface driverServiceInterface;// = new DriverServiceImplementation();

    /**
     * Welcome driver handler
     * @param logout
     * @param trips_pkey_takeIn
     * @param trips_pkey_done
     * @return
     */
    @RequestMapping(value = "/driver", method = RequestMethod.GET)
    public ModelAndView sayWelcomeDriver(@RequestParam(value = "logout", required = false) String logout,
                                         @RequestParam(value = "trips_pkey_takeIn", required = false) String trips_pkey_takeIn,
                                         @RequestParam(value = "trips_pkey_done", required = false) String trips_pkey_done) {
        ModelAndView mav = new ModelAndView();
        if (logout!=null){
            mav.addObject("loginSession", "");
            mav.setViewName("redirect:/");
            return mav;
        }
        String login = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        try {
            mav.setViewName("driver");
            long driver_id = driverServiceInterface.read(login).getUsersPkey().getUsersPkey();
            if (trips_pkey_takeIn != null)
            {
                tripServiceInterface.appointADriver(Integer.valueOf(trips_pkey_takeIn), driver_id, Status.Appointed);
            }
            if (trips_pkey_done != null)
            {
                tripServiceInterface.updateStatus(Integer.valueOf(trips_pkey_done), Status.Excecuted);
            }
            getMyOrders(mav, driver_id);
            getAllNewOrders(mav);
        } catch (Exception e) {
            mav.getModelMap().addAttribute("message", e.getMessage());
            mav.setViewName("redirect:error");
            return mav;
        }

        return mav;
    }

    /**
     * Gets all driver's orders
     * @param mav
     * @param driver_id
     * @throws ServletException
     * @throws IOException
     * @throws TaxiException
     */
    private void getMyOrders(ModelAndView mav, long driver_id) throws ServletException, IOException, TaxiException {
        List<Trip> myTrips;
        myTrips = tripServiceInterface.readList(driver_id);
        mav.addObject("myTrips", myTrips);
    }

    /**
     * Gets all new aorders, that can be taken by any driver
     * @param mav
     * @throws ServletException
     * @throws IOException
     * @throws TaxiException
     */
    private void getAllNewOrders(ModelAndView mav) throws ServletException, IOException, TaxiException {
        List<Trip> allTrips;
        allTrips = tripServiceInterface.readList(Status.Created);
        mav.addObject("allTrips", allTrips);
    }

}
