import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentWebDao {
    public boolean addStudent(
            String usn,
            String name,
            String sem,
            String email,
            String city,
            String pinCode,
            String dob,
            String gender,
            String mobile,
            String branch,
            String year,
            String degreeProgram,
            String departmentId,
            String subjects,
            String attendance) throws SQLException, ClassNotFoundException {

        String insertSql = "INSERT INTO sdata "
                + "(`USN`, `Name`, `SEM`, `Email`, `City`, `Pin_Code`, `DOB`, `Gender`, `Mobile_Number`, "
                + "`Branch`, `Year`, `Degree_Program`, `Department_ID`, `Current_Semester_Subjects`, `Attendance_Percent`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = new dbConnect().getConnection();
                PreparedStatement ps = conn.prepareStatement(insertSql)) {
            ps.setString(1, usn);
            ps.setString(2, name);
            ps.setString(3, sem);
            ps.setString(4, email);
            ps.setString(5, city);
            ps.setString(6, pinCode);
            ps.setString(7, dob);
            ps.setString(8, gender);
            ps.setString(9, mobile);
            ps.setString(10, branch);
            ps.setString(11, year);
            ps.setString(12, degreeProgram);
            ps.setString(13, departmentId);
            ps.setString(14, subjects);
            ps.setString(15, attendance);
            return ps.executeUpdate() > 0;
        }
    }

    public StudentRecord searchByUsn(String usn) throws SQLException, ClassNotFoundException {
        String sql = "SELECT `USN`, `Name`, `SEM`, `Email`, `City`, `Pin_Code`, `DOB`, `Gender`, "
                + "`Mobile_Number`, `Branch`, `Year`, `Degree_Program`, `Department_ID`, "
                + "`Current_Semester_Subjects`, `Attendance_Percent` FROM sdata WHERE `USN` = ?";

        try (Connection conn = new dbConnect().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usn);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                return new StudentRecord(
                        rs.getString("USN"),
                        rs.getString("Name"),
                        rs.getString("SEM"),
                        rs.getString("Email"),
                        rs.getString("City"),
                        rs.getString("Pin_Code"),
                        rs.getString("DOB"),
                        rs.getString("Gender"),
                        rs.getString("Mobile_Number"),
                        rs.getString("Branch"),
                        rs.getString("Year"),
                        rs.getString("Degree_Program"),
                        rs.getString("Department_ID"),
                        rs.getString("Current_Semester_Subjects"),
                        rs.getString("Attendance_Percent"));
            }
        }
    }

    public boolean updateEditableFields(
            String usn,
            String sem,
            String email,
            String mobile,
            String year,
            String subjects,
            String attendance) throws SQLException, ClassNotFoundException {

        String updateSql = "UPDATE sdata SET `SEM` = ?, `Email` = ?, `Mobile_Number` = ?, `Year` = ?, "
                + "`Current_Semester_Subjects` = ?, `Attendance_Percent` = ? WHERE `USN` = ?";

        try (Connection conn = new dbConnect().getConnection();
                PreparedStatement ps = conn.prepareStatement(updateSql)) {
            ps.setString(1, sem);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, year);
            ps.setString(5, subjects);
            ps.setString(6, attendance);
            ps.setString(7, usn);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteByUsn(String usn) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM sdata WHERE `USN` = ?";
        try (Connection conn = new dbConnect().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usn);
            return ps.executeUpdate() > 0;
        }
    }

    public static final class StudentRecord {
        public final String usn;
        public final String name;
        public final String sem;
        public final String email;
        public final String city;
        public final String pinCode;
        public final String dob;
        public final String gender;
        public final String mobile;
        public final String branch;
        public final String year;
        public final String degreeProgram;
        public final String departmentId;
        public final String subjects;
        public final String attendance;

        public StudentRecord(
                String usn,
                String name,
                String sem,
                String email,
                String city,
                String pinCode,
                String dob,
                String gender,
                String mobile,
                String branch,
                String year,
                String degreeProgram,
                String departmentId,
                String subjects,
                String attendance) {
            this.usn = usn;
            this.name = name;
            this.sem = sem;
            this.email = email;
            this.city = city;
            this.pinCode = pinCode;
            this.dob = dob;
            this.gender = gender;
            this.mobile = mobile;
            this.branch = branch;
            this.year = year;
            this.degreeProgram = degreeProgram;
            this.departmentId = departmentId;
            this.subjects = subjects;
            this.attendance = attendance;
        }
    }
}
