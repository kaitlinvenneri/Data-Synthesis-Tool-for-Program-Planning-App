package dataSynthesisApp;

public class Main {
    public static void main(String[] args) {
	    System.out.println("Welcome to a handy dandy tool to generate some data for program planning.\n");

	    Menu mainMenu = createMainMenu();
	    mainMenu.handleMenu();
    }

    private static Menu createMainMenu(){
        Menu mainMenu = new Menu("This is the main menu.");
        MenuOption createProgram = new CreateProgramMenuOption("Create a program", mainMenu);
        mainMenu.addOption(createProgram);
        MenuOption createTranscript = new CreateTranscriptMenuOption("Create a transcript", mainMenu);
        mainMenu.addOption(createTranscript);
        MenuOption quitOption = new QuitMenuOption("Quit");
        mainMenu.addOption(quitOption);

        return mainMenu;
    }
}
