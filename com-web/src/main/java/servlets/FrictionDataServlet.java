package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import comport.PortListener;
import domain.Load;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

@WebServlet(name = "FrictionDataServlet", urlPatterns = "/frictionData")
public class FrictionDataServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(FrictionDataServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PortListener portListener =  PortListener.getInstance();

        BlockingQueue<Load> portDataQueue = portListener.getPortDataQueue("1");
        Load realData = null;
        try {
            LOGGER.info("FrictionServlet waiting for realData from port 1");
            realData = portDataQueue.take();
            LOGGER.info("FrictionServlet have got realData " + realData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ObjectMapper objectMapper = new ObjectMapper();

        String jsonResponse = objectMapper.writeValueAsString(realData.asArray());
        System.out.println(jsonResponse);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}
