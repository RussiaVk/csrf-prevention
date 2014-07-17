<%@page import="model.classes.User"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <% List users = (List)request.getAttribute("users"); 
  %>
  
        <div>
            <ul class="nav nav-pills">
                <li> <button class="btn btn-primary  btn-sm" id="addUser">Add User</button></li>
            </ul>
        </div>
        <div style="margin-top:10px;">
            <table class="table table-striped table-hover">
                <tr><th>First Name</th><th>Last Name</th><th>User Name</th></tr>
             <%
              if  (users != null ){
                  for (Iterator iterator = users.iterator(); iterator.hasNext();) {
                    User user = (User) iterator.next();  
               %>
                <tr>
                    <td> <%=user.getFirstName()%> </td>
                    <td> <%=user.getLastName()%> </td>
                    <td> <%=user.getUsername()%> </td>
                </tr>
              <%   } 
              }%>


            </table>
        </div>