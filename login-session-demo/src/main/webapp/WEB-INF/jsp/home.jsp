<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>

<head>
<title>${firstName}'s Home Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<c:url value="/css/external/jquery-ui-1.12.1.css" />" />
<link rel="stylesheet" href="<c:url value="/css/external/bootstrap.min-4.0.0.css" />" />
<link rel="stylesheet" href="<c:url value="/css/style.css" />" />
<script type="text/javascript" src="<c:url value="/js/external/jquery-1.12.4.js"/>" ></script>
<script type="text/javascript" src="<c:url value="/js/external/jquery-ui-1.12.1.js"/>" ></script>
<script type="text/javascript" src="<c:url value="/js/external/popper.min-1.16.0.js"/>" ></script>
<script type="text/javascript" src="<c:url value="/js/external/bootstrap.min-4.0.0.js"/>" ></script>
</head>

<body class="bg-night">
	<section class="header-wrapper">
		<a href="/home">Home</a> 
		<a href="/logout">Logout</a>
	</section>
	<hr>
	<section class="body-wrapper">
		<div>
			<h4>
				Hello <a href="/profile" style="text-decoration: none"><font
					color="blue">${firstName}</font></a>, Good Evening!
			</h4>
		</div>

		<c:choose>
			<c:when test="${profile == 'true'}">
				<div>
					<table>
						<tr>
							<td>User Id: </td>
							<td>${userId}</td>
						</tr>
					</table>
				</div>
			</c:when>

			<c:otherwise>
				<div>
					<font color="red">${errorMessage}</font> 
					<font color="green">${successMessage}</font>
					<form:form id="todoForm" modelAttribute="newTodo"
						action="/add-new-todo" method="post">
						<table>
							<tr>
								<td><form:label path="desc">Todo</form:label></td>
								<td><form:input path="desc" name="todo-description"
										id="todo_description" /></td>
							</tr>
							<tr>
								<td><form:label path="targetDate">Target Date</form:label></td>
								<td><form:input path="targetDate" name="date-picker"
										id="target_date" autocomplete="off" /></td>
							</tr>
							<tr>
								<td></td>
								<td><form:button id="create_todo" name="create-todo">Add Todo</form:button>
								</td>
							</tr>
							<tr></tr>
						</table>
					</form:form>
					<h4 id="selectedDtaeVal"></h4>
				</div>
				<div>
					<font color="red">${updateErrorMessage}</font> 
					<font color="green">${updateSuccessMessage}</font>
					<a href="/show-todo">Show My Todos</a>
					<c:if test="${null != warningMessage}">
						<p>
							<font color="red">${warningMessage}</font>
						</p>
					</c:if>
					<c:if test="${null != todos}">
						<table>
							<tr>
								<th>Sr. No.</th>
								<th>Todo Description</th>
								<th>Target Date</th>
								<th>Status</th>
							</tr>
							<c:forEach items="${todos}" var="todo" varStatus="counter">
								<tr>
									<td>${counter.count }</td>
									<td>${todo.desc }</td>
									<td><fmt:formatDate type="date" value="${todo.targetDate}" /></td>
									<td>
										<c:choose>
											<c:when test="${todo.done == true}">
												Completed
											</c:when>
											<c:otherwise>
												<form action="/update-todo" method="post">
													<input type="text" value="${todo.todoId}" name="updateTodoId" hidden="hidden" />
													<input type="submit" value="Mark as Complete!" />
												</form>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
			</c:otherwise>
		</c:choose>
	</section>

	<script>
		$(function() {
			$("#target_date").datepicker();
		});
	</script>
</body>

</html>