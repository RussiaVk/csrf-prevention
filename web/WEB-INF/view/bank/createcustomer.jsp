<%-- 
    Document   : createcustomer
    Created on : Jul 14, 2014, 2:15:54 PM
    Author     : fara1_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="vendor/bootstrap-3.1.1/dist/css/bootstrap.min.css" rel="stylesheet" media="screen"> 
        <script src="vendor/jquery/jquery-1.11.1.min.js"></script>
        <script src="vendor/bootstrap-3.1.1/dist/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="javascripts/webapp.js"></script>
        <title>Create New Account</title>
    </head>
    <body>
        <div class="create-customer-form-container">
            <div id="createCustomerErrorMsg"></div>
            <form action="bank/createcustomer" method="post" role="form" id="createCustomer"><br>
                <fieldset><legend>Create Customer Profile</legend>
                    <table
                        <tr><td>First name:</td><td> <input type="text" name="firstname" /></td></tr>
                        <tr><td>Last name:</td> <td><input type="text" name="lastname" /></td></tr>
                        <tr><td>Address:</td><td><input type="text" name="address" /></td></tr>
                        <tr><td>Email:</td><td><input type="text" name="email" /></td><tr>
                        <tr><td>Phone:</td><td><input type="text" name="phone" /></td></tr>
                    </table>

                </fieldset>
                <fieldset><legend>Enter Account Information</legend>
                    <table>

                        <tr><td>Account Type:<input type="radio" name="accountType" value="Saving"/></td><td>Saving
                        <input type="radio" name="accountType" value="Checking" />Checking</td></tr>
                        <tr><td>Initial Amount:</td><td><input type="text" name="openinngAmount" /></td></tr>
                    </table>

                    <table>                       
                        <tr><td> <input type="radio" name="atmCard" value="true"/></td><td>ATM Card</td></tr>
                        <tr><td> <input type="radio" name="atmCard"value="false"/></td><td>No ATM Card</td></tr>
                    </table>
                </fieldset>
                <table>


                    <tr><td><button type="submit" class="btn btn-primary">Save</button></td>
                        <td><button type="button" class="btn btn-primary" id="closeCustomerForm">Cancel</button></td></tr>
                </table>
            </form>

        </div>
    </body>
</html>
