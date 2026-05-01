import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddStudentServlet extends HttpServlet {
    private final StudentWebDao dao = new StudentWebDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String usn = safe(req.getParameter("usn"));
        String name = safe(req.getParameter("name"));
        String sem = safe(req.getParameter("sem"));
        String email = safe(req.getParameter("email"));
        String city = safe(req.getParameter("city"));
        String pinCode = safe(req.getParameter("pinCode"));
        String dob = safe(req.getParameter("dob"));
        String gender = safe(req.getParameter("gender"));
        String mobile = safe(req.getParameter("mobile"));
        String branch = safe(req.getParameter("branch"));
        String year = safe(req.getParameter("year"));
        String degreeProgram = safe(req.getParameter("degreeProgram"));
        String departmentId = safe(req.getParameter("departmentId"));
        String subjects = safe(req.getParameter("subjects"));
        String attendance = safe(req.getParameter("attendance"));

        if (usn.isEmpty() || name.isEmpty() || sem.isEmpty() || email.isEmpty()) {
            renderError(out, "Add Student", "Please fill required fields",
                    "add-student.jsp");
            return;
        }

        // Comprehensive validation using Validation class
        String validationError = Validation.validateAllFields(
                name, usn, sem, email, city, pinCode, dob, gender, mobile,
                branch, year, degreeProgram, departmentId, subjects, attendance);

        if (!validationError.isEmpty()) {
            renderError(out, "Add Student", validationError, "add-student.jsp");
            return;
        }

        try {
            boolean ok = dao.addStudent(usn, name, sem, email, city, pinCode, dob, gender, mobile,
                    branch, year, degreeProgram, departmentId, subjects, attendance);
            renderSuccess(out, "Add Student",
                    ok ? "Student added successfully" : "Add failed",
                    "add-student.jsp");
        } catch (Exception ex) {
            renderError(out, "Add Student", "Database Error: " + ex.getMessage(), "add-student.jsp");
        }
    }

    private static String safe(String value) {
        return value == null ? "" : value.trim();
    }

    private static void renderSuccess(PrintWriter out, String title, String message, String backHref) {
        ServletUi.begin(out, title);
        ServletUi.heading(out, title);
        ServletUi.message(out, message);
        ServletUi.actions(out,
                ServletUi.button(backHref, "Back to Form"),
                ServletUi.button("index.jsp", "Home"));
        ServletUi.end(out);
    }

    private static void renderError(PrintWriter out, String title, String message, String backHref) {
        ServletUi.begin(out, title);
        ServletUi.heading(out, title);
        ServletUi.message(out, "Validation Error:\n" + message);
        ServletUi.actions(out,
                ServletUi.button(backHref, "Back to Form"),
                ServletUi.button("index.jsp", "Home"));
        ServletUi.end(out);
    }
}
