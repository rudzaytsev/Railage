<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 25.06.15
  Time: 1:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Trains</title>
    <link rel="stylesheet" href="<c:url value="/railage/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/railage/resources/css/dashboard.css"/>">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" type="text/javascript"></script>
    <script src="<c:url value="/railage/resources/js/bootstrap.js"/>" type="text/javascript"></script>
</head>
<body>

    <%@ include file="../../navigation_panel.jsp" %>

    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <c:if test="${isEmployee}">
                        <li><a id="addtrain" href="#">Add Train</a></li>
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
                <h1 class="page-header">Trains</h1>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Train Number</th>
                            <th>Number of seats</th>
                            <th>Train Rides</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="train" items="${trains}">
                            <tr>
                                <td>${train.id}</td>
                                <td>${train.number}</td>
                                <td>${train.seats}</td>
                                <td><a href="/trains/${train.id}" class="btn btn-info">Watch</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>

    <!-- Modal  Trains-->
    <div class="modal fade" id="add_train_modal_div" tabindex="-1" role="dialog" aria-labelledby="add_train_modal_label">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="add_train_modal_label">Add Train</h4>
                </div>
                <form id="add_train_form" action="/railage/add/train" method="post">
                    <div class="modal-body">
                        <label for="input_train_name">Train Number:</label>
                        <input id="input_train_name" name="trainNumber" type="text" class="form-control"/>
                        <label for="input_train_seats">Max number of seats:</label>
                        <input id="input_train_seats" name="seatsNumber" type="text" class="form-control"/>
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
        $(document).on('click',"#addtrain", function(){
            $('#add_train_modal_div').modal('show')
            return false;
        });
    </script>




</body>
</html>
