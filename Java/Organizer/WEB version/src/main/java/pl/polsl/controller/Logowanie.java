/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Konrad Rduch
 * @version f5
 * @since f4
 */
@WebServlet(name = "Logowanie", urlPatterns = {"/Logowanie"})
public class Logowanie extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Prosta walidacja użytkownika (możesz podać swoją logikę uwierzytelniania)
        if ("admin".equals(username) && "1234".equals(password)) {
            // Poprawne uwierzytelnienie - ustawienie sesji
            HttpSession session = request.getSession(true);

            session.setAttribute("user", username);

            response.sendRedirect("MenuView.html");
        } else {
            // Nieprawidłowe uwierzytelnienie - przekierowanie z powrotem do formularza logowania
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Login failed</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Login failed. Please try again.</h1>");
                int visitCount = 0;
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("visitCount")) {
                        visitCount = Integer.parseInt(cookie.getValue());
                        break;
                        }
                    }
                }
                visitCount++;
                out.println("<p>Wykorzystano ciasteczka: You have failed to login " + visitCount + " times.</p>");
                Cookie visitCookie = new Cookie("visitCount", String.valueOf(visitCount));
                response.addCookie(visitCookie);
                // Wyświetlenie formularza logowania z wprowadzonymi danymi
                out.println("<form action=\"Logowanie\" method=\"post\">");
                out.println("Username: <input type=\"text\" name=\"username\" value=\"" + username + "\" required/><br/>");
                out.println("Password: <input type=\"password\" name=\"password\" required/><br/>");
                out.println("<input type=\"submit\" value=\"Login\"/>");
                out.println("</form>");

                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    /**
     * Handles HTTP GET requests by forwarding them to the common processing
     * method.
     *
     * @param request The HttpServletRequest object containing the request
     * information.
     * @param response The HttpServletResponse object for sending the response
     * to the client.
     * @throws ServletException If an exception occurs that interrupts the
     * servlet's normal operation.
     * @throws IOException If an I/O error occurs during the processing of the
     * request.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles HTTP POST requests by forwarding them to the common processing
     * method.
     *
     * @param request The HttpServletRequest object containing the request
     * information.
     * @param response The HttpServletResponse object for sending the response
     * to the client.
     * @throws ServletException If an exception occurs that interrupts the
     * servlet's normal operation.
     * @throws IOException If an I/O error occurs during the processing of the
     * request.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return A string containing a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
