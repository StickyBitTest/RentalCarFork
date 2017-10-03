        <%--
  Created by IntelliJ IDEA.
  User: yuliia
  Date: 29.09.17
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Rental Car</title>
    <%@include file="base.jsp"%>

    <script type="text/javascript">
        $(document).ready(function () {

            $('#endDate').datepicker({
                changeMonth: true,
                changeYear : true,
                onSelect: function () { },
                onClose: function () { $(this).focus(); }
            });


            $('#startDate').datepicker({
                changeMonth:true,
                changeYear: true,
                onSelect:
                    function (dateText, inst) {
                        $('#endDate').datepicker("option", 'minDate', new Date(dateText));
                    }
                ,
                onClose: function () { $(this).focus(); }
            });
        });
    </script>
</head>
<body>

    <%@include file="header.jsp"%>


    <header class="masthead">
    <div class="overlay">
        <div class="header-search">
            <div class="container">
                <h2 class="text text-white">The easiest way to find your car</h2>
                <div class="search">
                    <form class="form-inline" action="/rental/Search">
                        <div class="form-group has-success"  >
                            <input id="startDate" class="form-control" name="startDate"
                                   placeholder="Pick up date" required>
                        </div>
                        <div class="form-group has-success">
                            <input class="form-control" placeholder="Drop off date" name="endDate"
                                   id="endDate" required>
                        </div>
                        <button class="btn btn-info text-white"  type="submit">Find</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</header>

    <div class="empty"></div>
    <div class="container">
        <%@include file="../html/info.html"%>
    </div>

    <div>
        <%@include file="../html/footer.html"%>
    </div>
</body>
</html>