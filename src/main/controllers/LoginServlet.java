package main.controllers;

import main.pojo.UserRole;
import main.services.UserServiceImplementation;
import main.services.UserServiceInterface;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;;

/*
 * Servlet for login page
 */
public class LoginServlet extends HttpServlet {

    private static UserServiceInterface userService = new UserServiceImplementation();

    static {
        PropertyConfigurator.configure(LoginServlet.class.getClassLoader()
                .getResource("log4j.properties"));
    }
    private static final org.apache.log4j.Logger logger = Logger.getLogger(DriverMainServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String asHwo = req.getParameter("toAuth");
        logger.info("iii");
//        if (asHwo != null) {
//            String login = req.getParameter("login");
//            String password = req.getParameter("password");
//            String fullName = req.getParameter("fullName");
//            String birth = req.getParameter("birth");
//            User user = new User(login, password);
//            user = userService.createBrandNew(user);
//            logger.info(user.getLogin());
//            logger.info(user.getRegistrationDate());
//            logger.info(user.getUserPassword());
//        }
        try {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
        catch (IOException e) {
            logger.info(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logIn(req, resp);
    }

    private void logIn(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

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
    }
}
