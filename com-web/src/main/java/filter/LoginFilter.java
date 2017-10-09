package filter;

import domain.User;
import org.apache.log4j.Logger;
import servlets.LoginServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Serg on 01.08.2017.
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/home"})
public class LoginFilter implements Filter {

    static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        Object o = session.getAttribute("user");

        if(o == null && !(o instanceof User)){
            LOGGER.info("User is not initialised");
            ((HttpServletResponse)resp).sendRedirect("/?notAuthorized=Please login");
        }else{
            User user = (User) o;
            LOGGER.info("LoginFilter: User tries to get access with login: " + user.getLogin());
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
