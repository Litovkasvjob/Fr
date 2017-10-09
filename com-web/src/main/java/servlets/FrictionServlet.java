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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Serg on 26.08.2017.
 */
@WebServlet(name = "FrictionServlet", urlPatterns = "/friction")
public class FrictionServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(HomeServlet.class.getName());


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private final String LOADS = "loads";
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOGGER.info("FrictionServlet try to calculate f");

        String[] numbers = request.getParameterValues("numbers");

        System.out.println(Arrays.deepToString(numbers));

        HttpSession session = request.getSession();
        List<Load> data = (List<Load>) session.getAttribute(LOADS);

        System.out.println(data);


        List<String> forces = (List<String>) session.getAttribute("forсes");

        System.out.println(forces.toString());

        Map<Integer, Double> friction = new LinkedHashMap<>();

        for (int i = 0; i < numbers.length; i++) {

            Double f = DataReader.calculateFriction(data, Integer.parseInt(numbers[i]), Integer.parseInt(forces.get(i)));
            friction.put(Integer.parseInt(forces.get(i)), f);
        }

        request.setAttribute("frictions", friction);

//TODO: write data to DB or file
//TODO: clean session attribute?

        LOGGER.info("Application goes to friction.jsp");
        request.getRequestDispatcher("/WEB-INF/views/friction.jsp").forward(request, response);

    }
}
