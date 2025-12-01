package model;
public class Feedback {
    private int id;
    private String studentName;
    private String courseName;
    private String teacherName;
    private int rating;
    private String comments;
    public Feedback() {}
    public Feedback(int id, String studentName, String courseName, String teacherName, int rating, String comments) {
        this.id = id;
        this.studentName = studentName;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.rating = rating;
        this.comments = comments;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getTeacherName() { return teacherName; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
}