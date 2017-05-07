package main.Exception;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*
 * It handles all app mistakes
 * 
 * Pervushow
 */
@ControllerAdvice
public class BaseController { //TODO сюда заходят не все. For instance, SQLException
    @ExceptionHandler(Exception.class)
    public ModelAndView resolveError(Exception ex){
        StackTraceElement[] stackTrace = ex.getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[0];
        String message = ex.getMessage() + " " + ex.getCause().toString();
        Logger logger = Logger.getLogger(stackTraceElement.getClassName());
        logger.error(message);
        ModelAndView model = new ModelAndView();
        model.setViewName("error");
        model.addObject("message", message);
        return model;
    }
}
