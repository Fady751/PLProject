public class Developer extends Person {
    private int bugId;
    private int completedBugs;

    public Developer() {
        super();
        this.completedBugs=0;
    }
    public Developer(String line,int bgId) {

        String[] atrip=line.split("-");
        super.setId(Integer.parseInt(atrip[0]));
        super.setName(atrip[1]);
        super.setPassword(atrip[2]);
        super.setType("Developer");
        this.bugId=bgId;
        this.completedBugs=Integer.parseInt(atrip[4]);

    }
    public Developer(String name, String password) {
        super(name, password,  "Developer");
        this.completedBugs=0;

    }
    public Developer(int id, String name, String password) {
        super(id, name, password, "Developer");
        this.completedBugs=0;

    }
    public boolean login(String name, String password) {
        FileManager fw = new FileManager("data\\users\\developer.txt");
        String[] arr = fw.getData();
        for (String s : arr) {
            String[] user = s.split("-");
            if (user.length >= 3 && user[1].equals(name) && user[2].equals(password)) {
                super.setId(Integer.parseInt(user[0]));
                super.setName(name);
                super.setPassword(password);
                super.setType("Developer");
                this.completedBugs= Integer.parseInt(user[4]);
                if(user.length>=4)
                    this.bugId=Integer.parseInt(user[3]);
                else
                    this.bugId=0;
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

        int intId = Integer.parseInt(id); // to use in buginfo
        String[] bugInfo = bugs.searchById(intId).split("-");
        bugInfo[5]="true";
        String bugafterChange= bugInfo[0]+"-"+bugInfo[1]+"-"+bugInfo[2]+"-"+bugInfo[3]+"-"+bugInfo[4]+"-"+bugInfo[5];

        String[] devInfo=dev.searchById(super.getId()).split("-");
        devInfo[4]= String.valueOf(this.completedBugs);
        String devAfterChange= devInfo[0]+"-"+devInfo[1]+"-"+devInfo[2]+"-"+devInfo[3]+"-"+devInfo[4];

        //update dev and bug
        if(bugs.update(id,bugafterChange)&&(dev.update(String.valueOf(super.getId()),devAfterChange)))return true;
        return false;

    }
    public void setBugId(int id){
        this.bugId=id;
    }
    public int getBugId(){
        return this.bugId;
    }
}

/* developer.txt file
    id-name-password-bugId-completedBugs
    ...
 */