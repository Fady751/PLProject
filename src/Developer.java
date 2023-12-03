public class Developer extends Person {
    private int bugId;

    public Developer() {
        super();
    }
    public Developer(String line,int bgId) {

        String[] atrip=line.split("-");
        super.setId(Integer.parseInt(atrip[0]));
        super.setName(atrip[1]);
        super.setPassword(atrip[2]);
        super.setType("Developer");
        this.bugId=bgId;

    }
    public Developer(String name, String password) {
        super(name, password,  "Developer");
    }
    public Developer(int id, String name, String password) {
        super(id, name, password, "Developer");
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
        return super.getId() + "-" + super.getName() + "-" + super.getPassword() + "-" + this.bugId;
    }
    public boolean changeStatus(String id){
        FileManager bugs = new FileManager("data//users//bug.txt");
        return bugs.delete(id);
    }
    public int getBugId(){
        return this.bugId;
    }
}

/* developer.txt file
    id-name-password-bugId
    ...
 */