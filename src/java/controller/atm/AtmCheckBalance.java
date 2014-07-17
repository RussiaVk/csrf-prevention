/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.atm;

import controller.bank.BankActions;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.classes.Account;
import model.classes.Card;
import model.classes.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tune
 */
@WebServlet(name = "AtmCheckBalance", urlPatterns = {"/AtmCheckBalance"})
public class AtmCheckBalance extends HttpServlet {

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
            HttpSession session = request.getSession();
            Card currentCard = (Card)session.getAttribute("current_card");
            if ( currentCard != null){
                           
                double amount =  AtmActions.getAmountfromCustomerID(currentCard.getCustomerId());
                System.out.println("amount = "+amount);

                if(amount != -1) {
                    request.setAttribute("amount", String.valueOf(amount));
                    request.getRequestDispatcher("/WEB-INF/view/atm/AtmCheckBalance.jsp").forward(request, response);   
                }
                else {
                    request.setAttribute("InvalidMessage", "Account not found");
                    request.getRequestDispatcher("/WEB-INF/view/atm/AtmWithdrawInvalid.jsp").forward(request, response);
                }
            }
            else{
                request.setAttribute("InvalidMessage", "Account not found");
                request.getRequestDispatcher("/WEB-INF/view/atm/AtmWithdrawInvalid.jsp").forward(request, response);
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
