import java.util.*;

class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name;
    }
}

class Course {
    private String code;
    private String title;

    public Course(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Course Code: " + code + ", Title: " + title;
    }
}

class Enrollment {
    private Student student;
    private Course course;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "Enrollment - " + student.toString() + ", " + course.toString();
    }
}

public class UniversityManagementSystem {
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Enrollment> enrollments = new ArrayList<>();

    public static void main(String[] args) {
        UniversityManagementSystem ums = new UniversityManagementSystem();
        ums.showMenu();
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("University Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. View Student Enrollments");
            System.out.println("5. View Course Enrollments");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    addCourse(scanner);
                    break;
                case 3:
                    enrollStudent(scanner);
                    break;
                case 4:
                    viewStudentEnrollments(scanner);
                    break;
                case 5:
                    viewCourseEnrollments(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
        scanner.close();
    }

    private void addStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        students.add(new Student(id, name));
        System.out.println("Student added successfully!");
    }

    private void addCourse(Scanner scanner) {
        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine();
        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine();
        courses.add(new Course(code, title));
        System.out.println("Course added successfully!");
    }

    private void enrollStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        enrollments.add(new Enrollment(student, course));
        System.out.println("Student enrolled in course successfully!");
    }

    private void viewStudentEnrollments(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Enrollments for Student ID: " + studentId);
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().getId().equals(studentId)) {
                System.out.println(enrollment.getCourse());
            }
        }
    }

    private void viewCourseEnrollments(Scanner scanner) {
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.println("Enrollments for Course Code: " + courseCode);
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourse().getCode().equals(courseCode)) {
                System.out.println(enrollment.getStudent());
            }
        }
    }

    private Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    private Course findCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        return null;
    }
}
