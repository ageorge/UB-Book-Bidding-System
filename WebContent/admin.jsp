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
        <li class="active"><a onclick="placebid()"></a></li>
        <li><a onclick="showdept()">DEPARTMENT</a></li>
        <li><a onclick="editcat()">CATEGORY</a></li>
        <li><a onclick="showreport()">REPORTS</a></li>
        <li><a href="messagewindow?userid=${user.userid}" target="_blank">MESSAGES</a></li>
        <li><a onclick="addcredits()"><span class="glyphicon glyphicon-plus"></span>CREDITS</a></li>
        <li><a onclick="removecredits()"><span class="glyphicon glyphicon-minus"></span>REFUND</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="index.html"><span class="glyphicon glyphicon-log-in"></span>Logout</a></li>
      </ul>
    </div>
  </div>
</nav>

<input type="text" id="mssg" value="${mssg}" hidden="true"/>

<script>
	function showmsg() {
		var msg = document.getElementById("mssg").value;
		if(msg != "") {
			$('#myModalmsg').modal('show');
		}
	}
	function showdept() {
		$('#myModaldeptdetails').modal('show');
	}
	function showreport() {
		$('#myModalreport').modal('show');
	}
	function editcat() {
		$('#myModalcategory').modal('show');
	}
	function addcredits() {
		$('#myModalcredits').modal('show');
	}
	function removecredits() {
		$('#myModalcreditsremove').modal('show');
	}
</script>

<div class="modal fade" id="myModalmsg" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">MESSAGE</h4>
        </div>
        <div class="modal-body">
        		<p>${mssg }</p>
        </div>
        <div class="modal-footer">
       		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
     	</div>
      </div>
    </div>
</div>

<div class="modal fade" id="myModaldeptdetails" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">DEPARTMENT DETAILS</h4>
        </div>
        <div class="modal-body">
		        <table class="table">
		        <tr>
		        <td><label><b>Department ID</b></label></td>
		        <td>${dept.dept_id }</td>
		        </tr>
		        <tr>
		        <td><label><b>Department Name</b></label></td>
		        <td>${dept.dept_name }</td>
		        </tr>
		        <tr>
		        <td><label><b>Start Date</b></label></td>
		        <td>${dept.start_date }</td>
		        </tr>
		        </table>
        </div>
        <div class="modal-footer">
       		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
     	</div>
      </div>
    </div>
</div>


<div class="modal fade" id="myModalcategory" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">CHANGE CATEGORY TYPE</h4>
        </div>
        <div class="modal-body">
        		<form action="category" method="POST">
		        <input type="text" name="userid" value = "${user.userid }" hidden="true"> 
		        <table class="table">
		        <tr>
		        <td> <label><b>Category ID</b></label></td>
		        <td><input type="text" name="catid"></td>
		        </tr>
		        <tr>
		        <td> <label><b>Auction Type</b></label></td>
		        <td><select name="auctionstatus">
				    <option value="selling">Selling</option>
				    <option value="renting">Renting</option>
				  </select>
				 </td>
		        </tr>
		        <tr>
		        <td colspan="2" align="center"> <button type="submit" class="btn btn-default">Change</button></td>
		        <td></td>
		        </tr>
		        </table>
	     	</form>
        </div>
        <div class="modal-footer">
       		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
     	</div>
      </div>s
    </div>
</div>

<div class="modal fade" id="myModalreport" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">REPORTS</h4>
        </div>
        <div class="modal-body">
        		<form action="getreports" method="POST" target="_blank">
		        <input type="text" name="userid" value = "${user.userid }" hidden="true"> 
		        <table class="table">
		        <tr>
		        <td> <label><b>Report Type*</b></label></td>
		        <td><select name="type">
				    <option value="auction">Auction</option>
				    <option value="book">Book Status</option>
				  </select></td>
		        </tr>
		        <tr>
		        <td> <label><b>BOOK ID</b></label></td>
		        <td><input type="text" name="bookid"></td>
		        </tr>
		        <tr>
		        <td colspan="2" align="center"> <button type="submit" class="btn btn-default">Fetch</button></td>
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

