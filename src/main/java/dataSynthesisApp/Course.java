package dataSynthesisApp;

public class Course {
    private String code;

    /**
     * Create the course and initialize the course code.
     *
     * @param courseCode the course code corresponding to the course
     */
    public Course(String courseCode) {
        code = courseCode;
    }

    /**
     * Get the course code of the course.
     *
     * @return The course code.
     */
    public String getCode() {
        return code;
    }
}
