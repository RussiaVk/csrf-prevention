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
        <title>Create New User</title>
    </head>
    <body>
        <div class="create-customer-form-container">
            <form action="bank/createuser" method="post" role="form" id="createUser"><br>
                <fieldset><legend>Create User Profile</legend>
                    <table
                        <tr><td>First name:</td><td> <input type="text" name="firstname" /></td></tr>
                        <tr><td>Last name:</td> <td><input type="text" name="lastname" /></td></tr>
                        <tr><td>User Name:</td><td><input type="text" name="username" /></td></tr>
                        <tr><td>Password:</td><td><input type="text" name="password" /></td><tr>
                    </table>
                </fieldset>
                <fieldset><legend>User Role</legend>
                    <table>                       
                    <tr><td> <input type="radio" name="role" value="administrator"/></td><td>Administrator</td></tr>
                    <tr><td> <input type="radio" name="role"value="account manager"/></td><td>Accounts Manager</td></tr>
                    </table>
                </fieldset>
                <table>
                <tr><td><button type="submit" class="btn btn-primary">Save</button></td>
                <td><button type="button" class="btn btn-primary" id="closeUserForm">Cancel</button></td></tr>
                </table>
            </form>

        </div>
    </body>
</html>
