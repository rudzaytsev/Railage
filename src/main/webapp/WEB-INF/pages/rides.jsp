<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 29.06.15
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>Train Rides</title>
    <link rel="stylesheet" href="<c:url value="/railage/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/railage/resources/css/dashboard.css"/>">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" type="text/javascript"></script>
    <script src="<c:url value="/railage/resources/js/bootstrap.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/railage/resources/js/show.js"/>" type="text/javascript"></script>
</head>
<body>

<%@ include file="navigation_panel.jsp" %>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
                    <li><a id="addride" href="#">Add Ride</a></li>
                </sec:authorize>
                    <li><a id="findrides" href="#">Find Rides</a></li>
                <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
                    <c:if test="${hasCurrentTrain}">
                        <li><a href="/railage/passengers/on/train/${currentTrain.id}">All Passengers for train</a>
                    </c:if>
                </sec:authorize>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <c:if test="${isValidationError}">
                <div class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Error!</strong> ${errorMsg}
                </div>
            </c:if>
            <c:if test="${success}">
                <div class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Well Done!</strong> ${infoMsg}
                </div>
            </c:if>

            <c:choose>
                <c:when test="${hasCurrentTrain}">
                    <h1 class="page-header">Train Rides for train ${currentTrain.number}</h1>
                </c:when>
                <c:otherwise>
                    <h1 class="page-header">Train Rides for All trains</h1>
                </c:otherwise>
            </c:choose>

            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td> Id </td>
                        <td> Train Number </td>
                        <td> Route </td>
                        <c:if test="${isSearchResult}">
                            <td>"${sourceStation.name}" time</td>
                            <td>"${destStation.name}" time</td>
                        </c:if>
                        <td> RideDate </td>
                        <td> Price $ </td>
                        <sec:authorize access="!hasRole('ROLE_EMPLOYEE')">
                            <td> Ticket </td>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
                            <td> Passengers</td>
                        </sec:authorize>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="ride" items="${trainRides}">
                        <tr>
                            <td>${ride.id}</td>
                            <td>${ride.train.number}</td>
                            <td>${ride.route.getStartStation().name} - ${ride.route.getEndStation().name}</td>
                            <c:if test="${isSearchResult}">
                                <td> ${ride.route.getTimeInfoByStationId(requestScope.get("sourceStation").id).departureTime} </td>
                                <td> ${ride.route.getTimeInfoByStationId(requestScope.get("destStation").id).departureTime} </td>
                            </c:if>
                            <td>${ride.date}</td>
                            <td>${ride.price}</td>
                            <sec:authorize access="!hasRole('ROLE_EMPLOYEE')">
                                <td><button id="buy_button_${ride.id}" onclick="showBuyTicketForm(this)" class="btn btn-success">Buy</button></td>
                            </sec:authorize>

                            <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
                                <td><a href="/railage/passengers/ride/${ride.id}" class="btn btn-info">View</a></td>
                            </sec:authorize>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>

    <!-- Modal Buy Ticket -->
    <div class="modal fade" id="buy_ticket_modal_div" tabindex="-1" role="dialog" aria-labelledby="buy_ticket_modal_label">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="buy_ticket_modal_label">Buy Ticket</h4>
                </div>
                <form:form id="buy_ticket_form" action="/railage/buy/ticket" method="post"
                        modelAttribute="buyTicketFormParams">
                    <input type="hidden" id="rideIdForTicket" name="rideIdForTicket" value="X">
                    <div class="modal-body">

                        <div class="form-group">
                            <div class="form-group">
                                <label for="passengerName">Passenger Name</label>
                                <input type="text" pattern="[a-zA-Z]+" class="form-control"
                                       id="passengerName" name="passengerName" placeholder="Ivan">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-group">
                                <label for="passengerLastName">Passenger Lastname</label>
                                <input type="text" pattern="[a-zA-Z]+" class="form-control"
                                       id="passengerLastName" name="passengerLastName" placeholder="Ivanov">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-group">
                                <label for="passengerBirthDate">Passenger Birth Date</label>
                                <input type="date" class="form-control"
                                       id="passengerBirthDate" name="passengerBirthDate" placeholder="YYYY-MM-DD">
                                <span id="helpBlock" class="help-block"> Input date in format YYYY-MM-DD, where Y - year, M - Mounth, D - day  </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="boardingStationId">Boarding Station</label>
                            <select class="form-control" id="boardingStationId" name="boardingStationId">
                                <c:forEach var="boardingStation" items="${stations}" varStatus="status">
                                    <c:choose>
                                        <c:when test="${status.count eq 0}">
                                            <option selected value="${boardingStation.id}">${$boardingStation.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${boardingStation.id}">${boardingStation.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>

                    </div>
                    <br>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary">Buy</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

    <!-- Modal Find Rides -->
    <div class="modal fade" id="find_rides_modal_div" tabindex="-1" role="dialog" aria-labelledby="find_rides_modal_label">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="find_rides_modal_label">Find Rides</h4>
                </div>
                <form:form id="find_rides_form" action="/railage/find/rides"
                      method="post" modelAttribute="findRidesFormParams">
                    <input type="hidden" name="cmd" value="findrides">
                    <div class="modal-body">

                        <div class="form-group">
                            <h4>Stations</h4>
                        </div>
                        <hr class="form-group">
                        <div class="form-group form-inline">
                            <div class="form-group col-sm-6">
                                <label for="sourceStationId">From Station</label>
                                <select class="form-control" id="sourceStationId" name="sourceStationId">
                                    <c:forEach var="station" items="${stations}" varStatus="status">
                                        <c:choose>
                                            <c:when test="${status.count eq 0}">
                                                <option selected value="${station.id}">${station.name}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${station.id}">${station.name}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-sm-6">
                                <label for="destStationId">To Station</label>
                                <select class="form-control" id="destStationId" name="destStationId">
                                    <c:forEach var="station" items="${stations}" varStatus="status">
                                        <c:choose>
                                            <c:when test="${status.count eq 0}">
                                                <option selected value="${station.id}">${station.name}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${station.id}">${station.name}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <br class="form-group">
                        <div class="form-group">
                            <h4>Time</h4>
                        </div>
                        <hr class="form-group">
                        <div class="form-group form-inline">
                            <div class="form-group col-sm-6">
                                <label for="lowerBoundTime">Lower bound</label>
                                <input type="text" pattern="[0-9]{1,2}:[0-9]{1,2}" class="form-control"
                                       id="lowerBoundTime" name="lowerBoundTime" placeholder="10:00">
                            </div>
                            <div class="form-group col-sm-6">
                                <label for="upperBoundTime">Upper bound</label>
                                <input type="text" pattern="[0-9]{1,2}:[0-9]{1,2}" class="form-control"
                                       id="upperBoundTime" name="upperBoundTime" placeholder="12:00">
                            </div>

                        </div>
                        <br class="form-group">
                    </div>
                    <br>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary">Find</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

    <script>
        $(document).on('click',"#findrides", function(){
            $('#find_rides_modal_div').modal('show')
            return false;
        });
    </script>

    <!-- Modal Add Ride -->
    <div class="modal fade " id="add_ride_modal_div" tabindex="-1" role="dialog" aria-labelledby="add_ride_modal_label">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="add_ride_modal_label">Add Ride</h4>
                </div>
                <form:form id="add_ride_form" action="/railage/add/ride" method="post"
                           modelAttribute="rideAdditionFormParams">
                    <div class="modal-body">
                        <label>Route:</label>
                        <select id="selected_route" name="routeId" class="form-control">
                            <c:forEach var="route" items="${routes}" varStatus="status">
                                <c:choose>
                                    <c:when test="${status.count eq 0}">
                                        <option selected value="${route.id}">${route.getRouteName()}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${route.id}">${route.getRouteName()}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <div class="modal-body help-block" id="route_info">
                        </div>
                        <label for="rideDate">Ride Date:</label>
                        <input id="rideDate" name="rideDate" type="date" class="form-control"/>
                        <span id="helpBlock" class="help-block"> Input date in format YYYY-MM-DD, where Y - year, M - Mounth, D - day  </span>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary">Add</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

    <script>
        $(document).on('click',"#addride", function(){
            $('#add_ride_modal_div').modal('show')
            return false;
        });
    </script>

    <script>
        $(document).ready(function() {
            $('#selected_route').change(
                function () {

                    $.ajax({
                        url: "/railage/ajax/route",
                        type: 'POST',
                        dataType: 'json',
                        data: "{ \"request\" :  \"route\"," +
                        "\"routeId\": \"" + $('#selected_route').val() + "\" }",
                        contentType: 'application/json',
                        mimeType: 'application/json',

                        success: function (data) {

                            var insideHtml = '';
                            var len = data.length;
                            console.log(data);

                            insideHtml = ''.concat(
                                    '<div class="modal-body">',
                                    '<h5> Route Data </h5>',
                                    '<p> train Number : ', data.trainNumber,
                                    ', number of seats : ', data.seats,
                                    '<p> number of stations : ', data.stationsNumber,
                                    ', time period : ', data.period,
                                    '</div>');

                            if ($('#route_info').length) {
                                $('#route_info').html(insideHtml);
                            }

                        },
                        error: function (data, status, er) {
                            alert("error: " + data + " status: " + status + " er:" + er);
                        }
                    });
                });


        });
    </script>





</body>
</html>
