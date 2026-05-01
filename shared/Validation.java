/**
 * Validation Utility Class - Uses String Methods for Input Validation
 * Validates all student record fields with specific formats and constraints
 */
public class Validation {

    // ========== NAME VALIDATION ==========
    /**
     * Validates Name - Only alphabets and spaces, no numerical numbers
     * Using: matches() - regex pattern matching
     */
    public static boolean isValidName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        // Pattern: Only letters (a-z, A-Z) and spaces allowed
        return name.matches("^[a-zA-Z\\s]+$");
    }

    // ========== USN VALIDATION ==========
    /**
     * Validates USN Format: 1 digit + 2 alphabets + 2 digits + 2 alphabets + 3
     * digits
     * Example: 1AB12CD345
     * Using: matches() - complex regex pattern
     */
    public static boolean isValidUSN(String usn) {
        if (usn == null || usn.isEmpty()) {
            return false;
        }
        // Pattern: 1 digit, 2 letters, 2 digits, 2 letters, 3 digits
        return usn.matches("^\\d{1}[a-zA-Z]{2}\\d{2}[a-zA-Z]{2}\\d{3}$");
    }

    // ========== SEM VALIDATION ==========
    /**
     * Validates SEM - Should be numerical and <= 8
     * Using: matches() - numeric check, Integer.parseInt() - conversion
     */
    public static boolean isValidSEM(String sem) {
        if (sem == null || sem.isEmpty()) {
            return false;
        }
        sem = sem.trim();
        // First check if it contains only digits
        if (!sem.matches("^\\d+$")) {
            return false;
        }
        try {
            int semNum = Integer.parseInt(sem);
            return semNum > 0 && semNum <= 8;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // ========== EMAIL VALIDATION ==========
    /**
     * Validates Email - Must contain @gmail.com
     * Using: contains() - substring check, endsWith() - suffix check
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        email = email.trim();
        // Email must contain @ and end with @gmail.com
        return email.contains("@") && email.endsWith("@gmail.com");
    }

    // ========== CITY VALIDATION ==========
    /**
     * Validates City - String only, no numbers
     * Using: matches() - regex pattern, allowing only alphabets and spaces
     */
    public static boolean isValidCity(String city) {
        if (city == null || city.isEmpty()) {
            return false;
        }
        // Pattern: Only letters and spaces
        return city.matches("^[a-zA-Z\\s]+$");
    }

    // ========== PIN CODE VALIDATION ==========
    /**
     * Validates Pin Code - Minimum 6 digits, only numbers
     * Using: matches() - digit pattern, length() - minimum length check
     */
    public static boolean isValidPinCode(String pinCode) {
        if (pinCode == null || pinCode.isEmpty()) {
            return false;
        }
        pinCode = pinCode.trim();
        // Pattern: 6 or more consecutive digits
        return pinCode.matches("^\\d{6,}$");
    }

    // ========== DATE OF BIRTH VALIDATION ==========
    /**
     * Validates DOB - Format DD-MM-YY, only numbers
     * Using: matches() - strict date format pattern
     */
    public static boolean isValidDOB(String dob) {
        if (dob == null || dob.isEmpty()) {
            return false;
        }
        dob = dob.trim();
        // Pattern: DD-MM-YY (exactly 8 digits with dashes)
        if (!dob.matches("^\\d{2}-\\d{2}-\\d{2}$")) {
            return false;
        }
        // Additional validation for day and month ranges
        String[] parts = dob.split("-");
        try {
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return day >= 1 && day <= 31 && month >= 1 && month <= 12 && year >= 0 && year <= 99;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    // ========== MOBILE NUMBER VALIDATION ==========
    /**
     * Validates Mobile - Exactly 10 digits, only numbers
     * Using: matches() - digit pattern, length() - exact length check
     */
    public static boolean isValidMobile(String mobile) {
        if (mobile == null || mobile.isEmpty()) {
            return false;
        }
        mobile = mobile.trim();
        // Pattern: Exactly 10 consecutive digits
        return mobile.matches("^\\d{10}$");
    }

    // ========== BRANCH VALIDATION ==========
    /**
     * Validates Branch - String only, no numbers
     * Using: matches() - alphabetic pattern
     */
    public static boolean isValidBranch(String branch) {
        if (branch == null || branch.isEmpty()) {
            return false;
        }
        // Pattern: Only letters and spaces
        return branch.matches("^[a-zA-Z\\s]+$");
    }

    // ========== YEAR VALIDATION ==========
    /**
     * Validates Year - Numerical only
     * Using: matches() - digit check, Integer.parseInt() - conversion
     */
    public static boolean isValidYear(String year) {
        if (year == null || year.isEmpty()) {
            return false;
        }
        year = year.trim();
        // Check if contains only digits
        if (!year.matches("^\\d+$")) {
            return false;
        }
        try {
            int y = Integer.parseInt(year);
            // Year must be between 1 and 4 (inclusive)
            return y >= 1 && y <= 4;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // ========== DEGREE PROGRAM VALIDATION ==========
    /**
     * Validates Degree Program - String only, no numbers
     * Using: matches() - alphabetic pattern
     */
    public static boolean isValidDegreeProgram(String degreeProgram) {
        if (degreeProgram == null || degreeProgram.isEmpty()) {
            return false;
        }
        // Pattern: Only letters and spaces
        return degreeProgram.matches("^[a-zA-Z\\s]+$");
    }

    // ========== CURRENT SEMESTER SUBJECTS VALIDATION ==========
    /**
     * Validates Current Subjects - Strings only (alphanumeric, comma, spaces)
     * Using: matches() - alphanumeric with special chars pattern
     */
    public static boolean isValidSubjects(String subjects) {
        if (subjects == null || subjects.isEmpty()) {
            return false;
        }
        // Pattern: Letters, numbers, commas, and spaces allowed
        return subjects.matches("^[a-zA-Z0-9,\\s()&-]+$");
    }

    // ========== ATTENDANCE VALIDATION ==========
    /**
     * Validates Attendance - Numerical only (0-100 percentage)
     * Using: matches() - numeric check, Double.parseDouble() - conversion
     */
    public static boolean isValidAttendance(String attendance) {
        if (attendance == null || attendance.isEmpty()) {
            return false;
        }
        attendance = attendance.trim();
        // Check if contains only digits (and optional decimal point)
        if (!attendance.matches("^\\d+(\\.\\d{1,2})?$")) {
            return false;
        }
        try {
            double att = Double.parseDouble(attendance);
            return att >= 0 && att <= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // ========== GENDER VALIDATION ==========
    /**
     * Validates Gender - Only predefined values
     * Using: equals() - exact string comparison
     */
    public static boolean isValidGender(String gender) {
        if (gender == null || gender.isEmpty()) {
            return false;
        }
        return gender.equals("Male") || gender.equals("Female") || gender.equals("Other");
    }

    // ========== DEPARTMENT ID VALIDATION ==========
    /**
     * Validates Department ID - Alphanumeric
     * Using: matches() - alphanumeric pattern
     */
    public static boolean isValidDepartmentId(String deptId) {
        if (deptId == null || deptId.isEmpty()) {
            return false;
        }
        return deptId.matches("^[a-zA-Z0-9]+$");
    }

    // ========== COMBINED VALIDATION ==========
    /**
     * Validates all fields together
     * Returns detailed error message
     */
    public static String validateAllFields(
            String name, String usn, String sem, String email, String city,
            String pinCode, String dob, String gender, String mobile, String branch,
            String year, String degreeProgram, String departmentId, String subjects, String attendance) {
        StringBuilder errors = new StringBuilder();

        if (!isValidName(name)) {
            errors.append("Invalid Name - Only letters and spaces allowed").append("\n");
        }
        if (!isValidUSN(usn)) {
            errors.append(
                    "Invalid USN - Format: 1 digit + 2 letters + 2 digits + 2 letters + 3 digits (e.g., 1AB12CD345)")
                    .append("\n");
        }
        if (!isValidSEM(sem)) {
            errors.append("Invalid SEM - Must be a number from 1 to 8").append("\n");
        }
        if (!isValidEmail(email)) {
            errors.append("Invalid Email - Must end with @gmail.com").append("\n");
        }
        if (!isValidCity(city)) {
            errors.append("Invalid City - Only letters and spaces allowed").append("\n");
        }
        if (!isValidPinCode(pinCode)) {
            errors.append("Invalid Pin Code - Minimum 6 digits required").append("\n");
        }
        if (!isValidDOB(dob)) {
            errors.append("Invalid DOB - Format must be DD-MM-YY (e.g., 15-06-02)").append("\n");
        }
        if (!isValidGender(gender)) {
            errors.append("Invalid Gender - Select Male, Female, or Other").append("\n");
        }
        if (!isValidMobile(mobile)) {
            errors.append("Invalid Mobile - Exactly 10 digits required").append("\n");
        }
        if (!isValidBranch(branch)) {
            errors.append("Invalid Branch - Only letters and spaces allowed").append("\n");
        }
        if (!isValidYear(year)) {
            errors.append("Invalid Year - Must be a number between 1 and 4").append("\n");
        }
        if (!isValidDegreeProgram(degreeProgram)) {
            errors.append("Invalid Degree Program - Only letters and spaces allowed").append("\n");
        }
        if (!isValidDepartmentId(departmentId)) {
            errors.append("Invalid Department ID - Alphanumeric only").append("\n");
        }
        if (!isValidSubjects(subjects)) {
            errors.append("Invalid Subjects - Only letters, numbers, commas, and spaces allowed").append("\n");
        }
        if (!isValidAttendance(attendance)) {
            errors.append("Invalid Attendance - Must be a number between 0 and 100").append("\n");
        }

        return errors.toString(); // Empty string means all valid
    }

    // ========== INDIVIDUAL FIELD ERROR MESSAGES ==========
    /**
     * Get specific error message for a field
     */
    public static String getFieldErrorMessage(String fieldName, String value) {
        switch (fieldName.toLowerCase()) {
            case "name":
                return isValidName(value) ? "" : "Name: Only letters and spaces allowed";
            case "usn":
                return isValidUSN(value) ? "" : "USN: Format 1digit+2letters+2digits+2letters+3digits";
            case "sem":
                return isValidSEM(value) ? "" : "SEM: Number from 1 to 8";
            case "email":
                return isValidEmail(value) ? "" : "Email: Must end with @gmail.com";
            case "city":
                return isValidCity(value) ? "" : "City: Only letters and spaces";
            case "pincode":
            case "pin_code":
                return isValidPinCode(value) ? "" : "Pin Code: Minimum 6 digits";
            case "dob":
            case "date of birth":
                return isValidDOB(value) ? "" : "DOB: Format DD-MM-YY";
            case "gender":
                return isValidGender(value) ? "" : "Gender: Male, Female, or Other";
            case "mobile":
            case "mobile_number":
                return isValidMobile(value) ? "" : "Mobile: Exactly 10 digits";
            case "branch":
                return isValidBranch(value) ? "" : "Branch: Only letters and spaces";
            case "year":
                return isValidYear(value) ? "" : "Year: Number between 1 and 4";
            case "degree_program":
            case "degreeprogram":
                return isValidDegreeProgram(value) ? "" : "Degree Program: Only letters and spaces";
            case "department_id":
            case "departmentid":
                return isValidDepartmentId(value) ? "" : "Department ID: Alphanumeric only";
            case "subjects":
            case "current_semester_subjects":
                return isValidSubjects(value) ? "" : "Subjects: Letters, numbers, commas, spaces";
            case "attendance":
            case "attendance_percent":
                return isValidAttendance(value) ? "" : "Attendance: Number between 0-100";
            default:
                return "Unknown field";
        }
    }

    /**
     * Returns a multi-line string describing all validation constraints.
     */
    public static String getAllConstraints() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: Only letters and spaces allowed\n");
        sb.append("USN: Format 1 digit + 2 letters + 2 digits + 2 letters + 3 digits (e.g., 1AB12CD345)\n");
        sb.append("SEM: Number from 1 to 8\n");
        sb.append("Email: Must contain @ and end with @gmail.com\n");
        sb.append("City: Only letters and spaces allowed\n");
        sb.append("Pin Code: Minimum 6 digits (numbers only)\n");
        sb.append("DOB: Format DD-MM-YY (e.g., 15-06-02)\n");
        sb.append("Gender: One of Male, Female, or Other\n");
        sb.append("Mobile: Exactly 10 digits (numbers only)\n");
        sb.append("Branch: Only letters and spaces allowed\n");
        sb.append("Year: Numeric only, value 1 to 4\n");
        sb.append("Degree Program: Only letters and spaces allowed\n");
        sb.append("Department ID: Alphanumeric only\n");
        sb.append("Subjects: Letters, numbers, commas, spaces, parentheses, ampersand, hyphen allowed\n");
        sb.append("Attendance: Number between 0 and 100 (optional decimal up to 2 places)\n");
        return sb.toString();
    }
}
