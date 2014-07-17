/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.classes;

import java.util.Date;

/**
 *
 * @author ebrima
 */
public class CardSession extends Session {
    private int cardId;
    private int customerId;
    public CardSession(){
         super();
    }
    
    public CardSession(int cardId, int customerId,String session){
      super(session);
      this.cardId = cardId;
      this.customerId = customerId;
    }
    
    public CardSession(int cardId, int customerId,String session, long timestamp){
      super(session);
      this.cardId = cardId;
      this.customerId = customerId;
      this.timestamp = timestamp;
    }
    
    public int getCardId(){
      return cardId;
    }
    
    public void setCardId( int cardId){
       this.cardId = cardId;
    }
    
    public int getCustomerId(){
      return customerId;
    }
    
    public void setCustomerId( int customerId){
       this.customerId = customerId;
    }
}

