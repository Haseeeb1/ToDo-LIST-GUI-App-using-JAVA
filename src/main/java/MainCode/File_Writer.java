package MainCode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class File_Writer {
    private static int i = 1;
    static DateFormat time = new SimpleDateFormat("hh:mm");
    static DateFormat newTime = new SimpleDateFormat("HH:mm a");
    static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    static DateFormat newDateFormat = new SimpleDateFormat("EEEE, MMM d, yyyy");

    public static void saveToFile(Vector<ToDoList> tasks) throws ParseException {
        int j = 1;
        String text = "";
        FileWriter fw = null;
        for (ToDoList task : tasks) {

            text += "Task# " + j + ": Subject: " + task.getSubject() + "\n\t\t Description: " + task.getDescription()
                    + "\n\t\t Date: "
                    + newDateFormat.format(newDateFormat.parse(newDateFormat.format(dateFormat.parse(task.getDate()))))
                    + "\n\t\t Start Time: "
                    + newTime.format(newTime.parse(newTime.format(time.parse(task.getTime())))) + "\n\t\t Location: "
                    + task.getLocation() + "\n\t\t" + task.getIsDone() + "\n"
                    + "\n===============================================================\n";
            j++;
        }
        try {
            File f = new File("To_Do_List.txt");
            fw = new FileWriter(f, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(text);
        pw.close();
    }

    public static void saveToHistory(ToDoList task) throws ParseException {

        String text = "";
        FileWriter hw = null;

        text += "Task# " + i + ": Subject: " + task.getSubject() + "\n\t\t Description: "
                + task.getDescription()
                + "\n\t\t Date: "
                + newDateFormat.format(
                        newDateFormat.parse(newDateFormat.format(dateFormat.parse(task.getDate()))))
                + "\n\t\t Start Time: "
                + newTime.format(newTime.parse(newTime.format(time.parse(task.getTime()))))
                + "\n\t\t Location: " + task.getLocation() + "\n\t\t" + task.getIsDone()
                + "\n" + "\n===============================================================";

        try {
            File h = new File("To_Do_List_History.txt");
            hw = new FileWriter(h, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(hw);
        pw.println(text);
        pw.close();
        i++;
    }

    public static void saveData(Vector<ToDoList> tasks) throws ParseException {
        String text = "";
        FileWriter fw = null;
        try {
            File f = new File("data.txt");
            fw = new FileWriter(f, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(text);

        for (ToDoList task : tasks) {
            text = task.getSubject() + "|" + task.getDescription()
                    + "|" + task.getDate() + "|"
                    + task.getTime() + "|" + task.getLocation() + "|" + task.getIsDoneValue();

            try {
                File f = new File("data.txt");
                fw = new FileWriter(f, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            pw = new PrintWriter(fw);
            pw.println(text);
            pw.close();

        }

    }

    public static void saveDataToHistory(ToDoList task) throws ParseException {
        String text = "";
        FileWriter fw = null;

        text += task.getSubject() + "|" + task.getDescription()
                + "|" + task.getDate() + "|"
                + task.getTime() + "|" + task.getLocation() + "|"
                + task.getIsDoneValue();

        try {
            File f = new File("historyData.txt");
            fw = new FileWriter(f, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(text);
        pw.close();

    }

}