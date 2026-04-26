import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/search-student")
public class SearchStudentServlet extends HttpServlet {
    private final StudentWebDao dao = new StudentWebDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usn = safe(req.getParameter("usn"));
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><body style='font-family:Arial'>");
        out.println("<h2>Search Result</h2>");

        if (usn.isEmpty()) {
            out.println("<p>Please enter USN.</p>");
            out.println("<a href='search-student.jsp'>Back</a>");
            out.println("</body></html>");
            return;
        }

        try {
            StudentWebDao.StudentRecord s = dao.searchByUsn(usn);
            if (s == null) {
                out.println("<p>No record found for USN: " + escape(usn) + "</p>");
            } else {
                out.println("<table border='1' cellpadding='6' cellspacing='0'>");
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
            out.println("<p>Error: " + escape(ex.getMessage()) + "</p>");
        }

        out.println("<p><a href='search-student.jsp'>Back</a></p>");
        out.println("</body></html>");
    }

    private static void row(PrintWriter out, String key, String value) {
        out.println("<tr><td><b>" + escape(key) + "</b></td><td>" + escape(value) + "</td></tr>");
    }

    private static String safe(String value) {
        return value == null ? "" : value.trim();
    }

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
}
