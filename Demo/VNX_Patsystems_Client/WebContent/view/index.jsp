<%@ page import="com.vnx.patsystems.model.Quote"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%
	Quote quoteTables[] = (Quote[])session.getAttribute("quoteTables");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to VNX Client System</title>
</head>
<body>
<%
	if(quoteTables != null && quoteTables.length > 0) {
%>	
		<table border="1">
<%
			for(int i = 0; i < quoteTables.length; i++) {
%>
				<tr>
					<td><%= quoteTables[i].getTitle() %></td>
					<td><%= quoteTables[i].getBid() %></td>
					<td><%= quoteTables[i].getAsk() %></td>
					<td><%= quoteTables[i].getLast() %></td>
					<td><%= quoteTables[i].getTickVol() %></td>
				</tr>
<%				
			}
%>		
		</table>
<%
	}
%>
</body>
</html>
