package dataSynthesisApp;

public class CourseAttempt extends Course {
    private Status status;
    private int grade;
    private String semester;

    /**
     * Create the course and initialize.
     *
     * @param courseCode the course code corresponding to the course
     * @param courseStatus the status of the course attempt in relation to the student's attempt
     * @param semesterTaken the semester the course has been attempted
     */
    public CourseAttempt(String courseCode, Status courseStatus, String semesterTaken) {
        super(courseCode);
        status = courseStatus;
        semester = semesterTaken;
    }

    /**
     * Set the grade of the course attempt.
     *
     * @param courseGrade The grade to set.
     */
    public void setGrade(int courseGrade) {
        grade = courseGrade;
    }

    /**
     * Get the status of the course attempt.
     *
     * @return The status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Get the grade associated with the course attempt.
     *
     * @return The grade of the course attempt.
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Get the semester of the course attempt.
     *
     * @return The semester the course was attempted
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Making a human readable toString() for the course attempt.
     */
    @Override
    public String toString() {
        return super.getCode() + " " + semester;
    }
}
