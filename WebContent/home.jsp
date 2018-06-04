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
        <li class="active"><a onclick="placebid()">BID</a></li>
        <li><a onclick="showbids()">VIEW</a></li>
        <li><a href="messagewindow?userid=${user.userid }" target="_blank">MESSAGES</a></li>
        <li><a href="auctionwindow?userid=${user.userid }" target="_blank">AUCTIONS</a></li>
        <li><a onclick="addcredits()"><span class="glyphicon glyphicon-plus"></span>CREDITS</a></li>
        <li><a onclick="removecredits()"><span class="glyphicon glyphicon-minus"></span>REFUND</a></li>
        <li><a onclick="addbook()"><span class="glyphicon glyphicon-plus"></span>BOOK</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#profile"><span class="glyphicon glyphicon-user"></span>Profile</a></li>
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
	function addcredits() {
		$('#myModalcredits').modal('show');
	}
	function removecredits() {
		$('#myModalcreditsremove').modal('show');
	}
	function addbook() {
		$('#myModaladdbook').modal('show');
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

<div class="modal fade" id="myModaladdbook" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">ADD BOOK</h4>
        </div>
        <div class="modal-body">
        		<form action="addbook" method="POST">
		        <input type="text" name="userid" value = "${user.userid }" hidden="true"> 
		        <table class="table">
		        <tr>
		        <td> <label><b>Book ID</b></label></td>
		        <td><input type="text" name="bookid"></td>
		        </tr>
		        <tr>
		        <td> <label><b>Book Name</b></label></td>
		        <td><input type="text" name="bookname"></td>
		        </tr>
		        <tr>
		        <td> <label><b>Author</b></label></td>
		        <td><input type="text" name="author"></td>
		        </tr>
		        <tr>
		        <td> <label><b>ISBN</b></label></td>
		        <td><input type="text" name="isbn"></td>
		        </tr>
		        <tr>
		        <td> <label><b>Initial Price</b></label></td>
		        <td><input type="text" name="iPrice"></td>
		        </tr>
		        <tr>
		        <td> <label><b>Original Price</b></label></td>
		        <td><input type="text" name="oPrice"></td>
		        </tr>
		        <tr>
		        <td> <label><b>Book Condition</b></label></td>
		        <td><select name="condition">
				    <option value="new">New</option>
				    <option value="old_good">Old Good</option>
				    <option value="old_bad">Old Bad</option>
				  </select>
				 </td>
		        </tr>
		        <tr>
		        <td><label><b>Department</b></label></td>
		        <td><select name="department">
				    <option value="D101">Computer Science</option>
				    <option value="D102">Mechanical</option>
				    <option value="D103">IPED</option>
				    <option value="D104">Criminal Justice</option>
				    <option value="D105">Fashion Design</option>
				    <option value="D106">Electrical Engineering</option>
				    <option value="D107">Arts and Science</option>
				    <option value="D108">Chiropractic</option>
				    <option value="D109">Biology</option>
				    <option value="D110">Mathematics</option>
				  </select></td>
		        </tr>
		        <tr>
		        <tr>
		        <td> <label><b>Book Category</b></label></td>
		        <td><select name="category">
				    <option value="C101">Textbook</option>
				    <option value="C102">Workbook</option>
				    <option value="C103">Reference</option>
				    <option value="C104">Journal</option>
				    <option value="C105">Multi-media</option>
				    <option value="C106">News Articles</option>
				    <option value="C107">Illustrations</option>
				  </select>
				 </td>
		        </tr>
		        <tr>
		        <td colspan="2" align="center"> <button type="submit" class="btn btn-default">Add</button></td>
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



<c:if test="${not empty mybookbids}">
<div class="container" id="mybookbids">
	<h3 class="text-center">Bids for ${selbook}</h3>
	<table class="table table-striped">
			<tr>
			<th>Bid ID</th>
			<th>Book ID</th>
			<th>Book Name</th>
			<th>Bid Price</th>
			<th>Status</th>
			<th>Category</th>
			<th>Bidder ID</th>
			</tr>
			<c:forEach items="${mybookbids}" var="bid">
				<tr>
					<td>${bid.bid_id}</td>
					<td>${bid.book_id}</td>
					<td>${bid.book_name}</td>
					<td>${bid.offer_price}</td>
					<td>${bid.book_status}</td>
					<td>${bid.cat_type}</td>
					<td>${bid.bidder_id}</td>
				</tr>
			</c:forEach>
			<tr>
			<td colspan="7" align="center"><input type="button" onclick="showauction()" value="auction"></td>
			</tr>
			
		</table>
</div>
</c:if>

<div class="modal fade" id="myModalbid" role="dialog">
    <div class="modal-dialog">
    
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">PLACE YOUR BID</h4>
        </div>
        <div class="modal-body">
        <form action="bid" method="POST">
        <input type="text" name="userid" value = "${user.userid }" hidden="true">
        <table class="table">
        <tr>
        <td><label><b>Book ID</b></label></td>
        <td><input type="text" name="bookid"></td>
        </tr>
        <tr>
        <td> <label><b>Bidding Price</b></label></td>
        <td><input type="text" name="price"></td>
        </tr>
        <tr>
        <td colspan="2" align="center"> <button type="submit" class="btn btn-default">Place Bid</button></td>
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

<script type="text/javascript">
function placebid() {
	$('#myModalbid').modal('show');
}
</script>

<c:if test="${not empty mybooks}">
<div class="container" id="mybooks">
	<h3 class="text-center">My Books</h3>
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
				</tr>
			</c:forEach>
	</table>
</div>
</c:if>

<div class="modal fade" id="myModalauction" role="dialog">
    <div class="modal-dialog">
    
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">AUCTION</h4>
        </div>
        <div class="modal-body">
        <form action="auction" method="POST">
        <input type="text" name="userid" value = "${user.userid }" hidden="true"> 
        <table class="table">
        <tr>
        <td> <label><b>Bid Id</b></label></td>
        <td><input type="text" name="bid_id"></td>
        </tr>
        <tr>
        <td colspan="2" align="center"> <button type="submit" class="btn btn-default">Auction</button></td>
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

<script type="text/javascript">
function showauction() {
	$('#myModalauction').modal('show');
}
</script>


<div class="modal fade" id="myModalshowbid" role="dialog">
    <div class="modal-dialog">
    
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">VIEW ALL BID</h4>
        </div>
        <div class="modal-body">
        <form action="allBids" method="POST">
        <input type="text" name="userid" value = "${user.userid }" hidden="true">
        <table class="table">
        <tr>
        <td><label><b>Book ID</b></label></td>
        <td><input type="text" name="bookid"></td>
        </tr>
        <tr>
        <td colspan="2" align="center"> <button type="submit" class="btn btn-default">View Bid</button></td>
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

<script type="text/javascript">
function showbids() {
	$('#myModalshowbid').modal('show');
}
</script>

<c:if test="${not empty mybids}">
<div class="container" id="mybids">
	<h3 class="text-center">My Bids</h3>
	<table class="table table-striped">
			<tr>
			<th>Bid ID</th>
			<th>Book ID</th>
			<th>Book Name</th>
			<th>My Price</th>
			<th>Status</th>
			</tr>
			<c:forEach items="${mybids}" var="bid">
				<tr>
					<td>${bid.bid_id}</td>
					<td>${bid.book_id}</td>
					<td>${bid.book_name}</td>
					<td>${bid.offer_price}</td>
					<td>${bid.book_status}</td>
				</tr>
			</c:forEach>
		</table>
</div>
</c:if>


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
		<tr>
			<td>Rating</td>
			<td>${user.rating}</td>
		</tr>
	</table>
</div>
	
</body>
</html>