<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <form id="createAccount" method="GET" action="${contextPath}/registeraccount">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <form id="editUser" method="GET" action="${contextPath}/edituser/">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome, ${pageContext.request.userPrincipal.name}!</h2>
        <button type="button" class="btn btn-danger" onclick="document.forms['logoutForm'].submit()">Logout</button>

        <button type="button" class="btn btn-primary" onclick="document.forms['createAccount'].submit()">Create a Bank
            Account
        </button>

        <!--
        <button type="button" class="btn btn-info" onclick="document.forms['editUser'].submit()">Edit User</button>
        -->
    </c:if>


    <h2>Your Accounts: </h2>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Agency</th>
            <th scope="col">Account Number</th>
            <th scope="col">Your Balance</th>
            <th scope="col">Balance with Overdraft</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${accounts}" var="acc">
            <tr>
                <td>${acc.agency}</td>
                <td>${acc.number}</td>
                <td>${acc.balance}</td>
                <td>${acc.overdraft + acc.balance}</td>
                <td>
                    <spring:url value="/credit/${acc.id}" var="creditValue"/>
                    <button class="btn btn-primary" onclick="location.href='${creditValue}'">Credit/Debit</button>

                    <spring:url value="/transfer/${acc.id}" var="transferValue"/>
                    <button class="btn btn-primary" onclick="location.href='${transferValue}'">Transfer Value</button>

                    <spring:url value="/delete/${acc.id}" var="deleteAccount"/>
                    <button class="btn btn-danger" onclick="location.href='${deleteAccount}'">Delete Account</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
