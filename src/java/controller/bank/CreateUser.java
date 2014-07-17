/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bank;

import common.Authenticate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.classes.User;

/**
 *
 * @author fara1_000
 */
public class CreateUser extends HttpServlet {

    /*
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

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("current_user");
        if (currentUser != null) {
            request.getRequestDispatcher("/WEB-INF/view/bank/create_user.jsp").forward(request, response);
        } else {
            //request.setAttribute("reference", "bank");
            response.sendRedirect("bank");
        }

    }

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         //get the session
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("current_user");
        if(currentUser != null){
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String role = request.getParameter("role");

            try {
                if (BankActions.createUser(username,password,firstName, lastName,role)) {
                    List users = BankActions.listUsers();
                    if(users != null)
                      request.setAttribute("users", users);
                    out.println("<div class=\"alert alert-success\">Successfully saved</div>");
                    request.getRequestDispatcher("/WEB-INF/view/bank/user_list.jsp").include(request, response);
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    out.println("Error data not saved");
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.println("Error saving data");
                out.close();
            }
        }
        else{
           response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
           out.println("Session is expired");
           out.close();
        }
       
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
