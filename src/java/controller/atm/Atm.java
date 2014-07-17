/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.atm;

import common.Authenticate;
import common.SessionManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.classes.Card;

/**
 *
 * @author ebrima
 */
public class Atm extends HttpServlet {

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
            response.setContentType("text/html;charset=UTF-8");

           HttpSession session = request.getSession();
           Card currentCard = (Card)session.getAttribute("current_card");
           if ( currentCard != null)
              request.getRequestDispatcher("/WEB-INF/view/atm/atm.jsp").forward(request, response);             
            else{
              request.setAttribute("reference","atm");
              request.setAttribute("realPersonSalt",Authenticate.generateRealPersonSalt());
              request.getRequestDispatcher("/WEB-INF/view/login/login.jsp").forward(request, response);
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
         //first validate the captcha text to make sure that a human is posting
          String realPerson = request.getParameter("atmRealPerson"),
                realPersonHash = request.getParameter("realPersonHash"),
                salt =  Authenticate.generateRealPersonSalt();
          if(Authenticate.validateRealPerson(realPerson,realPersonHash , salt))
          {
              int cardNo = Integer.parseInt(request.getParameter("cardNumber"));
               int pinCode = Integer.parseInt(request.getParameter("pinCode"));
               Card currentCard = Authenticate.checkATMCard(cardNo,pinCode);
                if(currentCard != null) 
                {
                    setSingleCardSession(request,response,currentCard);
                    processRequest(request, response);
                }
                else
                {
                    String errorMsg = "Invalid login crendentials";
                    request.setAttribute("errorMsg",errorMsg);
                    request.setAttribute("reference","atm");
                    request.setAttribute("realPersonSalt",Authenticate.generateRealPersonSalt());
                    request.getRequestDispatcher("/WEB-INF/view/login/login.jsp").forward(request, response);
                }
           }
           else{
               String errorMsg = "Invalid login crendentials<br />Captcha not correct";
                request.setAttribute("errorMsg",errorMsg);
                request.setAttribute("reference","atm");
                request.setAttribute("realPersonSalt",Authenticate.generateRealPersonSalt());
                request.getRequestDispatcher("/WEB-INF/view/login/login.jsp").forward(request, response);
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

     void setSingleCardSession(HttpServletRequest request, HttpServletResponse response,Card currentCard){
                         // Return the existing session if there is one. Create a new session otherwise.
                HttpSession session = SessionManager.getCardSession(currentCard.getId());
                if(session != null){
                   session.invalidate();
                 }
                session = request.getSession();
                session.setAttribute("current_card",currentCard);
               // session.setAttribute("user", "Pankaj");
                //setting session to expiry in 30 mins
                session.setMaxInactiveInterval(30*60);
                Cookie userName = new Cookie("user", Integer.toString(currentCard.getCardNo()));
                userName.setMaxAge(30*60);
                response.addCookie(userName);
                SessionManager.addCardSession(currentCard, session);
     }
}
