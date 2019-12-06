public abstract class Student {

    private Name name;
    private String studentID;

    public Student(String firstName, String lastName, String studentID) {
        this.name = new Name(firstName, lastName);
        this.studentID = studentID;
    }

    public String getName() {
        return name.getName();
    }

    public void setFirstName(String firstName) {
        name.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        name.setLastName(lastName);
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
