package entity;

public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private String emailID;

    public Student(int studentId, String firstName, String lastName, String emailID) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailID = emailID;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailID() {
        return emailID;
    }
}
