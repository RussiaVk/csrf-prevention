/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.classes;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ebrima
 */
public class Transaction implements Serializable{
    protected int id;
    protected int accountId;
    protected double amount;
    protected Date date;
    private String type;
    
    public Transaction(){
    
    }
    public Transaction(int accountId,double amount , String type){
         this.accountId = accountId;
         this.amount = amount;
         this.type = type;
    }
    
    public int getId(){
     return id;
    }
    
    public void setId(int id){
     this.id = id;
    }
    
    public double getAmount(){
     return amount;
    }
    
    public void setAmount(double amount){
      this.amount = amount;
    }
    
    public int getAccountId(){
     return accountId;
    }
    
    public void setAccountId( int accountId){
       this.accountId = accountId;
    }
    
    public Date getDate(){
      return date;
    }
    
    public void setDate(Date date){
      this.date = date;
    }
    
    public String getType(){
      return type;
    }
    
    public void setType(String type){
      this.type = type;
    }
}
