package dataSynthesisApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CreateTranscriptMenuOption implements MenuOption {
    private int value;
    private String description;
    private Menu parentMenu;

    /**
     * Create the CreateTranscriptMenuOption and initialize its description.
     *
     * @param desc The description to be assigned.
     */
    public CreateTranscriptMenuOption(String desc, Menu menu) {
        description = desc;
        parentMenu = menu;
    }

    /**
     * Get the description of the menu option.
     *
     * @return The description of the menu option.
     */
    @Override
    public String getMenuOptionDescription() {
        return description;
    }

    /**
     * Set the value of the menu option.
     *
     * @param val The value of the menu option.
     */
    @Override public void setMenuOptionValue(int val) {
        value = val;
    }

    /**
     * Get the value of the menu option.
     *
     * @return The value of the menu option.
     */
    @Override public int getMenuOptionValue() {
        return value;
    }

    /**
     * Handle the user selecting to create a program.
     */
    @Override
    public void handleMenuOption() {
        InputHandler inputHandler = new InputHandler();
        String transcriptName;
        String numCoursesString;
        int numberOfTranscriptCourses = 0;
        int numberOfSavedCourses;

        System.out.println("Please enter the filename of the transcript you would like to generate (i.e. \"My Transcript\"):");
        transcriptName = inputHandler.getInput();

        numberOfSavedCourses = DataUtility.getNumberOfSavedCourses();

        while(numberOfTranscriptCourses < 1 || numberOfTranscriptCourses > numberOfSavedCourses){
            System.out.println("Please enter the number of courses you would like to add to this transcript (must be in the range of 1 to " + numberOfSavedCourses + "):");
            numCoursesString = inputHandler.getInput();

            numberOfTranscriptCourses = Integer.parseInt(numCoursesString);

            if(numberOfTranscriptCourses < 1 || numberOfTranscriptCourses > numberOfSavedCourses){
                System.out.println("You entered a number of courses outside of the range.");
            }
        }

        generateTranscript(transcriptName, numberOfTranscriptCourses);

        System.out.println("\nTranscript has been written to " + transcriptName + ".csv in the resources folder.");

        parentMenu.handleMenu();
    }

    private void generateTranscript(String transcriptName, int numCourses){
        ArrayList<CourseAttempt> courseAttempts = new ArrayList<CourseAttempt>();
        ArrayList<AdminCourse> savedCourses = DataUtility.getStoredAdminCourses();
        HashMap<Integer, Integer> usedIndices = new HashMap<Integer, Integer>();
        Random rand = new Random();
        int courseIndex;
        Boolean courseAdded;
        CourseAttempt courseAttempt;

        for(int i = 0; i < numCourses; i++){
            courseAdded = false;
            while(!courseAdded) {
                courseIndex = rand.nextInt(savedCourses.size());
                if (!usedIndices.containsKey(courseIndex)) {
                    courseAdded = true;
                    courseAttempt = createRandomCourseAttempt(savedCourses.get(courseIndex).getCode());
                    courseAttempts.add(courseAttempt);
                    usedIndices.put(courseIndex, courseIndex);
                }
            }
        }

        DataUtility.writeTranscriptToFile(courseAttempts, transcriptName);
    }

    private CourseAttempt createRandomCourseAttempt(String courseCode){
        CourseAttempt courseAttempt;
        Random rand = new Random();
        int statusVal;
        int grade;
        Status status;
        String semesterLetter;
        int semesterRand;
        int semesterYear;
        String semesterString;

        statusVal = rand.nextInt(3);

        if(statusVal == 0){
            status = Status.COMPLETE;
        } else if(statusVal == 1){
            status = Status.IN_PROGRESS;
        } else {
            status = Status.PLANNED;
        }

        grade = rand.nextInt(101);

        semesterRand = rand.nextInt(3);

        if(semesterRand == 0){
            semesterLetter = "F";
        } else if(semesterRand == 1){
            semesterLetter = "W";
        } else {
            semesterLetter = "S";
        }

        semesterRand = rand.nextInt(7);

        semesterYear = semesterRand + 14;

        semesterString = semesterLetter + semesterYear;

        courseAttempt = new CourseAttempt(courseCode, status, semesterString);
        courseAttempt.setGrade(grade);

        return courseAttempt;
    }

    /**
     * Making a human readable toString() for the menu option.
     */
    @Override
    public String toString() {
        return description;
    }
}
