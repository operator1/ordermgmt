<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>About Orders Management</title>

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
					<li><a href="/">Home</a></li>
                    <li><a href="/orders">Orders</a></li>
					<li class="active"><a href="/about">About</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<h1>About</h1>

			<p>This is a sample web application brought to you by Christian Venning.</p>

			<p>The tools used to build the site were:</p>

			<ul>
			<li>Spring Boot - helped me get off the ground quickly with the scaffolding around Spring and Maven</li>
			<li>Spring MVC - powers the REST services</li>
			<li>Spring Security - has been used to wire in basic web authentication</li>
			<li>Bootstrap - to make things pretty</li>
			<li>JUnit and Mockito - for testing</li>
			</ul>

			<p>Assumptions, limitations and known issues:</p>

			<ul>
			<li>Security is authenticated against an in memory list of users and the registration does not add to this list. So despite the fact that you can register a new user, you cannot then log in with that user.</li>
			<li>I copied the bootstrap css and js files into the project and white listed them with spring security as it was routing me to the login page when it tried to load them. Not ideal, would like to have explored how to do this properly.</li>
			<li>Log out using Spring security is not working for me!</li>
			<li>Currently assuming that registration is always successful and never throws exceptions</li>
			<li>I was a touch confused about whether company was supposed to be part of the logon or registration - I added it to registration</li>
			<li>There are bits of JSP where I have could have used taglibs instead of the ugly notation, would change this with more time</li>
			<li>It took me longer than 2-3 hours</li>
			<li>Some tests are missing</li>
			<li>There is no form validation (red messages) on the login page</li>
			</ul>

			<p>The original spec was:</p>

<pre>
Develop a webservice using Java to implement the skeleton application below. The objective of the problem is to allow
the candidate to demonstrate their skill and experience in aspects of the development process including domain modelling,
object-oriented design, scalability and analysis, use of language constructs and features, etc.

Must use Spring and Maven (even if you don&#39;t know it) to package the solution. So we expect at least two programs,
a web service and also a basic client.

Candidate will have 48 hours to complete this exercise and must be your own work because you will be asked about the
design and technique used.

Exercise: Build a basic Order Management System, do not spend more than 2-3 hours and use open source (i.e., Spring boot)
for the scaffolding and focus on the application features. No persistence is required and list assumptions you are making
and why.

Please note, we expect this code as an example how you would code, so please use your normal coding style and how you
normally would have develop an application like this.

    The WebService is a skeleton Order Management System.

        1) User must be able to logon before placing any order, first time user needs to register first before he/she can logon

        2) Logon consists username, password, company

        3) Once logon successfully, user can query the webservice for a list of tradable products and also return your current list of orders

        4) You can buy/sell these tradable products if the price match, otherwise it will reject to explain why.

    The Client

        1) Build a HTML Client or a Swing Client to interact with the WebService.
</pre>

		</div>

	</div>

	<!--<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
	<script type="text/javascript" src="/js/bootstrap.min.js"></script>

</body>

</html>