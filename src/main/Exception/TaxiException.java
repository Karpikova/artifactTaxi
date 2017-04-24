package main.Exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * ${Classname}
 * 
 * Version 1.0 
 * 
 * 23.04.2017
 * 
 * Karpikova
 */
public class TaxiException extends Exception {
    public TaxiException(String message){
        super(message);
    }

    /**
     * To open error message
     * @param taxiException
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     */
    public static void redirect_to_error(Exception taxiException, HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute("message", taxiException.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
