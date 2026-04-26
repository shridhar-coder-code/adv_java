<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Update Student</title></head>
<body style="font-family: Arial; margin: 30px;">
<h2>Update Student (Editable fields only)</h2>
<p style="color: #b00020;"><%= request.getParameter("status") == null ? "" : request.getParameter("status") %></p>
<form method="post" action="update-student">
    USN: <input name="usn" required><br><br>
    SEM: <input name="sem"><br><br>
    Email: <input name="email"><br><br>
    Mobile: <input name="mobile"><br><br>
    Year: <input name="year"><br><br>
    Subjects: <input name="subjects"><br><br>
    Attendance: <input name="attendance"><br><br>
    <button type="submit">Update</button>
</form>
<p><a href="index.jsp">Back Home</a></p>
</body>
</html>
