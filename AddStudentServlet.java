import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {
    private final StudentWebDao dao = new StudentWebDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            resp.sendRedirect("add-student.jsp?status=Please+fill+required+fields");
            return;
        }

        if (!email.contains("@") || mobile.length() < 10 || !mobile.matches("\\d+")) {
            resp.sendRedirect("add-student.jsp?status=Invalid+email+or+mobile+number");
            return;
        }

        try {
            boolean ok = dao.addStudent(usn, name, sem, email, city, pinCode, dob, gender, mobile,
                    branch, year, degreeProgram, departmentId, subjects, attendance);
            resp.sendRedirect("add-student.jsp?status=" + (ok ? "Student+added+successfully" : "Add+failed"));
        } catch (Exception ex) {
            resp.sendRedirect("add-student.jsp?status=Error:+" + url(ex.getMessage()));
        }
    }

    private static String safe(String value) {
        return value == null ? "" : value.trim();
    }

    private static String url(String value) {
        return value == null ? "" : value.replace(" ", "+");
    }
}
