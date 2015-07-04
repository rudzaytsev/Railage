<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 25.06.15
  Time: 1:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Trains</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/dashboard.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.js" type="text/javascript"></script>
</head>
<body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Railage</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/stations/all">Stations</a></li>
                    <li><a href="/routes/all">Routes</a></li>
                    <li><a href="#">Help</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <li><a id="addtrain" href="#">Add Train</a></li>
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

    <!-- Modal -->
    <div class="modal fade" id="add_train_modal_div" tabindex="-1" role="dialog" aria-labelledby="add_train_modal_label">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="add_train_modal_label">Add Train</h4>
                </div>
                <form id="add_train_form" action="/add/train" method="post">
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
