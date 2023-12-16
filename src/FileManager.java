import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    File file;

    // Constructor to initialize the file ,takes the file path
    FileManager(String fileName) {
        file = new File(fileName);
        openFile();
    }

    // Open the file; create if it doesn't exist
    private int openFile() {
        try {
            if(file.createNewFile())
                return 1; // File created successfully
            return 0;   // File already exists
        } catch (IOException e) {
            return -1;  // Error creating the file
        }
    }

    // return data from the file as an array of strings
    public String[] getData() {
        String[] res = new String[0];
        try (Scanner input = new Scanner(file)) {
            String all ="";
            while(input.hasNext()) {
                String in = input.nextLine();
                if(!in.isEmpty())
                    all += in +"\n";
            }
            res = all.split("\n");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    // Append or overwrite data in the file using boolean append
    public void append(String s, boolean append) {
        // false = erase all data in the file and store new data
        // true = append to file
        try (FileWriter fw = new FileWriter(file, append)) {
            fw.append(s);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Search for a record by name in the file and return the id
    public String searchByName(String name) {
        name = name.toLowerCase();
        try {
            Scanner scanner = new Scanner(this.file);
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] lineCon = line.split("-");
                if (lineCon[1].toLowerCase().equals(name)) {
                    return lineCon[0]; // Return the ID associated with the name
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "Username NOT found";
    }

    // Update a record in the file by ID
    public boolean update(String id, String lineUpdate){
        boolean founded = false;
        try {
            Scanner scanner = new Scanner(this.file);
            String res = "";
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] lineCon = line.split("-");
                if (lineCon[0].equals(id)) {
                    res += lineUpdate+"\n";
                    founded = true;
                    continue;
                }
                res += line+"\n";
            }
            append(res,false);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return founded;
    }

    // Delete a record in the file by ID
    public boolean delete(String id){
        boolean done = false;
        try {
            Scanner scanner = new Scanner(this.file);
            String res = "";
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] lineCon = line.split("-");
                if (lineCon[0].equals(id)) {
                    done = true;
                    continue;
                }
                res += line+"\n";
            }
            append(res,false);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return done;
    }

    // Search for a record by ID in the file
    public String searchById(int id) {
        try {
            Scanner scanner = new Scanner(this.file);
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] lineCon = line.split("-");
                if (Integer.parseInt(lineCon[0]) == id) {
                    return line; // Return the entire record
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "NOT FOUND\n";
    }
}
