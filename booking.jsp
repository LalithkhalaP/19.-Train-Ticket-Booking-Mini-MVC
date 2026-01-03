<%@ page language="java" %>
<html>
<head>
    <title>Train Ticket Booking</title>
</head>
<body>

<h2>Book Train Ticket</h2>

<form action="<%= request.getContextPath() %>/bookTicket" method="post">
    Name: <input type="text" name="name" required><br><br>
    Source: <input type="text" name="source" required><br><br>
    Destination: <input type="text" name="destination" required><br><br>
    Travel Date: <input type="date" name="date" required><br><br>

    <input type="submit" value="Book Ticket">
</form>

<br>
<a href="<%= request.getContextPath() %>/viewTicket">View Tickets</a>

</body>
</html>
