<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dummy Page</title>
<style>
.error {
	color: #ff0000;
	font-style: italic;
}
</style>
</head>
<body>
	<center>
		<h2>Order Within Price Range Report</h2>
		<form:form modelAttribute="billRangeObj" method="POST" action="${pageContext.request.contextPath}/getOrderDetails.html">
			<table border="2">
				
				<tr>
					<th>From Bill:</th>
					<td><form:input path="fromPrice"/></td>
					<th>To Bill:</th>
					<td><form:input path="toPrice"/></td>
				</tr>
			</table>
			<br><br>
			<input type="submit" value="Fetch Details"/>
		<br><br>
		<c:if test="${not empty pizzaOrderList}">
				
				<table border="2">
				<tr>
					<th>Order Id</th><th>Customer Name</th><th>PizzaId</th><th>Bill</th><th>Quantity</th>
				</tr>
				
				<c:forEach items="${pizzaOrderList}" var="var">
				<tr>
					<td><c:out value="${var.orderId}"/> </td>
					<td><c:out value="${var.customerName}"/> </td>
					<td><c:out value="${var.pizzaId}"/></td>
					<td> <fmt:formatNumber value="${var.bill}" type="currency" currencySymbol="INR." ></fmt:formatNumber>
					</td>
					<td><c:out value="${var.numberOfPiecesOrdered}"/></td>
				</tr>
				</c:forEach>
				
			</table>
			
			</c:if>
			<br><br>
		<a href="${pageContext.request.contextPath}/index.jsp">Home</a><br><br>
			
		</form:form>
	</center>
</body>
</html>
