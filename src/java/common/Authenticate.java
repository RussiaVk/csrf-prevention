package common;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ebrima
 */
import com.mysql.jdbc.Driver;
import java.sql.*;
import model.classes.Card;
import model.classes.HibernateUtil;
import model.classes.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Authenticate
 {
 
     public static Card checkATMCard(int cardNo, int pinCode) 
     {
        Session session = HibernateUtil.getSessionFactory().openSession();
      //Transaction transaction = null;
      try {
        //    Transaction tx = session.beginTransaction();
            String hql = "from Card as u where u.cardNo = :cardno and u.pinCode = :pincode";
            Query q = session.createQuery(hql);
            q.setInteger("cardno", cardNo);
            q.setInteger("pincode", pinCode);
            Card card = (Card) q.uniqueResult();
            return card;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
         return null;                 
  }   
     
   public static User checkBankUser(String username,String password) 
     {
      Session session = HibernateUtil.getSessionFactory().openSession();
      //Transaction transaction = null;
      try {

        //    Transaction tx = session.beginTransaction();
            String hql = "from User as u where u.username = :username and u.password = :password";
            Query q = session.createQuery(hql);
            q.setString("username", username);
            q.setString("password",Util.encryptData(password));
            User user = (User) q.uniqueResult();
            return user;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
         return null;                 
  }   
     
  	/**
	 * Compute the hash value to check for "real person" submission.
	 * 
	 * @param  value  the entered value
	 * @return  its hash value
	 */
	
     //validate the captacha
     public static boolean validateRealPerson(String realPerson,String realPersonHash,String salt)
     {
         if( (realPersonHash != null) && (realPerson != null)){
             return realPersonHash(realPerson).equals(realPersonHash);
         }
         else{
             System.out.println("InValid real person");
             return false;
         }
     }
     
     //generate salt for realPerson captacha
     public static String generateRealPersonSalt(){
      return "23334";
     }
     
     private static String realPersonHash(String value) {
		int hash = 5381;
		value = value.toUpperCase();
		for(int i = 0; i < value.length(); i++) {
			hash = ((hash << 5) + hash) + value.charAt(i);
		}
                System.out.println("real person hash method: "+String.valueOf(hash));
		return String.valueOf(hash);
	}
    
   /* public static void main(String args[]){
      checkATMUser("test","test1") ;
    }
    */
}
