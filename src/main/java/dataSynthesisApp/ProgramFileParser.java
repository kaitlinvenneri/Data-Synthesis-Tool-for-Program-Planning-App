package dataSynthesisApp;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramFileParser {
    /**
     * Handle the parsing of the program file.
     *
     * @param filename The name of the file.
     * @return The parsed Program
     * @throws Exception Either file IO exception, or invalid file type exception
     */
    public Program parseFile(String filename) throws Exception {
        Scanner scanner = null;
        String oneLine;
        String fileContents = "";
        Program program;

        try {
            scanner = new Scanner(new File(filename));
            //generate fileContents
            while (scanner.hasNext()) {
                oneLine = scanner.nextLine();
                fileContents = fileContents + oneLine;
            }
            scanner.close();

            program = parseFileContentsIntoProgram(fileContents);
        } catch (Exception e) {
            throw e;
        }

        return program;
    }

    /**
     * Handle the parsing of a fileContents to a program.
     *
     * @param fileContents file contents to parse.
     * @return The parsed program
     * @throws Exception Regarding invalid file type
     */
    private Program parseFileContentsIntoProgram(String fileContents) throws Exception {
        Program program;
        String[] parsedLine = fileContents.split(",");
        String programName;
        ArrayList<String> requiredCourses = new ArrayList<String>();

        try {
            if (parsedLine.length < 2) {
                throw new Exception();
            }

            for (String linePart : parsedLine) {
                if (linePart.isEmpty()) {
                    throw new Exception();
                }
            }

            //TODO: Add more Exception Handling
            //Possibly allowing whitespace
            //Validate that each component is correct

            programName = parsedLine[0];
            for (int i = 1; i < parsedLine.length; i++) {
                requiredCourses.add(parsedLine[i]);
            }

            //TODO: Validate here that all course codes in requiredCourses
            // are already saved in the system as AdminCourses

            program = new Program(programName, requiredCourses);

            return program;
        } catch (Exception e) {
            throw e;
        }
    }
}
