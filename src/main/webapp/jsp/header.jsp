<%@ page import="com.rentalcar.models.Account" %><%--
  Created by IntelliJ IDEA.
  User: yuliia
  Date: 30.09.17
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty sessionScope.lang ? sessionScope.lang :
        not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="language" />

<% String message = null;
    if (request.getSession().getAttribute("error") != null) {
        message = (String) request.getSession().getAttribute("error");
%>
<script>
    $(window).on('load', function () {
        $('#errorDialog').modal('show');
    });
</script>

<%  request.getSession().removeAttribute("error"); } %>

<div>
    <script type="text/javascript" src="../js/validator.min.js"></script>
    <script src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#loginForm').validator();
            $('#signUpForm').validator();
        });
    </script>

    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a href="#" class="navbar-brand">CarRental</a>
                <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav navbar-right">
                    <% if (session.getAttribute("user") == null) {%>

                    <li><a  class="text" data-toggle="modal" data-target="#loginModal">
                        <fmt:message key="LOGIN" /></a>
                    </li>
                    <li><a class="text" data-toggle="modal" data-target="#signupModal">
                        <fmt:message key="SIGNUP" />
                    </a></li>
                    <% } else {
                        if(((Account)session.getAttribute("user")).isAdmin()){ %>
                        <li>
                            <a class="text" href="/rental/Cars"><fmt:message key="CARS"/></a>
                        </li>
                        <li>
                            <a class="text"><fmt:message key="ORDERS"/></a>
                        </li>

                    <%
                        }
                    %>

                    <li>
                        <a class="text"><fmt:message key="WELCOME"/>
                            <c:out value='<%= ((Account) session.getAttribute("user")).getLogin() %>'/>
                        </a>
                    </li>
                    <li>
                        <a class="text" href="/rental/Logout"><fmt:message key="LOG_OUT"/> </a>
                           <!-- <input id="return" type="hidden" name="return"> -->
                    </li>

                    <% }%>
                    <li>
                        <form method="post" action="/rental/Language">
                            <select name="lang"  onchange="this.form.submit()" class="btn btn-info">
                                <option selected>en</option>
                                <option value="en">en</option>
                                <option value="ru">ru</option>
                            </select>
                            <input id="return" type="hidden" name="return">
                        </form>
                    </li>
                </ul>

            </div>
        </div>
    </div>
    <!-- Modals -->
    <%@include file="account/login.jsp"%>
    <%@include file="account/signup.jsp"%>
</div>
<div id="header"></div>


<div  class="modal fade" id="errorDialog" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="title text">Oops!</h4>
            </div>

            <div class="modal-body">
                <h6  class="text-warning control-label">
                    <%=message%>
                </h6>
            </div>
        </div>
    </div>
</div>