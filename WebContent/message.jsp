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
        <li><a href="#catalogue">MY MESSAGES</a></li>
        <li><a href="#userlist">ALL USERS</a></li>
        <li><a onclick="newmessage()"><span class="glyphicon glyphicon-plus"></span>MESSAGE</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <!-- <li><a href="#profile"><span class="glyphicon glyphicon-user"></span>Profile</a></li>
        <li><a href="index.html"><span class="glyphicon glyphicon-log-in"></span>Logout</a></li> -->
      </ul>
    </div>
  </div>
</nav>


<div class="container" id = "catalogue">
	<h3 class="text-center">My Messages</h3>
		<table class="table table-striped">
			<c:if test="${not empty allmsgs}">
			<tr>
			<th>Message ID</th>
			<th>Sender ID</th>
			<th>Subject</th>
			<th>Message</th>
			</tr>
			<c:forEach items="${allmsgs}" var="msg" varStatus="index">
				<tr>
						<td>${msg.message_id}</td>
						<td>${msg.sender_id}</td>
						<td>${msg.subject}</td>
						<td>${msg.message_msg}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" align="center"><button type="button" onclick="newmessage()">Reply</button></td>
			</tr>
			</c:if>
			<c:if test="${empty allmsgs}">
			<tr>
				<td colspan="4" align="center">No Messages</td>
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
	function newmessage() {
		$('#myModalnewmessage').modal('show');
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

<div class="modal fade" id="myModalnewmessage" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">NEW MESSAGE</h4>
        </div>
        <div class="modal-body">
        		<form action="messagewindow" method="POST">
		        <input type="text" name="userid" value = "${userid}" hidden="true"> 
		        <table class="table">
		        <tr>
		        <td> <label><b>To</b></label></td>
		        <td><input type="text" name="to" placeholder="UserID"></td>
		        </tr>
		        <tr>
		        <td> <label><b>Subject</b></label></td>
		        <td><select name="subject">
				    <option value="enquiry">Enquiry</option>
				    <option value="complain">Complain</option>
				  </select>
				 </td>
		        </tr>
		        <tr>
		        <td> <label><b>Message</b></label></td>
		        <td><input type="text" name="messagebody"></td>
		        </tr>
		        <tr>
		        <td colspan="2" align="center"> <button type="submit" class="btn btn-default">Send</button></td>
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

<div class="container" id = "userlist">
	<h3 class="text-center">User List</h3>
		<table class="table table-striped">
			<tr>
			<th>User ID</th>
			<th>User Name</th>
			<th>Role</th>
			<th>Address</th>
			<th>Feedback</th>
			</tr>
			<c:forEach items="${allusers}" var="user" varStatus="index">
				<tr>
						<td>${user.userid}</td>
						<td>${user.username}</td>
						<td>${user.role}</td>
						<td>${user.address}</td>
						<td>${user.rating}</td>
				</tr>
			</c:forEach>
		</table>
</div>
	
</body>
</html>