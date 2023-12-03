public class Tester extends Person {

    public Tester() {
        super();
    }
    public Tester(String name, String password) {
        super(name, password, "Tester");
    }
    public Tester(int id, String name, String password) {
        super(id, name, password, "Tester");
    }
    public boolean login(String name, String password) {
        FileManager fw = new FileManager("data\\users\\tester.txt");
        String[] arr = fw.getData();
        for (String s : arr) {
            String[] user = s.split("-");
            if (user.length == 3 && user[1].equals(name) && user[2].equals(password)) {
                super.setId(Integer.parseInt(user[0]));
                super.setName(name);
                super.setPassword(password);
                super.setType("Tester");
                return true;
            }
        }
        return false;
    }
    public boolean asign(int devId,int bugId,String devName,String bugName){
        FileManager fileManager= new FileManager("data\\users\\developer.txt");
        FileManager bugs=new FileManager("data\\users\\bug.txt");

        String usingDevId,usingBugId;
        if(devId>=1)usingDevId= String.valueOf(devId);
        else{
            usingDevId= fileManager.searchByName(devName);
        }
        if (bugId>-1)usingBugId= String.valueOf(bugId);
        else {
            usingBugId =bugs.searchByName(bugName);
        }
        devId=Integer.parseInt(usingDevId);

        // check if tha developer has a bug right now or not
        String[] devInfo =fileManager.searchById(devId).split("-");
        if(Integer.parseInt(devInfo[3])>0){
            String[] bugInfo = bugs.searchById(Integer.parseInt(devInfo[3])).split("-");
            if(bugInfo[5].equals("false"))
                return false;
            else{
                Developer dev = new Developer(fileManager.searchById(devId),Integer.parseInt(usingBugId));
                dev.setBugId(Integer.parseInt(usingBugId));
                fileManager.update(usingDevId,dev.toString());
                return true;
            }
        }
        else{
            Developer dev=new Developer(fileManager.searchById(devId),Integer.parseInt(usingBugId));
            dev.setBugId(Integer.parseInt(usingBugId));
            fileManager.update(usingDevId,dev.toString());
            return true;
        }
    }
    public boolean defineBug(String bugName, String bugType,int bugLevel,int bugDate,boolean bugState,String projectName,int priority){
        Bug bug = new Bug(bugName, bugType, bugLevel, bugDate, bugState,projectName,priority);
        FileManager file = new FileManager("data\\users\\bug.txt");
        if(!file.searchByName(bug.getName()).equals("username NOT found")){
            return false;
        }
        file.append(bug.toString() + "\n", true);
        return true;
    }

    public Developer[] getAllDevelopers() {
        FileManager fw = new FileManager("data\\users\\developer.txt");
        String[] arr = fw.getData();
        if (arr[0].isEmpty())
            return new Developer[0];
        Developer[] users = new Developer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            String[] user = arr[i].split("-"); //id-name-pass
            users[i] = new Developer(Integer.parseInt(user[0]), user[1], user[2]);
        }
        return users;
    }
    public String toString() {
        return super.getId() + "-" + super.getName() + "-" + super.getPassword();
    }
}

/* tester.txt file
    id-name-password
    ...
 */