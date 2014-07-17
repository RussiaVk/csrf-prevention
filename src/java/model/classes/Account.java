/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ebrima
 */
public class Account implements Serializable {
    private int id;
    private int accountNo;
    private int customerId;
    private int cardId;
    private String accountType;
    private Date dateCreated;
    private double amount;
    private Customer customer;
            
    public Account(){
    }
    public Account(String accountType, double amount){
       this.accountType = accountType;
       this.amount = amount;
    }
    
     public Account(int customerId, String accountType){
       this.customerId = customerId;
       this.accountType = accountType;
     }
     
     public Account(int customerId, String accountType, double amount){
       this.customerId = customerId;
       this.accountType = accountType;
       this.amount = amount;
    }
      
    public int getId(){
       return id;
    }
    
    public void setId(int id){
      this.id = id;
    }
    
    public int getCardId(){
       return cardId;
    }
    
    public void setCardId(int cardId){
      this.cardId = cardId;
    }
    
    public int getAccountNo(){
       return accountNo;
    }
    
    public void setAccountNo(int accountNo){
       this.accountNo = accountNo;
    }
    
    public int getCustomerId(){
       return customerId;
    }
    
    public void setCustomerId( int customerId){
       this.customerId = customerId;
    }
    
    public double getAmount(){
       return amount;
    }
    
    public void setAmount(double amount){
        this.amount = amount;
    }
    
    public String getAccountType(){
      return accountType;
    }
    
    public void setAccountType(String accountType){
      this.accountType = accountType;
    }
    
    public Date getDateCreated(){
      return dateCreated;
    }
    
    public void setDateCreated(Date date){
        dateCreated = date;
    }
    
    public Customer getCustomer(){
      return customer;
    }
    
    public void setCustomer(Customer customer){
      this.customer = customer;
    }
}
