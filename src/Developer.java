public class Developer extends Person {
    private int bugId;
    private int completedBugs;

    public Developer() {
        super();
        this.completedBugs = 0;
    }
    public Developer(String line,int bgId) {

        String[] data = line.split("-");
        super.setId(Integer.parseInt(data[0]));
        super.setName(data[1]);
        super.setPassword(data[2]);
        super.setType("Developer");
        this.bugId = bgId;
        this.completedBugs = Integer.parseInt(data[4]);

    }
    public Developer(String name, String password) {
        super(name, password,  "Developer");
        this.completedBugs = 0;

    }
    public Developer(int id, String name, String password,int completedBugs) {
        super(id, name, password, "Developer");
        this.completedBugs=completedBugs;
    }
    public boolean login(String name, String password) {
        FileManager fw = new FileManager("data\\users\\developer.txt");
        String[] arr = fw.getData();
        for (String s : arr) {
            String[] user = s.split("-");
            if (4 < user.length && user[1].equals(name) && user[2].equals(password)) {
                super.setId(Integer.parseInt(user[0]));
                super.setName(name);
                super.setPassword(password);
                super.setType("Developer");
                this.completedBugs = Integer.parseInt(user[4]);
                this.bugId = Integer.parseInt(user[3]);
                return true;
            }
        }
        return false;
    }
    public String toString() {
        return super.getId() + "-" + super.getName() + "-" + super.getPassword() + "-" + this.bugId + "-" + this.completedBugs;
    }
    public boolean changeStatus(String id){
        this.completedBugs++;
        FileManager bugs = new FileManager("data//users//bug.txt");
        FileManager dev = new FileManager("data//users//developer.txt");

        int intId = Integer.parseInt(id); // to use in bug info
        String[] bugInfo = bugs.searchById(intId).split("-");
        if(7 >= bugInfo.length)
            return false;

        String bugAfterChange = bugInfo[0] + "-" + bugInfo[1] + "-" + bugInfo[2] + "-" + bugInfo[3] + "-" + bugInfo[4] + "-true-" + bugInfo[6] + "-" + bugInfo[7];

        String[] devInfo = dev.searchById(super.getId()).split("-");

        devInfo[4] = String.valueOf(this.completedBugs);

        String devAfterChange = devInfo[0] + "-" + devInfo[1] + "-" + devInfo[2] + "-" + devInfo[3] + "-" + devInfo[4];

        //update dev and bug
        return bugs.update(id, bugAfterChange) && dev.update(String.valueOf(super.getId()), devAfterChange);

    }
    public void setBugId(int id){
        this.bugId = id;
    }
    public int getBugId(){
        return this.bugId;
    }

    public int getCompletedBugs() {
        return completedBugs;
    }

    public void setCompletedBugs(int completedBugs) {
        this.completedBugs = completedBugs;
    }
}

/* developer.txt file
    id-name-password-bugId-completedBugs
    ...
 */