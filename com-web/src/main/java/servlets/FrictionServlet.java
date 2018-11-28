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
@WebServlet(name = "FrictionServlet", urlPatterns = "/friction")
public class FrictionServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(FrictionServlet.class.getName());

    private final String LOADS = "loads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOGGER.info("FrictionServlet try to calculate f");

        String str = request.getParameter("numbers");

        System.out.println(str);

        // parse data
        String string0 = str.substring(2, str.length() - 2);

        String reg = "\",\"";

        String[] array0 = string0.split(reg);
        int[] numbers = new int[array0.length];

        for (int i = 0; i < array0.length; i++) {

            Integer number = Integer.parseInt(array0[i]);
            numbers[i]= number;

        }
        System.out.println(Arrays.toString(numbers));


        String numbersData = request.getParameter("LOADS");
 // parse data
        String string = numbersData.substring(2, numbersData.length() - 2);

        String s = "],\\[";

        String[] array = string.split(s);

        List<Load> loadList = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {

            String[] inner = array[i].split(",");
            Integer id = Integer.parseInt(inner[0]);
            Double load = Double.parseDouble(inner[1]);
            loadList.add(new Load(id, load));
        }

        System.out.println(loadList);


        //TODO: Check for empty space in numbers and for 0 in loads and boundory of loads - numbers
        HttpSession session = request.getSession();

        List<String> forces = (List<String>) session.getAttribute("forсes");

        System.out.println(forces);

        Map<Integer, Double> friction = new LinkedHashMap<>();


        for (int i = 0; i < numbers.length; i++) {

            Double f = DataReader.calculateFriction(loadList, numbers[i], Integer.parseInt(forces.get(i)));
            friction.put(Integer.parseInt(forces.get(i)), f);
        }

        System.out.println(friction);
        request.setAttribute("frictions", friction);
        session.setAttribute("frictions", friction);
//TODO: write data to DB or file

        LOGGER.info("Application goes to friction.jsp");
  //      request.getRequestDispatcher("/WEB-INF/views/friction.jsp").forward(request, response);
//response.sendRedirect("/WEB-INF/views/friction.jsp");

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOGGER.info("FrictionServlet try to calculate f");

        String[] numbers = request.getParameterValues("numbers");

        System.out.println(Arrays.deepToString(numbers));

        HttpSession session = request.getSession();
        List<Load> data = (List<Load>) session.getAttribute(LOADS);

        System.out.println(data);

        //TODO: Check for empty space in numbers and for 0 in loads and boundory of loads - numbers


        List<String> forces = (List<String>) session.getAttribute("forсes");

        System.out.println(forces);

        Map<Integer, Double> friction = new LinkedHashMap<>();

        for (int i = 0; i < numbers.length; i++) {

            Double f = DataReader.calculateFriction(data, Integer.parseInt(numbers[i]), Integer.parseInt(forces.get(i)));
            friction.put(Integer.parseInt(forces.get(i)), f);
        }

        request.setAttribute("frictions", friction);

//TODO: write data to DB or file

        LOGGER.info("Application goes to friction.jsp");
        request.getRequestDispatcher("/WEB-INF/views/friction.jsp").forward(request, response);

    }
}
