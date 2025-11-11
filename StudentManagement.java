import java.util.ArrayList;
import java.util.Scanner;

// Student class: holds student data
class Student {
    int rollNo;
    String name;
    int age;
    String dept;
    double marks;

    Student(int rollNo, String name, int age, String dept, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.dept = dept;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "RollNo: " + rollNo +
               ", Name: " + name +
               ", Age: " + age +
               ", Dept: " + dept +
               ", Marks: " + marks;
    }
}

public class StudentManagement {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            showMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> {
                    System.out.println("Exiting... Bye!");
                    exit = true;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
            System.out.println(); // blank line
        }
        sc.close();
    }

    static void showMenu() {
        System.out.println("===== Student Management System =====");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by Roll No");
        System.out.println("4. Update Student Details");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
    }

    static void addStudent() {
        System.out.println("---- Add Student ----");
        int roll = readInt("Enter roll number: ");
        // ensure unique roll:
        if (findIndexByRoll(roll) != -1) {
            System.out.println("A student with this roll number already exists.");
            return;
        }
        String name = readString("Enter name: ");
        int age = readInt("Enter age: ");
        String dept = readString("Enter department: ");
        double marks = readDouble("Enter marks: ");
        Student s = new Student(roll, name, age, dept, marks);
        students.add(s);
        System.out.println("Student added successfully.");
    }

    static void viewStudents() {
        System.out.println("---- All Students ----");
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    static void searchStudent() {
        System.out.println("---- Search Student ----");
        int roll = readInt("Enter roll number to search: ");
        int idx = findIndexByRoll(roll);
        if (idx == -1) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Found: " + students.get(idx));
        }
    }

    static void updateStudent() {
        System.out.println("---- Update Student ----");
        int roll = readInt("Enter roll number to update: ");
        int idx = findIndexByRoll(roll);
        if (idx == -1) {
            System.out.println("Student not found.");
            return;
        }
        Student s = students.get(idx);
        System.out.println("Current: " + s);
        String name = readString("Enter new name (or press enter to keep '" + s.name + "'): ", true);
        if (!name.isEmpty()) s.name = name;
        String ageInput = readString("Enter new age (or press enter to keep '" + s.age + "'): ", true);
        if (!ageInput.isEmpty()) s.age = Integer.parseInt(ageInput);
        String dept = readString("Enter new department (or press enter to keep '" + s.dept + "'): ", true);
        if (!dept.isEmpty()) s.dept = dept;
        String marksInput = readString("Enter new marks (or press enter to keep '" + s.marks + "'): ", true);
        if (!marksInput.isEmpty()) s.marks = Double.parseDouble(marksInput);
        System.out.println("Student updated successfully.");
    }

    static void deleteStudent() {
        System.out.println("---- Delete Student ----");
        int roll = readInt("Enter roll number to delete: ");
        int idx = findIndexByRoll(roll);
        if (idx == -1) {
            System.out.println("Student not found.");
            return;
        }
        students.remove(idx);
        System.out.println("Student deleted successfully.");
    }

    // helper: find index by roll number, -1 if not found
    static int findIndexByRoll(int roll) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).rollNo == roll) return i;
        }
        return -1;
    }

    // safe integer input with prompt
    static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    // safe double input
    static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    // read String (non-empty)
    static String readString(String prompt) {
        return readString(prompt, false);
    }

    // read String, allowEmpty true -> returns "" if user presses enter
    static String readString(String prompt, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            if (allowEmpty) return s;
            if (!s.trim().isEmpty()) return s.trim();
            System.out.println("Input cannot be empty. Please try again.");
        }
    }
}