

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bank Login Page</title>
    </head>
    <body>
        <div>
             <h2> Bank User Login</h2>
             <form role="form" action="bank" method="POST">
                <div class="form-group">
                    <label for="user-name">Username</label>
                    <input type="text" class="form-control" id="user-name" name="username" placeholder="UserName">
                  </div>
                  <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                  </div>
                  <div class="form-group">
                     <input type="text"  id="bankRealPerson" name="realPerson">
                   </div>
                  <button type="submit" class="btn btn-primary btn-lg">Submit</button>
            </form>
        </div>
    </body>
</html>
