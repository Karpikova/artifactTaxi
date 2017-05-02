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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Main controller
 */
@Controller
@SessionAttributes("loginSession")
public class MainController {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(MainController.class);

    @Autowired
    private UserServiceInterface userServiceInterface;
    @Autowired
    private UserServiceInterface userService;// = new UserServiceImplementation();
    @Autowired
    private DriverServiceInterface driverService;// = new DriverServiceImplementation();
    @Autowired
    private PassengerServiceInterface passengerService;// = new PassengerServiceImplementation();
    private static AuthenticationManager am = new TaxiAuthenticationManager();

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView sayHello(@RequestParam(value = "fullName", required = false) String fullName,
                           @RequestParam(value = "loginNew", required = false) String loginNew,
                           @RequestParam(value = "passwordNew", required = false) String passwordNew,
                           @RequestParam(value = "carNumber", required = false) String carNumber,
                           @RequestParam(value = "carDescription", required = false) String carDescription,
                           @RequestParam(value = "passport", required = false) String passport,
                           @RequestParam(value = "birth", required = false) String birth) {
        ModelAndView mav = new ModelAndView();
        String asHwo = fullName;
        if (asHwo != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            Date birthDate = null;
            try {
                birthDate = format.parse(birth);
            } catch (ParseException e) {
                logger.error(e);
                mav.getModelMap().addAttribute("message", e.getMessage());
                mav.setViewName("redirect:error");
                return mav;
            }
            User user = null;
            try {
                user = new User(loginNew, passwordNew);
                user = userService.createBrandNew(user);
                if (user == null) {
                    mav.getModelMap().addAttribute("message", "User like this already exsists");
                    mav.setViewName("redirect:error");
                    return mav;
                }
                mav.addObject("success", true);
                if (carNumber!=null) { //it's a driver
                    Driver driver = new Driver(user, fullName, carNumber, carDescription, passport, birthDate);
                    driverService.create(driver);
                } else { //it's a passenger
                    Passenger passenger = new Passenger(user, fullName, birthDate);
                    passengerService.create(passenger);
                }
            } catch (TaxiException taxiException) {
                logger.error(taxiException);
                mav.getModelMap().addAttribute("message", taxiException.getMessage());
                mav.setViewName("redirect:error");
                return mav;
            }
        }
        mav.setViewName("login");

        return mav;
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "login", required = true) String login,
                              @RequestParam(value = "password", required = true) String password,
                              HttpServletRequest req) {

        ModelAndView mav = new ModelAndView();
        UserRole userRole = null;
        try {
            if (userService.auth(login, password) != null) {
                userRole = userService.getRole(login);
                if (userRole == UserRole.Driver){
                    mav.setViewName("redirect:driver");
                }
                else if (userRole == UserRole.Passenger) {
                    mav.setViewName("redirect:passenger");
                }
                else {
                    logger.error("No role " + userRole.toString());
                    mav.getModelMap().addAttribute("message", "No role");
                    mav.setViewName("redirect:error");
                }
            } else {
                mav.getModelMap().addAttribute("message", "No, you scred up. Wrong password");
                mav.setViewName("redirect:error");
            }
        } catch (TaxiException e) {
            logger.error(e.getMessage());
            mav.getModelMap().addAttribute("message", e.getMessage());
            mav.setViewName("redirect:error");
        }
        logger.info(mav.getViewName());
        mav.addObject("loginSession", login);

//        try {
//            Authentication request = new UsernamePasswordAuthenticationToken(login, password);
//            Authentication result = am.authenticate(request);
//            SecurityContextHolder.getContext().setAuthentication(result);
//            logger.info("555555555555555"+SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        } catch(AuthenticationException e) {
//            logger.error(e);
//            mav.getModelMap().addAttribute("message", e.getMessage());
//            mav.setViewName("redirect:error");
//            return mav;
//        }

        return mav;
    }

}
