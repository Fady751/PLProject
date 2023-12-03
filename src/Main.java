import javax.swing.table.TableRowSorter;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        while(true) {
            System.out.println("===============\nLogin to \n1-Admin\n2-Tester\n3-Developer\n0-Exit\n===============");
            System.out.print("Choose 1 or 2 or 3 or 0: ");
            int ch = input.nextInt();
            input.nextLine();//to solve string issue in java

            if (ch == 1) {
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
                adminPage(user);
            }
            else if (ch == 2) {
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
                   testerPage(user);
            }
            else if (ch == 3) {
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
                developerPage(user);
            }
            else if(ch == 0){
                System.out.println("Bye :)");
                break;
            }
            else {
                System.out.println("invalid input :(");
            }
        }
    }
    static public void adminPage(Admin user) {
        while(true){
            System.out.println("1-View all bugs");
            System.out.println("2-View all users");
            System.out.println("3-add new user");
            System.out.println("4-delete user");
            System.out.println("5-update user");
            System.out.println("0-Exit");

            int ch = input.nextInt();
            input.nextLine();//to solve string issue in java

            if(ch == 1) {
                FileManager bugsFile=new FileManager("data//users//bug.txt");
                String[] bugs =bugsFile.getData();
                for(String bug:bugs) {
                    System.out.println("=======================================================");
                    String[] data = bug.split("-");
                    System.out.println("Bug Id: " + data[0]);
                    System.out.println("Bug Name: " + data[1]);
                    System.out.println("Bug type: " + data[2]);
                    System.out.println("Project Name: " + data[3]);
                    System.out.println("Bug date: " + data[4]);
                    System.out.println("State: " + ((data[5].equals("false")) ? "NOT Solved yet" : "Solved"));
                    System.out.println("=======================================================");
                }
            }
            else if(ch == 2) {
                Admin[] ad = user.getAllAdmins();
                Tester[] te = user.getAllTesters();
                Developer[] de = user.getAllDevelopers();

                System.out.println("id  |  name  |  Type");
                for(Admin u : ad) {
                    System.out.println(u.getId() + "   |  " + u.getName() + " |  " + u.getType());
                }
                for(Tester u : te) {
                    System.out.println(u.getId() + "   |  " + u.getName() + "  |  " + u.getType());
                }
                for(Developer u : de) {
                    System.out.println(u.getId() + "   |  " + u.getName() + "  |  " + u.getType());
                }
            }
            else if(ch == 3) {
                addUser(user);
            }
            else if(ch == 4) {
                deleteUser(user);
            }
            else if(ch == 5) {
                updateUser(user);
            }
            else if(ch == 0) {
                return;
            }
            else {
                System.out.println("invalid input :(");
            }
        }
    }
    static void addUser(Admin user) {
        while (true){
            System.out.println("1-add new Admin");
            System.out.println("2-add new Tester");
            System.out.println("3-add new Developer");
            System.out.println("0-Exit");
            int ch = input.nextInt();
            input.nextLine();

            if (ch == 1) {
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
            } else if (ch == 2) {
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
            } else if (ch == 3) {
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
            } else if (ch == 0) {
                return;
            } else {
                System.out.println("invalid input :(");
            }
        }
    }
    static void deleteUser(Admin user) {
        while (true){
            System.out.println("1-delete Admin");
            System.out.println("2-delete Tester");
            System.out.println("3-delete Developer");
            System.out.println("0-Exit");
            int ch = input.nextInt();
            input.nextLine();
            if (ch == 1) {
                System.out.print("user id: ");
                String id = input.nextLine().trim();

                while (!user.deleteAdmin(id)) {
                    System.out.println("wrong id");
                    System.out.print("user id: ");
                    id = input.nextLine().trim();
                }
                System.out.println("User deleted successfully :)");
            } else if (ch == 2) {
                System.out.print("user id: ");
                String id = input.nextLine().trim();

                while (!user.deleteTester(id)) {
                    System.out.println("wrong id");
                    System.out.print("user id: ");
                    id = input.nextLine().trim();
                }
                System.out.println("User deleted successfully :)");
            } else if (ch == 3) {
                System.out.print("user id: ");
                String id = input.nextLine().trim();

                while (!user.deleteDeveloper(id)) {
                    System.out.println("wrong id");
                    System.out.print("user id: ");
                    id = input.nextLine().trim();
                }
                System.out.println("User deleted successfully :)");
            } else if (ch == 0) {
                return;
            } else {
                System.out.println("invalid input :(");
            }
        }
    }
    static void updateUser(Admin user) {
        while (true){
            System.out.println("1-update Admin");
            System.out.println("2-update Tester");
            System.out.println("3-update Developer");
            System.out.println("0-Exit");
            int ch = input.nextInt();
            input.nextLine();

            if (ch == 1) {
                System.out.print("user id: ");
                String id = input.nextLine();
                id = id.trim();
                System.out.print("new user name: ");
                String name = input.nextLine();
                System.out.print("new password: ");
                String pass = input.nextLine();

                int res = user.updateAdmin(id, name, pass);
                while (res != 1) {
                    if(res == -1) {
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
            } else if (ch == 2) {
                System.out.print("user id: ");
                String id = input.nextLine();
                id = id.trim();
                System.out.print("new user name: ");
                String name = input.nextLine();
                System.out.print("new password: ");
                String pass = input.nextLine();

                int res = user.updateTester(id, name, pass);
                while (res != 1) {
                    if(res == -1) {
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
            } else if (ch == 3) {
                System.out.print("user id: ");
                String id = input.nextLine();
                id = id.trim();
                System.out.print("new user name: ");
                String name = input.nextLine();
                System.out.print("new password: ");
                String pass = input.nextLine();

                int res = user.updateDeveloper(id, name, pass);
                while (res != 1) {
                    if(res == -1) {
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
            } else if (ch == 0) {
                return;
            } else {
                System.out.println("invalid input :(");
            }
        }
    }
    static public void testerPage(Tester user) {

        while (true) {
            System.out.println("1-Define a bug");
            System.out.println("2-assign bug to developer");
            System.out.println("3-view all bugs");
            System.out.println("4-Exit");
            System.out.print("->");
            int userInput = input.nextInt();
            input.nextLine();
            if (userInput == 1) {
                boolean bugState;
                String bugName="",bugType,priority,projectName;
                int bugLevel,bugDate;
                boolean cnt=false;//to print this bug name is used after 1 loop
                do{
                    if(cnt){
                        System.out.println("=======================================================");
                        System.out.println("this bug name {"+bugName+"} is been used before");
                        input.nextLine();
                    }
                    System.out.print("Enter the bug name: ");
                    bugName = input.nextLine();
                    System.out.println("Bug name: " + bugName);

                    System.out.print("Enter the bug type: ");
                    bugType = input.nextLine();
                    System.out.println("\n" + "Bug name: " + bugName + "\n" + "Bug Type: " + bugType);

                    System.out.print("Enter the priority: ");
                    priority = input.nextLine();
                    System.out.println("\n" + "Bug name: " + bugName + "\n" + "Bug Type: " + bugType + "\n" + "priority: " + priority);

                    System.out.print("Enter the bug level: ");
                    bugLevel = input.nextInt();
                    System.out.println("\n" + "Bug name: " + bugName + "\n" + "Bug Type: " + bugType + "\n" + "priority: " + priority + "\n"
                            + "bug level: " + bugLevel);
                    input.nextLine();
                    System.out.print("Enter the project name: ");
                    projectName = input.nextLine();
                    System.out.println("\n" + "Bug name: " + bugName + "\n" + "Bug Type: " + bugType + "\n" + "priority: " + priority
                            + "bug level: " + bugLevel + "\n" + "project name: " + projectName);
                    System.out.print("Enter the bugDate: ");
                    bugDate = input.nextInt();
                    System.out.println("\n" + "Bug name: " + bugName + "\n" + "Bug Type: " + bugType + "\n" + "priority: " + priority
                            + "bug level: " + bugLevel + "\n" + "project name: " + projectName + "\n" + "Bug date : " + bugDate);
                    bugState = false;
                    cnt=true;
                }while(!user.defineBug(bugName, bugType, bugLevel, bugDate, bugState));
                System.out.println("Done :)");

            }

            else if (userInput == 2) {
                System.out.print("enter bugId or bugName: ");
                String bugName = "";
                int bugId = -1;
                try {
                    bugId = input.nextInt();
                    input.nextLine();

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
                if (user.asign(devId, bugId, devName, bugName)) System.out.println("Done :)");
                System.out.println("===============");
            } else if (userInput == 3) {
                FileManager bugsFile = new FileManager("data//users//bug.txt");
                String[] bugs = bugsFile.getData();
                for (String bug : bugs) {
                    System.out.println("=======================================================");
                    String[] data = bug.split("-");
                    System.out.println("Bug Id: " + data[0]);
                    System.out.println("Bug Name: " + data[1]);
                    System.out.println("Bug type: " + data[2]);
                    System.out.println("Project Name: " + data[3]);
                    System.out.println("Bug date: " + data[4]);
                    System.out.println("State: " + ((data[5].equals("false")) ? "NOT Solved yet" : "Solved"));
                    System.out.println("=======================================================");
                }
            } else if (userInput == 4) return;
        }
    }
    static public void developerPage(Developer user) {
        while(true){
            System.out.println("1-view assigned bugs");
            System.out.println("2-change status of bug");
            System.out.println("3-Exit");
            System.out.print("->");
            int userInput = input.nextInt();
            input.nextLine();

            FileManager bugs=new FileManager("data//users//bug.txt");
            String bugLine=bugs.searchById(user.getBugId());
            String[] data= bugLine.split("-");
            if(userInput==1){
                if(user.getBugId()==0)System.out.println("enjoy NO BUGS ASSIGNED yet :)");
                else{
                    System.out.println("=======================================================");
                    System.out.println("Bug Id: "+data[0]);
                    System.out.println("Bug Name: "+data[1]);
                    System.out.println("Bug type: "+data[2]);
                    System.out.println("Project Name: "+data[3]);
                    System.out.println("Bug date: "+data[4]);
                    System.out.println("State: "+((data[5].equals("false"))?"NOT Solved yet":"Solved"));
                    System.out.println("=======================================================");

                }
            }
            else if(userInput==2){
                System.out.println("Bug Id: "+data[0]);
                System.out.println("Project Name: "+data[3]);
                System.out.println("did you finished your task:) ?");
                System.out.print("yes||no: ");
                String userinput=input.nextLine();
                if(userinput.equals("yes")){
                    if(user.changeStatus(data[0]))
                        System.out.println("changed successfully");
                    else
                        System.out.println("error");
                }
            }
            else if(userInput==3)return;
        }
    }
    
}