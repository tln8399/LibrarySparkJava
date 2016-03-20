/**
 * Created by Tushar on 3/16/2016.
 */
public class User {

    private int id;
    private String fName;
    private String mName;
    private String lName;
    private int age;
    private char gender;
    private String phone;
    private String zip;

    public User( int id, String fName, String mName, String lName, int age, char gender, String phone, String zip) {
        this.id = id;
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.zip = zip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return fName;
    }

    public String getMiddleName() {
        return mName;
    }

    public String getLastName() {
        return lName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender( char gender ) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}
