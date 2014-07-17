/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *<Account,Transaction>
 * @author ebrima
 */
public class Customer implements Serializable{
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    
    private Set<Account> accounts = new HashSet<Account>(0);
    
    Customer(){}
    
    public Customer(String firstName,String lastName,  String address, String email,String phone){
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.email = email;
      this.phone = phone;
    }
    
    public String getFirstName(){
      return firstName;
    }
    
    public void setFirstName(String firstName){
       this.firstName = firstName;
    }
    
    public String getLastName(){
      return lastName;
    }
    
    public void setLastName(String lastName){
       this.lastName = lastName;
    }
    
    public int getId(){
     return id;
    }
    public void setId(int id)
    {
     this.id = id;
    }
    
    public String getEmail(){
      return email;
    }
    
    public void setEmail(String email){
       this.email = email;
    }
    
    public String getPhone(){
      return phone;
    }
    
    public void setPhone(String phone){
       this.phone = phone;
    }
    
    public String getAddress(){
      return address;
    }
    
    public void setAddress(String address){
       this.address = address;
    }
    
    public Set<Account> getAccounts() {
      return accounts;
   }
   public void setAccounts( Set<Account> accounts ) {
      this.accounts = accounts;
   }
    
}
