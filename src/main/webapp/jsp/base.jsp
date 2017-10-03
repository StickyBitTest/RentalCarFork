<%--
  Created by IntelliJ IDEA.
  User: yuliia
  Date: 29.09.17
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript" src="../js/validator.min.js"></script>

<link href="../css/custom_hw.css" type="text/css" rel="stylesheet">
<link href="../css/new_bootstrap.css" type="text/css" rel="stylesheet">
<link href="../css/bootstrap_theme.css" type="text/css" rel="stylesheet">
<link href="../css/jquery-ui.css" type="text/css" rel="stylesheet">

<script>
    $(document).ready(function () {
        returnElements = document.querySelectorAll('[id=return]');
        var i;
        for(i in returnElements){
           returnElements[i].value = location.pathname;
        }
        }
    );
</script>