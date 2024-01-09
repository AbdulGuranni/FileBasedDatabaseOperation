import entity.Student;
import utils.DbUtils;
import utils.FileUtils;
import utils.StudentDetails;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Scanner sc = new Scanner(System.in);
        DbUtils dbutils = new DbUtils();
        FileUtils fileutils = new FileUtils();
        Connection connection = dbutils.getSqlConnection();
        String createQuery ="CREATE TABLE IF NOT EXISTS StudentDetails(StudentID int PRIMARY KEY,FirstName varchar(20),LastName varchar(20),EmailID varchar(30))";
        dbutils.executeSqlQuery(connection,createQuery);
        System.out.println("Enter Student count");
        int StudentCount = sc.nextInt();
        do{
            String insertQuery = "insert into StudentDetails values(?,?,?,?)";
            StudentDetails studentDetails = new StudentDetails();
            Student student = studentDetails.getStudentDetails(connection);
            dbutils.executeQuery(connection,insertQuery,student);
            StudentCount--;
        }while(StudentCount>0);

        String getAllStudentsQuery = "select * from StudentDetails";
        ResultSet resultSet = dbutils.executeNonCrudSqlQuery(connection,getAllStudentsQuery);
        fileutils.writeTextFile(resultSet);
        }


    }
