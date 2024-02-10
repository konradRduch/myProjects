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
import pl.polsl.model.TaskEntity;

/**
 *
 * @author Konrad Rduch
 * @version f5
 * @since f4
 */
@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class TaskServlet extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String description = request.getParameter("add_description");

            if (!description.equals("")) {

                try {
                    TaskEntity task = new TaskEntity();
                    task.setDescription(description);
                    task.setDone(false);
                    dbManager.persistObject(task);

                    
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println(" <meta http-equiv=\"Refresh\" content=\"2; url=TaskView.html\" />");
                    out.println("<head>");
                    out.println("<title>Task Servlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Success</h1>");
                    out.println("</body>");
                    out.println("</html>");
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println(" <meta http-equiv=\"Refresh\" content=\"2; url=TaskView.html\" />");
                    out.println("<head>");
                    out.println("<title>Task Servlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Error adding task to database</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            } else {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <meta http-equiv=\"Refresh\" content=\"2; url=TaskView.html\" />");
                out.println("<head>");
                out.println("<title>Task Servlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Description is empty</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

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

    @Override
    public String getServletInfo() {
        return "Task Servlet";
    }
}
