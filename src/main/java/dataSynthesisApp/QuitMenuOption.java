package dataSynthesisApp;

public class QuitMenuOption implements MenuOption {
    private int value;
    private String description;

    /**
     * Create the QuitMenuOption and initialize its description.
     *
     * @param desc The description to be assigned.
     */
    public QuitMenuOption(String desc) {
        description = desc;
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
     * Handle the user selecting to quit.
     */
    @Override
    public void handleMenuOption() {
        System.out.println("Goodbye now.");
    }

    /**
     * Making a human readable toString() for the menu option.
     */
    @Override
    public String toString() {
        return description;
    }
}
