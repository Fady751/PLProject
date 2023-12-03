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
        String usingDevId,usingBugId;
        if(devId>=1)usingDevId= String.valueOf(devId);
        else{
            usingDevId= fileManager.searchByName(devName);
        }
        if (bugId>-1)usingBugId= String.valueOf(bugId);
        else {
            FileManager bugs=new FileManager("data\\users\\bug.txt");
            usingBugId =bugs.searchByName(bugName);
        }
        devId=Integer.parseInt(usingDevId);
        Developer dev=new Developer(fileManager.searchById(devId),Integer.parseInt(usingBugId));
        fileManager.update(usingDevId,dev.toString());
        return true;
    }
    public boolean defineBug(String bugName, String bugType,int bugLevel,int bugDate,boolean bugState){
        Bug bug = new Bug(bugName, bugType, bugLevel, bugDate, bugState);
        FileManager file = new FileManager("data\\users\\bug.txt");
        if(!file.searchByName(bug.getName()).equals("username NOT found")){
            return false;
        }
        file.append(bug.toString() + "\n", true);
        return true;
    }
    public String toString() {
        return super.getId() + "-" + super.getName() + "-" + super.getPassword();
    }
}

/* tester.txt file
    id-name-password
    ...
 */