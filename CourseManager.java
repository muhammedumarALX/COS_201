import java.io.*;
import java.util.ArrayList;

public class CourseManager {

    // ==========================================
    // INSTANCE VARIABLES
    // ==========================================
    private ArrayList<Course> courseList;
    private final String FILE_NAME = "courses.txt";

    // ==========================================
    // CONSTRUCTOR
    // ==========================================
    public CourseManager() {
        courseList = new ArrayList<>();
    }

    // ==========================================
    // ADD COURSE
    // ==========================================
    public void addCourse(Course course) {
        courseList.add(course);
    }

    // ==========================================
    // CHECK IF COURSE EXISTS
    // ==========================================
    public boolean courseExists(String code) {

        for (Course course : courseList) {

            if (course.fetchCourseCode().equalsIgnoreCase(code)) {
                return true;
            }

        }

        return false;
    }

    // ==========================================
    // VIEW ALL COURSES
    // ==========================================
    public void viewCourses() {

        if (courseList.isEmpty()) {
            System.out.println("\nNo courses available.");
            return;
        }

        System.out.println("\n========== COURSE LIST ==========");

        int count = 1;

        for (Course course : courseList) {

            System.out.println("\nCourse " + count++);
            System.out.println("----------------------------");
            System.out.println(course);

        }

    }

    // ==========================================
    // SEARCH COURSE
    // ==========================================
    public Course searchCourse(String code) {
        return recursiveSearch(code, 0);
    }

    // ==========================================
    // RECURSIVE SEARCH
    // ==========================================
    private Course recursiveSearch(String code, int index) {

        if (index >= courseList.size()) {
            return null;
        }

        Course current = courseList.get(index);

        if (current.fetchCourseCode().equalsIgnoreCase(code)) {
            return current;
        }

        return recursiveSearch(code, index + 1);
    }

    // ==========================================
    // COMPUTE TOTAL CREDIT UNITS
    // ==========================================
    public int computeTotalUnits() {

        int total = 0;

        for (Course course : courseList) {
            total += course.fetchUnits();
        }

        return total;
    }

    // ==========================================
    // SAVE COURSES TO FILE
    // ==========================================
    public void saveCourses() {

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {

            for (Course course : courseList) {
                writer.println(course.toFileString());
            }

            System.out.println("\nCourses saved successfully.");

        }

        catch (IOException e) {
            System.out.println("\nError saving courses.");
        }

    }

    // ==========================================
    // LOAD COURSES FROM FILE
    // ==========================================
    public void loadCourses() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        courseList.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                Course course = Course.fromFileString(line);

                if (course != null) {
                    courseList.add(course);
                }

            }

        }

        catch (IOException e) {

            System.out.println("\nError loading courses.");

        }

    }

    // ==========================================
    // RETURN COURSE LIST
    // ==========================================
    public ArrayList<Course> getCourseList() {
        return courseList;
    }

}



