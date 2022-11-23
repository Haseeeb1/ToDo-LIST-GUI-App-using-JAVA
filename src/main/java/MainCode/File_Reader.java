package MainCode;

import java.text.ParseException;
import java.util.Scanner;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class File_Reader {

    public static Vector<ToDoList> checkFileForData() throws ParseException {
        Vector<ToDoList> tasks = new Vector<ToDoList>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));

            if (br.readLine() != null) {
                tasks = getDataFromFiles("data.txt");
                System.out.println("Data Has Been Imported\n\n");
            }
            br.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("No File Found For Importing Data!");
        }

        return (Vector<ToDoList>) tasks;
    }

    public static Vector<ToDoList> getDataFromFiles(String Path) {

        Vector<ToDoList> tasks = new Vector<ToDoList>();

        try {
            File file = new File(Path);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] objectData = line.split("\\|");

                String subject = objectData[0];
                String description = objectData[1];
                String date = objectData[2];
                String time = objectData[3];
                String location = objectData[4];
                Boolean isDone = Boolean.valueOf(objectData[5]);

                ToDoList task = new ToDoList(time, date, subject, description, location, isDone);

                tasks.add(task);
            }
            input.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Files Not Found!");
        }

        return (Vector<ToDoList>) tasks;
    }

}
