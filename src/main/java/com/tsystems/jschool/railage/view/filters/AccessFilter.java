package com.tsystems.jschool.railage.view.filters;

import com.tsystems.jschool.railage.view.Pages;
import com.tsystems.jschool.railage.view.Utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by rudolph on 26.06.15.
 */
public class AccessFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpSession session = ((HttpServletRequest) req).getSession();
        Boolean logged = (Boolean) session.getAttribute(Utils.LOGGED_SESSION_ATTRIB);
        if ( logged != null && logged){

            Boolean isEmployee = (Boolean) session.getAttribute(Utils.IS_EMPLOYEE_SESSION_ATTRIB);
            String uri = ((HttpServletRequest) req).getRequestURI();
            boolean accessToRouteBuilder = uri.contains(Pages.ROUTE_BULDER);
            if (!isEmployee && accessToRouteBuilder ) {
                (( HttpServletResponse ) resp).sendRedirect(Pages.INDEX);
                return;
            }
            chain.doFilter(req, resp);
        }
        else {
            (( HttpServletResponse ) resp).sendRedirect(Pages.INDEX);
            return;
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
