<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>  
<!DOCTYPE html>
<html>
<head>
  <title>UB Home</title>
  <link rel="icon" href="images/logo.png" type="image/png" sizes="16x16">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="css/header.css">
</head>
<body onload="showmsg()">

<nav class="navbar navbar-default navbar-fixed-top navbar-offset">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#"><img src="images/logo.png"/></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">QUERY</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="index.html"><span class="glyphicon glyphicon-log-in"></span>HOME</a></li>
      </ul>
    </div>
  </div>
</nav>


<input type="text" id="mssg" value="${mssg}" />


<div class="container" id = "catalogue">
	<h3 class="text-center">QUERY: DML ONLY</h3>
	<form action="allQuery" method="post">
		<table class="table table-striped">
			<tr>
				<td>Enter your query</td>
				<td><input type = "text" name="query" size="100"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Query"></td>
			</tr>
		</table>
	</form>
</div>


<c:if test="${not empty result}">
<div class="container" id="mybookbids">
	<h3 class="text-center">RESULTS</h3>
	<table class="table table-striped">
			<%-- <tr>
				<c:forEach items="${heading}" var="head">
					<th>${head}</th>
				</c:forEach>
			</tr> --%>
			<c:forEach items="${result}" var="res">
				<tr>
					<c:forEach items="${res}" var="r">
						<td>${r}</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
</div>
</c:if>
	
</body>
</html>