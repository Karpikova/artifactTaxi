package main.controllers;

import main.Exception.TaxiException;
import main.pojo.Status;
import main.pojo.Trip;
import main.services.PassengerServiceInterface;
import main.services.TripServiceInterface;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Controller for passenger page
 */
@Controller
public class PassengerController {

    @Autowired
    private TripServiceInterface tripServiceInterface;
    @Autowired
    private PassengerServiceInterface passegerServiceInterface;

    /**
     * The firts passenger page handler
     * @param logout
     * @param trips_pkey
     * @param trips_pkey_to_delete
     * @return
     */
    @RequestMapping(value = "/passenger", method = RequestMethod.GET)
    public ModelAndView sayWelcomePassenger(@RequestParam(value = "logout", required = false) String logout,
                                            @RequestParam(value = "trips_pkey", required = false) String trips_pkey,
                                            @RequestParam(value = "trips_pkey_to_delete", required = false) String trips_pkey_to_delete) throws Exception {
        ModelAndView mav = new ModelAndView();
        if (logout != null) {
            mav.setViewName("redirect:/");
            return mav;
        }
        mav = openPage(mav, trips_pkey, trips_pkey_to_delete);
        return mav;
    }

    /**
     * Open passenger page handler
     * @param mav
     * @param trips_pkey
     * @param trips_pkey_to_delete
     * @return
     * @throws IOException
     * @throws ServletException
     */
    private ModelAndView openPage(ModelAndView mav, String trips_pkey, String trips_pkey_to_delete) throws Exception {
        if (trips_pkey != null) {
            mav.getModelMap().addAttribute("trips_pkey", trips_pkey);
            mav.setViewName("redirect:report");
            return mav;
        }
        if (trips_pkey_to_delete != null) {
            long pkey = Long.parseLong(trips_pkey_to_delete);
            tripServiceInterface.updateStatus(pkey, Status.Cancelled);
        }
        String login = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        long passenger_id = passegerServiceInterface.read(login).getUsersPkey().getUsersPkey();
        getCurrentOrders(mav, passenger_id);
        getHistoricalOrders(mav, passenger_id);
        mav.setViewName("passenger");
        return mav;
    }

    /**
     * Gets current passenger's orders
     * @param mav
     * @param passenger_id
     * @throws ServletException
     * @throws IOException
     * @throws TaxiException
     */
    private void getCurrentOrders(ModelAndView mav, long passenger_id) throws ServletException, IOException, TaxiException, InterruptedException, ExecutionException, SQLException {
        List<Trip> currentTrips;
        currentTrips = tripServiceInterface.readList(passenger_id, Status.Created);
        currentTrips.addAll(tripServiceInterface.readList(passenger_id, Status.Appointed));
        mav.addObject("currentTrips", currentTrips);
    }

    /**
     * Gets historical passenger's orders
     * @param mav
     * @param passenger_id
     * @throws ServletException
     * @throws IOException
     * @throws TaxiException
     */
    private void getHistoricalOrders(ModelAndView mav, long passenger_id) throws ServletException, IOException, TaxiException, InterruptedException, ExecutionException, SQLException {
        List<Trip> historicalTrips;
        historicalTrips = tripServiceInterface.readHistoryListOfPassenger(passenger_id);
        mav.addObject("historicalTrips", historicalTrips);
    }

    /**
     * Sends passenger's respond about executed trip
     * @param message
     * @param from
     * @param to
     * @param price_string
     * @param trips_pkey
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView sendReport(@RequestParam(value = "message", required = false) String message,
                                   @RequestParam(value = "from", required = false) String from,
                                   @RequestParam(value = "to", required = false) String to,
                                   @RequestParam(value = "price", required = false) String price_string,
                                   @RequestParam(value = "trips_pkey", required = false) String trips_pkey) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("passenger");
        if (message != null) {
            tripServiceInterface.updateReport(Long.valueOf(trips_pkey), message);
            return mav;
        }
        String login = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        if (from != null) {
            int price = Integer.parseInt(price_string);
            long passenger_id = passegerServiceInterface.read(login).getUsersPkey().getUsersPkey();
            tripServiceInterface.createABrandNew(passenger_id, from, to, price);
            openPage(mav, null, null);
        }
        return mav;
    }

}
