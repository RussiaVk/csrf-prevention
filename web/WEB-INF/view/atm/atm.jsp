<%-- 
    Document   : AtmTransaction
    Created on : Jul 14, 2014, 1:42:35 PM
    Author     : tune
--%>

<%@page import="model.classes.Card"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Card currentCard = (Card)session.getAttribute("current_card") ;%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ATM Transaction</title>
        <link rel="stylesheet" href="css/ATM_style.css" media="screen" type="text/css" />  
         <script type="text/javascript" src="vendor/jquery/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="javascripts/webapp.js"></script>
    </head>
    <body>
        <div>Welcome :
       <%   if(currentCard == null)
                response.sendRedirect("atm");
            else {   
       %>
                <%= currentCard.getCustomer().getFirstName() %>  <%= currentCard.getCustomer().getLastName() %> 
        <%  }  %>
        </div>
        <div id="atm-main-container">
            <%@include  file="AtmTransaction.jsp" %>
        </div>
    </body>
</html>
