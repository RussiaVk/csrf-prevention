<%-- 
    Document   : deposit
    Created on : Jul 14, 2014, 2:19:53 PM
    Author     : fara1_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="vendor/bootstrap-3.1.1/dist/css/bootstrap.min.css" rel="stylesheet" media="screen">      
        <script src="vendor/jquery/jquery-1.11.1.min.js"></script>
        <script src="vendor/bootstrap-3.1.1/dist/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="javascripts/webapp.js"></script>
        <title>Deposit</title>
    </head>
    <body>  
        <div>            
            <form action="deposit" method="post" role="form"><br>
                Account Number: <input type="text" name="accountno"><br>     
                Amount to Deposit: <input type="text" name="amount"><br>   
            <button type="submit" class="btn btn-primary">Deposit</button>
            </form>
        </div>
    </body>
</html>
