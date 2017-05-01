package main.controllers.filtres;

import main.dao.UserImplementation;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * ${Classname}
 * 
 * Version 1.0 
 * 
 * 01.05.2017
 * 
 * Karpikova
 */
public class GenFB extends GenericFilterBean {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(GenFB.class);

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String userLogin = (String) ((HttpServletRequest) servletRequest)
                .getSession().getAttribute("userLogin");

        SecurityContext context = SecurityContextHolder.getContext();

        Authentication authentication = context.getAuthentication();

       //logger.info("1212121212 0 + user" + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        logger.info("1212121212 0 + user" + authentication);
        if (userLogin != "") {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse)
                    .sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/");
        }
    }


}