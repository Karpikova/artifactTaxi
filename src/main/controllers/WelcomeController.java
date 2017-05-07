package main.controllers;

import main.Exception.TaxiException;
import main.pojo.Driver;
import main.pojo.Passenger;
import main.pojo.User;
import main.pojo.UserRole;
import main.services.DriverServiceInterface;
import main.services.PassengerServiceInterface;
import main.services.UserServiceInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * The first page controller
 */
@Controller
public class WelcomeController {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(WelcomeController.class);

    @Autowired
    private UserServiceInterface userService;
    @Autowired
    private DriverServiceInterface driverService;
    @Autowired
    private PassengerServiceInterface passengerService;

    /**
     * The first page
     * @param fullName
     * @param loginNew
     * @param passwordNew
     * @param carNumber
     * @param carDescription
     * @param passport
     * @param birth
     * @param toAuth
     * @return
     * @throws TaxiException
     */
    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public ModelAndView welcomePage(@RequestParam(value = "fullName", required = false) String fullName,
                                    @RequestParam(value = "loginNew", required = false) String loginNew,
                                    @RequestParam(value = "passwordNew", required = false) String passwordNew,
                                    @RequestParam(value = "carNumber", required = false) String carNumber,
                                    @RequestParam(value = "carDescription", required = false) String carDescription,
                                    @RequestParam(value = "passport", required = false) String passport,
                                    @RequestParam(value = "birth", required = false) String birth,
                                    @RequestParam(value = "authError", required = false) boolean authError,
                                    @RequestParam(value = "doubleUser", required = false) boolean doubleUser,
                                    @RequestParam(value = "toAuth", required = false) String toAuth) throws TaxiException, ParseException, InterruptedException, ExecutionException, SQLException, IOException {
       logger.warn("123warn");
       logger.debug("123debug");
        ModelAndView mav = new ModelAndView();
        mav.addObject("authError", authError);
        mav.addObject("doubleUser", doubleUser);
        mav.setViewName("login");
        String asHwo = fullName;
        if (asHwo!=null){
            mav = registration(fullName, loginNew, passwordNew, carNumber, carDescription, passport, birth, mav);
        }
        return mav;
    }

    /**
     * Login controller
     * @return
     * @throws Exception
     */
    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public ModelAndView login(
                            //    @RequestParam(value = "j_username", required = true) String login, //WTH TODO
                           //   @RequestParam(value = "j_password", required = true) String password,

                            ) throws Exception {
        ModelAndView mav = new ModelAndView();
        String login = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        mav = choosePage(mav, login);
        return mav;
    }

    /**
     * Registration handler for passengers and drivers
     * @param fullName
     * @param loginNew
     * @param passwordNew
     * @param carNumber
     * @param carDescription
     * @param passport
     * @param birth
     * @param mav
     * @return
     * @throws TaxiException
     */
    private ModelAndView registration(String fullName, String loginNew, String passwordNew, String carNumber,
                                      String carDescription,String passport, String birth, ModelAndView mav) throws TaxiException, ParseException, IOException, InterruptedException, ExecutionException, SQLException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date birthDate = format.parse(birth);
        User user = new User(loginNew, passwordNew);
        user = userService.createBrandNew(user);
        if (user == null) {
            mav.getModelMap().addAttribute("doubleUser", true);
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.addObject("success", true);
        if (carNumber != null) { //it's a driver
            Driver driver = new Driver(user, fullName, carNumber, carDescription, passport, birthDate);
            driverService.create(driver);
        } else { //it's a passenger
            Passenger passenger = new Passenger(user, fullName, birthDate);
            passengerService.create(passenger);
        }
        mav.setViewName("login");
        return mav;
    }

    /**
     * Auth handler
     * @param mav
     * @param login
     * @return
     * @throws TaxiException
     */
    private ModelAndView choosePage(ModelAndView mav, String login) throws Exception {
        UserRole userRole = userService.getRole(login);
        if (userRole == UserRole.Driver) {
            mav.setViewName("redirect:driver");
        } else if (userRole == UserRole.Passenger) {
            mav.setViewName("redirect:passenger");
        } else {
            logger.error("No role " + userRole.toString());
            mav.getModelMap().addAttribute("message", "No role");
            mav.setViewName("redirect:error");
        }
        return mav;
    }

    /**
     * Wrong choosePage handler
     * @return
     */
    @RequestMapping(value = { "/errorAuth" }, method = RequestMethod.GET)
    public ModelAndView welcomeError() {
        ModelAndView model = new ModelAndView();
        model.addObject("message", "Auth problem");
        model.setViewName("error");
        return model;
    }

    /**
     * Wrong choosePage handler
     * @return
     */
    @RequestMapping(value = { "/accessDenied" }, method = RequestMethod.GET)
    public ModelAndView welcomeAccessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("accessDenied");
        return model;
    }
}