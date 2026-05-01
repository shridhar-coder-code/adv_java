import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteStudentServlet extends HttpServlet {
    private final StudentWebDao dao = new StudentWebDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String usn = safe(req.getParameter("usn"));
        if (usn.isEmpty()) {
            renderError(out, "Delete Student", "USN is required", "delete-student.jsp");
            return;
        }

        if (!Validation.isValidUSN(usn)) {
            renderError(out, "Delete Student",
                    "Invalid USN format. Expected: 1 digit + 2 letters + 2 digits + 2 letters + 3 digits",
                    "delete-student.jsp");
            return;
        }

        try {
            boolean ok = dao.deleteByUsn(usn);
            renderSuccess(out, "Delete Student",
                    ok ? "Student deleted successfully" : "Student not found",
                    "delete-student.jsp");
        } catch (Exception ex) {
            renderError(out, "Delete Student", "Database Error: " + ex.getMessage(), "delete-student.jsp");
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
