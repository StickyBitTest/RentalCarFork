<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: yuliia
  Date: 01.10.17
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<header>
    <%@include file="../base.jsp"%>

    <script>
        $(document).ready(function () {
            $('#queryForm').validator();

            $('#expires').datepicker({
                changeMonth: true,
                changeYear : true,
                dateFormat : 'mm/yy',
                minDate : new Date(),
                onSelect: function () { },
                onClose: function () { $(this).focus(); }
            });

            $('#queryForm').on('show.bs.modal', function (e) {
                el2 = e.relatedTarget.parentNode.parentNode;
                console.log(el2);

                idEl = el2.getElementsByTagName('h1')[0].innerText;
                document.getElementById("carID").value = idEl;

                imgEl = el2.getElementsByTagName('img');
                var $imgSrc = imgEl[0].src;
                document.getElementById('imgQueryForm').src = $imgSrc;

                divEl = el2.getElementsByTagName('div')[0];

                h3El = divEl.getElementsByTagName('h3');
                document.getElementById('modelQueryForm').innerText = h3El[0].innerText;

                rowDiv = divEl.getElementsByTagName('div');
                perDayPrice= rowDiv[0].getElementsByTagName('h4')[1];
                document.getElementById('perDayPriceForm').innerText = perDayPrice.innerText;
                totalPrice= rowDiv[1].getElementsByTagName('h6')[1];
                document.getElementById('totalPriceForm').innerText = totalPrice.innerText;

            });

        });

    </script>
</header>
<body>
<%@include file="../header.jsp"%>


<div class="empty"></div>
<div class="empty"></div>

<div class="container">
    <div class="row text-center">

    <c:forEach items="${cars}" var="car" varStatus="rowCount">

        <div class="col-lg-4 col-md-6 mb-4">
            <div class="card car_item " style="height: 70%">
            <img src="../../img/cars/${car.imgFile}.jpg" class="car_img">
                <h1 hidden>${car.id}</h1>
            <div class="card-body">
                <h3 class="card-title text">${car.model}</h3>
                <div class="row ">
                    <h4 class="col-lg-6 text-right">per day</h4>
                    <h4 class="col-lg-3 text-left">$${car.dailyPrice}</h4>
                </div>
                <div class="row">
                    <h6 class="col-lg-6 text-right">total</h6>
                    <h6 class="col-lg-6 text-left">
                        <fmt:formatNumber value = "${car.dailyPrice * days}" type = "currency"/>
                    </h6>
                </div>
            </div>
            <div class="card-footer">
                <a class="text btn btn-info" data-toggle="modal" data-target="#queryForm">Choose</a>
            </div>
        </div>
        </div>

    </c:forEach>
        </div>
    <div class="empty"></div>
</div>

<div>
    <%@include file="../../html/footer.html"%>
</div>


<!-- Modals -->

<div class="modal fade" id="queryForm" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="title text">Fill form to rent a car</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="card car_item">
                            <img class="car_img "  id="imgQueryForm">
                            <div class="card-body">
                                <div class="row">
                                    <h5 class="col-lg-6">Car model:</h5>
                                    <h5 class="col-lg-6 text-right" id="modelQueryForm"></h5>
                                </div>
                                <div class="row">
                                    <h5 class="col-lg-6">Pick up</h5>
                                    <h5 class="col-lg-6 text-right">
                                        <fmt:formatDate pattern = "MM/dd/yyyy"
                                                        value = "${term.pickUp}" />
                                        </h5>
                                </div>
                                <div class="row">
                                    <h5 class="col-lg-6">Drop off</h5>
                                    <h5 class="col-lg-6 text-right">
                                        <fmt:formatDate pattern = "MM/dd/yyyy"
                                                        value = "${term.dropOff}" />
                                    </h5>
                                </div>
                                <div class="row">
                                    <h5 class="col-lg-6">Price per day</h5>
                                    <h5 class="col-lg-6 text-right" id="perDayPriceForm">$40</h5>
                                </div>
                                <div class="row">
                                    <h5 class="col-lg-6">Total</h5>
                                    <h5 class="col-lg-6 text-right" id="totalPriceForm">$150</h5>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div>
                            <form data-toggle="validator" method="post" action="/rental/NewOrder" id="newOrder" >
                                <div class="form-group has-warning">
                                    <label for="name" class="text-warning">Full Name:</label>
                                    <input class="form-control" placeholder="Full name" id="name"
                                           required minlength="3" maxlength="50" name="full_name">
                                    <div class="help-block with-errors"></div>
                                </div>
                                <div class="form-group has-warning">
                                    <label for="email" class="text-warning">Email address:</label>
                                    <input class="form-control " placeholder="E-mail" type="email" required name="email"
                                           id="email" pattern="(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$)">
                                    <div class="help-block with-errors"></div>
                                </div>
                                <div class="form-group has-warning">
                                    <label for="passport" class="text-warning">Passport Number:</label>
                                    <input class="form-control" placeholder="Passport No." name="passport"
                                           id="passport" required minlength="5" maxlength="20">
                                    <div class="help-block with-errors"></div>
                                </div>
                                <div class="form-group has-warning">
                                    <label for="credit-number" class="text-warning">Credit card number:</label>
                                    <input class="form-control" placeholder="Credit Card No." name="card_number"
                                           id="credit-number" pattern="\d{16}" required>
                                    <div class="help-block with-errors"></div>
                                </div>
                                <div class="form-group has-warning ">
                                    <div class="row">
                                        <div class="col-lg-2">
                                            <label for="expires" class="text-warning">Expires:</label>
                                        </div>
                                        <div class="col-lg-5">
                                            <input class="form-control" placeholder="Expires" id="expires"
                                                   required type="date" name="card_expires">
                                            <div class="help-block with-errors"></div>
                                        </div>
                                        <div class="col-lg-2">
                                            <label for="cvv2" class="text-warning">CVV2:</label>
                                        </div>
                                        <div class="col-lg-3">
                                            <input class="form-control" placeholder="CVV2" id="cvv2" type="password"
                                                   required pattern="\d{3}" name="cvv2">
                                        </div>
                                    </div>
                                </div>
                                <input hidden name="car_id" id="carID" type="hidden">
                                <div class="form-group">
                                    <input class="btn btn-default btn-group-justified text" value="Submit" type="submit">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>