<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

    <title>Transfer Value</title>

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

    <form:form method="POST" action="${contextPath}/transfer/${id}" class="form-signin">
        <h2 class="form-signin-heading">Transfer Value</h2>
        <div class="form-group">

            <label>Please specify the agency: </label>
            <span class="input-group-addon">Agency</span>
            <input value="${agency}" type="text" name="agency" id="agency" class="form-control"
                   aria-label="Agency"/>

            <label>Please specify the account number: </label>
            <span class="input-group-addon">Account Number</span>
            <input value="${number}" type="text" name="number" id="number" class="form-control"
                   aria-label="Account Number"/>

            <label>Please specify the amount you would like to transfer: </label>
            <span class="input-group-addon">Amount $</span>
            <input value="${amount}" type="text" name="amount" id="amount" class="form-control"
                   aria-label="Amount (to the nearest dollar)"/>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Transfer Value</button>
    </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
