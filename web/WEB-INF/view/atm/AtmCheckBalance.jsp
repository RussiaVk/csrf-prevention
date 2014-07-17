<%-- 
    Document   : AtmCheckBalance
    Created on : Jul 14, 2014, 3:14:05 PM
    Author     : tune
--%>

<%-- 
    Document   : AtmTransaction
    Created on : Jul 14, 2014, 1:42:35 PM
    Author     : tune
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ATM Transaction</title>
       
        <link rel="stylesheet" href="css/ATM_style.css" media="screen" type="text/css" /> 
        <script type="text/javascript" src="javascripts/webapp.js"></script>

    </head>
    <body>
        
        <% String amount = (String)request.getAttribute("amount"); %>
        
        <table class="center">
            <col width="100px" />
            <col width="250px" />
            <col width="250px" />
            <col width="100px" />
            <tr>
                <td colspan="4">
                    <div class="fontStyle-header">
                        welcome to the Programmers Bank
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="4" height="120">
                    <div class="fontStyle-menu">
                        Balance Checking
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div>
                        <a href="#" class="button">></a>
                    </div>
                </td>
                <td colspan="2">
                    <div class="fontStyle-center">
                        Amount Money: <%=amount%>$
                    </div>
                </td>
                <td>
                    <div>
                        <a href="#" class="button"><</a>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div>
                        <a href="#" class="button">></a>
                    </div>
                </td>
                <td>
                    <div class="fontStyle-left">
                      
                    </div>
                </td>
                <td>
                    <div class="fontStyle-right">
                 
                    </div>
                </td>
                <td>
                    <div>
                        <a href="#" class="button"><</a>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div>
                        <a href="atm" class="button">></a>
                    </div>
                </td>
                <td>
                    <div class="fontStyle-left">
                        menu
                    </div>
                </td>
                <td>
                    <div class="fontStyle-right">
                        log out
                    </div>
                </td>
                <td>
                    <div>
                        <a href="logout" class="button logout"><</a>
                    </div>
                </td>
            </tr>
            
        </table>

        
        
    </body>
</html>

