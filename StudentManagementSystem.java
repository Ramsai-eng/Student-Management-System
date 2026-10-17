import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private final int id;
    private final String name;
    private final String course;
    private final double marks;

    Student(int id, String name, String course, double marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    int getId() { return id; }
    String getName() { return name; }
    String getCourse() { return course; }
    double getMarks() { return marks; }

    void display() {
        System.out.printf("%-8d %-22s %-18s %.2f%n", id, name, course, marks);
    }
}

public class StudentManagementSystem {
    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Delete student by ID");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> deleteStudent();
                case 5 -> running = false;
                default -> System.out.println("Invalid option. Choose 1-5.");
            }
        }
        System.out.println("Goodbye!");
        scanner.close();
    }

    private static void addStudent() {
        System.out.print("Student ID: ");
        int id = readInt();

        if (findById(id) != null) {
            System.out.println("That ID already exists.");
            return;
        }

        System.out.print("Student name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Course/branch: ");
        String course = scanner.nextLine().trim();
        System.out.print("Marks (0-100): ");
        double marks = readDouble();

        if (name.isEmpty() || course.isEmpty() || marks < 0 || marks > 100) {
            System.out.println("Invalid details. Name/course must not be empty and marks must be 0-100.");
            return;
        }

        students.add(new Student(id, name, course, marks));
        System.out.println("Student added successfully.");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.printf("%-8s %-22s %-18s %s%n", "ID", "Name", "Course", "Marks");
        for (Student student : students) {
            student.display();
        }
    }

    private static void searchStudent() {
        System.out.print("Enter student ID to search: ");
        Student student = findById(readInt());
        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.printf("%-8s %-22s %-18s %s%n", "ID", "Name", "Course", "Marks");
            student.display();
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = readInt();
        boolean removed = students.removeIf(student -> student.getId() == id);
        System.out.println(removed ? "Student deleted." : "Student not found.");
    }

    private static Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) return student;
        }
        return null;
    }

    private static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Enter a valid whole number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static double readDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Enter a valid number: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
}
