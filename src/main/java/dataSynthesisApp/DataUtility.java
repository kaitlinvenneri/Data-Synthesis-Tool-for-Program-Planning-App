package dataSynthesisApp;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

public final class DataUtility {
    private DataUtility() {
        //not called
    }

    /**
     * Get the filename of where the admin courses are currently saved.
     *
     * @return name of file containing saved courses.
     */
    public static String getSavedCoursesFilename() {
        String separator = System.getProperty("file.separator");

        String filename = "." + separator + "src" + separator + "main"
                + separator + "resources" + separator + "courselist.csv";

        return filename;
    }

    /**
     * Get the stored admin courses.
     *
     * @return stored admin courses.
     */
    public static ArrayList<AdminCourse> getStoredAdminCourses() {
        ArrayList<AdminCourse> storedAdminCourses = new ArrayList<AdminCourse>();
        CourseFileParser courseFileParser = new CourseFileParser();
        String filename;
        File savedCoursesFile;

        try {
            filename = getSavedCoursesFilename();

            savedCoursesFile = new File(filename);

            if (savedCoursesFile.exists()) {
                storedAdminCourses = courseFileParser.parseFile(filename);
            }
        } catch (Exception e) {
            //TODO: Improve error handling here:
            System.out.println("Something went wrong trying to get saved courses.");
        }

        return storedAdminCourses;
    }

    /**
     * Get the name of the directory where the programs are currently saved.
     *
     * @return name of directory containing saved programs.
     */
    public static String getSavedProgramsDirectory() {
        String separator = System.getProperty("file.separator");

        String folderName = "." + separator + "src" + separator + "main"
                + separator + "resources";

        return folderName;
    }

    /**
     * Write program to be saved to the file where it is stored.
     *
     * @param programToWrite program to save
     */
    public static void writeProgramToFile(Program programToWrite) {
        String programLine;
        PrintWriter myWriter = null;

        try {
            myWriter = new PrintWriter(new FileWriter(new File(getProgramFilename(programToWrite.getName()))));

            programLine = "";
            programLine = programLine + programToWrite.getName() + ",";

            for (int i = 0; i < programToWrite.getRequiredCoursesByName().size(); i++) {
                programLine = programLine + programToWrite.getRequiredCoursesByName().get(i);

                if (i + 1 < programToWrite.getRequiredCoursesByName().size()) {
                    programLine = programLine + ",";
                }
            }
            myWriter.println(programLine);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            myWriter.close();
        }
    }

    /**
     * Get the name of the file that corresponds with the program.
     *
     * @param programName name of program to get filename for
     * @return filename of program in storage
     */
    public static String getProgramFilename(String programName) {
        String separator = System.getProperty("file.separator");
        return getSavedProgramsDirectory() + separator + programName + ".csv";
    }

    public static void writeTranscriptToFile(ArrayList<CourseAttempt> courseAttempts, String transcriptName) {
        String courseAttemptLine;
        PrintWriter myWriter = null;
        Status status;
        String statusString;

        try {
            myWriter = new PrintWriter(new FileWriter(new File(getProgramFilename(transcriptName))));

            for(CourseAttempt courseAttempt : courseAttempts) {
                courseAttemptLine = "";
                courseAttemptLine = courseAttemptLine + courseAttempt.getCode() + ",";

                status = courseAttempt.getStatus();

                if(status == Status.COMPLETE){
                    statusString = "Complete";
                } else if (status == Status.IN_PROGRESS){
                    statusString = "InProgress";
                } else {
                    statusString = "Planned";
                }

                courseAttemptLine = courseAttemptLine + statusString + ",";

                if(status == Status.COMPLETE){
                    courseAttemptLine = courseAttemptLine + courseAttempt.getGrade();
                }

                courseAttemptLine = courseAttemptLine + ",";

                courseAttemptLine = courseAttemptLine + courseAttempt.getSemester();

                myWriter.println(courseAttemptLine);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            myWriter.close();
        }
    }
}
