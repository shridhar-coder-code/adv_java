<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Delete Student</title></head>
<body style="font-family: Arial; margin: 30px;">
<h2>Delete Student</h2>
<p style="color: #b00020;"><%= request.getParameter("status") == null ? "" : request.getParameter("status") %></p>
<form method="post" action="delete-student">
    USN: <input name="usn" required><br><br>
    <button type="submit">Delete</button>
</form>
<p><a href="index.jsp">Back Home</a></p>
</body>
</html>
