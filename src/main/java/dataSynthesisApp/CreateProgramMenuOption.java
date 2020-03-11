package dataSynthesisApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CreateProgramMenuOption implements MenuOption {
    private int value;
    private String description;
    private Menu parentMenu;

    /**
     * Create the CreateProgramMenuOption and initialize its description.
     *
     * @param desc The description to be assigned.
     */
    public CreateProgramMenuOption(String desc, Menu menu) {
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
        String programName;
        String numCoursesString;
        int numberOfSavedCourses;
        int numberOfProgramCourses = 0;

        System.out.println("Please enter the name of the program you would like to generate (i.e. \"My Program\"):");
        programName = inputHandler.getInput();

        numberOfSavedCourses = DataUtility.getNumberOfSavedCourses();

        while(numberOfProgramCourses < 1 || numberOfProgramCourses > numberOfSavedCourses){
            System.out.println("Please enter the number of courses you would like to add to this program (must be in the range of 1 to " + numberOfSavedCourses + "):");
            numCoursesString = inputHandler.getInput();

            numberOfProgramCourses = Integer.parseInt(numCoursesString);

            if(numberOfProgramCourses < 1 || numberOfProgramCourses > numberOfSavedCourses){
                System.out.println("You entered a number of courses outside of the range.");
            }
        }

        generateProgram(programName, numberOfProgramCourses);

        parentMenu.handleMenu();
    }

    private void generateProgram(String programName, int numCourses){
        ArrayList<String> requiredCourses = new ArrayList<String>();
        ArrayList<AdminCourse> savedCourses = DataUtility.getStoredAdminCourses();
        HashMap<Integer, Integer> usedIndices = new HashMap<Integer, Integer>();
        Random rand = new Random();
        int courseIndex;
        Boolean courseAdded;

        for(int i = 0; i < numCourses; i++){
            courseAdded = false;
            while(!courseAdded) {
                courseIndex = rand.nextInt(savedCourses.size());
                if (!usedIndices.containsKey(courseIndex)) {
                    courseAdded = true;
                    requiredCourses.add(savedCourses.get(courseIndex).getCode());
                    usedIndices.put(courseIndex, courseIndex);
                }
            }
        }

        Program program = new Program(programName, requiredCourses);
        DataUtility.writeProgramToFile(program);

        System.out.println("\nProgram has been written to " + programName + ".csv in the resources folder.");
    }

    /**
     * Making a human readable toString() for the menu option.
     */
    @Override
    public String toString() {
        return description;
    }
}
