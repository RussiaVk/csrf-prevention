/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.bank;

import common.Util;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.classes.Account;
import model.classes.Card;
import model.classes.Customer;
import model.classes.Deposit;
import model.classes.HibernateUtil;
import model.classes.User;
import model.classes.UserRole;
import model.classes.Withdraw;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BankActions {

	
	public static boolean createCustomer(String firstName,
                                             String lastName,
                                             String address, 
                                             String email,
                                             String phone, 
                                             String accountType, 
                                             double  openningAmount, 
                                             boolean createAtmCard,
                                             int currentUserId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean save = false;
		try {
			transaction = session.beginTransaction();
			Customer customer = new Customer(firstName,lastName,address,email, phone);
		        int customerId = (Integer) session.save(customer);
			transaction.commit();     
                        save = addCustomerAccount(customerId,accountType, openningAmount,createAtmCard,currentUserId);
                                              
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return save;
		
	}
	
	public static List<Customer> listCustomers()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List customers = session.createQuery("from Customer ORDER BY id DESC").list();
			transaction.commit();
			return customers;
                        
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
        
        public static List<Customer> listAccounts()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List accounts = session.createQuery("from Account ORDER BY dateCreated DESC").list();
			transaction.commit();
			return accounts;
                        
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
        
        public static List<Transaction> listTransactions()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List transactions = session.createQuery("from Transaction ORDER BY id DESC").list();
			transaction.commit();
			return transactions;
                        
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
        
	
	public void updateCustomer(Long customerId, String courseName)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Customer course = (Customer) session.get(Customer.class, customerId);
			//course.setCourseName(courseName);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void deleteCustomer(Long customerId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Customer customer = (Customer) session.get(Customer.class, customerId);
			session.delete(customer);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
        
        public static boolean addCustomerAccount(int customerId, String accountType, double openningAmount, boolean createAtmCard,int currentUserId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean save = false;
		try {
			transaction = session.beginTransaction();
			Account account = new Account(customerId,accountType);
			account.setAccountNo(Util.generateAccountNumber());
		        int accountId = (int)session.save(account);
			transaction.commit();
                        save = true;
                        //create atm card for the customer and append this account
                        if(createAtmCard)
                        {
                         save = createAccountCard(customerId,accountId);
                         save = (save)? creditAccount(accountId,openningAmount,currentUserId) : save; 
                        }

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return save;
		
	}
        
        //create atm card
         public static boolean createAccountCard(int customerId,int accountId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean save = false;
		try {
			transaction = session.beginTransaction();
                        //int customerId, int cardNo, int pinCode
                        int cardNumber = Util.generateCardNumber();// TODO -- will generated it by the 
                        int pinCode = Util.generatePinCode(); // TODO--- will be generated by the system
			Card card = new Card(customerId,cardNumber,pinCode);
			//customer.setCourseName(courseName);
		        int cardId = (int)session.save(card);
                        //update the account
                        //set the cardId for the account
                        Account account = (Account) session.get(Account.class, accountId);
			account.setCardId(cardId);
                        session.update(account);
                        transaction.commit();
                        save = true;
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return save;
		
	}
        
        //withdraw
         public static boolean debitAccount(int accountId,double amount)
	{
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
			   //customer.setCourseName(courseName);
			   session.update(account);
                           account.setAmount(account.getAmount() - withdraw.getAmount());
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
         
        //deposit into the account
        public static boolean creditAccount(int accountId,double amount,  int currentUserId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean save = false;
		try {
			transaction = session.beginTransaction();
                        Account account = (Account) session.get(Account.class, accountId);
			//course.setCourseName(courseName);
                        //if account exists
                        if(account != null ){                      
			   Deposit deposit = new Deposit(account.getId(), amount, currentUserId);
			   //customer.setCourseName(courseName);
                           System.out.print("---------------------------");
                           System.out.println(account.getId());
                           System.out.println(amount);
                           System.out.println(currentUserId);
                           System.out.print("----------------------------");
                           session.save(deposit);
                           account.setAmount(account.getAmount() + deposit.getAmount());
                           //update account after deposit
                           session.update(account);
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
        
        //create bank user
        public static boolean createUser( String username, String password,String firstName,String lastName,String role)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean save = false;
		try {
			transaction = session.beginTransaction();
                        String encryptedPassword = Util.encryptData(password);
			User user = new User(username,encryptedPassword,firstName,lastName);
		        int userId = (Integer) session.save(user);
			transaction.commit();
                        save = addUserRole(userId,role);
                                              
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return save;		
	}
        
         public static boolean addUserRole(int userId, String role)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean save = false;
		try {
			transaction = session.beginTransaction();
			UserRole userRole = new UserRole(userId,role);
			//customer.setCourseName(courseName);
		        session.save(userRole);
			transaction.commit();
                        save = true;
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return save;
		
	}

       public static List<User> listUsers()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List users = session.createQuery("from User").list();
			transaction.commit();
			return users;
                        
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
       
       public static List<UserRole> listUserRoles(int userId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			 Query query = session.createQuery("from UserRole Were userId = :userId ");
                         query.setParameter("userId", userId);
                         List<UserRole> roles= query.list();
			return roles;
                        //q.uniqueResult();
                        
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
       
       public static UserRole isUserRoleAssigned(int userId, String roleName)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			 Query query = session.createQuery("from UserRole where userId = :userid and role = :rolename");
                         query.setParameter("userid", userId);
                         query.setParameter("rolename", roleName);
                         UserRole role = (UserRole)query.uniqueResult();
			return role;
                        
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
       
}
