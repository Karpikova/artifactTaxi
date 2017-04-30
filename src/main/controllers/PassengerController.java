package main.controllers;

import main.Exception.TaxiException;
import main.pojo.Status;
import main.pojo.Trip;
import main.services.PassengerServiceInterface;
import main.services.TripServiceInterface;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller for passenger page
 */
@Controller
@SessionAttributes("loginSession")
public class PassengerController {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(PassengerController.class);

    @Autowired
    private TripServiceInterface tripServiceInterface;// = new TripServiceImplementation();
    @Autowired
    private PassengerServiceInterface passegerServiceInterface;//= new PassengerServiceImplementation();

    @RequestMapping(value = "/passenger", method = RequestMethod.GET)
    public ModelAndView sayWelcomePassenger(@ModelAttribute("loginSession") String loginSession,
                                            @RequestParam(value = "logout", required = false) String logout,
                                            @RequestParam(value = "trips_pkey", required = false) String trips_pkey,
                                            @RequestParam(value = "trips_pkey_to_delete", required = false) String trips_pkey_to_delete) {
        ModelAndView mav = new ModelAndView();

        if (logout != null) {
            mav.addObject("loginSession", "");
            mav.setViewName("redirect:/");
            return mav;
        }
        try {
            mav = openPage(mav, loginSession, trips_pkey, trips_pkey_to_delete);
            logger.info(mav.getViewName());
        } catch (Exception e) {
            mav.getModelMap().addAttribute("message", e.getMessage());
            mav.setViewName("redirect:error");
            return mav;
        }
        return mav;
    }

    private ModelAndView openPage(ModelAndView mav, String loginSession, String trips_pkey, String trips_pkey_to_delete) throws IOException, ServletException {
        try {
            if (trips_pkey != null) {
                mav.getModelMap().addAttribute("trips_pkey", trips_pkey);
                mav.setViewName("redirect:report");
                return mav;
            }
            if (trips_pkey_to_delete != null) {
                long pkey = Long.parseLong(trips_pkey_to_delete);
                tripServiceInterface.updateStatus(pkey, Status.Cancelled);
            }
            long passenger_id = passegerServiceInterface.read(loginSession).getUsersPkey().getUsersPkey();
            getCurrentOrders(mav, passenger_id);
            getHistoricalOrders(mav, passenger_id);
        } catch (TaxiException e) {
            mav.getModelMap().addAttribute("message", e.getMessage());
            mav.setViewName("redirect:error");
            return mav;
        }
        mav.setViewName("passenger");
        return mav;
    }

    private void getCurrentOrders(ModelAndView mav, long passenger_id) throws ServletException, IOException, TaxiException {
        List<Trip> currentTrips;
        currentTrips = tripServiceInterface.readList(passenger_id, Status.Created);
        mav.addObject("currentTrips", currentTrips);
    }

    private void getHistoricalOrders(ModelAndView mav, long passenger_id) throws ServletException, IOException, TaxiException {
        List<Trip> historicalTrips;
        historicalTrips = tripServiceInterface.readListExStatus(passenger_id, Status.Created);
        mav.addObject("historicalTrips", historicalTrips);
    }

    @RequestMapping(method = RequestMethod.POST) //ТУТ Я ВООБЩЕ НИЧЕГО НЕ НАПИСАЛА И НОРМ! Работает!
    public ModelAndView sendReport(@ModelAttribute("loginSession") String loginSession,
                                   @RequestParam(value = "message", required = false) String message,
                                   @RequestParam(value = "from", required = false) String from,
                                   @RequestParam(value = "to", required = false) String to,
                                   @RequestParam(value = "price", required = false) String price_string,
                                   @RequestParam(value = "trips_pkey", required = false) String trips_pkey){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("passenger");
        try {
            if (message!=null) {
                tripServiceInterface.updateReport(Long.valueOf(trips_pkey), message);
                return mav;
            }
            if (from!=null) {
                int price = Integer.parseInt(price_string);
                long passenger_id = passegerServiceInterface.read(loginSession).getUsersPkey().getUsersPkey();
                tripServiceInterface.createABrandNew(passenger_id, from, to, price);
                openPage(mav, loginSession, null, null);
            }
        } catch (Exception e) {
            mav.getModelMap().addAttribute("message", e.getMessage());
            mav.setViewName("redirect:error");
        }
        return mav;
    }

}
