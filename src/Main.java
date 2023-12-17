
import java.util.Scanner;
public class Main {

    public static void main(String args[]) {
        while (true) {
            int inp = Menu.mainMenu();
            if (inp == 1) {
                Admin user = Menu.adminLogin();
                Menu.adminPage(user);
            } else if (inp == 2) {
                Tester user = Menu.testerLogin();
                Menu.testerPage(user);
            } else if (inp == 3) {
                Developer dev = Menu.developerLogin();
                Menu.developerPage(dev);
            } else if (inp == 4) {
                ProjectManager prjM = Menu.projectMangerLogin();
                Menu.projectManagerPage(prjM);
            } else {
                System.out.println("Bye :)");
                break;
            }
        }
    }

}