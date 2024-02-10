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
import java.util.ArrayList;
import java.util.List;
import pl.polsl.dbmanager.DbManager;
import pl.polsl.model.TaskEntity;
import pl.polsl.model.TaskGroupEntity;

/**
 * @author Konrad Rduch
 * @version f5
 * @since f5
 */
@WebServlet(name = "AddTaskGroupServlet", urlPatterns = {"/AddTaskGroupServlet"})
public class AddTaskGroupServlet extends HttpServlet {

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
            // Pobierz parametry z formularza
            String taskNamesString = request.getParameter("task_names");
            String groupName = request.getParameter("group_name");

            // Przetwórz identyfikatory zadań oddzielone przecinkami
            String[] taskNames = taskNamesString.split(",");

            // Dodaj grupę z zadaniami do bazy danych
            try {
                TaskGroupEntity taskGroup = new TaskGroupEntity();
                taskGroup.setName(groupName);
                taskGroup.setDone(false);

                List<TaskEntity> tasks = new ArrayList<>();

                for (String taskName : taskNames) {
                    TaskEntity task = new TaskEntity();
                    task.setDescription(taskName);  // Ustaw opis zadania (możesz dostosować)
                    task.setDone(false);
                    task.setGroup(taskGroup);

                    tasks.add(task);
                }

                taskGroup.setGroupTask(tasks);

                // Użyj istniejącej sesji EntityManager
                dbManager.persistObject(taskGroup);

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet AddTaskGroupServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet AddTaskGroupServlet at " + request.getContextPath() + "</h1>");
                out.println("</body>");
                out.println("</html>");
                // Przekieruj na odpowiednią stronę po dodaniu grupy
            } catch (Exception e) {
                // Obsłuż ewentualny błąd (np. nieudane dodanie grupy)
                response.getWriter().println("Error: " + e.getMessage());
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
