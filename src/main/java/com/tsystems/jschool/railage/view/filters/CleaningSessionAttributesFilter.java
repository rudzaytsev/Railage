package com.tsystems.jschool.railage.view.filters;

import com.tsystems.jschool.railage.view.Utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by rudolph on 04.07.15.
 */
public class CleaningSessionAttributesFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        Boolean logged = (Boolean) session.getAttribute(Utils.LOGGED_SESSION_ATTRIB);
        session.removeAttribute(Utils.IS_VALIDATION_ERR);
        session.removeAttribute(Utils.VALIDATION_ERROR_MSG);
        session.removeAttribute(Utils.SUCCESS);
        session.removeAttribute(Utils.INFO_MSG);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
