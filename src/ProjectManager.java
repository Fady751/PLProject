public class ProjectManager extends Person  {


    public ProjectManager() {
        super();
    }
    public ProjectManager(String name, String password) {
        super(name, password, "ProjectManager");
    }
    public boolean login(String name, String password) {
        FileManager fw = new FileManager("data\\users\\ProjectManager.txt");
        String[] arr = fw.getData();
        for (String s : arr) {
            String[] user = s.split("-");
            if (user.length > 1 && user[1].equals(name) && user[2].equals(password)) {
                super.setId(Integer.parseInt(user[0]));
                super.setName(name);
                super.setPassword(password);
                super.setType("Project Manager");
                return true;
            }
        }
        return false;
    }
    public String[] vewClosedBugs() {
        FileManager fm = new FileManager("data\\users\\bug.txt");
        String[] res = fm.getData();
        String bugID="";
        for(String s : res) {
            String[] line = s.split("-");//id-name-type-level-date-state
            if(line[5].equals("true")){
                bugID+=line[0]+" "+ line[1]+"\n";
            }
        }
        String[] output=bugID.split("\n");

        return output;
    }

    public String[] veiwOpenBugs() {
        FileManager fm = new FileManager("data\\users\\bug.txt");
        String[] res = fm.getData();
        String bugID="";
        for(String s : res) {
            String[] line = s.split("-");//id-name-type-level-date-state
            if(line[5].equals("false")){
                bugID+=line[0]+" "+ line[1]+"\n";
            }
        }
        String[] output=bugID.split("\n");

        return output;
    }
}