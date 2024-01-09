package utils;

import entity.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentDetails {

    DbUtils dbUtils = new DbUtils();

    public Student getStudentDetails(Connection connection){
        Scanner sc= new Scanner(System.in);

        int studentId = initializeNextStudentId(connection);
        System.out.println("Enter Student Details");
        System.out.println("Enter First Name : ");
        String firstName = sc.next();
        System.out.println("Enter Last Name : ");
        String lastName=sc.next();
        System.out.println("Enter EmailId : ");
        String emailID =sc.next();
        return new Student(studentId,firstName,lastName,emailID);
    }


    public int initializeNextStudentId(Connection connection) {
        int nextStudentId = 1;

        try {
            String query = "SELECT MAX(StudentID) FROM StudentDetails";
            ResultSet resultSet = dbUtils.executeNonCrudSqlQuery(connection,query);

            if (resultSet!=null && resultSet.next()) {
                nextStudentId = resultSet.getInt(1) + 1;
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextStudentId;
    }
}
