import java.util.Scanner;

public class Menu {
    private static Scanner input = new Scanner(System.in);

     static public int mainMenu() {
        while (true) {
            System.out.println("===============\nLogin to \n1-Admin\n2-Tester\n3-Developer\n4-Project Manager\n0-Exit\n===============");
            System.out.print("Choose 1 or 2 or 3 or 4 or 0: ");
            String ch = input.nextLine().trim();
            int inp=Integer.parseInt(ch);
            if(inp<=4 && inp>= 0)return inp;
            System.out.println("invalid input :(");
        }
    }
    static public Admin adminLogin(){
            Admin user = new Admin();
            while (!user.loggedIn()) {
                System.out.print("user name: ");
                String name = input.nextLine();
                System.out.print("password: ");
                String password = input.nextLine();
                if (user.login(name, password)) {
                    System.out.println("Welcome, " + name + " you have been logged in successfully.");
                } else {
                    System.out.println("Wrong user name or password please try again.");
                }
            }
            return user;
        }

    static public void adminPage (Admin user){
        while (true) {
            System.out.println("1-View all bugs");
            System.out.println("2-View all users");
            System.out.println("3-add new user");
            System.out.println("4-delete user");
            System.out.println("5-update user");
            System.out.println("0-Exit");

            String ch = input.nextLine();
            ch = ch.trim();

            if (ch.equals("1")) {
                FileManager bugsFile = new FileManager("data//users//bug.txt");
                String[] bugs = bugsFile.getData();
                for (String bug : bugs) {
                    /* bug.txt file
                        0 - 1  - 2  - 3   - 4  - 5   - 6     - 7
                        id-name-type-level-date-state-project-priority
                        ...
                     */
                    String[] data = bug.split("-");
                    if (data.length < 8)
                        continue;
                    System.out.println("=======================================================");
                    System.out.println("Bug Id: " + data[0]);
                    System.out.println("Bug Name: " + data[1]);
                    System.out.println("Bug type: " + data[2]);
                    System.out.println("Bug level: " + data[3]);
                    System.out.println("Bug date: " + data[4]);
                    System.out.println("State: " + ((data[5].equals("false")) ? "NOT Solved yet" : "Solved"));
                    System.out.println("Bug project: " + data[4]);
                    System.out.println("Bug priority: " + data[4]);
                    System.out.println("=======================================================");
                }
            }
            else if (ch.equals("2")) {
                Admin[] ad = user.getAllAdmins();
                Tester[] te = user.getAllTesters();
                Developer[] de = user.getAllDevelopers();

                System.out.println("id  |  name  |  Type");
                for (Admin u : ad) {
                    System.out.println(u.getId() + "   |  " + u.getName() + " |  " + u.getType());
                }
                for (Tester u : te) {
                    System.out.println(u.getId() + "   |  " + u.getName() + "  |  " + u.getType());
                }
                for (Developer u : de) {
                    System.out.println(u.getId() + "   |  " + u.getName() + "  |  " + u.getType());
                }
            } else if (ch.equals("3")) {
                addUser(user);
            } else if (ch.equals("4")) {
                deleteUser(user);
            } else if (ch.equals("5")) {
                updateUser(user);
            } else if (ch.equals("0")) {
                return;
            } else {
                System.out.println("invalid input :(");
            }
        }
    }

    static public Tester testerLogin(){
        Tester user = new Tester();
        while (!user.loggedIn()) {
            System.out.print("user name: ");
            String name = input.nextLine();

            System.out.print("password: ");
            String password = input.nextLine();
            if (user.login(name, password)) {
                System.out.println("Welcome, " + name + " you have been logged in successfully.");
            } else {
                System.out.println("Wrong user name or password please try again.");
            }
        }
        return user;
    }


    static void addUser (Admin user){
        while (true) {
            System.out.println("1-add new Admin");
            System.out.println("2-add new Tester");
            System.out.println("3-add new Developer");
            System.out.println("0-Exit");
            String ch = input.nextLine().trim();

            if (ch.equals("1")) {
                System.out.print("user name: ");
                String name = input.nextLine();
                System.out.print("password: ");
                String pass = input.nextLine();

                while (!user.addAdmin(name, pass)) {
                    System.out.println("user name is used :(");
                    System.out.print("user name: ");
                    name = input.nextLine();
                }
                System.out.println("User added successfully :)");
            } else if (ch.equals("2")) {
                System.out.print("user name: ");
                String name = input.nextLine();
                System.out.print("password: ");
                String pass = input.nextLine();

                while (!user.addTester(name, pass)) {
                    System.out.println("user name is used :(");
                    System.out.print("user name: ");
                    name = input.nextLine();
                }
                System.out.println("User added successfully :)");
            } else if (ch.equals("3")) {
                System.out.print("user name: ");
                String name = input.nextLine();
                System.out.print("password: ");
                String pass = input.nextLine();

                while (!user.addDeveloper(name, pass)) {
                    System.out.println("user name is used :(");
                    System.out.print("user name: ");
                    name = input.nextLine();
                }
                System.out.println("User added successfully :)");
            } else if (ch.equals("0")) {
                return;
            } else {
                System.out.println("invalid input :(");
            }
        }
    }

