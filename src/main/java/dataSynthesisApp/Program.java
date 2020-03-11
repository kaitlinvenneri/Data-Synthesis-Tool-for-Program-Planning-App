package dataSynthesisApp;

import java.util.ArrayList;

public class Program {
    private String name;
    private ArrayList<String> requiredCoursesByName;

    /**
     * Create the Program and initialize name and required courses.
     *
     * @param programName the name of the program
     * @param requiredCourses the required courses for the program
     */
    public Program(String programName, ArrayList<String> requiredCourses) {
        name = programName;
        requiredCoursesByName = requiredCourses;
    }

    /**
     * Get the name of the program.
     *
     * @return The program name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the list of required courses by name.
     *
     * @return A collection of names of courses required for the program.
     */
    public ArrayList<String> getRequiredCoursesByName() {
        return requiredCoursesByName;
    }

    /**
     * Set the required courses for a program.
     *
     * @param requiredCourses Course codes for required courses.
     */
    public void setRequiredCoursesByName(ArrayList<String> requiredCourses) {
        this.requiredCoursesByName = requiredCourses;
    }
}
