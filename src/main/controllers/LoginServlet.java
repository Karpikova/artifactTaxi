package main.controllers;

import main.Exception.ExceptionDBStructure;
import main.Exception.TaxiException;
import main.pojo.Driver;
import main.pojo.Passenger;
import main.pojo.User;
import main.pojo.UserRole;
import main.services.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;;

/*
 * Servlet for login page
 */
//@Service
public class LoginServlet extends HttpServlet {

    @Autowired
    private UserServiceInterface userService; //= new UserServiceImplementation();
    @Autowired
    private DriverServiceInterface driverService;// = new DriverServiceImplementation();
    @Autowired
    private PassengerServiceInterface passengerService;// = new PassengerServiceImplementation();

    static {
        PropertyConfigurator.configure(LoginServlet.class.getClassLoader()
                .getResource("log4j.properties"));
    }
    private static final org.apache.log4j.Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.
                processInjectionBasedOnServletContext(this,
                        config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String asHwo = req.getParameter("fullName");
        if (asHwo != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            String login = req.getParameter("loginNew");
            String password = req.getParameter("passwordNew");
            String fullName = req.getParameter("fullName");
            String carNumber = req.getParameter("carNumber");
            String carDescription = req.getParameter("carDescription");
            String passport = req.getParameter("passport");
            Date birth = null;
            try {
                birth = format.parse(req.getParameter("birth"));
            } catch (ParseException e) {
                logger.error(e);
                TaxiException.redirect_to_error(e, req, resp);
                return;
            }
            User user = null;
            try {
                user = new User(login, password);
                user = userService.createBrandNew(user);
                if (user == null) {
                    Exception ex = new ExceptionDBStructure("User like this already exsists");
                    TaxiException.redirect_to_error(ex, req, resp);
                    return;
                }
                req.setAttribute("success", true);
                if (req.getParameter("carNumber")!=null) { //it's a driver
                    Driver driver = new Driver(user, fullName, carNumber, carDescription, passport, birth);
                    driverService.create(driver);
                } else { //it's a passenger
                    Passenger passenger = new Passenger(user, fullName, birth);
                    passengerService.create(passenger);
                }
            } catch (TaxiException taxiException) {
                logger.error(taxiException);
                TaxiException.redirect_to_error(taxiException, req, resp);
                return;
            }
        }
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logIn(req, resp);
    }

    private void logIn(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            if (userService.auth(login, password) != null) {
                req.getSession().setAttribute("userLogin", login);
                UserRole userRole = userService.getRole(login);
                if (userRole == UserRole.Driver){
                    resp.sendRedirect(req.getContextPath() + "/driverMain");
                }
                else if (userRole == UserRole.Passenger) {
                    resp.sendRedirect(req.getContextPath() + "/passengerMain");
                }
                else {
                    resp.sendRedirect(req.getContextPath() + "/errorNoRole");
                }
                req.getSession().setAttribute("role", userRole);
            }
            else {
                resp.sendRedirect(req.getContextPath() + "/error");
            }
        } catch (TaxiException e) {
            logger.error(e);
            TaxiException.redirect_to_error(e, req, resp);
            return;
        }
    }
}
