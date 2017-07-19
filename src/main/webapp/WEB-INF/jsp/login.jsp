<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login to your account</title>

	<!--<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />-->
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />

        <c:url value="/css/main.css" var="jstlCss" />
        <link href="${jstlCss}" rel="stylesheet" />
	</head>
	<body>

        <nav class="navbar navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/">UBS</a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="/">Home</a></li>
                        <li><a href="/orders">Orders</a></li>
                        <li><a href="/about">About</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container">

            <div class="starter-template">

                <h2>Please fill in the form below to log in to your account</h2>
                <p/><p/>

                <font color="red">${message}</font>
                <form:form id="loginForm" method="post" action="login" modelAttribute="userCredentials">

                    <div class="form-group">
                        <form:label path="username">User name</form:label><font color="red">&nbsp;&nbsp;<form:errors path="username"/></font>
                        <form:input id="username" name="username" path="username" class="form-control" />
                    </div>

                    <div class="form-group">
                        <form:label path="password">Password</form:label><font color="red">&nbsp;&nbsp;<form:errors path="password" /></font>
                        <form:password id="password" name="password" path="password" class="form-control" />
                    </div>

                    <p/><p/>
                    <input type="submit" class="btn btn-primary" value="Log me in please!" />
                    <a href="/" class="btn btn-primary">Actually, no thanks</a>
                </form:form>

                <a href="register">Not yet registered?</a>

            </div>

        </div>

        <!--<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
        <script type="text/javascript" src="/js/bootstrap.min.js"></script>

	</body>
</html>