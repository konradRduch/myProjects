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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import pl.polsl.dbmanager.DbManager;
import pl.polsl.model.EventEntity;

/**
 * @author Konrad Rduch
 * @version f5
 * @since f4
 */
@WebServlet(name = "EventShowServlet", urlPatterns = {"/EventShowServlet"})
public class EventShowServlet extends HttpServlet {

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

            List<Object> EventsList = new ArrayList();
            EventsList = dbManager.findObjects("SELECT e FROM EventEntity e");

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EventShowServlet</title>");
            out.println("<style>");
            out.println("body { font-family: 'Arial', sans-serif; background-color: #f4f4f4; margin: 20px; }");
            out.println("h1 { color: #333; }");
            out.println("p { color: #555; }");
            out.println("form { margin-top: 20px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Your events: </h1>");
            out.println("<br>");
            for (Object obj : EventsList) {
                if (obj instanceof EventEntity) {
                    EventEntity eventEntity = (EventEntity) obj;
                    out.println("<div class='event-container'>");
                    out.println("<p>ID: " + eventEntity.getId() + "</p>");
                    out.println("<p>Title: " + eventEntity.getTitle() + "</p>");
                    out.println("<p>Description: " + eventEntity.getDescription() + "</p>");
                    out.println("<p>Start date: " + dateFormat.format(eventEntity.getStartDate()) + "</p>");
                    out.println("<p>End date: " + dateFormat.format(eventEntity.getEndDate()) + "</p>");
                    out.println("<br>");
                    out.println("</div>");
                }
            }

            out.println("<br>");
            out.println("<form action=\"EventView.html\">\n"
                    + "            <input type=\"submit\" value=\"Back\" />\n"
                    + "             </form>");
            out.println("</body>");
            out.println("</html>");
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
