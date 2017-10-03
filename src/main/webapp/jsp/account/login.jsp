<%--
  Created by IntelliJ IDEA.
  User: yuliia
  Date: 29.09.17
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal fade" id="loginModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="title text">Log in to your account</h4>
            </div>

            <div class="modal-body">
                <form data-toggle="validator" id="loginForm" role="form" action="/rental/Login" method="post">
                    <div class="form-group has-warning">
                        <label for="login-user" class="text-warning control-label">User:</label>
                        <input class="form-control" placeholder="Login" id="login-user" maxlength="30"
                               required data-match-error="Empty username" name="login">
                        <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group has-warning">
                        <label for="login-password" class="control-label text-warning">Password:</label>
                        <input class="form-control " placeholder="Password" type="password" name="password"
                               id="login-password" maxlength="50" required data-match-error="Empty password">
                        <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                        <input class="btn btn-default btn-group-justified text" value="Login" type="submit">
                    </div>
                    <%@ page isELIgnored="false" %>

                    <input id="return" type="hidden" name="return">
                </form>
            </div>

        </div>

    </div>
</div>
