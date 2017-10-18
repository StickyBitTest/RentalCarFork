<%--
  Created by IntelliJ IDEA.
  User: yuliia
  Date: 07.10.17
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>

    $(function() {

        // We can attach the `fileselect` event to all file inputs on the page
        $(document).on('change', ':file', function() {
            var input = $(this),
                numFiles = input.get(0).files ? input.get(0).files.length : 1,
                label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [numFiles, label]);
        });

        // We can watch for our custom `fileselect` event like this
        $(document).ready( function() {
            $(':file').on('fileselect', function(event, numFiles, label) {

                var input = $(this).parents('.input-group').find(':text'),
                    log = numFiles > 1 ? numFiles + ' files selected' : label;

                if( input.length ) {
                    input.val(log);
                } else {
                    if( log ) alert(log);
                }

            });
        });

    });
</script>
<div class="modal fade" id="addCarModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="title text">Add new car</h4>
            </div>

            <div class="modal-body">
                <form data-toggle="validator" id="loginForm" role="form" action="/rental/AddCar" enctype = "multipart/form-data" method="post" novalidate="true">
                    <div class="form-group has-warning has-error has-danger">
                        <label for="car-model" class="text-warning control-label">Car model:</label>
                        <input name="model" class="form-control" placeholder="Car model" id="car-model" maxlength="30" required="" data-match-error="Empty car model">

                    </div>

                    <div class="form-group">
                        <label for="car-transmission" class="text-warning control-label">Transmission type: </label>
                        <select id="car-transmission" class="form-control" name="transmission">
                            <option>AUTOMATIC</option>
                            <option>MANUAL</option>
                            <option>CVT</option>
                            <option>SEMI_AUTOMATIC</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="car-vehicle" class="text-warning control-label">Vehicle type: </label>
                        <select id="car-vehicle" class="form-control" name="vehicle">
                            <option>SEDAN</option>
                            <option>SUV</option>
                            <option>PICKUP</option>
                            <option>COUPE</option>
                            <option>MINIVAN</option>
                            <option>WAGON</option>
                            <option>HATCHBACK</option>
                            <option>VAN</option>
                        </select>
                    </div>
                    <div class="form-group has-error has-warning has-danger">
                        <label for="car-price" class="text-warning control-label">Daily price:</label>
                        <input name="daily_price" class="form-control" placeholder="$50.00" id="car-price">
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <label class="input-group-btn">
                            <span class="btn btn-primary">
                        Browse image… <input style="display: none;" multiple="" type="file" name="img_file">
                            </span>
                            </label>
                            <input class="form-control" readonly="" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <input class="btn btn-default btn-group-justified text disabled" value="Submit" type="submit">
                    </div>


                </form>
            </div>
        </div>

    </div>
</div>
