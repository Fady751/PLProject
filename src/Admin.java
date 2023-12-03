public class Admin extends Person {

    public Admin() {
        super();
    }

    public Admin(String name, String password) {
        super(name, password, "Admin");
    }

    public Admin(int id, String name, String password) {
        super(id, name, password, "Admin");
    }

    public boolean login(String name, String password) {
        FileManager fw = new FileManager("data\\users\\admin.txt");
        String[] arr = fw.getData();
        for (String s : arr) {
            String[] user = s.split("-");
            if (user.length == 3 && user[1].equals(name) && user[2].equals(password)) {
                super.setId(Integer.parseInt(user[0]));
                super.setName(name);
                super.setPassword(password);
                super.setType("Admin");
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return super.getId() + "-" + super.getName() + "-" + super.getPassword();
    }

    //getting all users data
    public Admin[] getAllAdmins() {
        FileManager fw = new FileManager("data\\users\\admin.txt");
        String[] arr = fw.getData();
        if (arr[0].isEmpty())
            return new Admin[0];
        Admin[] users = new Admin[arr.length];
        for (int i = 0; i < arr.length; i++) {
            String[] user = arr[i].split("-"); //id-name-pass
            users[i] = new Admin(Integer.parseInt(user[0]), user[1], user[2]);
        }
        return users;
    }

    public Tester[] getAllTesters() {
        FileManager fw = new FileManager("data\\users\\tester.txt");
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

    public Developer[] getAllDevelopers() {
        FileManager fw = new FileManager("data\\users\\developer.txt");
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

    //-----------------------------adding new user-----------------------------
    public boolean addTester(String name, String password) {
        FileManager fw = new FileManager("data\\users\\tester.txt");
        String[] arr = fw.getData();
        for (String s : arr) {
            String[] user = s.split("-");
            if (user.length == 3 && user[1].equals(name)) {
                return false;
            }
        }

        Tester user = new Tester(name, password);
        fw.append(user.toString() + '\n', true);

        return true;
    }

    public boolean addDeveloper(String name, String password) {
        FileManager fw = new FileManager("data\\users\\developer.txt");
        String[] arr = fw.getData();
        for (String s : arr) {
            String[] user = s.split("-");
            if (user.length == 3 && user[1].equals(name)) {
                return false;
            }
        }

        Developer user = new Developer(name, password);
        fw.append(user.toString() + '\n', true);

        return true;
    }

    public boolean addAdmin(String name, String password) {
        FileManager fw = new FileManager("data\\users\\admin.txt");
        String[] arr = fw.getData();
        for (String s : arr) {
            String[] user = s.split("-");
            if (user[1].equals(name)) {
                return false;
            }
        }

        Admin user = new Admin(name, password);
        fw.append(user.toString() + '\n', true);

        return true;
    }

    //-----------------------------delete user-----------------------------
    public boolean deleteTester(String id) {
        FileManager fw = new FileManager("data\\users\\tester.txt");

        return fw.delete(id);
    }

    public boolean deleteDeveloper(String id) {
        FileManager fw = new FileManager("data\\users\\developer.txt");

        return fw.delete(id);
    }

    public boolean deleteAdmin(String id) {
        FileManager fw = new FileManager("data\\users\\admin.txt");

        return fw.delete(id);
    }

    //-----------------------------update user-----------------------------
    public int updateTester(String id, String name, String password) {
        FileManager fw = new FileManager("data\\users\\tester.txt");

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

        Tester user = new Tester(Integer.parseInt(id), name, password,0);
        fw.update(id, user.toString());
        return 1;
    }

    public int updateDeveloper(String id, String name, String password) {
        FileManager fw = new FileManager("data\\users\\developer.txt");

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
    public int updateAdmin(String id, String name, String password) {
        FileManager fw = new FileManager("data\\users\\admin.txt");

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