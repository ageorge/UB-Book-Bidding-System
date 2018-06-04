<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page errorPage="error.jsp" %>   --%>
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
        <li><a href="#catalogue">MY AUCTIONS</a></li>
        <li><a onclick="feedback()"><span class="glyphicon glyphicon-plus"></span>SELLER FEEDBACK</a></li>
        <li><a onclick="buyerfeedback()"><span class="glyphicon glyphicon-plus"></span>BUYER FEEDBACK</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="container" id = "catalogue">
	<h3 class="text-center">My Auctions</h3>
		<table class="table table-striped">
			<c:if test="${not empty alltrans}">
			<tr>
			<th>Auction ID</th>
			<th>Book ID</th>
			<th>Bid ID</th>
			<th>Price</th>
			<th>Transaction Date</th>
			<th>Payment Mode</th>
			<th>Shipping ID</th>
			<th>Shipping Status</th>
			</tr>
			<c:forEach items="${alltrans}" var="trans" varStatus="index">
				<tr>
						<td>${trans.auction_id}</td>
						<td>${trans.bookid}</td>
						<td>${trans.bid_id}</td>
						<td>${trans.price}</td>
						<td>${trans.transaction_date}</td>
						<td>${trans.payment_mode}</td>
						<td>${trans.shipping_id}</td>
						<td>${trans.shipping_status}</td>
				</tr>
			</c:forEach>
			</c:if>
			<c:if test="${empty alltrans}">
			<tr>
				<td colspan="4" align="center">No Auctions</td>
			</tr>
			</c:if>
		</table>
</div>

<div class="container" id = "catalogue">
	<h3 class="text-center">My Books</h3>
		<table class="table table-striped">
			<c:if test="${not empty allauctions}">
			<tr>
			<th>Auction ID</th>
			<th>Book ID</th>
			<th>Bid ID</th>
			<th>Price</th>
			<th>Transaction Date</th>
			<th>Payment Mode</th>
			<th>Buyer ID</th>
			</tr>
			<c:forEach items="${allauctions}" var="trans" varStatus="index">
				<tr>
						<td>${trans.auction_id}</td>
						<td>${trans.bookid}</td>
						<td>${trans.bid_id}</td>
						<td>${trans.price}</td>
						<td>${trans.transaction_date}</td>
						<td>${trans.payment_mode}</td>
						<td>${trans.buyerid}</td>
				</tr>
			</c:forEach>
			</c:if>
			<c:if test="${empty allauctions}">
			<tr>
				<td colspan="4" align="center">No Auctions</td>
			</tr>
			</c:if>
		</table>
</div>

<input type="text" id="mssg" value="${mssg}" hidden="true"/>

<script>
	function showmsg() {
		var msg = document.getElementById("mssg").value;
		if(msg != "") {
			$('#myModalmsg').modal('show');
		}
	}
	function feedback() {
		$('#myModalfeedback').modal('show');
	}
	function buyerfeedback() {
		$('#myModalbuyerfeedback').modal('show');
	}
</script>

<div class="modal fade" id="myModalmsg" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">MESSAGE</h4>
        </div>
        <div class="modal-body">
        		<p>${mssg}</p>
        </div>
        <div class="modal-footer">
       		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
     	</div>
      </div>
    </div>
</div>

<div class="modal fade" id="myModalfeedback" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">RATE THE SELLER</h4>
        </div>
        <div class="modal-body">
        		<form action="auctionwindow" method="POST">
		        <input type="text" name="userid" value = "${userid}" hidden="true"> 
		        <input type="text" name="type" value = "seller" hidden="true">
		        <table class="table">
		        <tr>
		        <td> <label><b>Auction ID</b></label></td>
		        <td><input type="text" name="auctionid"></td>
		        </tr>
		        <tr>
		        <td> <label><b>Rating: 1(bad) - 5(good)</b></label></td>
		        <td><input type="text" name="rating"></td>
		        </tr>
		        <tr>
		        <td colspan="2" align="center"> <button type="submit" class="btn btn-default">Rate</button></td>
		        <td></td>
		        </tr>
		        </table>
	     	</form>
        </div>
        <div class="modal-footer">
       		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
     	</div>
      </div>
    </div>
</div>

<div class="modal fade" id="myModalbuyerfeedback" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">RATE THE BUYER</h4>
        </div>
        <div class="modal-body">
        		<form action="auctionwindow" method="POST">
		        <input type="text" name="userid" value = "${userid}" hidden="true"> 
		        <input type="text" name="type" value = "buyer" hidden="true">
		        <table class="table">
		        <tr>
		        <td> <label><b>Auction ID</b></label></td>
		        <td><input type="text" name="auctionid"></td>
		        </tr>
		        <tr>
		        <td> <label><b>Rating: 1(bad) - 5(good)</b></label></td>
		        <td><input type="text" name="rating"></td>
		        </tr>
		        <tr>
		        <td colspan="2" align="center"> <button type="submit" class="btn btn-default">Rate</button></td>
		        <td></td>
		        </tr>
		        </table>
	     	</form>
        </div>
        <div class="modal-footer">
       		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
     	</div>
      </div>
    </div>
</div>
	
</body>
</html>