<%-- 
    Document   : account_list
    Created on : Jul 15, 2014, 4:15:22 PM
    Author     : ebrima
--%>

<%@page import="model.classes.Account"%>
<%@page import="model.classes.Customer"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <% List accounts = (List)request.getAttribute("accounts"); 
  %>
 <div>
  <ul class="nav nav-pills">
       <li> 
           <button class="btn btn-primary  btn-sm" id="addCustomer">
               Add Customer
          </button>
           <li> 
           <button style="margin-left:20px;" class="btn btn-primary  btn-sm" id="addCustomer">
               Deposit
           </button>
       </li>
  </ul>
</div>
<div style="margin-top:10px;">
    <div id="inline-form-submit-message"></div>
    <table class="table table-striped table-hover">
        <tr>
            <th></th>
            <th>Acc No.</th>
            <th>Balance</th>
            <th>Customer</th>
            <th></th>
        </tr>
     <%
      if  (accounts != null ){
          for (Iterator iterator = accounts.iterator(); iterator.hasNext();) {
            Account account = (Account) iterator.next();  
       %>
        <tr>
            <td><input type="checkbox" name="id" value="<%=account.getId()%>" /></td>
            <td><%=account.getAccountNo()%></td>
            <td><%=account.getAmount()%></td>
            <td>
                <% Customer customer = account.getCustomer(); %>
                <%= (customer == null)? "": customer.getFirstName() %>
            </td>
            <td>
                <button type="button" class="inline-deposit_button btn btn-default  btn-sm" 
               data-container="body" data-toggle="popover" data-placement="left" 
              data-content='<form action="bank/deposit" method="POST" class="deposit-inline-form">
                             <span>Deposit</span>
                              Amount: <input type="text" name="depositAmount"/><br/> 
                              <input type="hidden" name="accountId" value="<%= account.getId()%>" /> <br />
                             <button class="btn btn-primary  btn-sm close-popover">Cancel</button>
                             <button class="btn btn-primary  btn-sm submit-form-popover"type="submit" >Save</button>
                            </form>'
                       >
                 <span class="glyphicon glyphicon-plus"></span>
               </button>
                </td>
        </tr>
      <%   } 
      }%>
                       
    
    </table>
</div>