    static void deleteUser (Admin user){
        while (true) {
            System.out.println("1-delete Admin");
            System.out.println("2-delete Tester");
            System.out.println("3-delete Developer");
            System.out.println("0-Exit");
            String ch = input.nextLine().trim();
            if (ch.equals("1")) {
                System.out.print("user id: ");
                String id = input.nextLine().trim();

                while (!user.deleteAdmin(id)) {
                    System.out.println("wrong id");
                    System.out.print("user id: ");
                    id = input.nextLine().trim();
                }
                System.out.println("User deleted successfully :)");
            } else if (ch.equals("2")) {
                System.out.print("user id: ");
                String id = input.nextLine().trim();

                while (!user.deleteTester(id)) {
                    System.out.println("wrong id");
                    System.out.print("user id: ");
                    id = input.nextLine().trim();
                }
                System.out.println("User deleted successfully :)");
            } else if (ch.equals("3")) {
                System.out.print("user id: ");
                String id = input.nextLine().trim();

                while (!user.deleteDeveloper(id)) {
                    System.out.println("wrong id");
                    System.out.print("user id: ");
                    id = input.nextLine().trim();
                }
                System.out.println("User deleted successfully :)");
            } else if (ch.equals("0")) {
                return;
            } else {
                System.out.println("invalid input :(");
            }
        }
    }

    static void updateUser (Admin user){
        while (true) {
            System.out.println("1-update Admin");
            System.out.println("2-update Tester");
            System.out.println("3-update Developer");
            System.out.println("0-Exit");
            String ch = input.nextLine().trim();

            if (ch.equals("1")) {
                System.out.print("user id: ");
                String id = input.nextLine();
                id = id.trim();
                System.out.print("new user name: ");
                String name = input.nextLine();
                System.out.print("new password: ");
                String pass = input.nextLine();

                int res = user.updateAdmin(id, name, pass);
                while (res != 1) {
                    if (res == -1) {
                        System.out.println("name is used");
                        System.out.print("new user name: ");
                        name = input.nextLine();
                    } else {
                        System.out.println("id not found");
                        System.out.print("user id: ");
                        id = input.nextLine();
                        id = id.trim();
                    }
                    res = user.updateAdmin(id, name, pass);
                }
                System.out.println("User updated successfully :)");
            } else if (ch.equals("2")) {
                System.out.print("user id: ");
                String id = input.nextLine();
                id = id.trim();
                System.out.print("new user name: ");
                String name = input.nextLine();
                System.out.print("new password: ");
                String pass = input.nextLine();

                int res = user.updateTester(id, name, pass);
                while (res != 1) {
                    if (res == -1) {
                        System.out.println("name is used");
                        System.out.print("new user name: ");
                        name = input.nextLine();
                    } else {
                        System.out.println("id not found");
                        System.out.print("id: ");
                        id = input.nextLine();
                        id = id.trim();
                    }
                    res = user.updateTester(id, name, pass);
                }
                System.out.println("User updated successfully :)");
            } else if (ch.equals("3")) {
                System.out.print("user id: ");
                String id = input.nextLine();
                id = id.trim();
                System.out.print("new user name: ");
                String name = input.nextLine();
                System.out.print("new password: ");
                String pass = input.nextLine();

                int res = user.updateDeveloper(id, name, pass);
                while (res != 1) {
                    if (res == -1) {
                        System.out.println("name is used");
                        System.out.print("new user name: ");
                        name = input.nextLine();
                    } else {
                        System.out.println("id not found");
                        System.out.print("id: ");
                        id = input.nextLine();
                        id = id.trim();
                    }
                    res = user.updateDeveloper(id, name, pass);
                }
                System.out.println("User updated successfully :)");
            } else if (ch.equals("0")) {
                return;
            } else {
                System.out.println("invalid input :(");
            }
        }
    }

