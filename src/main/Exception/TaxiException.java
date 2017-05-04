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
}