<div class="modal fade" id="myModalcredits" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">ADD CREDITS</h4>
        </div>
        <div class="modal-body">
        		<form action="pay" method="POST">
		        <input type="text" name="userid" value = "${user.userid }" hidden="true"> 
		        <table class="table">
		        <tr>
		        <td> <label><b>Card Number</b></label></td>
		        <td><input type="text" name="cardnum"></td>
		        </tr>
		        <tr>
		        <td> <label><b>Expiry Date</b></label></td>
		        <td><input type="text" name="expiry" placeholder="MM/YY"></td>
		        </tr>
		        <tr>
		        <td> <label><b>CVV</b></label></td>
		        <td><input type="text" name="cvv"></td>
		        </tr>
		        <tr>
		        <td> <label><b>Amount</b></label></td>
		        <td><input type="text" name="credits"></td>
		        </tr>
		        <tr>
		        <td colspan="2" align="center"> <button type="submit" class="btn btn-default">Pay</button></td>
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


<div class="modal fade" id="myModalcreditsremove" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">REFUND CREDITS</h4>
        </div>
        <div class="modal-body">
        		<form action="refund" method="POST">
		        <input type="text" name="userid" value = "${user.userid }" hidden="true"> 
		        <table class="table">
		        <tr>
		        <td> <label><b>Card Number</b></label></td>
		        <td><input type="text" name="cardnum"></td>
		        </tr>
		        <tr>
		        <td> <label><b>Expiry Date</b></label></td>
		        <td><input type="text" name="expiry" placeholder="MM/YY"></td>
		        </tr>
		        <tr>
		        <td> <label><b>CVV</b></label></td>
		        <td><input type="text" name="cvv"></td>
		        </tr>
		        <tr>
		        <td> <label><b>Amount</b></label></td>
		        <td><input type="text" name="credits"></td>
		        </tr>
		        <tr>
		        <td colspan="2" align="center"> <button type="submit" class="btn btn-default">Refund</button></td>
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

<div class="container" id = "catalogue">
	<h3 class="text-center">Catalogue</h3>
		<table class="table table-striped">
			<tr>
			<th>Book ID</th>
			<th>Book Name</th>
			<th>Author</th>
			<th>ISBN</th>
			<th>Status</th>
			<th>Condition</th>
			<th>Initial Price</th>
			<th>Original Price</th>
			<th>Auction Type</th>
			</tr>
			<c:forEach items="${books}" var="book" varStatus="index">
				<tr>
						<td>${book.book_id}</td>
						<td>${book.book_name}</td>
						<td>${book.author}</td>
						<td>${book.isbn}</td>
						<td>${book.status}</td>
						<td>${book.condition}</td>
						<td>${book.iPrice}</td>
						<td>${book.oPrice}</td>
						<td>${book.bid_category}</td>
				</tr>
			</c:forEach>
		</table>
</div>


<c:if test="${not empty mybooks}">
<div class="container" id="mybooks">
	<h3 class="text-center">Department Books</h3>
	<table class="table table-striped">
			<tr>
			<th>Book ID</th>
			<th>Book Name</th>
			<th>Author</th>
			<th>ISBN</th>
			<th>Status</th>
			<th>Condition</th>
			<th>Initial Price</th>
			<th>Original Price</th>
			<th>Final Price</th>
			<th>User ID</th>
			</tr>
			<c:forEach items="${mybooks}" var="book">
				<tr>
					<td>${book.book_id}</td>
					<td>${book.book_name}</td>
					<td>${book.author}</td>
					<td>${book.isbn}</td>
					<td>${book.status}</td>
					<td>${book.condition}</td>
					<td>${book.iPrice}</td>
					<td>${book.oPrice}</td>
					<td>${book.fPrice}</td>
					<td>${book.u_id}</td>
				</tr>
			</c:forEach>
	</table>
</div>
</c:if>


<div class="container">
	<h3 class="text-center">Categories</h3>
		<table class="table table-striped">
			<tr>
			<th>Category ID</th>
			<th>Category Type</th>
			<th>Auction Type</th>
			</tr>
			<c:forEach items="${categories}" var="cat" varStatus="index">
				<tr>
						<td>${cat.cat_id}</td>
						<td>${cat.cat_type}</td>
						<td>${cat.auction_status}</td>
				</tr>
			</c:forEach>
		</table>
</div>

<div class="container bg-1" id="profile">
	<h3 class="text-center">My Account</h3>
	<table class="table">
		<tr>
			<td>User ID</td>
			<td>${user.userid}</td>
		</tr>
		<tr>
			<td>User Name</td>
			<td>${user.username}</td>
		</tr>
		<tr>
			<td>Date Of Birth</td>
			<td>${user.dob}</td>
		</tr>
		<tr>
			<td>Role</td>
			<td>${user.role}</td>
		</tr>
		<tr>
			<td>Account Balance</td>
			<td>$ ${user.acc_bal}</td>
		</tr>
		<tr>
			<td>Address</td>
			<td>${user.address}</td>
		</tr>
	</table>
</div>
	
</body>
</html>