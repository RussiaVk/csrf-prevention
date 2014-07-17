package controller.bank;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import common.Authenticate;
import common.SessionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.classes.User;

/**
 *
 * @author ebrima
 */
public class Bank extends HttpServlet {

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
         
           HttpSession session = request.getSession();
           User currentUser = (User)session.getAttribute("current_user");
           if ( currentUser != null){
              List accounts = BankActions.listAccounts();
              List users = BankActions.listUsers();
              if (accounts != null){
                  request.setAttribute("accounts",accounts);
                }
              if (users != null){
                  request.setAttribute("users",users);
                }
             
              //account_list
               String distination = request.getParameter("dest");
               if(distination != null){
                  
                   switch(distination){
                       case "account_list":
                            request.getRequestDispatcher("/WEB-INF/view/bank/account_list.jsp").forward(request, response);
                           break;
                       case "user_list":
                            request.getRequestDispatcher("/WEB-INF/view/bank/user_list.jsp").forward(request, response);
                           break;
                           
                  }             
                }
               else
                   request.getRequestDispatcher("/WEB-INF/view/bank/bank.jsp").forward(request, response);             
             } else{
              request.setAttribute("reference","bank");
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
         String realPerson = request.getParameter("realPerson"),
                realPersonHash = request.getParameter("realPersonHash"),
                salt =  Authenticate.generateRealPersonSalt();
         if(Authenticate.validateRealPerson(realPerson,realPersonHash , salt))
          {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
               //String realPerson = request.getParameter("defaultReal");
               //String realPersonHash = request.getParameter("defaultRealHash")
                User currentUser = Authenticate.checkBankUser(username,password);
                if(currentUser != null) 
                  {
                   setSingleUserSession(request,response,currentUser);
                   processRequest(request, response);
                 }
                 else
                 {
                   String errorMsg = "Invalid login crendentials";
                   request.setAttribute("errorMsg",errorMsg);
                   request.setAttribute("reference","bank");
                   request.setAttribute("realPersonSalt",Authenticate.generateRealPersonSalt());
                   request.getRequestDispatcher("/WEB-INF/view/login/login.jsp").forward(request, response);
                 }
          }
         else{
            String errorMsg = "Invalid login crendentials<br />Captcha not correct";
            request.setAttribute("errorMsg",errorMsg);
            request.setAttribute("reference","bank");
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

    void setSingleUserSession(HttpServletRequest request, HttpServletResponse response,User currentUser){
         HttpSession session;
         session = SessionManager.getUserSession(currentUser.getId());
         if(session != null){
           session.invalidate();
          }
          session  = request.getSession();     
          session.setAttribute("current_user",currentUser);
        //save the session into the database
          SessionManager.addUserSession(currentUser.getId(), session);
    }
}
