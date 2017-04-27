package main.controllers;

import main.Exception.TaxiException;
import main.pojo.Status;
import main.pojo.Trip;
import main.services.DriverServiceImplementation;
import main.services.DriverServiceInterface;
import main.services.TripServiceImplementation;
import main.services.TripServiceInterface;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/*
 * Servler for driver main page
 */

public class DriverMainServlet extends HttpServlet {

    static {
        PropertyConfigurator.configure(LoginServlet.class.getClassLoader()
                .getResource("log4j.properties"));
    }
    private static final org.apache.log4j.Logger logger = Logger.getLogger(DriverMainServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.
                processInjectionBasedOnServletContext(this,
                        config.getServletContext());
    }

    @Autowired
    private TripServiceInterface tripServiceInterface;// = new TripServiceImplementation();
    @Autowired
    private DriverServiceInterface driverServiceInterface;// = new DriverServiceImplementation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("logout")!=null){
            req.getSession().setAttribute("userLogin", null);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
        String takeInWork = req.getParameter("trips_pkey");
        String login = (String) req.getSession().getAttribute("userLogin");
        try {
            long driver_id = driverServiceInterface.read(login).getUsersPkey().getUsersPkey();
            if (takeInWork != null)
            {
                tripServiceInterface.appointADriver(Integer.valueOf(takeInWork), driver_id, Status.Appointed);
            }
            getAllNewOrders(req, resp);
            getMyOrders(req, resp, driver_id);
        } catch (TaxiException e) {
            logger.error(e);
            TaxiException.redirect_to_error(e, req, resp);
            return;
        }

        getServletContext().getRequestDispatcher("/driverMain.jsp")
                .forward(req, resp);
    }

    private void getAllNewOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, TaxiException {
        List<Trip> allTrips;
        allTrips = tripServiceInterface.readList(Status.Created);

        req.setAttribute("allTrips", allTrips);
    }

    private void getMyOrders(HttpServletRequest req, HttpServletResponse resp, long driver_id) throws ServletException, IOException, TaxiException {
        List<Trip> myTrips;
        myTrips = tripServiceInterface.readList(driver_id);

        req.setAttribute("myTrips", myTrips);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
