package ua.foxminded.nikasgig.sqljdbcschool.app;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import ua.foxminded.nikasgig.sqljdbcschool.model.StudentCourse;
import ua.foxminded.nikasgig.sqljdbcschool.service.DataService;
import ua.foxminded.nikasgig.sqljdbcschool.service.FormatDataService;
import ua.foxminded.nikasgig.sqljdbcschool.service.GeneratorTestDataService;

public class Application {

    public static void main(String[] args) {
        System.out.println("Welcome to SQL JDBC School");
        FormatDataService formatDataService = new FormatDataService();
        DataService dataService = new DataService();
        dataService.complexCheck();

        boolean isProgramWorks = true;
        boolean checkForMenuOne = true;
        Scanner in = new Scanner(System.in);
        while (isProgramWorks) {
            System.out.println("----------------------");
            System.out.println("1. Generate the test data");
            System.out.println("2. Find all groups with less or equal student's number");
            System.out.println("3. Find all students related to the course with the given name");
            System.out.println("4. Add a new student");
            System.out.println("5. Delete a student by the STUDENT_ID");
            System.out.println("6. Add a student to the course (from a list)");
            System.out.println("7. Remove the student from one of their courses.");
            System.out.println("0. Exit");
            System.out.print(">>> ");
            while (!in.hasNextInt()) {
                System.out.println("Incorrect command");
                System.out.print(">>> ");
                in.nextLine();
            }
            int menu = in.nextInt();
            System.out.println("\n----------------------");
            switch (menu) {
            case 1:
                if (checkForMenuOne) {
                    dataService.generateTestData();
                    checkForMenuOne = false;
                } else {
                    System.out.println("The test data has already generated");
                }
                break;
            case 2:
                System.out.print("Enter number >>> ");
                int number = in.nextInt();
                System.out.println(formatDataService.formatFindGroupsWithLessOrEqualStudents(
                        dataService.findGroupsWithLessOrEqualStudents(number)));
                break;
            case 3:
                for (String string : GeneratorTestDataService.getCourses()) {
                    System.out.print(string + ", ");
                }
                System.out.println();
                System.out.print("Enter course name >>> ");
                String courseName = (String) in.next();
                System.out.println(formatDataService
                        .formatFindStudentsByCourseName(dataService.findStudentsByCourseName(courseName)));
                break;
            case 4:
                System.out.print("Enter first name of new student >>> ");
                String firstName = in.next();
                System.out.print("Enter last name of new student >>> ");
                String lastName = in.next();
                dataService.createNewStudent(firstName, lastName);
                System.out.println("New student " + firstName + " " + lastName);
                
                break;
            case 5:
                System.out.print("Enter student id >>> ");
                int studentId = in.nextInt();
                dataService.deleteStudent(studentId);
                System.out.println("Student with " + studentId + " id has been deleted");
                break;
            case 6:
                System.out.print("Enter student id >>> ");
                int studentId1 = in.nextInt();
                System.out.println("----------------------");
                int index = 0;
                for (String string : GeneratorTestDataService.getCourses()) {
                    System.out.println(index + ". " + string);
                    index++;
                }
                System.out.print("Enter number of course >>> ");
                int courseId = in.nextInt();
                dataService.addStudentToCourse(studentId1, courseId);
                break;
            case 7:
                System.out.print("Enter student id >>> ");
                int studentId2 = in.nextInt();
                System.out.println("----------------------");
                try {
                    for (StudentCourse element : dataService.readStudentCourse(studentId2, 0)) {
                        System.out.print(element.getCourseId() + " ");
                    }
                    System.out.println();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.print("Enter number of course >>> ");
                int courseId2 = in.nextInt();
                dataService.deleteStudentFromCourse(studentId2, courseId2);
                break;
            case 0:
                isProgramWorks = false;
                in.close();
                dataService.closeConnection();
                System.out.println("SQL JDBC School has been finished");
                break;

            default:
                System.out.println("Incorrect command");
                break;
            }
        }
    }
}
