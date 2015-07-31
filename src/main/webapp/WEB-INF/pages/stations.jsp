<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 01.07.15
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Stations</title>
    <link rel="stylesheet" href="<c:url value="/railage/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/railage/resources/css/dashboard.css"/>">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" type="text/javascript"></script>
    <script src="<c:url value="/railage/resources/js/bootstrap.js"/>" type="text/javascript"></script>
</head>
<body>

    <%@ include file="navigation_panel.jsp" %>

    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <c:if test="${isEmployee}">
                        <li><a id="addstation" href="#">Add Station</a></li>
                    </c:if>
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
                <h1 class="page-header">Stations</h1>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <td> Id </td>
                            <td> Station Name </td>
                            <td> Timetable </td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="station" items="${stations}">
                            <tr>
                                <td>${station.id}</td>
                                <td>${station.name}</td>
                                <td><a href="/railage/timetable/station/${station.id}" class="btn btn-info">Watch</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>


    <!-- Modal -->
    <div class="modal fade" id="add_station_modal_div" tabindex="-1" role="dialog" aria-labelledby="add_station_modal_label">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="add_station_modal_label">Add Station</h4>
                </div>
                <form id="add_station_form" action="/add/station" method="post">
                <div class="modal-body">
                     <label for="input_station_name">Station Name:</label>
                     <input id="input_station_name" name="stationName" type="text" class="form-control"/>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
                </form>
            </div>
        </div>
    </div>

    <script>
        $(document).on('click',"#addstation", function(){
            $('#add_station_modal_div').modal('show')
            return false;
        });
    </script>



</body>
</html>
