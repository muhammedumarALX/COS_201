import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // ==========================================
    // GLOBAL OBJECTS
    // ==========================================
    private static final Scanner scanner = new Scanner(System.in);
    private static final CourseManager manager = new CourseManager();

    // ==========================================
    // MAIN METHOD
    // ==========================================
    public static void main(String[] args) {

        // Load previously saved courses
        manager.loadCourses();

        // Start the program
        displayMenu();

    }

    // ==========================================
    // DISPLAY MENU (RECURSION)
    // ==========================================
    private static void displayMenu() {

        System.out.println("\n===========================================");
        System.out.println("      STUDENT COURSE MANAGEMENT SYSTEM");
        System.out.println("===========================================");
        System.out.println("1. Add Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Search Course");
        System.out.println("4. Compute Total Units");
        System.out.println("5. Save Courses");
        System.out.println("6. Load Courses");
        System.out.println("7. Exit");
        System.out.println("===========================================");
        System.out.print("Enter your choice: ");

        try {

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addCourse();
                    break;

                case 2:
                    manager.viewCourses();
                    break;

                case 3:
                    searchCourse();
                    break;

                case 4:
                    System.out.println("\nTotal Credit Units: "
                            + manager.computeTotalUnits());
                    break;

                case 5:
                    manager.saveCourses();
                    break;

                case 6:
                    manager.loadCourses();
                    System.out.println("\nCourses loaded successfully.");
                    break;

                case 7:
                    System.out.println("\nThank you for using the system.");
                    System.exit(0);

                default:
                    System.out.println("\nInvalid choice.");

            }

        }

        catch (InputMismatchException e) {

            System.out.println("\nPlease enter a valid number.");
            scanner.nextLine();

        }

        // Recursive call
        displayMenu();

    }

    // ==========================================
    // ADD COURSE
    // ==========================================
    private static void addCourse() {

        try {

            System.out.print("\nEnter Course Code: ");
            String code = scanner.nextLine().trim().toUpperCase();

            if (code.isEmpty()) {
                throw new InvalidCourseException(
                        "Course code cannot be empty.");
            }

            if (manager.courseExists(code)) {
                throw new InvalidCourseException(
                        "Course code already exists.");
            }

            System.out.print("Enter Course Title: ");
            String title = scanner.nextLine().trim();

            if (title.isEmpty()) {
                throw new InvalidCourseException(
                        "Course title cannot be empty.");
            }

            System.out.print("Enter Credit Unit (1 - 6): ");

            int unit;

            try {

                unit = scanner.nextInt();
                scanner.nextLine();

            }

            catch (InputMismatchException e) {

                scanner.nextLine();

                throw new InvalidCourseException(
                        "Credit unit must be a whole number.");

            }

            if (unit < 1 || unit > 6) {

                throw new InvalidCourseException(
                        "Credit unit must be between 1 and 6.");

            }

            Course course = new Course(code, title, unit);

            manager.addCourse(course);

            System.out.println("\nCourse added successfully.");

        }

        catch (InvalidCourseException e) {

            System.out.println("\nError: " + e.getMessage());

        }

    }

    // ==========================================
    // SEARCH COURSE
    // ==========================================
    private static void searchCourse() {

        System.out.print("\nEnter Course Code: ");

        String code = scanner.nextLine().trim().toUpperCase();

        Course course = manager.searchCourse(code);

        if (course != null) {

            System.out.println("\nCourse Found");
            System.out.println("--------------------------");
            System.out.println(course);

        }

        else {

            System.out.println("\nCourse not found.");

        }

    }

}
