<%--
    Document   : index
    Created on : Jul 16, 2014, 6:14:35 PM
    Author     : ebrima
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
   String current_user = (String)session.getAttribute("current_user") ;
   String current_card = (String)session.getAttribute("current_card") ;
%>
