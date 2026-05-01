import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateStudentServlet extends HttpServlet {
    private final StudentWebDao dao = new StudentWebDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String usn = safe(req.getParameter("usn"));
        String sem = safe(req.getParameter("sem"));
        String email = safe(req.getParameter("email"));
        String mobile = safe(req.getParameter("mobile"));
        String year = safe(req.getParameter("year"));
        String subjects = safe(req.getParameter("subjects"));
        String attendance = safe(req.getParameter("attendance"));

        if (usn.isEmpty()) {
            renderError(out, "Update Student", "USN is required", "update-student.jsp");
            return;
        }

        StringBuilder errors = new StringBuilder();
        String m;
        m = !sem.isEmpty() ? Validation.getFieldErrorMessage("sem", sem) : "";
        if (!m.isEmpty())
            errors.append(m).append("\n");
        m = !email.isEmpty() ? Validation.getFieldErrorMessage("email", email) : "";
        if (!m.isEmpty())
            errors.append(m).append("\n");
        m = !mobile.isEmpty() ? Validation.getFieldErrorMessage("mobile", mobile) : "";
        if (!m.isEmpty())
            errors.append(m).append("\n");
        m = !year.isEmpty() ? Validation.getFieldErrorMessage("year", year) : "";
        if (!m.isEmpty())
            errors.append(m).append("\n");
        m = !subjects.isEmpty() ? Validation.getFieldErrorMessage("subjects", subjects) : "";
        if (!m.isEmpty())
            errors.append(m).append("\n");
        m = !attendance.isEmpty() ? Validation.getFieldErrorMessage("attendance", attendance) : "";
        if (!m.isEmpty())
            errors.append(m).append("\n");

        if (errors.length() > 0) {
            renderError(out, "Update Student", errors.toString().trim(), "update-student.jsp");
            return;
        }

        try {
            boolean ok = dao.updateEditableFields(usn, sem, email, mobile, year, subjects, attendance);
            renderSuccess(out, "Update Student",
                    ok ? "Student updated successfully" : "No record updated",
                    "update-student.jsp");
        } catch (Exception ex) {
            renderError(out, "Update Student", "Database Error: " + ex.getMessage(), "update-student.jsp");
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
