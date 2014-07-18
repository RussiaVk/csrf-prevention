<%-- 
    Document   : account_list
    Created on : Jul 15, 2014, 4:15:22 PM
    Author     : ebrima
--%>

<%@page import="model.classes.Transaction"%>
<%@page import="model.classes.Account"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <% List transactions = (List)request.getAttribute("transactions"); 
  %>
 <div>
  <ul class="nav nav-pills">
       <li> 
           <button class="btn btn-primary  btn-sm" id="refreshTransactions">
               Refresh
          </button>
       </li>
  </ul>
</div>
<div style="margin-top:10px;">
    <table class="table table-striped table-hover">
        <tr>
            <th>Account No.</th>
            <th>Type of Transaction</th>
            <th>Amount</th>
            <th>Date</th>
        </tr>
     <%
      if  (transactions != null ){
          for (Iterator iterator = transactions.iterator(); iterator.hasNext();) {
            Transaction transaction = (Transaction) iterator.next(); 
             Account account = (Account)transaction.getAccount();
       %>
        <tr>
            <td><%= (account != null)? account.getAccountNo(): "" %></td>
            <td><%=transaction.getType() %></td>
            <td> <%=transaction.getAmount() %> </td>
            <td> <%=transaction.getDate().toString() %> </td>
        </tr>
      <%   } 
      }%>
                       
    
    </table>
</div>