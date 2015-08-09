<%--
  Created by IntelliJ IDEA.
  User: rudolph
  Date: 22.07.15
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title></title>
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
            <ul class="nav navbar-nav">
                <sec:authentication property="principal.username" var="login" scope="session"/>
                <li><a href="#"> ${login} </a></li>
                <sec:authorize access="hasRole('ROLE_CLIENT')">
                    <sec:authentication property="principal.balance" var="balance" scope="request"/>
                    <li><a id="add_balance" href="#">Balance : ${balance}</a> </li>
                </sec:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/railage/trains/all">Trains</a></li>
                <li><a href="/railage/stations/all">Stations</a></li>
                <li><a href="/railage/rides/all">Rides</a></li>
                <li><a href="/railage/routes/all">Routes</a></li>
                <li><a href="javascript:formSubmit()">Log Out</a></li>
            </ul>
        </div>
    </div>
</nav>
    <c:url value="/j_spring_security_logout" var="logOutUrl" />
    <form id="log_out_form" action="${logOutUrl}" method="post" >
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <script>
        function formSubmit() {
            document.getElementById("log_out_form").submit();
        }
    </script>
    <script>
        $(document).on('click',"#add_balance", function(){
            $('#add_balance_modal_div').modal('show')
            return false;
        });
    </script>

    <!-- Modal Add Ride -->
    <div class="modal fade " id="add_balance_modal_div" tabindex="-1" role="dialog" aria-labelledby="add_balance_modal_div">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="add_ride_modal_label">Add Money To Current Balance</h4>
                </div>
                <form:form id="add_ride_form" action="/railage/deposit/money" method="post"
                           modelAttribute="depositMoneyFormParams">
                    <div class="modal-body">
                        <label>Deposit Amount $:</label>
                        <select id="selected_amount" name="selectedAmount" class="form-control">
                            <c:forEach var="amount" items="${depositMoneyFormParams.amounts}" varStatus="status">
                                <c:choose>
                                    <c:when test="${status.count eq 0}">
                                        <option selected value="${amount}">${amount}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${amount}">${amount}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="lastViewName" value="${springViewName}" />

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary">Add</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

</body>
</html>
