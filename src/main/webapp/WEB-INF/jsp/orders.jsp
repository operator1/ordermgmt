<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>My orders</title>

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
                        <li><a href="/">Home</a></li>
                        <li class="active"><a href="/orders">Orders</a></li>
                        <li><a href="/about">About</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container">

            <div class="starter-template">

                <h2>Here are the products that are available for trading:</h2>

                <table class="table">
                    <thead>
                      <tr>
                        <th>ID</th>
                        <th>Description</th>
                        <th>You buy at</th>
                        <th>You sell at</th>
                        <th>Units available</th>
                      </tr>
                    </thead>
                    <tbody>


                <c:if test="${not empty portfolio.productStockList}">

                    <ul>
                        <c:forEach var="productStock" items="${portfolio.productStockList}">

                              <tr>
                                <td>${productStock.product.id}</td>
                                <td>${productStock.product.description}</td>
                                <td>${productStock.product.buyPrice}</td>
                                <td>${productStock.product.sellPrice}</td>
                                <td>${productStock.unitsAvailable}</td>
                              </tr>

                        </c:forEach>
                    </ul>

                </c:if>

                    </tbody>
                  </table>
                  <p>



                <h2>Here is your current portfolio:</h2>

                <table class="table">
                    <thead>
                      <tr>
                        <th>ID</th>
                        <th>Description</th>
                        <th>You buy at</th>
                        <th>You sell at</th>
                        <th>Units available</th>
                        <th></th>
                      </tr>
                    </thead>
                    <tbody>


                <c:if test="${not empty userPortfolio.productStockList}">

                    <ul>
                        <c:forEach var="productStock" items="${userPortfolio.productStockList}">

                              <tr>
                                <td>${productStock.product.id}</td>
                                <td>${productStock.product.description}</td>
                                <td>${productStock.product.buyPrice}</td>
                                <td>${productStock.product.sellPrice}</td>
                                <td>${productStock.unitsAvailable}</td>
                                <td><a href="/orders/trade/${productStock.product.id}">trade</a></td>
                              </tr>

                        </c:forEach>
                    </ul>

                </c:if>

                    </tbody>
                  </table>
                  <p>

            </div>

        </div>

	<!--<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
	<script type="text/javascript" src="/js/bootstrap.min.js"></script>

	</body>
</html>