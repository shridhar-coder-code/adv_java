# String Methods Used in Student Management System

## Project Overview

This is a comprehensive student record management system using Swing GUI, JDBC database, Collections, and web-based Servlets.

---

## STRING METHODS USED IN THIS PROJECT

### 1. **trim()** - Remove Leading and Trailing Whitespace

**Purpose:** Removes spaces from the beginning and end of a string.

#### Usage in Add.java (GUI Form Validation):

```java
String name = textField.getText().trim();
String usn = textField1.getText().trim();
String sem = textField2.getText().trim();
String email = textField3.getText().trim();
String city = cityField.getText().trim();
String pinCode = pinCodeField.getText().trim();
String dob = dobField.getText().trim();
String mobile = mobileField.getText().trim();
String branch = branchField.getText().trim();
String year = yearField.getText().trim();
String degreeProgram = degreeProgramField.getText().trim();
String departmentId = departmentIdField.getText().trim();
String subjects = subjectsArea.getText().trim();
String attendance = attendanceField.getText().trim();
```

#### Usage in Servlets (AddStudentServlet.java):

```java
String usn = safe(req.getParameter("usn"));  // safe() method uses trim()
String name = safe(req.getParameter("name"));
String email = safe(req.getParameter("email"));

private static String safe(String value) {
    return value == null ? "" : value.trim();
}
```

#### Usage in SearchStudentServlet.java:

```java
String usn = safe(req.getParameter("usn"));  // trim() via safe()
```

---

### 2. **isEmpty()** - Check if String is Empty

**Purpose:** Checks whether a string has zero length.

#### Usage in Add.java (Input Validation):

```java
if (name.isEmpty() || usn.isEmpty() || sem.isEmpty() || email.isEmpty() ||
    city.isEmpty() || pinCode.isEmpty() || dob.isEmpty() || mobile.isEmpty() ||
    branch.isEmpty() || year.isEmpty() || degreeProgram.isEmpty() ||
    departmentId.isEmpty() || subjects.isEmpty() || attendance.isEmpty() ||
    "Select".equals(gender)) {
    javax.swing.JOptionPane.showMessageDialog(frame, "Please fill all fields before adding.");
    return;
}
```

#### Usage in AddStudentServlet.java:

```java
if (usn.isEmpty() || name.isEmpty() || sem.isEmpty() || email.isEmpty()) {
    resp.sendRedirect("add-student.jsp?status=Please+fill+required+fields");
    return;
}
```

#### Usage in UpdateStudentServlet.java:

```java
if (usn.isEmpty()) {
    resp.sendRedirect("update-student.jsp?status=USN+is+required");
    return;
}

if (!email.isEmpty() && !email.contains("@")) {
    resp.sendRedirect("update-student.jsp?status=Invalid+email");
    return;
}

if (!mobile.isEmpty() && !mobile.matches("\\d+")) {
    resp.sendRedirect("update-student.jsp?status=Invalid+mobile+number");
    return;
}
```

#### Usage in DeleteStudentServlet.java:

```java
if (usn.isEmpty()) {
    resp.sendRedirect("delete-student.jsp?status=USN+is+required");
    return;
}
```

#### Usage in SearchStudentServlet.java:

```java
if (usn.isEmpty()) {
    out.println("<p>Please enter USN.</p>");
    out.println("<a href='search-student.jsp'>Back</a>");
    out.println("</body></html>");
    return;
}
```

---

### 3. **equals()** - Compare Two Strings

**Purpose:** Compares two strings for exact equality (case-sensitive).

#### Usage in Add.java (Gender Selection Validation):

```java
if ("Select".equals(gender)) {
    javax.swing.JOptionPane.showMessageDialog(frame, "Please fill all fields before adding.");
    return;
}
```

#### Usage in dbConnect.java (Login Validation):

```java
// Username and password comparison in database
String sql = "SELECT * FROM students WHERE username = ? AND pass = ?";
pstmt.setString(1, user);
pstmt.setString(2, pass);
```

---

### 4. **contains()** - Check if String Contains Substring

**Purpose:** Checks whether a string contains a specified sequence of characters.

#### Usage in AddStudentServlet.java (Email Validation):

```java
if (!email.contains("@") || mobile.length() < 10 || !mobile.matches("\\d+")) {
    resp.sendRedirect("add-student.jsp?status=Invalid+email+or+mobile+number");
    return;
}
```

#### Usage in UpdateStudentServlet.java (Email Validation):

```java
if (!email.isEmpty() && !email.contains("@")) {
    resp.sendRedirect("update-student.jsp?status=Invalid+email");
    return;
}
```

---

### 5. **length()** - Get String Length

**Purpose:** Returns the number of characters in a string.

#### Usage in AddStudentServlet.java (Mobile Number Validation):

```java
if (!email.contains("@") || mobile.length() < 10 || !mobile.matches("\\d+")) {
    resp.sendRedirect("add-student.jsp?status=Invalid+email+or+mobile+number");
    return;
}
```

---

### 6. **matches()** - Check String Against Regex Pattern

**Purpose:** Tests whether a string matches a regular expression pattern.

#### Usage in AddStudentServlet.java (Mobile Number Validation):

```java
if (!email.contains("@") || mobile.length() < 10 || !mobile.matches("\\d+")) {
    resp.sendRedirect("add-student.jsp?status=Invalid+email+or+mobile+number");
    return;
}
// \\d+ matches one or more digits (numeric validation)
```

#### Usage in UpdateStudentServlet.java (Mobile Number Validation):

```java
if (!mobile.isEmpty() && !mobile.matches("\\d+")) {
    resp.sendRedirect("update-student.jsp?status=Invalid+mobile+number");
    return;
}
```

---

