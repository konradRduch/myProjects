/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import pl.polsl.dbmanager.DbManager;
import pl.polsl.exceptions.StartDateIsAfterEndDateException;

import pl.polsl.model.EventEntity;

/**
 *
 * @author Konrad Rduch
 * @version f5
 * @since f4
 */
@WebServlet(name = "EventServlet", urlPatterns = {"/EventServlet"})
public class EventServlet extends HttpServlet {

    private DbManager dbManager;

    /**
     * Overrides the default {@code init} method to initialize the servlet and
     * set up resources. In this case, it obtains an instance of the
     * {@code DbManager} to manage database operations.
     *
     * @throws ServletException if an exception occurs during servlet
     * initialization.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        dbManager = DbManager.getInstance();
    }

    /**
     * Overrides the default {@code destroy} method to clean up resources when
     * the servlet is being destroyed. It ensures proper closure of the
     * {@code DbManager} instance by calling its {@code close} method.
     */
    @Override
    public void destroy() {
        super.destroy();
        dbManager.close();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                String startDateInput = request.getParameter("add_start_date");
                String endDateInput = request.getParameter("add_end_date");
                Date startDate = null;
                Date endDate = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

                try {
                    startDate = dateFormat.parse(startDateInput);
                    endDate = dateFormat.parse(endDateInput);

                    if (startDate.compareTo(endDate) <= 0) {
                    } else {
                        throw new StartDateIsAfterEndDateException("The start date cannot be later than the end date.");
                    }
                } catch (ParseException e) {

                    //return;
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet TaskEditServlet</title>");
                    out.println(" <meta http-equiv=\"Refresh\" content=\"2; url=EventView.html\" />");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("Invalid date format. Failed to add the event.");
                    out.println("</body>");
                    out.println("</html>");
                }

                String eventName = request.getParameter("add_name");

                String eventDescription = request.getParameter("add_description");

                EventEntity event = new EventEntity();
                event.setDescription(eventDescription);
                event.setTitle(eventName);
                event.setStartDate(startDate);
                event.setEndDate(endDate);
                dbManager.persistObject(event);

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet TaskEditServlet</title>");
                out.println(" <meta http-equiv=\"Refresh\" content=\"2; url=EventView.html\" />");
                out.println("</head>");
                out.println("<body>");
                out.println("Succes");
                out.println("</body>");
                out.println("</html>");
            } catch (StartDateIsAfterEndDateException e) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet EventEditServlet</title>");
                out.println(" <meta http-equiv=\"Refresh\" content=\"2; url=EventView.html\" />");
                out.println("</head>");
                out.println("<body>");
                out.println("The start date cannot be later than the end date.");
                out.println("</body>");
                out.println("</html>");
            }

        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
