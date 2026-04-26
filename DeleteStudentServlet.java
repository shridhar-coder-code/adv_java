import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete-student")
public class DeleteStudentServlet extends HttpServlet {
    private final StudentWebDao dao = new StudentWebDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usn = safe(req.getParameter("usn"));
        if (usn.isEmpty()) {
            resp.sendRedirect("delete-student.jsp?status=USN+is+required");
            return;
        }

        try {
            boolean ok = dao.deleteByUsn(usn);
            resp.sendRedirect(
                    "delete-student.jsp?status=" + (ok ? "Student+deleted+successfully" : "Student+not+found"));
        } catch (Exception ex) {
            resp.sendRedirect("delete-student.jsp?status=Error:+" + url(ex.getMessage()));
        }
    }

    private static String safe(String value) {
        return value == null ? "" : value.trim();
    }

    private static String url(String value) {
        return value == null ? "" : value.replace(" ", "+");
    }
}
