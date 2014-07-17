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
public class Session {
    private int id;
    private String session;
    protected long timestamp;
    
    public Session(){
    }
    
    public Session(String session){
     this.session = session;
    }
    
    public Session(String session, long timestamp){
     this.session = session;
     this.timestamp = timestamp;
    }
    
    public int getId(){
     return id;
    }
    
    public void  setId(int id){
      this.id = id;
    }
    
    public String getSession(){
     return session;
    }
    
    public void  setSession(String session){
      this.session = session;
    }
    
    public long getTimestamp(){
     return timestamp;
    }
    
    public void  setTimestamp(long timestamp){
      this.timestamp = timestamp;
    }
}
