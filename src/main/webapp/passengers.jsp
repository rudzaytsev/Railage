<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 01.07.15
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Passengers</title>
    <link rel="stylesheet" href="/railage/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/railage/resources/css/dashboard.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" type="text/javascript"></script>
    <script src="/railage/resources/js/bootstrap.js" type="text/javascript"></script>
</head>
<body>

    <%@ include file="WEB-INF/pages/navigation_panel.jsp" %>

    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <!-- -->
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
                    <c:when test="${hasCurrentRide}">
                        <h1 class="page-header">Passengers registered for train ride # ${currentTrainRide.id} </h1>
                    </c:when>
                    <c:when test="${hasCurrentTrain}">
                        <h1 class="page-header">Passengers registered for train # ${currentTrain.number} </h1>
                    </c:when>
                    <c:otherwise>
                        <h1 class="page-header">Passengers</h1>
                    </c:otherwise>
                </c:choose>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <td> Id </td>
                            <td> Name </td>
                            <td> Lastname </td>
                            <td> Birth date </td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="passenger" items="${passengers}">
                            <tr>
                                <td>${passenger.id}</td>
                                <td>${passenger.name}</td>
                                <td>${passenger.lastName}</td>
                                <td>${passenger.getFormattedBirthDate()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>

</body>
</html>
