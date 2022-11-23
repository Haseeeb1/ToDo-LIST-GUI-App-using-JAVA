package MainCode;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Vector;

public class ToDoList {
    private String time;
    private String date;
    private String subject, description, location;
    private Boolean isDone;
    static DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    static DateFormat newTimeForamt = new SimpleDateFormat("hh:mm a");
    static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    static DateFormat newDateFormat = new SimpleDateFormat("EEEE, MMM d, yyyy");

    // ********* Creating Constructors*********

    ToDoList(String time, String date, String subject, String description, String location, Boolean isDone)
            throws ParseException {
        this.subject = subject;
        this.description = description;
        this.location = location;
        this.time = time;
        this.date = date;
        this.isDone = isDone;
    }

    public ToDoList(String time, String date, String subject, String description, String location) throws ParseException {
        this(time, date, subject, description, location, false);
    }

    // Setting Starting Time, Date, Subject, Description, Location of Tasks

    public void setSubject(String subject) {

        this.subject = subject;

    }

    public void setTime(String time) throws ParseException {

        this.time = time;

    }

    public void setDate(String date) throws ParseException {

        this.date = date;

    }

    public void setDescription(String description) {

        this.description = description;

    }

    public void setLocation(String location) {

        this.location = location;

    }

    public void setTaskAsDone() {

        this.isDone = true;

    }

    public void setTaskAsincomplete() {

        this.isDone = false;

    }

    // Getting Starting Time, Date, Subject, Description, Location of Tasks

    public String getSubject() {

        return this.subject;
    }

    public String getTime() throws ParseException {

        return this.time;

    }

    public String getDate() throws ParseException {

        return this.date;

    }

    public String getDescription() {

        if (this.description.length() == 0)
            return " ";
        return this.description;

    }

    public String getLocation() {

        if (this.location.length() == 0)
            return " ";
        return this.location;

    }

    public String getIsDone() {

        if (this.isDone)
            return "This Task Has Been Done";
        return "This Task Has Not Been Done";

    }

    public String getIsDoneValue() {

        return String.valueOf(this.isDone);

    }
    // ************Sorting The List****************

    public static Vector<ToDoList> sortSchedule(Vector<ToDoList> tasks) throws ParseException {
        for (int i = 0; i < tasks.size(); i++) {
            for (int j = 0; j < tasks.size(); j++) {
                if ((tasks.elementAt(i).getDate().compareTo(tasks.elementAt(j).getDate()) < 0)) {
                    Collections.swap(tasks, i, j);
                }
                if (((tasks.elementAt(i).getDate().compareTo(tasks.elementAt(j).getDate()) == 0)
                        && (tasks.elementAt(i).getTime().compareTo(tasks.elementAt(j).getTime())) < 0)) {
                    Collections.swap(tasks, i, j);
                }
            }

        }
        return (Vector<ToDoList>) tasks;
    }

    // *********Displaying List***********

    public static void displayToDoList(Vector<ToDoList> tasks) throws ParseException {
        int i = 1;
        if (tasks.size() == 0) {
            System.out.println("To Do List is Empty.");
        }
        for (ToDoList task : tasks) {
            System.out.println("Task# " + i + ": Subject: " + task.subject + "\n\tDescription: " + task.description
                    + "\n\tDate: " + newDateFormat.format(newDateFormat.parse(newDateFormat.format(dateFormat.parse(task.date))))
                    + "\n\tStart Time: "
                    + newTimeForamt.format(newTimeForamt.parse(newTimeForamt.format(timeFormat.parse(task.time)))) + "\n\tLocation: "
                    + task.location + "\n\t" + task.getIsDone());
            System.out.println();
            System.out.println("============================================\n");
            i++;
        }
    }

    // *******Displaying History********************

    public static void displayHistory() throws ParseException, FileNotFoundException {

        Vector<ToDoList> tasksHistory = new Vector<ToDoList>();
        tasksHistory = File_Reader.getDataFromFiles("historyData.txt");
        int i = 1;
        if (tasksHistory.size() == 0) {
            System.out.println("\nNo History.\n");
        }
        for (ToDoList task : tasksHistory) {
            System.out.println("Task# " + i + ": Subject: " + task.subject + "\n\tDescription: " + task.description
                    + "\n\tDate: " + newDateFormat.format(newDateFormat.parse(newDateFormat.format(dateFormat.parse(task.date))))
                    + "\n\tStart Time: "
                    + newTimeForamt.format(newTimeForamt.parse(newTimeForamt.format(timeFormat.parse(task.time)))) + "\n\tLocation: "
                    + task.location + "\n\t" + task.getIsDone());
            System.out.println();
            System.out.println("============================================\n");
            i++;
        }
    }

    // **********Display Tasks That Have Been Done**********

    public static void displayFinishedTasks(Vector<ToDoList> tasks) throws ParseException {
        int i = 1;
        if (tasks.size() == 0) {
            System.out.println("To Do List is Empty.");
        }
        for (ToDoList task : tasks) {
            if (task.isDone) {
                System.out.println("Task# " + i + ": Subject: " + task.subject + "\n\tDescription: " + task.description
                        + "\n\tDate: " + newDateFormat.format(newDateFormat.parse(newDateFormat.format(dateFormat.parse(task.date))))
                        + "\n\tStart Time: "
                        + newTimeForamt.format(newTimeForamt.parse(newTimeForamt.format(timeFormat.parse(task.time)))) + "\n\tLocation: "
                        + task.location + "\n\t" + task.getIsDone());
                System.out.println();
                System.out.println("============================================\n");
            }
        }
    }

    // **********Searching A Task**********
    public static void searchTask(Vector<ToDoList> tasks, String s) throws ParseException {
        boolean found = false;
        for (ToDoList task : tasks) {
            if (task.subject.compareToIgnoreCase(s) == 0) {
                System.out.println("Subject: " + task.subject + "\n\tDescription: " + task.description
                        + "\n\tDate: " + newDateFormat.format(newDateFormat.parse(newDateFormat.format(dateFormat.parse(task.date))))
                        + "\n\tStart Time: "
                        + newTimeForamt.format(newTimeForamt.parse(newTimeForamt.format(timeFormat.parse(task.time)))) + "\n\tLocation: "
                        + task.location + "\n\t" + task.getIsDone());
                System.out.println();
                found = true;
            }
        }
        if (!found)

        {
            System.out.println("No Such Task in The List");
        }
    }

}