    static public void testerPage (Tester user){

        while (true) {
            System.out.println("1-Define a bug");
            System.out.println("2-assign bug to developer");
            System.out.println("3-view all bugs");
            System.out.println("4-view all developers");
            System.out.println("0-Exit");
            System.out.print("->");
            String userInput = input.nextLine().trim();

            boolean ok = false;
            if (userInput.equals("1")) {
                boolean bugState;
                String bugName = "", bugType, projectName;
                int bugLevel, bugDate, priority;
                boolean cnt = false;//to print this bug name is used after 1 loop
                do {
                    if (cnt) {
                        System.out.println("=======================================================");
                        System.out.println("this bug name {" + bugName + "} is been used before");
                    }
                    System.out.print("Enter the bug name: ");
                    bugName = input.nextLine();

                    System.out.print("Enter the bug type: ");
                    bugType = input.nextLine();

                    System.out.print("Enter the priority: ");
                    priority = input.nextInt();

                    System.out.print("Enter the bug level: ");
                    bugLevel = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter the project name: ");
                    projectName = input.nextLine();

                    System.out.print("Enter the bugDate: ");
                    bugDate = input.nextInt();
                    input.nextLine();

                    bugState = false;
                    cnt = true;
                } while (!user.defineBug(bugName, bugType, bugLevel, bugDate, bugState, projectName, priority));
                System.out.println("Done :)");

            } else if (userInput.equals("2")) {
                int res;
                do {
                    System.out.print("enter bugId or bugName (-1 to exit): ");
                    String bugName = "";
                    int bugId = -1;
                    try {
                        bugId = input.nextInt();
                        input.nextLine();
                        if(bugId == -1) {
                            break;
                        }
                    } catch (Exception e) {
                        bugName = input.nextLine();
                    }

                    System.out.print("enter dev or devName: ");
                    String devName = "";
                    int devId = -1;
                    try {
                        devId = input.nextInt();
                        input.nextLine();

                    } catch (Exception e) {
                        devName = input.nextLine();
                    }
                    // -1 bugid or devid notFound || 0 dev hav a bug || 1 done || 2
                    res= (user.assign(devId, bugId, devName, bugName));
                    if (res==-1){
                        System.out.println("BugId or DevId notFounded");
                        System.out.println("===============");
                    }
                    else if(res==0){
                        System.out.println("this dev have a bug right now");
                        System.out.println("===============");

                    }
                    ok = res == 1;
                }while (res!=1);
                if(ok)
                    System.out.println("Done :)");
            } else if (userInput.equals("3")) {
                FileManager bugsFile = new FileManager("data//users//bug.txt");
                String[] bugs = bugsFile.getData();
                for (String bug : bugs) {
                    /* bug.txt file
                        0 - 1  - 2  - 3   - 4  - 5   - 6     - 7
                        id-name-type-level-date-state-project-priority
                        ...
                     */
                    String[] data = bug.split("-");
                    if (data.length < 8)
                        continue;
                    System.out.println("=======================================================");
                    System.out.println("Bug Id: " + data[0]);
                    System.out.println("Bug Name: " + data[1]);
                    System.out.println("Bug type: " + data[2]);
                    System.out.println("Bug level: " + data[3]);
                    System.out.println("Bug date: " + data[4]);
                    System.out.println("State: " + ((data[5].equals("false")) ? "NOT Solved yet" : "Solved"));
                    System.out.println("Bug project: " + data[4]);
                    System.out.println("Bug priority: " + data[4]);
                    System.out.println("=======================================================");
                }
            } else if (userInput.equals("4")) {
                Developer[] developers = user.getAllDevelopers();

                System.out.println("id  |  name  |  Type");

                for (Developer u : developers) {
                    System.out.println(u.getId() + "   |  " + u.getName() + "  |  " + u.getType());
                }
                System.out.println("=====================================");
            } else if (userInput.equals("0")) {
                return;
            } else {
                System.out.println("invalid Input :(");
            }
        }
    }

