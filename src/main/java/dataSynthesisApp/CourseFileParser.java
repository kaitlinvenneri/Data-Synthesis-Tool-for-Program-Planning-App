package dataSynthesisApp;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseFileParser {
    /**
     * Handle the parsing of the course list file.
     *
     * @param filename The name of the file.
     * @return The parsed collection of admin courses
     * @throws Exception Either file IO exception, or invalid file type exception
     */
    public ArrayList<AdminCourse> parseFile(String filename) throws Exception {
        Scanner scanner = null;
        String oneLine;
        AdminCourse course;
        ArrayList<AdminCourse> coursesToUpload = new ArrayList<AdminCourse>();

        try {
            scanner = new Scanner(new File(filename));
            //try to convert each line of file to a course
            while (scanner.hasNext()) {
                oneLine = scanner.nextLine();
                course = parseLineIntoAdminCourse(oneLine);
                coursesToUpload.add(course);
            }
            scanner.close();
        } catch (Exception e) {
            throw e;
        }

        return coursesToUpload;
    }

    /**
     * Handle the parsing of a line to an admin course.
     *
     * @param line A single line to parse.
     * @return The parsed admin course
     * @throws Exception Regarding invalid file type
     */
    private AdminCourse parseLineIntoAdminCourse(String line) throws Exception {
        String[] parsedLine = line.split(",");
        AdminCourse adminCourse;
        String courseCode;
        double credits;
        String name;
        ArrayList<String> prereqs;
        String[] parsedCodes;

        try {
            if (parsedLine.length < 3) {
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

            courseCode = parsedLine[0];

            credits = Double.parseDouble(parsedLine[1]);

            name = parsedLine[2];
            prereqs = new ArrayList<String>();

            if (parsedLine.length == 4) {
                parsedCodes = parsedLine[3].split(":");
                for (String code : parsedCodes) {
                    prereqs.add(code);
                }
            }

            adminCourse = new AdminCourse(courseCode, credits, name, prereqs);

            return adminCourse;
        } catch (Exception e) {
            throw e;
        }
    }
}
