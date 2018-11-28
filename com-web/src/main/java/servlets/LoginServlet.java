package servlets;

import domain.User;
import org.apache.log4j.Logger;
import util.StringValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Serg on 23.08.2017.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        LOGGER.info("User tries to get access with login: " + login);

        List<String> errors = new ArrayList<>();

        if (StringValidator.isEmpty(login)) {
            errors.add("Login can't be empty");
        } else {
          /*  if (!StringValidator.isEmailValid(login)) {
                errors.add("Please specify valid login (email)");
            }*/
        }

        if (StringValidator.isEmpty(password)) {
            errors.add("Password can't be empty");
        } else {
            if (!StringValidator.isPasswordValid(password)) {
                errors.add("Password must have 3-10 characters");
            }
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
        } else {

            User user = new User();//TODO: take User from DB

            if (user != null) {
                if (user.getLogin().equals(login) & user.getPassword().equals(password)) {

                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                    //response.addCookie(new Cookie("sid", session.getId()));//TODO: for what do we add cookie
                    response.sendRedirect("home");
                } else {
                    errors.add("User not found");
                    request.setAttribute("errors", errors);
                    request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
                }
            } else {
                errors.add("User not found");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object o = request.getSession().getAttribute("user");
        if(o != null && o instanceof User){
            response.sendRedirect("home");
        }else{
            String notAuthorized = request.getParameter("notAuthorized");
            if(notAuthorized != null)
                request.setAttribute("errors", Arrays.asList(new String[]{notAuthorized}));
            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
        }

    }
}
