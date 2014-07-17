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
public class UserRole {
    private int id;
    private int userId;
    private String role;
    
    public UserRole(){}
    
    public UserRole(int userId, String role){
     this.userId = userId;
     this.role = role;
    }
    
    public int getId(){
       return id;
    }
    
    public void setId(int id){
       this.id = id;
    }
    
    public int getUserId(){
       return userId;
    }
    
    public void setUserId(int userId){
       this.userId = userId;
    }
    
    public String getRole(){
       return role;
    }
    
    public void setRole(String role){
       this.role = role;
    }
}
