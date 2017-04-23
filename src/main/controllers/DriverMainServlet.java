package main.controllers;

import main.pojo.Status;
import main.pojo.Trip;
import main.services.DriverServiceImplementation;
import main.services.DriverServiceInterface;
import main.services.TripServiceImplementation;
import main.services.TripServiceInterface;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;

/*
 * Servler for driver main page
 */
public class DriverMainServlet extends HttpServlet {

    private static TripServiceInterface tripServiceInterface = new TripServiceImplementation();
    private static DriverServiceInterface driverServiceInterface = new DriverServiceImplementation();

    static {
        PropertyConfigurator.configure(LoginServlet.class.getClassLoader()
                .getResource("log4j.properties"));
    }
    private static final org.apache.log4j.Logger logger = Logger.getLogger(DriverMainServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String takeInWork = req.getParameter("trips_pkey");
        List<Trip> allTrips = null;
        String login = (String) req.getSession().getAttribute("userLogin");
        long driver_id = driverServiceInterface.read(login).getUsersPkey().getUsersPkey();
        if (takeInWork != null)
        {
            tripServiceInterface.appointADriver(Integer.valueOf(takeInWork), driver_id, Status.Appointed);
        }
        getAllNewOrders(req, resp);
        getMyOrders(req, resp, driver_id);

        getServletContext().getRequestDispatcher("/driverMain.jsp")
                .forward(req, resp);
    }

    private void getAllNewOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Trip> allTrips;
        allTrips = tripServiceInterface.readList(Status.Created);

        req.setAttribute("allTrips", allTrips);
    }

    private void getMyOrders(HttpServletRequest req, HttpServletResponse resp, long driver_id) throws ServletException, IOException {
        List<Trip> myTrips;
        myTrips = tripServiceInterface.readList(driver_id);

        req.setAttribute("myTrips", myTrips);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}