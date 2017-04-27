package main.controllers;

import main.Exception.TaxiException;
import main.pojo.Status;
import main.pojo.Trip;
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
import java.util.List;

/*
 * Servler for passenger main page
 */
@Service
public class PassengerMainServlet extends HttpServlet{

    @Autowired
    private TripServiceInterface tripServiceInterface;// = new TripServiceImplementation();
    @Autowired
    private PassengerServiceInterface passegerServiceInterface ;//= new PassengerServiceImplementation();

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("logout")!=null){
            req.getSession().setAttribute("userLogin", null);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
        openPage(req, resp);
    }

    private void openPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        try {
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
        } catch (TaxiException e) {
            logger.error(e);
            TaxiException.redirect_to_error(e, req, resp);
            return;
        }

        getServletContext().getRequestDispatcher("/passengerMain.jsp")
                .forward(req, resp);
    }

    private void getCurrentOrders(HttpServletRequest req, HttpServletResponse resp, long passenger_id) throws ServletException, IOException, TaxiException {
        List<Trip> currentTrips;
        currentTrips = tripServiceInterface.readList(passenger_id, Status.Created);
        req.setAttribute("currentTrips", currentTrips);
    }

    private void getHistoricalOrders(HttpServletRequest req, HttpServletResponse resp, long passenger_id) throws ServletException, IOException, TaxiException {
        List<Trip> historicalTrips;
        historicalTrips = tripServiceInterface.readListExStatus(passenger_id, Status.Created);
        req.setAttribute("historicalTrips", historicalTrips);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String from = req.getParameter("from");
            String to = req.getParameter("to");
            int price = Integer.parseInt(req.getParameter("price"));
            String login = (String) req.getSession().getAttribute("userLogin");
            long passenger_id = 0;
            passenger_id = passegerServiceInterface.read(login).getUsersPkey().getUsersPkey();
            tripServiceInterface.createABrandNew(passenger_id, from, to, price);
        } catch (TaxiException e) {
            logger.error(e);
            TaxiException.redirect_to_error(e, req, resp);
            return;
        }
        openPage(req, resp);
    }
}
