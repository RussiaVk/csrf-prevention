/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.atm;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tune
 */
@WebServlet(name = "AtmWithdrawComfirm", urlPatterns = {"/AtmWithdrawComfirm"})
public class AtmWithdrawConfirm extends HttpServlet {

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
        
        String WAmount = request.getParameter("WAmount");
        
        System.out.println("WAmount = "+ WAmount);
        
        if(Integer.parseInt(WAmount)>0 && Integer.parseInt(WAmount)<=1000){
            request.setAttribute("WAmount", WAmount);
            request.getRequestDispatcher("/WEB-INF/view/atm/AtmWithdrawConfirm.jsp").forward(request, response);
        }
        else{
            request.setAttribute("InvalidMessage", "You Can not withdraw the money more that 1000$ per day");
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
       
        String WAmount = request.getParameter("WAmount");
        
        System.out.println("WAmount = "+ WAmount);
        
        if(Integer.parseInt(WAmount)>0 && Integer.parseInt(WAmount)<=1000){
            request.setAttribute("WAmount", WAmount);
            request.getRequestDispatcher("/WEB-INF/view/atm/AtmWithdrawConfirm.jsp").forward(request, response);
        }
        else{
            request.setAttribute("InvalidMessage", "You Can not withdraw the money more that 1000$ per day");
            request.getRequestDispatcher("/WEB-INF/view/atm/AtmWithdrawInvalid.jsp").forward(request, response);
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
