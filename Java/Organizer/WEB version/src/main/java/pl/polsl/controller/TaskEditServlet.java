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
import pl.polsl.dbmanager.DbManager;
import pl.polsl.exceptions.IdNotExistsException;
import pl.polsl.model.TaskEntity;

/**
 *
 * @author Konrad Rduch
 * @version f5
 * @since f4
 */
@WebServlet(name = "TaskEditServlet", urlPatterns = {"/TaskEditServlet"})
public class TaskEditServlet extends HttpServlet {

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
            throws ServletException, IOException, IdNotExistsException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            try {
                Long id = Long.parseLong(request.getParameter("edit_id"));
                String description = request.getParameter("edit_description");
                TaskEntity task = new TaskEntity();
                task.setId(id);
                task.setDescription(description);
                task.setDone(false);
                dbManager.editObject(task);

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet TaskEditServlet</title>");
                out.println(" <meta http-equiv=\"Refresh\" content=\"2; url=TaskView.html\" />");
                out.println("</head>");
                out.println("<body>");
                out.println("Succes");
                out.println("</body>");
                out.println("</html>");
            } catch (NumberFormatException e) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet TaskEditServlet</title>");
                out.println(" <meta http-equiv=\"Refresh\" content=\"2; url=TaskView.html\" />");
                out.println("</head>");
                out.println("<body>");
                out.println("Entered value is not an integer.");
                out.println("</body>");
                out.println("</html>");
            } catch (IdNotExistsException e) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet TaskEditServlet</title>");
                out.println(" <meta http-equiv=\"Refresh\" content=\"2; url=TaskView.html\" />");
                out.println("</head>");
                out.println("<body>");
                out.println("Error: " + e.getMessage());
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
        try {
            processRequest(request, response);
        } catch (IdNotExistsException e) {
            // Handle the exception as needed
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
        }
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
        try {
            processRequest(request, response);
        } catch (IdNotExistsException e) {
            // Handle the exception as needed
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
        }
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
