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
public class UserSession extends Session {
    private int userId;
    public UserSession(){
         super();
    }
    
    public UserSession(int userId, String session){
      super(session);
      this.userId = userId;
    }
    
    public UserSession(int userId, String session, long timestamp){
      super(session);
      this.userId = userId;
      this.timestamp = timestamp;
    }
    
    public int getUserId(){
      return userId;
    }
    
    public void setUserId( int userId){
       this.userId = userId;
    }
}
