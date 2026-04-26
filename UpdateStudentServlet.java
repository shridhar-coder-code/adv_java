import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update-student")
public class UpdateStudentServlet extends HttpServlet {
    private final StudentWebDao dao = new StudentWebDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usn = safe(req.getParameter("usn"));
        String sem = safe(req.getParameter("sem"));
        String email = safe(req.getParameter("email"));
        String mobile = safe(req.getParameter("mobile"));
        String year = safe(req.getParameter("year"));
        String subjects = safe(req.getParameter("subjects"));
        String attendance = safe(req.getParameter("attendance"));

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

        try {
            boolean ok = dao.updateEditableFields(usn, sem, email, mobile, year, subjects, attendance);
            resp.sendRedirect(
                    "update-student.jsp?status=" + (ok ? "Student+updated+successfully" : "No+record+updated"));
        } catch (Exception ex) {
            resp.sendRedirect("update-student.jsp?status=Error:+" + url(ex.getMessage()));
        }
    }

    private static String safe(String value) {
        return value == null ? "" : value.trim();
    }

    private static String url(String value) {
        return value == null ? "" : value.replace(" ", "+");
    }
}
