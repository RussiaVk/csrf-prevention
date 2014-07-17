/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bank;

import common.Authenticate;
import java.io.IOException;
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
public class Deposit extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("current_user");
        if(currentUser != null){
            request.getRequestDispatcher("/WEB-INF/view/bank/deposit.jsp").forward(request, response);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//                Account Number: <input type="text" name="accountno"><br>     
//                Amount to Deposit: <input type="text" name="amount"><br>  
//                        
        String account = request.getParameter("accountno");
        String amount = request.getParameter("amount");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
