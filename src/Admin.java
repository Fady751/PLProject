public class Admin extends Person {
    // Default admin
    public Admin() {
        super();  // Calls the parent class (Person) constructor
    }

    // Admin with name and password parameters
    public Admin(String name, String password) {
        super(name, password, "Admin");
    }

    // Admin with id, name, and password parameters
    public Admin(int id, String name, String password) {
        super(id, name, password, "Admin");
    }

    // Login an admin method
    public boolean login(String name, String password) {
        FileManager fw = new FileManager("data//users//admin.txt");  // Create a FileManager object
        String[] arr = fw.getData();  // Get the data from the file

        // Read the file line-by-line
        for (String s : arr) {
            String[] user = s.split("-");  // Split the line into an array using the delim "-"

            // Check if the array has more than 2 elements "enough for an id, name and password"
            // and if the name and password from the input match the provided ones in the file
            if (2 < user.length && user[1].equals(name) && user[2].equals(password)) {
                super.setId(Integer.parseInt(user[0]));  // Set the id of the admin
                super.setName(name);  // Set the name of the admin
                super.setPassword(password);  // Set the password of the admin
                super.setType("Admin");  // Set the type of the admin
                return true;  // Return true indicating successful login
            }
        }
        return false;  // when the login process is unsuccessful
    }
    // Convert a new admin to string representation for files method
    public String toString() {
        return super.getId() + "-" + super.getName() + "-" + super.getPassword();
    }

    // List all admins method
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

    //-----------------------------add a new user-----------------------------
    // Add a new Tester method
    public boolean addTester(String name, String password) {
        FileManager fw = new FileManager("data//users//tester.txt");  // Create a FileManager object to handle file operation
        String[] arr = fw.getData();  // Get the data from the file
        for (String s : arr) {
            String[] user = s.split("-");  // Split the line into an array using the delim "-"
            if (1 < user.length && user[1].equals(name)) {
                return false;  // If a tester with the same name already exists
            }
        }
        Tester user = new Tester(name, password);  // Create a new Tester object
        fw.append(user.toString() + '\n', true);  // Append the string representation of the Tester object to the file
        return true; // For successful addition
    }
    // Same thing as addTester()
    public boolean addDeveloper(String name, String password) {
        FileManager fw = new FileManager("data//users//developer.txt");
        String[] arr = fw.getData();
        for (String s : arr) {
            String[] user = s.split("-");
            if (1 < user.length && user[1].equals(name)) {
                return false;
            }
        }

        Developer user = new Developer(name, password);
        fw.append(user.toString() + '\n', true);

        return true;
    }

    // Same thing as addTester()
    public boolean addAdmin(String name, String password) {
        FileManager fw = new FileManager("data//users//admin.txt");
        String[] arr = fw.getData();
        for (String s : arr) {
            String[] user = s.split("-");
            if (1 < user.length && user[1].equals(name)) {
                return false;
            }
        }

        Admin user = new Admin(name, password);
        fw.append(user.toString() + '\n', true);

        return true;
    }
    //-----------------------------delete a user-----------------------------

    // Delete a tester
    public boolean deleteTester(String id) {
        FileManager fw = new FileManager("data//users//tester.txt");  // Creates a FileManager object to handle file operations
        return fw.delete(id);  // Deletes the tester with the provided id from the file and returns the result
    }

    // Same thing as deleteTester()
    public boolean deleteDeveloper(String id) {
        FileManager fw = new FileManager("data//users//developer.txt");

        return fw.delete(id);
    }

    // Same thing as deleteTester()
    public boolean deleteAdmin(String id) {
        FileManager fw = new FileManager("data//users//admin.txt");

        return fw.delete(id);
    }

    //-----------------------------update a user-----------------------------

    // Update tester info method
    public int updateTester(String id, String name, String password) {
        FileManager fw = new FileManager("data//users//tester.txt");  // Creates a FileManager object to handle file operations
        String[] data = fw.getData();  // Gets the data from the file

        boolean found = false;
        for(String s : data) {
            String[] line = s.split("-");  // Splits the line into an array using "-" as the delimiter
            found |= line[0].equals(id);  // Checks if the id matches the provided one
            if (!line[0].equals(id) && line[1].equals(name)) {
                return -1;  // If a tester with the same name but different id already exists.
            }
        }
        if(!found) {
            return 0;  // If no tester with the provided id is found, returns 0
        }

        Tester user = new Tester(Integer.parseInt(id), name, password,0);  // Creates a new Tester object with the provided details
        fw.update(id, user.toString());  // Updates the tester in the file
        return 1;  // For successful update
    }

    // Same thing as updateTester()
    public int updateDeveloper(String id, String name, String password) {
        FileManager fw = new FileManager("data//users//developer.txt");

        String[] data = fw.getData();

        boolean found = false;
        for(String s : data) {
            String[] line = s.split("-");
            found |= line[0].equals(id);
            if (!line[0].equals(id) && line[1].equals(name)) {
                return -1; //name is used
            }
        }
        if(!found) {
            return 0;
        }

        Developer user = new Developer(Integer.parseInt(id), name, password,0);
        fw.update(id, user.toString());
        return 1;
    }

    // Same thing as updateTester()
    public int updateAdmin(String id, String name, String password) {
        FileManager fw = new FileManager("data//users//admin.txt");

        String[] data = fw.getData();

        boolean found = false;
        for(String s : data) {
            String[] line = s.split("-");
            found |= line[0].equals(id);
            if (!line[0].equals(id) && line[1].equals(name)) {
                return -1; //name is used
            }
        }
        if(!found) {
            return 0;
        }

        Admin user = new Admin(Integer.parseInt(id), name, password);
        fw.update(id, user.toString());
        return 1;
    }
}

/* admin.txt file
    id-name-password
    ...
 */