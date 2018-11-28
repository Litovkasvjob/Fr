package servlets;

import domain.Load;
import org.apache.log4j.Logger;
import util.DataReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by Serg on 26.08.2017.
 */
@WebServlet(name = "FrictionOnlineServlet", urlPatterns = "/frictionOnline")
public class FrictionOnlineServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(FrictionOnlineServlet.class.getName());



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOGGER.info("FrictionOnlineServlet try to calculate f");

        HttpSession session = request.getSession();

        Map<Integer, Double> friction = (Map<Integer, Double>) session.getAttribute("frictions");

        request.setAttribute("frictions", friction);

        System.out.println(friction);

        LOGGER.info("Application goes to friction.jsp");

        request.getRequestDispatcher("/WEB-INF/views/friction.jsp").forward(request, response);


    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
