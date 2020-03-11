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

        System.out.println("Please enter the name of the program you would like to generate:");
        programName = inputHandler.getInput();

        System.out.println("Please enter the number of courses you would like to add to this program:");
        numCoursesString = inputHandler.getInput();

        //TODO:
        // Error handle if user inputs non-numeric value for numCourses
        // Error handle if the user inputs a value greater than the number of courses in courselist.csv

        generateProgram(programName, Integer.parseInt(numCoursesString));

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
