<%-- 
    Document   : atm_login
    Created on : Jul 13, 2014, 7:45:51 PM
    Author     : ebrima
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ATM User login</title>
    </head>
    <body>
        <div>
           <h2> ATM User Login</h2>
           <form role="form" action="atm" method="POST">
             <div class="form-group">
                <label for="card-number">Card Number</label>
                <input type="text" class="form-control" id="card-number" name="cardNumber" placeholder="Card number">
              </div>
              <div class="form-group">
                <label for="pin-code">Pin code</label>
                <input type="password" class="form-control" id="pin-code" name="pinCode" placeholder="Pin code">
              </div>
              <div class="form-group">
                <input type="text"  id="atmRealPerson" name="atmRealPerson">
              </div>
              <button type="submit" class="btn btn-primary btn-lg">Submit</button>
            </form>
        </div>
    </body>
</html>
