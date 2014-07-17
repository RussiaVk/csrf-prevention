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
               add Customer
          </button>
       </li>
  </ul>
</div>
<div style="margin-top:10px;">
    <table class="table table-striped table-hover">
        <tr>
            <th>Acc No.</th>
            <th>Transaction</th>
            <th>Customer</th>
        </tr>
     <%
      if  (accounts != null ){
          for (Iterator iterator = accounts.iterator(); iterator.hasNext();) {
            Account account = (Account) iterator.next();  
       %>
        <tr>
            <td><%=account.getAccountNo()%></td>
            <td><%=account.getAmount()%></td>
            <td>
                <% Customer customer = account.getCustomer(); %>
                <%= (customer == null)? "": customer.getFirstName() %>
            </td>
        </tr>
      <%   } 
      }%>
                       
    
    </table>
</div>