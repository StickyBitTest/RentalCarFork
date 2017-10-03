<div class="modal fade" id="signupModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="title text">Sign up once</h4>
            </div>

            <div class="modal-body">
                <form data-toggle="validator" id="signUpForm" action="/rental/Signup" method="post">
                    <div class="form-group has-warning">
                        <label for="signup-username" class="control-label text-warning">Username:</label>
                        <input class="form-control" placeholder="Username" minlength="1"
                               maxlength="30" id="signup-username" name="login" required>
                        <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group has-warning">
                        <label for="signup-email" class="control-label text-warning">E-Mail:</label>
                        <input class="form-control" placeholder="Email Address" type="email"
                               pattern="(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$)"
                               id="signup-email" name="email" required>
                        <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group has-warning">
                        <label for="password" class="text-warning">Password:</label>
                        <input class="form-control " placeholder="Password" type="password"
                               id="password" required minlength="1" maxlength="50" name="password">
                        <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group has-warning">
                        <label for="signup-confirm" class="text-warning">Confirm password:</label>
                        <input class="form-control" placeholder="Confirm password" required
                               data-match="#password" type="password" id="signup-confirm"
                               data-match-error="Passwords must matches!" name="confirmPassword">
                        <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                        <input class="btn btn-default btn-group-justified text" value="Sign up" type="submit">
                    </div>
                    <%@ page isELIgnored="false" %>

                    <input id="return" type="hidden" name="return">
                </form>
            </div>

        </div>

    </div>
</div>
