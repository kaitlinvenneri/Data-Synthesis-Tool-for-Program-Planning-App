package dataSynthesisApp;

import java.util.ArrayList;

public class AdminCourse extends Course {
    private double credits;
    private String name;
    private ArrayList<String> prerequisiteCodes;

    /**
     * Create the admin course and initialize.
     *
     * @param courseCode the course code corresponding to the course
     * @param courseCredits the number of credits associated with the course
     * @param courseName the name of the course
     * @param coursePrereqs the course codes for prerequisites of the course
     */
    public AdminCourse(String courseCode, double courseCredits, String courseName, ArrayList<String> coursePrereqs) {
        super(courseCode);
        credits = courseCredits;
        name = courseName;
        prerequisiteCodes = coursePrereqs;
    }

    /**
     * Get the course name of the admin course.
     *
     * @return The admin course name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the number of credits associated with the admin course.
     *
     * @return The number of credits for the admin course.
     */
    public double getCredits() {
        return credits;
    }

    /**
     * Get the prerequisites required for the admin course.
     *
     * @return The prerequisites for the admin course.
     */
    public ArrayList<String> getPrerequisiteCodes() {
        return prerequisiteCodes;
    }

    /**
     * Set the name of the admin course.
     *
     * @param courseName The name to set.
     */
    public void setName(String courseName) {
        name = courseName;
    }

    /**
     * Set the credits of the admin course.
     *
     * @param courseCredits The credits to set.
     */
    public void setCredits(double courseCredits) {
        credits = courseCredits;
    }

    /**
     * Set the prerequisites required for the admin course.
     *
     * @param prereqCodes The course codes for the prerequisites to set.
     */
    public void setPrerequisiteCodes(ArrayList<String> prereqCodes) {
        prerequisiteCodes = prereqCodes;
    }

    /**
     * Update the admin course fields based on an existing admin course.
     *
     * @param updatedCourse The admin course to get field information to update from.
     */
    public void updateCourseInfo(AdminCourse updatedCourse) {
        name = updatedCourse.getName();
        credits = updatedCourse.getCredits();
        prerequisiteCodes = updatedCourse.getPrerequisiteCodes();
    }

    /**
     * Making a human readable toString() for the admin course.
     */
    @Override
    public String toString() {
        String adminCourseString = "";
        adminCourseString = adminCourseString + "Course Code: " + super.getCode()
                + ", Course name: " + name + ", Credits: " + credits + ", Prerequisites: ";

        //Add prereqs only if there are any to the string
        if (prerequisiteCodes.size() > 0) {
            adminCourseString = adminCourseString + prerequisiteCodes.get(0);

            for (int i = 1; i < prerequisiteCodes.size(); i++) {
                adminCourseString = adminCourseString + ", " + prerequisiteCodes.get(i);
            }
        } else {
            adminCourseString = adminCourseString + "N/A";
        }
        return adminCourseString;
    }
}
