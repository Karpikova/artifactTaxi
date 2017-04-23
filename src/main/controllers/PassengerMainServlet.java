package main.controllers;

import main.pojo.Status;
import main.pojo.Trip;
import main.services.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
 * Servler for passenger main page
 */
public class PassengerMainServlet extends HttpServlet{
    private static TripServiceInterface tripServiceInterface = new TripServiceImplementation();
    private static PassengerServiceInterface passegerServiceInterface = new PassengerServiceImplementation();

    static {
        PropertyConfigurator.configure(LoginServlet.class.getClassLoader()
                .getResource("log4j.properties"));
    }
    private static final org.apache.log4j.Logger logger = Logger.getLogger(DriverMainServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        openPage(req, resp);
    }

    private void openPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String trips_pkey = req.getParameter("trips_pkey");
        String trips_pkey_to_delete = req.getParameter("trips_pkey_to_delete");
        if (trips_pkey != null) {
            long pkey = Long.parseLong(req.getParameter("trips_pkey"));
            req.getSession().setAttribute("trips_pkey", pkey);
            resp.sendRedirect(req.getContextPath() + "/passengerMain/report");
            return;
        }
        if (trips_pkey_to_delete != null) {
            long pkey = Long.parseLong(req.getParameter("trips_pkey_to_delete"));
            tripServiceInterface.updateStatus(pkey, Status.Cancelled);
        }
        String login = (String) req.getSession().getAttribute("userLogin");
        long passenger_id = passegerServiceInterface.read(login).getUsersPkey().getUsersPkey();

        getCurrentOrders(req, resp, passenger_id);
        getHistoricalOrders(req, resp, passenger_id);

        getServletContext().getRequestDispatcher("/passengerMain.jsp")
                .forward(req, resp);
    }

    private void getCurrentOrders(HttpServletRequest req, HttpServletResponse resp, long passenger_id) throws ServletException, IOException {
        List<Trip> currentTrips;
        currentTrips = tripServiceInterface.readList(passenger_id, Status.Created);
        req.setAttribute("currentTrips", currentTrips);
    }

    private void getHistoricalOrders(HttpServletRequest req, HttpServletResponse resp, long passenger_id) throws ServletException, IOException {
        List<Trip> historicalTrips;
        historicalTrips = tripServiceInterface.readListExStatus(passenger_id, Status.Created);
        req.setAttribute("historicalTrips", historicalTrips);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        int price = Integer.parseInt(req.getParameter("price"));
        String login = (String) req.getSession().getAttribute("userLogin");
        long passenger_id = passegerServiceInterface.read(login).getUsersPkey().getUsersPkey();
        tripServiceInterface.createABrandNew(passenger_id, from, to, price);

        openPage(req, resp);
    }
}
