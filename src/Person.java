public abstract class Person {
    private int id;
    private String name, password, type;// type equal "Admin" || "Tester" || "Developer";
    public Person(int id, String name, String password, String type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.type = type;
    }
    public Person(String name, String password, String type) {
        //to get last cnt id
        FileManager filePersonID = new FileManager("data\\personID.txt");
        String[] arr = filePersonID.getData();
        int cnt = Integer.parseInt(arr[0]);
        //=======================================================================
        this.id = cnt++;
        this.name = name;
        this.password = password;
        this.type = type;
        filePersonID.append(cnt + "", false);
    }
    public Person() {
        this.id = 0;
        this.name = "";
        this.password = "";
        this.type = "";
    }
    public boolean loggedIn() {
        return this.id != 0;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean login() {
        return false;
    }





    public String toString() {
        return this.id + "-" + this.name + "-" + this.password;
    }
}
