<%@ page import="java.sql.*" %>
<html>
<head>
    <title>View Tickets</title>
</head>
<body>

<h2>Booked Tickets</h2>

<table border="1">
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Source</th>
    <th>Destination</th>
    <th>Date</th>
</tr>

<%
    ResultSet rs = (ResultSet) request.getAttribute("rs");
    while (rs.next()) {
%>
<tr>
    <td><%= rs.getInt("id") %></td>
    <td><%= rs.getString("name") %></td>
    <td><%= rs.getString("source") %></td>
    <td><%= rs.getString("destination") %></td>
    <td><%= rs.getDate("travel_date") %></td>
</tr>
<% } %>

</table>

<br>
<a href="<%= request.getContextPath() %>/booking.jsp">
    Book Another Ticket
</a>

</body>
</html>
