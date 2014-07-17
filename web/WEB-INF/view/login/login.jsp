<%-- 
    Document   : login
    Created on : Jul 13, 2014, 5:57:05 AM
    Author     : ebrima
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <%
        String reference = (String)request.getAttribute("reference");
        String errorMsg = (String)request.getAttribute("errorMsg");
      %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link href="vendor/bootstrap-3.1.1/dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="stylesheet" type="text/css" href="vendor/jquery/realperson/jquery.realperson.css">
        <link rel="stylesheet" type="text/css" href="stylesheets/webapp.css">
        
        <script src="vendor/jquery/jquery-1.11.1.min.js"></script>
        <script src="vendor/bootstrap-3.1.1/dist/js/bootstrap.min.js"></script>
        <% if (reference == null) {  %>
          <script src="vendor/bootstrap-3.1.1/js/tab.js"></script>
         <% } %>
         <script>
          $salt = <%=(String)request.getAttribute("realPersonSalt")  %> 
        </script>
        <script type="text/javascript" src="vendor/jquery/realperson/jquery.plugin.js"></script> 
        <script type="text/javascript" src="vendor/jquery/realperson/jquery.realperson.js"></script>
        <script type="text/javascript" src="javascripts/webapp.js"></script>
         <title>Bank ATM Login page</title>
    </head>
    <body>
        <h1 class="center-block" style="text-align: center;margin-bottom: 20px;">Welcome to Farask Bank</h1>
        <div>
            <div class="center-block main-login-container" >
                <%
                      if (errorMsg != null) {       
                 %>
                       <div>
                           <span style="color:red"><%= errorMsg %></span>
                       </div>
                 <% } %>
                 <div>
                     <%
                      if(reference != null && reference.equals("atm")){
                    %>
                          <%@include file= "atm_login.jsp" %>
                    <%
                      }
                      else  if(reference != null && reference.equals("bank")){
                    %>
                          <%@include file= "bank_login.jsp" %>
                    <% } 
                      else {
                      %>
                         <!-- Nav tabs -->
                            <ul class="nav nav-pills nav-justified" role="tablist">
                              <li class="active"><a href="#bank" role="tab" data-toggle="tab">Bank</a></li>
                              <li><a href="#atm" role="tab" data-toggle="tab">ATM Machine</a></li>
                            </ul>

                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div class="tab-pane active" id="bank">
                                    <%@include file= "bank_login.jsp" %>
                                </div>
                              <div class="tab-pane" id="atm">
                                    <%@include file= "atm_login.jsp" %>
                              </div>
                            </div>                   
                     <% }%>
                 </div>
            </div>
        </div>
    </body>
</html>
