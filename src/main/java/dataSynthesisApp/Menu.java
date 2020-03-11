package dataSynthesisApp;

import java.util.LinkedList;

public class Menu {
    private String description;
    private LinkedList<MenuOption> menuOptions;

    /**
     * Create the menu and initialize the description and menuOptions.
     *
     * @param menuDescription the description of the menu
     */
    public Menu(String menuDescription) {
        description = menuDescription;
        menuOptions = new LinkedList<MenuOption>();
    }

    /**
     * Get the menu option from the provided value.
     *
     * @param value Value associated with a menu option.
     * @return The menu option.
     */
    public MenuOption getMenuOptionByValue(int value) {
        MenuOption option = null;
        for (MenuOption opt : menuOptions) {
            if (opt.getMenuOptionValue() == value) {
                option = opt;
            }
        }
        return option;
    }

    /**
     * Check if an option exists that corresponds with the option provided.
     *
     * @param option Value possibly associated with a menu option.
     * @return True if option is contained in the menu. False otherwise.
     */
    public boolean hasOption(int option) {
        //menu options start at 1
        if (option > 0 && option <= menuOptions.size()) {
            return true;
        }
        return false;
    }

    /**
     * Provide the message for when an option is not in a menu.
     *
     * @return Message for non-option.
     */
    public String getNonOptionMessage() {
        return "That is not an option for this menu.";
    }

    /**
     * Provide the message at the start of the menu.
     *
     * @return Message for non-option.
     */
    public String getMainMessage() {
        return "Please enter the number corresponding to the menu option that you would like to select.";
    }

    /**
     * Add a MenuOption to the menu.
     *
     * @param option MenuOption to be added to the menu.
     */
    public void addOption(MenuOption option) {
        int optionValue = menuOptions.size() + 1;
        option.setMenuOptionValue(optionValue);
        menuOptions.add(option);
    }

    /**
     * Handle the menu.
     * Print out the menu and get user input.
     * Then handle the command based on the menu option chosen.
     */
    public void handleMenu() {
        InputHandler inputHandler = new InputHandler();
        System.out.print("\n" + toString());

        int command = inputHandler.getCommand();
        inputHandler.processCommand(this, command);
    }

    /**
     * Making a human readable toString() for the menu.
     */
    @Override
    public String toString() {
        String menuString = "";
        int currOption = 0;
        menuString = menuString + description + "\n";
        for (int i = 0; i < menuOptions.size(); i++) {
            currOption = i + 1;
            menuString += currOption + ": " + menuOptions.get(i).toString() + "\n";
        }
        menuString = menuString + "\n" + getMainMessage() + "\n";
        return menuString;
    }
}
