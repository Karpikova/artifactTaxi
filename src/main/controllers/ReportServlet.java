package main.controllers;

import main.Exception.TaxiException;
import main.services.TripServiceImplementation;
import main.services.TripServiceInterface;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * ${Classname}
 * 
 * Version 1.0 
 * 
 * 22.04.2017
 * 
 * Karpikova
 */
public class ReportServlet extends HttpServlet {

    static {
        PropertyConfigurator.configure(LoginServlet.class.getClassLoader()
                .getResource("log4j.properties"));
    }
    private static final org.apache.log4j.Logger logger = Logger.getLogger(ReportServlet.class);
    private static TripServiceInterface tripServiceInterface = new TripServiceImplementation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/report.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            tripServiceInterface.updateReport((Long) req.getSession().getAttribute("trips_pkey"), req.getParameter("mesage"));
            resp.sendRedirect(req.getContextPath() + "/passengerMain");
        } catch (TaxiException e) {
            logger.error(e);
            TaxiException.redirect_to_error(e, req, resp);
            return;
        }
    }
}
