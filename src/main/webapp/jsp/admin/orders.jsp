<%--
  Created by IntelliJ IDEA.
  User: yuliia
  Date: 10.10.17
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Car rental</title>
    <%@include file="../base.jsp"%>
    <script>
        $(document).ready(function () {
           $('#queryDetail').on('show.bs.modal', function (e) {
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

</head>
<body>
<%@include file="../header.jsp"%>
<div class="empty"></div>
<div class="empty"></div>
<div class="container container-fluid wrap">
    <div class="bs-component">
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th>Order id</th>
                <th>Car model</th>
                <th>Pick up date</th>
                <th>Drop off date</th>
                <th>Total $</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${orders}" var="order" varStatus="rowCount">
                    <tr class="success">
                        <th>${order.id}</th>
                        <th>${order.car.model}</th>
                        <th>${order.term.pickUp}</th>
                        <th>${order.term.dropOff}</th>
                        <th>${order.totalPrice}</th>
                        <th>
                            <a class="text-success" data-toggle="modal" data-target="#queryDetail">${order.status}</a>
                        </th>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<!-- modals -->
<div class="modal fade" id="queryDetail" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">x</button>
                <h4 class="title text">Query details #1 </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="card car_item">
                            <img class="car_img " id="carID">
                            <div class="card-body">
                                <div class="row">
                                    <label class="col-lg-6">Car Model:</label>
                                    <h5 class="col-lg-6 text-right" id="carModel"></h5>
                                </div>
                                <div class="row">
                                    <label class="col-lg-6">Pick up date:</label>
                                    <h5 class="col-lg-6 text-right" id="pickUpDate"></h5>
                                </div>
                                <div class="row">
                                    <label class="col-lg-6">Drop off date:</label>
                                    <h5 class="col-lg-6 text-right" id="dropOffDate"></h5>
                                </div>
                                <div class="row">
                                    <label class="col-lg-6 ">Price per day</label>
                                    <h5 class="col-lg-6 text-right" id="pricePerDay"></h5>
                                </div>
                                <div class="row">
                                    <label class="col-lg-6 ">Total</label>
                                    <h5 class="col-lg-6 text-right" id="totalPrice"></h5>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div>
                            <div class="row">
                                <label class="col-lg-6">Username:</label>
                                <h5 class="col-lg-6 text-right" id="clientName"></h5>
                            </div>
                            <div class="row">
                                <label class="col-lg-6">Email:</label>
                                <h5 class="col-lg-6 text-right" id="clientEmail"></h5>
                            </div>
                            <div class="row">
                                <label class="col-lg-6">Passport Number:</label>
                                <h5 class="col-lg-6 text-right" id="clientPassport"></h5>
                            </div>
                        </div>
                        <div>
                            <form>
                                <div class="form-group has-warning">
                                    <div class="row">
                                        <div class="col-lg-2">
                                            <label>Surcharge:</label>
                                        </div>
                                        <div class="col-lg-3">
                                            <input class="form-control" type="number">
                                        </div>
                                        <div class="col-lg-2">
                                            <label>Status:</label>
                                        </div>
                                        <div class="col-lg-5">
                                            <div class="btn-group open">
                                                <a class="btn btn-default" id="status">Confirmed</a>
                                                <a class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                                   aria-expanded="false"><span class="caret"></span></a>
                                                <ul class="dropdown-menu">
                                                    <li><a>New</a></li>
                                                    <li><a>Confirmed</a></li>
                                                    <li><a>Rejected</a></li>
                                                    <li><a>Surcharged</a></li>
                                                    <li><a>Closed</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group has-warning">
                                        <label>Comments:</label>
                                        <textarea class="form-control" rows="6"></textarea>

                                    </div>
                                    <div class="form-group">
                                        <input class="btn btn-default btn-group-justified text" value="Submit" type="submit">
                                    </div>
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
