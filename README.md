# Student Record Management System (Java Swing + JDBC + Servlet)

A student management project with:

- Desktop UI using Java Swing
- Database operations using JDBC (MySQL)
- CRUD features: Add, Update, Delete, Search
- Temporary in-memory history using Java Collections
- Web access layer using Servlets + JSP (Tomcat)

## Features

### Desktop (Swing)

- Login screen
- Dashboard navigation
- Add student record
- Update student record
- Delete student record
- Search student record
- Recent USN history support in search fields

### Web (Servlet + JSP)

- Add student page
- Update student page
- Delete student page
- Search student page

## Tech Stack

- Java
- Swing
- JDBC
- MySQL
- Apache Tomcat 9
- JSP/Servlet

## Project Structure

- `swing/` - desktop Swing UI screens and helpers
- `servlet/` - servlet classes and servlet-side DAO/UI helpers
- `shared/` - shared validation and database connection code
- `web/` - JSP pages and `WEB-INF` configuration

## 1. Clone the Project

Use your repository URL:

```bash
git clone https://github.com/shridhar-coder-code/adv_java.git
cd adv_java
```

## 2. Prerequisites

- Java JDK 8+ (recommended 11 or 17)
- MySQL running locally
- Apache Tomcat 9 (for web module)
- MySQL JDBC driver jar available

## 3. Database Setup

1. Create database: `adv_java`
2. Ensure required tables exist (`students` for login, `sdata` for student records)
3. Update DB credentials in dbConnect.java if needed:
   - host: localhost
   - db: adv_java
   - user/password as per your local setup

## 4. Run Desktop App (Swing)

Compile:

```powershell
javac -cp ".;mysql-connector-j-9.6.0\mysql-connector-j-9.6.0\mysql-connector-j-9.6.0.jar" shared\dbConnect.java shared\Validation.java swing\MyFrame.java swing\Dashboard.java swing\Add.java swing\Delete.java swing\Update.java swing\Search.java swing\UsnHistory.java swing\RoundedBorder.java
```

Run:

```powershell
java -cp ".;swing;shared;mysql-connector-j-9.6.0\mysql-connector-j-9.6.0\mysql-connector-j-9.6.0.jar" MyFrame
```

## 5. Deploy and Run Web App (Tomcat 9)

Example Tomcat path used in this project setup:
`C:\Program Files\Apache Software Foundation\Tomcat 9.0_Tomcat90`

### Step A: Create target classes folder

```powershell
New-Item -ItemType Directory -Force "C:\Program Files\Apache Software Foundation\Tomcat 9.0_Tomcat90\webapps\adv_java\WEB-INF\classes"
```

### Step B: Compile servlets and DAO into WEB-INF/classes

```powershell
javac -d "C:\Program Files\Apache Software Foundation\Tomcat 9.0_Tomcat90\webapps\adv_java\WEB-INF\classes" -cp ".;C:\Program Files\Apache Software Foundation\Tomcat 9.0_Tomcat90\lib\servlet-api.jar;C:\Program Files\Apache Software Foundation\Tomcat 9.0_Tomcat90\lib\mysql-connector-j-9.6.0.jar" servlet\AddStudentServlet.java servlet\UpdateStudentServlet.java servlet\DeleteStudentServlet.java servlet\SearchStudentServlet.java servlet\StudentWebDao.java servlet\ServletUi.java shared\dbConnect.java shared\Validation.java
```

### Step C: Copy JSP + WEB-INF config

```powershell
Copy-Item -Path "E:\adv_java\web\*" -Destination "C:\Program Files\Apache Software Foundation\Tomcat 9.0_Tomcat90\webapps\adv_java" -Recurse -Force
```

### Step D: Start Tomcat

```powershell
& "C:\Program Files\Apache Software Foundation\Tomcat 9.0_Tomcat90\bin\startup.bat"
```

### Step E: Open in browser

- Home: http://localhost:8080/adv_java/

If your Tomcat HTTP port is changed (for example 9090), use:

- http://localhost:9090/adv_java/

## Common Issues

### error: no source files

You ran javac without listing .java files at the end of the command.

### package javax.servlet does not exist

You must include servlet-api.jar in javac classpath.

### Port conflict on 8080

Change Tomcat Connector port in conf/server.xml and restart Tomcat.

## License

Use this project for learning and academic purposes unless your repository defines a different license.
