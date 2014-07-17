
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.classes.User"%>
<% User currentUser = (User)session.getAttribute("current_user") ;%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="vendor/bootstrap-3.1.1/dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
         <link  rel="stylesheet" href="stylesheets/webapp.css" />
        <script type="text/javascript" src="vendor/jquery/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="vendor/bootstrap-3.1.1/dist/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="vendor/bootstrap-3.1.1/js/tab.js"></script>
        <script type="text/javascript" src="javascripts/webapp.js"></script>
        <title>Bank Account Page</title>
    </head>
    <body>    
        <div style=" margin: auto; width: 80%;">
         <div class="jumbotron" style="position:relative;width: 80%; margin: 20px auto auto;padding-bottom: 10px;padding-top: 10px;">
                 <div style="display: inline-block;">
                    <h3>Welcome to the Bank Account page</h3>
                  </div>
                    <div style="display: inline-block; position: absolute; right: 20px; top: 20%;">
                        <span><%= currentUser.getFirstName() %></span>
                        <form action="logout" method="post" role="form">
                                 <button type="submit" class="btn btn-primary">Logout</button><br>
                         </form>
                    </div>
              </div>
        <div style="width: 80%; margin: 10px auto auto;" >
                <ul class="nav nav-tabs nav-justified" role="tablist">
                    <li class="active"><a href="#bankAccountsBank" role="tab" data-toggle="tab">Accounts</a></li>
                    <li><a href="#transactions" role="tab" data-toggle="tab">Transactions</a></li>
                    <li><a href="#bankUsers" role="tab" data-toggle="tab">Users</a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active bank-container-pane" id="bankAccountsBank">
                         <%@include file="account_list.jsp" %>
                    </div>
                    <div class="tab-pane bank-container-pane" id="transactions">
                         Transactions
                    </div>
                    <div class="tab-pane bank-container-pane" id="bankUsers">
                          <%@include file="user_list.jsp" %>
                    </div>
               </div> 
        </div>
        </div>
    </body>
</html>
