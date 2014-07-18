/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.classes;

/**
 *
 * @author ebrima
 */
public class Withdraw extends Transaction {
    private Integer cardId; 
    
    public Withdraw(){
    
    }
    
    public Withdraw(int accountNo, Integer cardId, double amount) {
        super(accountNo,amount,"WITHDRAW");
        this.cardId = cardId;
    }
      
    public Integer getCardId(){
     return cardId;
    }
    
    
    public void setCardId(Integer cardId){
      this.cardId = cardId;
    }
}
