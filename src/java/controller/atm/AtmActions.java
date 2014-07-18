/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.atm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.classes.Account;
import model.classes.Card;
import model.classes.HibernateUtil;
import model.classes.Withdraw;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tune
 */
public class AtmActions {
    
    public static int getCustomerIDfromCardID(int cardId) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
	Transaction transaction = null;
        
        try {
            
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Card where id= :id ");
            query.setParameter("id", cardId);
            
            List<Card> list = query.list();

            return list.get(0).getCustomerId();
            
	} catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
	} finally {
            session.close();
	}
        
        return -1;
    }
    
    
    public static double getAmountfromCustomerID(int cusId) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
	Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Account where customerId = :customerId ");
            query.setParameter("customerId", cusId);
            
            List<Account> list = query.list();
            Account account = list.get(0);
            
            return list.get(0).getAmount();
            
	} catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
	} finally {
            session.close();
            
	}
        
        return -1;
        
    }
    
    public static double getAmountWithdrawFromTransaction(int cardId) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
	Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Withdraw where cardId = :cardId and type = :type and  day(date) = :date and month(date) = :month and year(date) = :year ");
            Calendar cal = Calendar.getInstance();  
            cal.setTime(new Date());  
            query.setInteger("date", cal.get(Calendar.DATE));  
            query.setInteger("month", cal.get(Calendar.MONTH)+1);  
            query.setInteger("year", cal.get(Calendar.YEAR));
            query.setParameter("cardId", cardId);
            query.setParameter("type", "WITHDRAW");
            
            List<Withdraw> list = query.list();
           
            if( list == null ) 
                return 0;
            
            double totalAmount = 0;
            for(int i=0 ; i<list.size() ; i++) { 
                totalAmount += list.get(i).getAmount();
            }
            
            System.out.println("totalAmount = "+totalAmount);
            
            return totalAmount;
            
	} catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
	} finally {
            session.close();
            
	}
        
        return -1;
        
    }
    
    public static Date getTodayDate() {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date today = new Date();
        Date todayWithZeroTime = null;
        try {
            todayWithZeroTime = formatter.parse(formatter.format(today));
        } catch (ParseException ex) {
            Logger.getLogger(AtmActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return todayWithZeroTime;
    }
    
    public static Account getAccountFromCardID(int cardId) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
	Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Account where cardId = :cardId ");
            query.setParameter("cardId", cardId);
            List<Account> list = query.list();
                    
            if( list == null ) 
                return null;          
            else
               return list.get(0);
            
	} catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
	} finally {
            session.close();
            
	}
        
        return null;
        
    }
    
    //withdraw
    public static boolean debitAccount(int accountId,double amount) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean save = false;
		try {
			transaction = session.beginTransaction();
                        Account account = (Account) session.get(Account.class, accountId);
			//course.setCourseName(courseName);
                        //if account exists
                        if(account != null ){                      
			   Withdraw withdraw = new Withdraw(account.getId(), account.getCustomerId(), amount);
			   withdraw.setType("WITHDRAW");
			   session.save(withdraw);
                           account.setAmount(account.getAmount() - withdraw.getAmount());
                          // session.save(account);
                           //update account after withdraw
			   transaction.commit();
                           save = true;
                        }
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return save;
		
    }
    
    
}
