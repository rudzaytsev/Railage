<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 05.07.15
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Route builder</title>
    <link rel="stylesheet" href="/railage/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/railage/resources/css/dashboard.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" type="text/javascript"></script>
    <script src="/railage/resources/js/bootstrap.js" type="text/javascript"></script>
</head>

<body>

    <%@ include file="navigation_panel.jsp" %>

    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <li><a href="/build/route">Add Route</a></li>
                </ul>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <h1 class="page-header">Route building</h1>
                <form class="form-horizontal" action="/add/route" method="post">
                    <h3>Choose Route Train</h3>
                    <div class="form-inline row">
                        <div class="form-group col-sm-5">
                            <label for="trainId" class="col-sm-3 control-label">Train</label>
                            <div class="col-sm-6">
                                <select class="form-control" id="trainId" name="trainId">
                                    <c:forEach var="train" items="${trains}" varStatus="status">
                                        <c:choose>
                                            <c:when test="${status.count eq 0}">
                                                <option selected value="${train.id}">${train.number}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${train.id}">${train.number}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-sm-3">
                            <label for="period" class="col-sm-4 control-label">Period</label>
                            <div class="col-sm-7">
                                <select class="form-control" id="period" name="period">
                                    <c:forEach var="period" items="${periods}" varStatus="status">
                                        <c:choose>
                                            <c:when test="${status.count eq 0}">
                                                <option selected value="${period}">${period}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${period}">${period}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <h3>Choose Route Stations</h3>
                    <div class="form-inline row">
                        <div class="form-group col-sm-5">
                            <label for="stationId1" class="col-sm-3 control-label">#1</label>
                            <div class="col-sm-6">
                                <select class="form-control" id="stationId1" name="stationId1">
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
                        <div class="form-group col-sm-3">
                            <label for="departureTime1" class="col-sm-4 control-label">Time</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="departureTime1"
                                       name="departureTime1" placeholder="10:00"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-inline row">
                        <div class="form-group col-sm-5">
                            <label for="stationId2" class="col-sm-3 control-label">#2</label>
                            <div class="col-sm-6">
                                <select class="form-control" id="stationId2" name="stationId2">
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
                        <div class="form-group col-sm-3">
                            <label for="departureTime2" class="col-sm-4 control-label">Time</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="departureTime2"
                                       name="departureTime2" placeholder="10:00"/>
                            </div>
                        </div>
                    </div>
                    <div id="place_to_insert" >
                    </div>
                    <div class="form-inline row col-sm-offset-2">
                        <div class="form-group row">
                            <div class="col-sm-offset-2 col-sm-3">
                                <button type="button" id="button_add_route_part" class="btn btn-success">Add Station</button>
                            </div>
                            <div class="col-sm-offset-1 col-sm-3">
                                <button type="button" id="button_remove_route_part" class="btn btn-danger">Remove Station</button>
                            </div>
                        </div>
                    </div>
                    <div class="form-inline row">
                        <div class="col-sm-offset-8 col-sm-4">
                            <button type="submit" class="btn btn-info">Add Route</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>
        function sendAjax(currentStationNumber){

            $.ajax({
                url: "/ajax/stations",
                type: 'POST',
                dataType: 'json',
                data: "{ \"request\" : \"stations\" }",
                contentType: 'application/json',
                mimeType: 'application/json',

                success: function (data) {

                    var insideHtml = '';
                    var len = data.length;
                    console.log(data);
                    for ( var i = 0; i < len; i++) {
                        insideHtml += '<option value="' + data[i].stationId + '">'
                        + data[i].stationName + '</option>';
                    }

                    var offsetVal = 3;
                    if($('#stationId' + currentStationNumber).length) {
                        $('#stationId' + currentStationNumber).html(insideHtml);
                    }
                },
                error:function(data,status,er) {
                    alert("error: "+data+" status: "+status+" er:"+er);
                }
            });
        }

        $(document).ready(function() {

            var iCnt = 2;
            // CREATE A "DIV" ELEMENT AND DESIGN IT USING JQUERY ".css()" CLASS.
            var container = $('#place_to_insert');

            $('#button_add_route_part').click(function() {
                if (iCnt <= 20) {

                    iCnt = iCnt + 1;

                    divStr = ''.concat('<div id="routepartX" class="form-inline row">',
                                '<div  class="form-group col-sm-5">',
                                '<label for="stationIdX" class="col-sm-3 control-label">#X</label>',
                                '<div class="col-sm-6">',
                                '<select class="form-control" id="stationIdX" name="stationIdX"><option value="-1">Not Selected</option>',
                                '</select>',
                                '</div>',
                                '</div>',
                                '<div class="form-group col-sm-3">',
                                '<label for="departureTimeX" class="col-sm-4 control-label">Time</label>',
                                '<div class="col-sm-7">',
                                '<input type="text" class="form-control" id="departureTimeX" name="departureTimeX" placeholder="10:00"/>',
                                '</div>',
                                '</div>',
                                '</div>').replace(/[X]/g,iCnt);

                    $(container).append(divStr);

                    sendAjax(iCnt);
                }
                else {
                    $(container).append('<label>Reached the limit</label>');
                    $('#button_add_route_part').attr('class', 'btn btn-success disabled');
                    $('#button_add_route_part').attr('disabled', 'disabled');
                }
            });

            $('#button_remove_route_part').click(function() {
                if (iCnt > 2) {
                    $('#routepartX'.replace(/[X]/g,iCnt)).remove();
                    iCnt = iCnt - 1;
                    $('#button_add_route_part').removeAttribute('disabled');
                    $('#button_add_route_part').attr('class', 'btn btn-success');
                }

                if (iCnt == 2) {
                    $(container).empty();
                    $('#button_add_route_part').removeAttribute('disabled');
                    $('#button_add_route_part').attr('class', 'btn btn-success');
                }
            });


        });

    </script>

</body>

</html>
