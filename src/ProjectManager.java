public class ProjectManager extends Person {


    public ProjectManager() {
        super();
    }

    public boolean login(String name, String password) {
        FileManager fw = new FileManager("data\\users\\ProjectManager.txt");
        String[] arr = fw.getData();         //get data from the file fw
        for (String s : arr) {            //loop through the data we read
            String[] user = s.split("-");  // split data into fields
            if (user.length > 2 && user[1].equals(name) && user[2].equals(password)) {
                //set those attributes if data is correct
                super.setId(Integer.parseInt(user[0])); //convert string to integer
                super.setName(name);
                super.setPassword(password);
                super.setType("Project Manager");
                return true;
            }
        }
        return false;
    }

    public String[] vewClosedBugs() {
        FileManager fm = new FileManager("data\\users\\bug.txt"); //create file object
        String[] res = fm.getData(); //store bug data from file object fm into res
        String bugID = "";//we will store the bug information in this empty string
        for (String s : res) { //loop through res
            String[] line = s.split("-"); //create an array of strings, every word that comes before '-' is a string
            if ( line[5].equals("true")) {// if the attribute at the 5th locattion equals true
                bugID += line[0] + " " + line[1] + "\n"; //store the attribute number 0 and 1 inside we used \n to split between the bugs
            }
        }

        return bugID.split("\n"); //here w split between the bugs using \n
    }

    public String[] viewOpenBugs() { //read the method above it has the same implemetation but reverse this condition  line[5].equals("true")
        FileManager fm = new FileManager("data\\users\\bug.txt");
        String[] res = fm.getData();
        String bugID = "";
        for (String s : res) {
            String[] line = s.split("-");
            if (line[5].equals("false")) {
                bugID += line[0] + " " + line[1] + "\n";
            }
        }
        String[] output = bugID.split("\n");

        return output;
    }

    public String checkTesterPerformance(int id) {
        FileManager fm = new FileManager("data\\users\\tester.txt"); //store the data inside a file object
        String[] res = fm.searchById(id).split("-"); //split between the data, everytime we see the character '-' the word before it is stored as a string
        if(res[0].equals("NOT FOUND\n")){
            return "this id was not found";
        }
        else {
            return res[3];     //return how many bugs this tester found
        }
    }

    public String checkDeveloperPerformance(int id) {//read the method above its the same but here we get the data of the dev
        FileManager fm = new FileManager("data\\users\\developer.txt");
        String[] res = fm.searchById(id).split("-");
        if(res[0].equals("NOT FOUND\n")){
            return "this id was not found";
        }
        else {
            return res[4];         //return how many bugs this developer fixed
        }
    }

    public String toString() {
        return super.getId() + "-" + super.getName() + "-" + super.getPassword() + "-" ;
    }

    public Admin[] getAllAdmins() {
        FileManager fw = new FileManager("data//users//admin.txt");  // Create a FileManager object to handle file operations
        String[] arr = fw.getData();  // Get the data from the file
        if (arr[0].isEmpty())
            return new Admin[0];  // If the file is empty, returns an empty array
        Admin[] users = new Admin[arr.length];  // Create an array of Admin objects
        for (int i = 0; i < arr.length; i++) {
            String[] user = arr[i].split("-");  // Split the line into an array using "-" as the delimiter
            users[i] = new Admin(Integer.parseInt(user[0]), user[1], user[2]);  // Create a new Admin object and adds it to  the array
        }
        return users;
    }

    // Same thing as getAlladmins()
    public Tester[] getAllTesters() {
        FileManager fw = new FileManager("data//users//tester.txt");
        String[] arr = fw.getData();
        if (arr[0].isEmpty())
            return new Tester[0];
        Tester[] users = new Tester[arr.length];
        for (int i = 0; i < arr.length; i++) {
            String[] user = arr[i].split("-"); //id-name-pass
            users[i] = new Tester(Integer.parseInt(user[0]), user[1], user[2],0);
        }
        return users;
    }

    // Same thing as getAlladmins()
    public Developer[] getAllDevelopers() {
        FileManager fw = new FileManager("data//users//developer.txt");
        String[] arr = fw.getData();
        if (arr[0].isEmpty())
            return new Developer[0];
        Developer[] users = new Developer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            String[] user = arr[i].split("-"); //id-name-pass
            users[i] = new Developer(Integer.parseInt(user[0]), user[1], user[2],0);
        }
        return users;
    }

}
/* ProjectManger.txt file
    id-name-password
 */