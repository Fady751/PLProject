public class Bug {
    private String name, type;
    private int level, date,id;
    private boolean state;

    public Bug() {
        this.name = "";
        this.type = "";
        this.level = 0;
        this.date = 0;
        this.state = false;
    }
    public Bug(String name, String type, int level, int date, boolean state) {
        FileManager file = new FileManager("data\\bugID.txt");
        file.getData();
        String[] arr = file.getData();
        int cnt = Integer.parseInt(arr[0]);
        cnt=cnt+1;
        file.append(String.valueOf(cnt),false);
        this.id=cnt;
        this.name = name;
        this.type = type;
        this.level = level;
        this.date = date;
        this.state = state;
    }

    public String toString() {
        return this.id+"-"+ this.name + "-" + this.type + "-" + this.level + "-" + this.date + "-" + this.state;
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
    name-type-level-date-state
    ...
 */