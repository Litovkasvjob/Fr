package servlets;

import comport.PortListener;
import domain.Load;
import jssc.SerialPortException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import org.apache.log4j.Logger;
import util.DataReader;


@WebServlet(name = "HomeServlet", urlPatterns = "/home")
@MultipartConfig
public class HomeServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(HomeServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String com = request.getParameter("com");
        String option = request.getParameter("option");
        String file = request.getParameter("file");

        HttpSession session = request.getSession();

        //TODO: check parameters

        LOGGER.info("Application uses '" + option + "' mode");

        if ("online".equals(option)) {

            LOGGER.info("Application start listening port: " + com);

            PortListener portListener = PortListener.getInstance();
            try {
                portListener.listenPort(com);

            } catch (SerialPortException e) {
                e.printStackTrace();
            }

            List forces = DataReader.loadForces();
            session.setAttribute("forсes", forces);

            request.getRequestDispatcher("/WEB-INF/views/loadOnline.jsp").forward(request,response);

        } else if ("offline".equals(option)) {



            //String filename = "D:/TEMP/" + request.getParameter("file"); //TODO: take file from filesystem?

            Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
            String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            InputStream fileContent = filePart.getInputStream();
            //System.out.println(filename);

            //take data from util.DataReader
            List<Load> loads = DataReader.loadData(fileContent, filename);
            session.setAttribute("loads", loads);
            List forces = DataReader.loadForces();
            session.setAttribute("forсes", forces);

            LOGGER.info("Application goes to loadOffline.jsp");

            request.getRequestDispatcher("/WEB-INF/views/loadOffline.jsp").forward(request,response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (!session.isNew()) {

            LOGGER.info("Remove session attributes: loads, forces");
            session.removeAttribute("loads");
            session.removeAttribute("forces");
        }
        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request,response);
    }

}