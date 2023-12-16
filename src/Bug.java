/*
public class Bug {

    private String name, type, project;
    private int level, date, id, priority;
    private boolean state;

    public Bug() {
        this.name = "";
        this.type = "";
        this.project = "";
        this.id = 0;
        this.level = 0;
        this.date = 0;
        this.priority = 0;
        this.state = false;
    }
    public Bug(String name, String type, int level, int date, boolean state, String project, int priority) {
        FileManager file = new FileManager("data\\bugID.txt");
        file.getData();
        String[] arr = file.getData();
        int cnt = Integer.parseInt(arr[0]);
        this.id = cnt;
        this.name = name;
        this.type = type;
        this.level = level;
        this.date = date;
        this.state = state;
        this.project = project;
        this.priority = priority;
        cnt += 1;
        file.append(String.valueOf(cnt),false);
    }

    public String toString() {
        return this.id + "-" + this.name + "-" + this.type + "-" + this.level + "-" + this.date + "-" + this.state + "-" + this.project + "-" + this.priority;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getDate() {
        return date;
    }
    public void setDate(int date) {
        this.date = date;
    }
    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
}
/* bug.txt file
    id-name-type-level-date-state-project-priority
    ...
 */
class Bug {
    private String name, type, project;
    private int level, date, id, priority;
    private boolean state;

    public Bug() { //Constructor no bug
        this.name = "";
        this.type = "";
        this.project = "";
        this.id = 0;
        this.level = 0;
        this.date = 0;
        this.priority = 0;
        this.state = false;
    }
    public Bug(String name, String type, int level, int date, boolean state, String project, int priority) { //Constructor found bug
        FileManager file = new FileManager("data\\bugID.txt");
        file.getData();
        String[] arr = file.getData(); //To show Data in FileManager
        int cnt = Integer.parseInt(arr[0]);   //to get last cnt id
        this.id = cnt;
        this.name = name;
        this.type = type;
        this.level = level;
        this.date = date;
        this.state = state;
        this.project = project;
        this.priority = priority;
        cnt += 1;
        file.append(String.valueOf(cnt),false); //To append Data in FileManager
    }
    //   @override
    public String toString() {
        return this.id + "-" + this.name + "-" + this.type + "-" + this.level + "-" + this.date + "-" + this.state + "-" + this.project + "-" + this.priority;
    }
    //    ============================== get Methods=========================================
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public int getLevel() {
        return level;
    }
    public int getDate() {
        return date;
    }
    public int getPriority() {
        return priority;
    }
    //      =========================== method return state ==================================
    public boolean isState() {
        return state;
    }
    //      ============================set methods==========================================
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setPriority(int priority){
        this.priority=priority;
    }
}