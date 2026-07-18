// Create Course class

public class Course {

    // ==========================================
    // INSTANCE VARIABLES
    // ==========================================
    private String courseCode;
    private String courseTitle;
    private int units;

    // ==========================================
    // CONSTRUCTOR
    // ==========================================
    public Course(String courseCode, String courseTitle, int units) {
        this.courseCode = courseCode.trim().toUpperCase();
        this.courseTitle = courseTitle.trim().toUpperCase();
        this.units = units;
    }

    // ==========================================
    // GETTER METHODS
    // ==========================================
    public String fetchCourseCode() {
        return courseCode;
    }

    public String fetchCourseTitle() {
        return courseTitle;
    }

    public int fetchUnits() {
        return units;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    
    // CONVERT OBJECT TO FILE FORMAT
   
    public String toFileString() {
        return courseCode + "|" + courseTitle + "|" + units;
    }

    // ==========================================
    // CONVERT FILE DATA TO COURSE OBJECT
    // ==========================================
    public static Course fromFileString(String line) {

        String[] parts = line.split("\\|");

        if (parts.length != 3) {
            return null;
        }

        String code = parts[0];
        String title = parts[1];
        int units = Integer.parseInt(parts[2]);

        return new Course(code, title, units);
    }

    // ==========================================
    // DISPLAY COURSE DETAILS
    // ==========================================
    @Override
    public String toString() {

        return "Course Code : " + courseCode +
        "\nCourse Title: " + courseTitle +
        "\nCredit Unit : " + units;

    }
}
