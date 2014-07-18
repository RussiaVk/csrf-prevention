/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.atm;

import controller.bank.BankActions;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Security;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.classes.Account;
import model.classes.Card;

/**
 *
 * @author tune
 */
@WebServlet(name = "AtmWithdraw", urlPatterns = {"/AtmWithdraw"})
public class AtmWithdraw extends HttpServlet {

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
        request.getRequestDispatcher("/WEB-INF/view/atm/AtmWithdraw.jsp").forward(request, response);
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
        
        System.out.println("enter enter jud ja di da");
        HttpSession session = request.getSession();
        Card currentCard = (Card)session.getAttribute("current_card");
        if ( currentCard != null){
            
            int WAmount = Integer.parseInt(request.getParameter("WAmount"));
          
            //check accout have more money than user request
        
            double AAmount = AtmActions.getAmountfromCustomerID(currentCard.getCustomerId());

            //System.out.println("AAmount = "+AAmount);
            if(WAmount > AAmount) {
                request.setAttribute("InvalidMessage", "Money in your account is not enought to do the transaction");
                request.getRequestDispatcher("/WEB-INF/view/atm/AtmWithdrawInvalid.jsp").forward(request, response);
                return;
            }

            //check transection not over than 1000$ per day
            double WTran = AtmActions.getAmountWithdrawFromTransaction(currentCard.getCustomerId());
            System.out.println("WTran = " + WTran);
            
            if(WTran + WAmount > 1000) {
                request.setAttribute("InvalidMessage", "You Can not withdraw the money more that 1000$ per day");
                request.getRequestDispatcher("/WEB-INF/view/atm/AtmWithdrawInvalid.jsp").forward(request, response);
                return;
            }
            
            //update table account  //create new transaction and insert
            if(!AtmActions.debitAccount(currentCard.getCustomerId(), WAmount)) {
                request.setAttribute("InvalidMessage", "There are a problem<br>When do the transaction");
                request.getRequestDispatcher("/WEB-INF/view/atm/AtmWithdrawInvalid.jsp").forward(request, response);
                return;
            }
            
            //send email
            try {
                String recipientEmail = currentCard.getCustomer().getEmail();
                String CCrecipient ="";
                String title =  "inform withdraw transaction";
                String message = "Hi " + currentCard.getCustomer().getFirstName() + " " + currentCard.getCustomer().getLastName() + ",\n" +
                                 "We would like to inform you that your latest withdraw transaction was successful.\n" + 
                                 "You withdraw "+WAmount+"$ from an ATM." +
                                 "\n\nHave a nice day!";
                sendEmail.Send(recipientEmail, CCrecipient, title, message);
            } catch (MessagingException ex) {
                Logger.getLogger(AtmWithdraw.class.getName()).log(Level.SEVERE, null, ex);
            }

            //sent sms
            String smsRev = currentCard.getCustomer().getPhone() + "@txt.att.net";
            String SMSHeader = "Inform Withdraw Transaction";
            String SMSText = "You withdraw "+WAmount+"$ from an ATM";
            SMTPSender sms = new SMTPSender(smsRev, SMSHeader, SMSText);
            sms.run();
           
            /*
            sendsms.init();
            sendsms.server = "http://127.0.0.1:8800/";
            sendsms.user = "tunlaya";
            sendsms.password = "tunlaya1";
            sendsms.phonenumber = "+16466435435";
            sendsms.text = "This is a test message";
            sendsms.send();
           */

            request.setAttribute("ValidMessage", "your withdraw transaction<br>is completed");
            request.getRequestDispatcher("/WEB-INF/view/atm/AtmWithdrawValid.jsp").forward(request, response);
        }
        else {
            request.setAttribute("InvalidMessage", "Card Not Found");
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
