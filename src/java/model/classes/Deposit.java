/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.classes;

import model.classes.Transaction;

/**
 *
 * @author ebrima
 */
 
public class Deposit extends Transaction {
    private int userId;
    
    public Deposit(){
       super();
    }
    public Deposit(int accountNo, double amount,int userId) {
        super(accountNo,amount, "Deposit");
        this.userId = userId;
    }
    
    public int getUserId(){
      return userId;
    }
    
    public void setUserId(int userId){
      this.userId  = userId;
    }
    
}

