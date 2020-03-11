package dataSynthesisApp;

public interface MenuOption {
    /**
     * Get the description of the menu option.
     *
     * @return The description of the menu option.
     */
    String getMenuOptionDescription();

    /**
     * Set the value of the menu option.
     *
     * @param optionValue The value of the menu option.
     */
    void setMenuOptionValue(int optionValue);

    /**
     * Get the value of the menu option.
     *
     * @return The value of the menu option.
     */
    int getMenuOptionValue();

    /**
     * Handle the menu option.
     */
    void handleMenuOption();
}
