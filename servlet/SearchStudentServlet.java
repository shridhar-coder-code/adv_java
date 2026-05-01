import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchStudentServlet extends HttpServlet {
    private final StudentWebDao dao = new StudentWebDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usn = safe(req.getParameter("usn"));
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        ServletUi.begin(out, "Search Student");
        ServletUi.heading(out, "Search Result");

        if (usn.isEmpty()) {
            ServletUi.message(out, "Please enter USN.");
            ServletUi.actions(out,
                    ServletUi.button("search-student.jsp", "Back to Form"),
                    ServletUi.button("index.jsp", "Home"));
            ServletUi.end(out);
            return;
        }

        if (!Validation.isValidUSN(usn)) {
            ServletUi.message(out,
                    "Invalid USN format. Expected: 1 digit + 2 letters + 2 digits + 2 letters + 3 digits");
            ServletUi.actions(out,
                    ServletUi.button("search-student.jsp", "Back to Form"),
                    ServletUi.button("index.jsp", "Home"));
            ServletUi.end(out);
            return;
        }

        try {
            StudentWebDao.StudentRecord s = dao.searchByUsn(usn);
            if (s == null) {
                ServletUi.message(out, "No record found for USN: " + usn);
            } else {
                out.println("<table>");
                row(out, "USN", s.usn);
                row(out, "Name", s.name);
                row(out, "SEM", s.sem);
                row(out, "Email", s.email);
                row(out, "City", s.city);
                row(out, "Pin Code", s.pinCode);
                row(out, "DOB", s.dob);
                row(out, "Gender", s.gender);
                row(out, "Mobile", s.mobile);
                row(out, "Branch", s.branch);
                row(out, "Year", s.year);
                row(out, "Degree Program", s.degreeProgram);
                row(out, "Department ID", s.departmentId);
                row(out, "Subjects", s.subjects);
                row(out, "Attendance", s.attendance);
                out.println("</table>");
            }
        } catch (Exception ex) {
            ServletUi.message(out, "Error: " + ex.getMessage());
        }

        ServletUi.actions(out,
                ServletUi.button("search-student.jsp", "Back to Form"),
                ServletUi.button("index.jsp", "Home"));
        ServletUi.end(out);
    }

    private static void row(PrintWriter out, String key, String value) {
        ServletUi.row(out, key, value);
    }

    private static String safe(String value) {
        return value == null ? "" : value.trim();
    }
}