    static public Developer developerLogin(){
        Developer user = new Developer();
        while (!user.loggedIn()) {
            System.out.print("user name: ");
            String name = input.nextLine();

            System.out.print("password: ");
            String password = input.nextLine();
            if (user.login(name, password)) {
                System.out.println("Welcome, " + name + " you have been logged in successfully.");
            } else {
                System.out.println("Wrong user name or password please try again.");
            }
        }
        return user;
}
    static public void developerPage (Developer user){
        while (true) {
            System.out.println("1-view assigned bugs");
            System.out.println("2-change status of bug");
            System.out.println("0-Exit");
            System.out.print("->");
            String userInput = input.nextLine().trim();

            FileManager bugs = new FileManager("data//users//bug.txt");
            String bugLine = bugs.searchById(user.getBugId());
            String[] data = bugLine.split("-");
            if (userInput.equals("1")) {
                if (user.getBugId() == 0) System.out.println("enjoy NO BUGS ASSIGNED yet :)");
                else {
                    System.out.println("=======================================================");
                    System.out.println("Bug Id: " + data[0]);
                    System.out.println("Bug Name: " + data[1]);
                    System.out.println("Bug type: " + data[2]);
                    System.out.println("Bug level: " + data[3]);
                    System.out.println("Bug date: " + data[4]);
                    System.out.println("State: " + ((data[5].equals("false")) ? "NOT Solved yet" : "Solved"));
                    System.out.println("Bug project: " + data[4]);
                    System.out.println("Bug priority: " + data[4]);
                    System.out.println("=======================================================");

                }
            } else if (userInput.equals("2")) {
                FileManager devs = new FileManager("data//users//developer.txt");
                String[] dev = devs.searchById(user.getId()).split("-");

                int bugId = Integer.parseInt(dev[3]);

                FileManager bugsFile = new FileManager("data//users//bug.txt");
                String[] bug = bugsFile.searchById(bugId).split("-");

                if (bug[5].equals("false")) {
                    System.out.println("Bug Id: " + data[0]);
                    System.out.println("Project Name: " + data[3]);
                    System.out.println("did you finished your task:) ?");
                    System.out.print("yes||no: ");
                    String userinput = input.nextLine();
                    if (userinput.equals("yes")) {
                        if (user.changeStatus(data[0]))
                            System.out.println("changed successfully");
                        else
                            System.out.println("error");
                    }
                } else {
                    System.out.println("you don't have bugs :)");
                }
            } else if (userInput.equals("0")) {
                return;
            } else {
                System.out.println("invalid input :(");
            }
        }
    }
    static public ProjectManager projectMangerLogin(){
        ProjectManager user = new ProjectManager();

        while (!user.loggedIn()) {
            System.out.print("user name: ");
            String name = input.nextLine();

            System.out.print("password: ");
            String password = input.nextLine();
            if (user.login(name, password)) {
                System.out.println("Welcome, " + name + " you have been logged in successfully.");
            } else {
                System.out.println("Wrong user name or password please try again.");
            }
        }
        return user;
    }
    static public void projectManagerPage (ProjectManager user){
        while (true) {
            System.out.println("1-View all closed bugs");
            System.out.println("2-View all open bugs");
            System.out.println("3-check tester performance");
            System.out.println("4-check developer performance");
            System.out.println("5-View all users");
            System.out.println("0-Exit");

            String ch = input.nextLine().trim();

            if (ch.equals("1")) {
                String[] res = user.vewClosedBugs();
                for (String bug : res)
                    System.out.println(bug);

            } else if (ch.equals("2")) {
                String[] res = user.viewOpenBugs();
                for (String bug : res)
                    System.out.println(bug);

            } else if (ch.equals("3")) {
                System.out.print("Enter the id of the tester: ");
                int id = input.nextInt();
                input.nextLine();
                String res = user.checkTesterPerformance(id);
                if (res.equals("this id was not found")) {
                    System.out.println("this id was not found");
                } else {
                    System.out.println("this tester discoverd " + res + "  bugs");
                }
            } else if (ch.equals("4")) {
                System.out.print("Enter the id of the developer: ");
                int id = input.nextInt();
                input.nextLine();
                String res = user.checkDeveloperPerformance(id);
                if (res.equals("this id was not found")) {
                    System.out.println("this id was not found");
                } else {
                    int fixedBugs = Integer.parseInt(res);
                    FileManager fm = new FileManager("data\\users\\developer.txt");
                    String[] assignedBugs = fm.searchById(id).split("-");
                    int assigned_Bugs = Integer.parseInt(assignedBugs[4]);

                  System.out.println("this developer fixed  "+ assigned_Bugs+" bugs");
                }
            }
            else if (ch.equals("5")) {
                Admin[] ad = user.getAllAdmins();
                Tester[] te = user.getAllTesters();
                Developer[] de = user.getAllDevelopers();

                System.out.println("id  |  name  |  Type");
                for (Admin u : ad) {
                    System.out.println(u.getId() + "   |  " + u.getName() + " |  " + u.getType());
                }
                for (Tester u : te) {
                    System.out.println(u.getId() + "   |  " + u.getName() + "  |  " + u.getType());
                }
                for (Developer u : de) {
                    System.out.println(u.getId() + "   |  " + u.getName() + "  |  " + u.getType());
                }
            }
            else if (ch.equals("0")) {
                return;
            } else {
                System.out.println("invalid input :(");
            }
        }
    }
}