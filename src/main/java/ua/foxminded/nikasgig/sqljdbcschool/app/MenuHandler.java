package ua.foxminded.nikasgig.sqljdbcschool.app;

import java.util.List;
import java.util.Scanner;

import ua.foxminded.nikasgig.sqljdbcschool.model.Group;
import ua.foxminded.nikasgig.sqljdbcschool.model.Student;
import ua.foxminded.nikasgig.sqljdbcschool.model.StudentCourse;
import ua.foxminded.nikasgig.sqljdbcschool.service.DataService;
import ua.foxminded.nikasgig.sqljdbcschool.service.GeneratorTestDataService;

public class MenuHandler {

    private boolean isTestDataGenerated = false;
    private Scanner in = new Scanner(System.in);
    private DataService dataService;

    private final String IntroMenu = "----------------------\n" 
            + "1. Generate the test data\n"
            + "2. Find all groups with less or equal student's number\n"
            + "3. Find all students related to the course with the given name\n" 
            + "4. Add a new student\n"
            + "5. Delete a student by the STUDENT_ID\n" 
            + "6. Add a student to the course (from a list)\n"
            + "7. Remove the student from one of their courses.\n" 
            + "0. Exit\n" 
            + ">>> ";

    public MenuHandler(DataService dataService) {
        this.dataService = dataService;
    }

    public void printMenu() {
        System.out.print(IntroMenu);
    }

    public int scanMenu() {
        while (!in.hasNextInt()) {
            System.out.println("Incorrect command");
            System.out.print(">>> ");
            in.nextLine();
        }
        return in.nextInt();
    }

    public void printLine() {
        System.out.println("\n----------------------");
    }

    public void generateTestData() {
        if (isTestDataGenerated) {
            System.out.println("The test data has already generated");
        } else {
            dataService.generateTestData();
            isTestDataGenerated = true;
        }
    }

    public void findGroupsWithStudents() {
        System.out.print("Enter number >>> ");
        int number = in.nextInt();
        System.out.println(dataService.findGroupsWithLessOrEqualStudents(number));
    }

    public void findStudentsByCourse() {
        System.out.println(GeneratorTestDataService.getCourses());
        System.out.print("\nEnter course name >>> ");
        String courseName = (String) in.next();
        System.out.println(dataService.findStudentsByCourseName(courseName));
    }

    public void createNewStudent() {
        System.out.print("Enter first name of new student >>> ");
        String firstName = in.next();
        System.out.print("Enter last name of new student >>> ");
        String lastName = in.next();
        System.out.println("New student: " + dataService.createNewStudent(firstName, lastName));
    }

    public void deleteStudent() {
        System.out.print("Enter student id >>> ");
        int studentId = in.nextInt();
        dataService.deleteStudent(studentId);
        System.out.println("Student with " + studentId + " id has been deleted");
    }

    public void addStudentToCourse() {
        System.out.print("Enter student id >>> ");
        int studentId1 = in.nextInt();
        System.out.println("----------------------");
        System.out.println(GeneratorTestDataService.getCoursesWithNumbers());
        System.out.print("Enter number of course >>> ");
        int courseId = in.nextInt();
        System.out.println(dataService.addStudentToCourse(studentId1, courseId));
    }

    public void deleteStudentFromCourse() {
        System.out.print("Enter student id >>> ");
        int studentId2 = in.nextInt();
        System.out.println("----------------------");
        System.out.println(dataService.readStudentCourse(studentId2, 0));
        System.out.print("\nEnter number of course >>> ");
        int courseId2 = in.nextInt();
        System.out.println(dataService.deleteStudentFromCourse(studentId2, courseId2));
    }

    public void closeProgram() {
        in.close();
        System.out.println("SQL JDBC School has been finished");
        System.exit(0);
    }
}
