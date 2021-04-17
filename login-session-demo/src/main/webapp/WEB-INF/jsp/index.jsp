<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Maintain Your Todos</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<c:url value="/css/external/bootstrap.min-4.0.0.css" />" />
<link rel="stylesheet" href="<c:url value="/css/style.css" />" />
<script type="text/javascript" src="<c:url value="/js/external/jquery-1.12.4.js"/>" ></script>
<script type="text/javascript" src="<c:url value="/js/external/popper.min-1.16.0.js"/>" ></script>
<script type="text/javascript" src="<c:url value="/js/external/bootstrap.min-4.0.0.js"/>" ></script>
</head>

<body class="bg-night">
	<nav class="navbar navbar-dark bg-dark justify-content-between position-sticky sticky-top">
		<a class="navbar-brand text-primary" href="/index">TodoWebApp</a>
		<form class="form-inline" action="/login" method="post">
			<input class="form-control mr-sm-2" type="text" name="userId"
				placeholder="User Id" aria-label="UserId" required="required"/> 
			<input class="form-control mr-sm-2" type="password" name="password"
				placeholder="Password" aria-label="Password" required="required" /> 
			<input class="btn btn-outline-primary my-2 my-sm-0" type="submit"
				value="Login" />
		</form>
	</nav>
	<c:if test="${null != errorMessage || null != successMessage}">
		<div class="container mt-3 w-50 pl-5 pr-5" id="message_panel">
			<div class="card bg-dark text-light text-center border border-white">
			    <div class="card-header">TodoWebApp returned a message</div>
			    <c:if test="${null != errorMessage}">
			    	<div class="card-body text-danger">${errorMessage}</div>
			    </c:if>
			    <c:if test="${null != successMessage}">
			    	<div class="card-body text-success">${successMessage}</div>
			    </c:if>
			</div>
		</div>
	</c:if>
	<div class="container-fluid mt-3 w-75 bg-dark text-white">
		<div class="row">
			<div class="col col-lg-8">
				<img src="/css/external/images/648854.jpg" class="img-fluid mx-auto d-block" alt="ToDo Sand Compass">
			</div>
	    	<div class="col col-lg-4 pt-3 pb-3">
	    		<div class="pl-3 text-danger"><a class="form-header">New User Registration</a></div>
	    		<hr />
				<form:form id="regForm" modelAttribute="newUser" action="/register"
					method="post">
					<div class="row jsp-form-control">
						<div class="col-sm-12">
							<form:input class="form-control mb-2" path="userId"
								name="userId" id="userId" placeholder="User Name" required="required" />
						</div>
					</div>
					<div class="row jsp-form-control">
						<div class="col-sm-12">
							<form:password class="form-control mb-2" path="password"
								name="password" id="password" placeholder="Password" required="required" />
						</div>
					</div>
					<div class="row jsp-form-control">
						<div class="col-sm-12">
							<form:input class="form-control mb-2" path="userName"
								name="userName" id="userName" placeholder="First Name" required="required" />
						</div>
					</div>
					<div class="row jsp-form-control">
						<div class="col-sm-12">
							By Registering to the WebApp, you agree to it's <a href="#"
								title="Terms & Conditions" data-toggle="popover"
								data-placement="top"
								data-content="This WebApp is to be used for Learning purpose only.">Terms
								& Conditions</a>.
						</div>
					</div>
					<div class="row jsp-form-control">
						<div class="col-sm-12">
							<form:button class="btn btn-outline-primary my-2 my-sm-0 float-right" id="register" name="register">Register</form:button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function(){
		  $('[data-toggle="popover"]').popover();   
		  $('.toast').toast('show');
		});
	</script>
</body>

</html>