### 7. **replace()** - Replace Characters in String

**Purpose:** Replaces all occurrences of a substring with another substring.

#### Usage in AddStudentServlet.java (URL Encoding for Spaces):

```java
private static String url(String value) {
    return value == null ? "" : value.replace(" ", "+");
}
// Example: "Student added successfully" → "Student+added+successfully"
```

#### Usage in UpdateStudentServlet.java:

```java
private static String url(String value) {
    return value == null ? "" : value.replace(" ", "+");
}
```

#### Usage in DeleteStudentServlet.java:

```java
private static String url(String value) {
    return value == null ? "" : value.replace(" ", "+");
}
```

#### Usage in SearchStudentServlet.java (HTML Entity Escaping):

```java
private static String escape(String value) {
    if (value == null) {
        return "";
    }
    return value.replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#39;");
}
// Prevents XSS (Cross-Site Scripting) attacks by escaping HTML characters
```

---

### 8. **valueOf()** - Convert Object to String

**Purpose:** Converts a non-string object to its String representation.

#### Usage in Add.java (Gender Dropdown):

```java
String gender = String.valueOf(genderBox.getSelectedItem());
// Converts the ComboBox selected item to String
```

---

### 9. **getMessage()** - Get Exception Message

**Purpose:** Returns the error message from an Exception object.

#### Usage in Add.java (Error Handling):

```java
} catch (SQLException | ClassNotFoundException ex) {
    javax.swing.JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage());
}
```

#### Usage in AddStudentServlet.java (Error Handling):

```java
} catch (Exception ex) {
    resp.sendRedirect("add-student.jsp?status=Error:+" + url(ex.getMessage()));
}
```

#### Usage in dbConnect.java (Login Error):

```java
} catch (SQLException | ClassNotFoundException ex) {
    JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage());
}
```

#### Usage in SearchStudentServlet.java (Error Display):

```java
} catch (Exception ex) {
    out.println("<p>Error: " + escape(ex.getMessage()) + "</p>");
}
```

---

## SUMMARY TABLE OF STRING METHODS

| Method           | Count | Purpose               | Input Validation         |
| ---------------- | ----- | --------------------- | ------------------------ |
| **trim()**       | 15+   | Remove whitespace     | ✓ Yes                    |
| **isEmpty()**    | 12+   | Check empty string    | ✓ Yes (Primary)          |
| **equals()**     | 3+    | String comparison     | ✓ Yes                    |
| **contains()**   | 4+    | Check substring       | ✓ Yes (Email validation) |
| **length()**     | 2+    | Get string length     | ✓ Yes (Min length check) |
| **matches()**    | 3+    | Regex validation      | ✓ Yes (Numeric check)    |
| **replace()**    | 8+    | Replace characters    | ✓ Yes (XSS protection)   |
| **valueOf()**    | 1+    | Convert to string     | -                        |
| **getMessage()** | 4+    | Get exception message | -                        |

---

## INPUT VALIDATION WORKFLOW

### Add Student (Add.java → AddStudentServlet.java → StudentWebDao.java)

1. **trim()** - Remove whitespace from all fields
2. **isEmpty()** - Check if required fields are empty
3. **equals()** - Verify gender dropdown selection
4. **contains()** - Validate email format (@)
5. **length()** - Check mobile number minimum length
6. **matches("\\d+")** - Validate mobile is numeric only

### Update Student (UpdateStudentServlet.java)

1. **trim()** - Remove whitespace
2. **isEmpty()** - Check if USN is provided
3. **contains()** - Validate email if provided
4. **matches("\\d+")** - Validate mobile if provided

### Delete Student (DeleteStudentServlet.java)

1. **trim()** - Remove whitespace
2. **isEmpty()** - Check if USN is provided

### Search Student (SearchStudentServlet.java)

1. **trim()** - Remove whitespace
2. **isEmpty()** - Check if USN provided
3. **replace()** - HTML escape characters for security

---

## DATA FLOW WITH STRING OPERATIONS

```
GUI (Swing) → Servlet → DAO → Database
   ↓          ↓         ↓
trim()      trim()    setString()
isEmpty()   isEmpty()
contains()  contains()
length()    length()
matches()   matches()
```

---

## SECURITY FEATURES USING STRING METHODS

### XSS Protection (Cross-Site Scripting)

```java
// SearchStudentServlet.java
private static String escape(String value) {
    return value.replace("&", "&amp;")
               .replace("<", "&lt;")
               .replace(">", "&gt;")
               .replace("\"", "&quot;")
               .replace("'", "&#39;");
}
```

### Null & Empty Check

```java
// All Servlets
private static String safe(String value) {
    return value == null ? "" : value.trim();
}
```

### Format Validation

```java
// Email: must contain "@"
if (!email.contains("@"))

// Mobile: must be all digits and at least 10 characters
if (!mobile.matches("\\d+") || mobile.length() < 10)
```

---

## COLLECTIONS USAGE IN PROJECT

While String methods are for data validation, Collections store data:

- **Temporary storage**: JComboBox dropdown lists (USN history)
- **ResultSet processing**: Database records converted to StudentRecord objects
- **List operations**: Collections Framework for maintaining search history

---

## KEY TAKEAWAYS

1. **String.trim()** - Most frequently used for form input cleaning
2. **String.isEmpty()** - Primary validation check for all forms
3. **String.contains()** - Used for pattern checking (email @, etc.)
4. **String.matches()** - Used for strict format validation (digits only)
5. **String.replace()** - Critical for security (HTML escaping) and formatting
6. **String.length()** - Used for minimum/maximum constraints
7. **equals()** - Used for dropdown selections and comparisons

All these String methods work together to ensure data integrity, security, and user experience in this comprehensive student management system.
