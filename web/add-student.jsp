<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Add Student</title></head>
<body style="font-family: Arial; margin: 30px;">
<h2>Add Student</h2>
<p style="color: #b00020;"><%= request.getParameter("status") == null ? "" : request.getParameter("status") %></p>
<form method="post" action="add-student">
    USN: <input name="usn" required><br><br>
    Name: <input name="name" required><br><br>
    SEM: <input name="sem" required><br><br>
    Email: <input name="email" required><br><br>
    City: <input name="city"><br><br>
    Pin Code: <input name="pinCode"><br><br>
    DOB: <input name="dob"><br><br>
    Gender: <input name="gender"><br><br>
    Mobile: <input name="mobile"><br><br>
    Branch: <input name="branch"><br><br>
    Year: <input name="year"><br><br>
    Degree Program: <input name="degreeProgram"><br><br>
    Department ID: <input name="departmentId"><br><br>
    Subjects: <input name="subjects"><br><br>
    Attendance: <input name="attendance"><br><br>
    <button type="submit">Add</button>
</form>
<p><a href="index.jsp">Back Home</a></p>
</body>
</html>
