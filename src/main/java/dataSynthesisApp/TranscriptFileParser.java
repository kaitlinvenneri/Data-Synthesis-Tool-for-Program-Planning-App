package dataSynthesisApp;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TranscriptFileParser {
    /**
     * Handle the parsing of the transcript file.
     *
     * @param filename The name of the file.
     * @return The parsed collection of courses
     * @throws Exception Either file IO exception, or invalid file type exception
     */
    public ArrayList<CourseAttempt> parseFile(String filename) throws Exception {
        InputHandler inputHandler = new InputHandler();
        Scanner scanner = null;
        String oneLine;
        CourseAttempt courseAttempt;
        ArrayList<CourseAttempt> coursesOnTranscript = new ArrayList<CourseAttempt>();

        try {
            scanner = new Scanner(new File(filename));
            //try to parse each line into a course attempt
            while (scanner.hasNext()) {
                oneLine = scanner.nextLine();
                courseAttempt = parseLineIntoCourseAttempt(oneLine);
                coursesOnTranscript.add(courseAttempt);
            }
            scanner.close();
        } catch (Exception e) {
            throw e;
        }

        return coursesOnTranscript;
    }

    /**
     * Handle the parsing of a line to a course attempt.
     *
     * @param line A single line to parse.
     * @return The parsed course attempt
     * @throws Exception Regarding invalid file type
     */
    private CourseAttempt parseLineIntoCourseAttempt(String line) throws Exception {
        String[] parsedLine = line.split(",");
        CourseAttempt courseAttempt;
        String courseCode;
        Status status;
        String semester;
        int grade;

        try {
            //Ensure that all parts of course attempt are contained in the line
            if (parsedLine.length != 4) {
                throw new Exception();
            }

            //Ensure that none of the required parts are empty
            if (parsedLine[0].isEmpty() || parsedLine[1].isEmpty() || parsedLine[3].isEmpty()) {
                throw new Exception();
            }

            //TODO: Add more Exception Handling
            //Possibly allowing whitespace
            //Validate that each component is correct

            courseCode = parsedLine[0];

            if (parsedLine[1].equals("Complete")) {
                status = Status.COMPLETE;
            } else if (parsedLine[1].equals("InProgress")) {
                status = Status.IN_PROGRESS;
            } else {
                status = Status.PLANNED;
            }

            semester = parsedLine[3];
            courseAttempt = new CourseAttempt(courseCode, status, semester);

            //Also need to put the grade if applicable
            if (status == Status.COMPLETE) {
                grade = Integer.parseInt(parsedLine[2]);
                courseAttempt.setGrade(grade);
            }

            return courseAttempt;
        } catch (Exception e) {
            throw e;
        }
    }
}
