/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

import java.util.Hashtable;
import java.util.List;
import javax.servlet.http.HttpSession;
import model.classes.Card;
import model.classes.CardSession;
import model.classes.Customer;
import model.classes.HibernateUtil;
import model.classes.UserSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author ebrima
 */
public class SessionManager {
    private static  Hashtable activeUserSessions = new Hashtable(); 
    private static  Hashtable activeCardSessions = new Hashtable();
    
    public static  boolean addCardSession(Card card,  HttpSession httpSession){    
        Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
                boolean save = false;
		try {
                    
                        transaction = session.beginTransaction();
			CardSession cardSession = new CardSession(card.getId(),card.getCustomerId(),httpSession.getId(),httpSession.getCreationTime());
		        session.save(cardSession);
			transaction.commit();
                        activeCardSessions.put(card.getId(), httpSession);
                        save = true;
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
                return save;
	}
    
     public static  boolean addUserSession(int userId, HttpSession httpSession){
        Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
                boolean save = false;
		try {
                        transaction = session.beginTransaction();
			UserSession  userSession = new UserSession(userId,httpSession.getId(),httpSession.getCreationTime());
		        session.save(userSession);
			transaction.commit();
                        save = true;
                        activeUserSessions.put(userId, httpSession);
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
                return save;
	}
     
      public static HttpSession getUserSession(int userId){
          return (HttpSession)activeUserSessions.get(userId);       
      }
       
      public static HttpSession getCardSession(int cardId){
          return (HttpSession)activeCardSessions.get(cardId);       
      }
}
