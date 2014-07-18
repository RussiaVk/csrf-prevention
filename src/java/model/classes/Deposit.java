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
    private Integer userId;
    
    public Deposit(){
       super();
    }
    public Deposit(int accountNo, double amount,Integer userId) {
        super(accountNo,amount, "DEPOSIT");
        this.userId = userId;
    }
    
    public Integer getUserId(){
      return userId;
    }
    
    public void setUserId(Integer userId){
      this.userId  = userId;
    }
    
}

