/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.classes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author ebrima
 */
public class Card implements Serializable{
    private int id;
    private int customerId;
    private int cardNo;
    private int pinCode;
    private Set<Account> accounts = new HashSet<Account>(0);
    private Customer customer;
    
    public Card(){}
    
    public Card(int customerId, int cardNo, int pinCode){
      this.customerId = customerId;
      this.cardNo = cardNo;
      this.pinCode = pinCode;
    }
    
    
    public int getId(){
       return id;
    }
    
    public void setId(int id){
      this.id = id;
    }
    
    public int getCustomerId(){
       return customerId;
    }
    public void setCustomerId(int customerId){
      this.customerId = customerId;
    }
    
    public void setPinCode(int pinCode){
      this.pinCode = pinCode;
    }
    
    public int getPinCode(){
     return pinCode;
    }
    
    public int getCardNo(){
        return cardNo;
    }
    
    public void setCardNo(int cardNo){
        this.cardNo = cardNo;
    }
    
    public Set<Account> getAccounts() {
      return accounts;
    }
    
     public void setAccounts( Set<Account> accounts ) {
      this.accounts = accounts;
    }
     
    public Customer getCustomer(){
      return customer;
    }
    
    public void setCustomer(Customer customer){
      this.customer = customer;
    }
}
