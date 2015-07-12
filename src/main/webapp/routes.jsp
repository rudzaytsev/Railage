<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 02.07.15
  Time: 1:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Routes</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/dashboard.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.js" type="text/javascript"></script>
    <script src="/resources/js/show.js" type="text/javascript"></script>
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
                    <li><a href="/trains/all">Trains</a></li>
                    <li><a href="/stations/all">Stations</a></li>
                    <li><a href="/rides/all">Rides</a></li>
                    <li><a href="#">Help</a></li>
                </ul>
            </div>
        </div>
    </nav>


    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <li><a href="/build/route">Add Route</a></li>
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
                <h1 class="page-header">Routes</h1>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <td> Id </td>
                            <td> Start Station </td>
                            <td> End Station </td>
                            <td> Plying train </td>
                            <td> Station list </td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="route" items="${routes}">
                            <tr>
                                <td>${route.id}</td>
                                <td>${route.getStartStation().name}</td>
                                <td>${route.getEndStation().name}</td>
                                <td>${route.train.number}</td>
                                <td><button id="btn_route_watch_${route.id}" class="btn btn-info"
                                            onclick="showRouteStationListModalWindow(this)">Watch</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>

    <!-- Modal Station List -->
    <div class="modal fade" id="route_stations_list_modal_div" tabindex="-1" role="dialog" aria-labelledby="route_stations_list_modal_label">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="route_stations_list_modal_label">Route Station List</h4>
                </div>

                <div class="modal-body" id="route_stations_list_content">

                </div>
                <br>
                <div class="modal-footer">
                    <button id="OK" type="submit" class="btn btn-primary">OK</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        $(document).on('click',"#OK", function(){
            $('#route_stations_list_modal_div').modal('hide')
            return false;
        });
    </script>

</body>
</html>
