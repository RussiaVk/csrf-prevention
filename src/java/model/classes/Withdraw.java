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
    private int cardId; 
    
    public Withdraw(){
    
    }
    
    public Withdraw(int accountNo, int cardId, double amount) {
        super(accountNo,amount,"Withdraw");
        this.cardId = cardId;
    }
      
    public int getCardId(){
     return cardId;
    }
    
    
    public void setCardId(int cardId){
      this.cardId = cardId;
    }
}
