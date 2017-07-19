<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

	<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
	<!--<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />-->
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />

	<!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
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
			<h1>UBS Order Management</h1>

			<font color="red">${message}</font>

			<% if (request.getRemoteUser() != null) { %>
                <h2> Welcome <%=request.getRemoteUser()%>, you are logged in! </h2>

                <p>
                <!-- TODO: Neither of these are working at the moment -->
                <!--<a href="/j_spring_security_logout">Log out</a>-->
                <a href="/login?logout">Log out</a>
                </p>

            <% } else { %>

                <p>
                <a href="/login">Log in</a>
                </p>

                <p>
                <a href="/register">Not yet registered?</a>
                </p>

            <% } %>
		</div>

	</div>

	<!--<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
	<script type="text/javascript" src="/js/bootstrap.min.js"></script>

</body>

</html>