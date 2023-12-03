import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    private String fileName;
    File file;
    FileManager(String fileName) {
        this.fileName = fileName;
        file = new File(fileName);
        openFile();
    }
    private boolean openFile() {
        try {
            file.createNewFile();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
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
    public void append(String s, boolean append) {
        //false = erase all data in the file and store new data
        //true = append to file
        try (FileWriter fw = new FileWriter(file, append)) {
            fw.append(s);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public String searchByName(String name) { //////////////////////////////////////////
        name = name.toLowerCase();
        try {
            Scanner scanner = new Scanner(this.file);
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] linecon = line.split("-");
                if (linecon[1].toLowerCase().equals(name)) {
                    return linecon[0];
                }
            }
        } catch (IOException e) {
            System.out.println("WRONG path");
        }

        return "username NOT found";
    }
    public boolean update(String id,String lineUpdate){
        boolean founded=false;
        try {
            Scanner scanner = new Scanner(this.file);
            String res ="";
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] linecon = line.split("-");
                if (linecon[0].equals(id)) {
                    res+=lineUpdate+"\n";
                    founded=true;
                    continue;
                }
                res+=line+"\n";
            }
            append(res,false);
        } catch (IOException e) {
            System.out.println("WRONG path");
        }

        return founded;
    }
    public boolean delete(String id){
        boolean done=false;
        try {
            Scanner scanner = new Scanner(this.file);
            String res ="";
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] linecon = line.split("-");
                if (linecon[0].equals(id)) {
                    done=true;
                    continue;
                }
                res+=line+"\n";
            }
            append(res,false);
        } catch (IOException e) {
            System.out.println("WRONG path");
        }

        return done;
    }
    public String searchById(int id) { ///////////////////////////////////////

        try {
            Scanner scanner = new Scanner(this.file);
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] linecon = line.split("-");
                int lineId = Integer.parseInt(linecon[0]);
                if (lineId == id) {
                    return line;
                }
            }
        } catch (IOException e) {
            System.out.println("WRONG path");
        }

        return "NOT FOUND\n";
    }
}
