import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Tushar on 3/16/2016.
 */

public class UserUtils {

    private ArrayList<User> users = new ArrayList<User>();
    HashSet<String> set = new HashSet<String>();

    public List<User> getAllUsers() {
        return users;
    }

    public User getUser(String idStr) {
        int id = Integer.parseInt(idStr);
        return users.get(id);
    }

    public User createUser(String fName, String mName, String lName, String ageStr, String genderStr, String phone, String zip) {
        int age = Integer.parseInt(ageStr);
        char gender = genderStr.charAt(0);
        if( !set.contains( fName+lName ) ) {
            int id = getNextId();
            failIfInvalid(id, fName, mName, lName, age, gender, phone, zip);
            User user = new User(id, fName, mName, lName, age, gender, phone, zip);
            users.add(user);
            set.add( fName+lName );
            return user;
        }
        else
            throw new IllegalArgumentException("User with name '" + fName + " "+ lName + "' already exist.");
    }

    public User updateUser( String idStr, String fName, String mName, String lName, String ageStr, String genderStr, String phone, String zip) {
        int id = Integer.parseInt(idStr);
        int age = Integer.parseInt(ageStr);
        char gender = genderStr.charAt(0);
        User user = users.get(id);
        if (user == null) {
            throw new IllegalArgumentException("No user with id '" + id + "' found");
        }
        failIfInvalid(id, fName, mName, lName, age, gender, phone, zip);
        user.setfName(fName);
        user.setmName(mName);
        user.setlName(lName);
        user.setAge(age);
        user.setGender(gender);
        user.setPhone(phone);
        user.setZip(zip);
        return user;
    }

    private void failIfInvalid( int id, String fName, String mName, String lName, int age, char gender, String phone, String zip) {
        if (fName == null || fName.isEmpty() || !isValidName(fName)) {
            throw new IllegalArgumentException("Parameter 'first Name' '" + fName + "' is invalid");
        }
        if (lName == null || lName.isEmpty() || !isValidName(lName)) {
            throw new IllegalArgumentException("Parameter 'Last Name'' '" + lName + "' is invalid");
        }
        if (age <= 0 || age > 120 ) {
            throw new IllegalArgumentException("Parameter 'age' '" + age + "'  is invalid");
        }
        if (zip.length() != 5 || !isValidZip(zip) ) {
            throw new IllegalArgumentException("Parameter 'zip' '" + zip + "'  is invalid");
        }
        if (gender != 'M' && gender != 'F') {
            throw new IllegalArgumentException("Invalid 'gender' '" + gender + "'");
        }
        if (phone == null || phone.isEmpty() || phone.length() != 10 || !isValidPhone(phone) ) {
            throw new IllegalArgumentException("Parameter 'phone' must contain 10 numeric digits.");
        }

        if( mName != null && !isValidName(mName)) {
            throw new IllegalArgumentException("Parameter 'middle name' must contain alphabets.");
        }

    }

    private boolean isValidName( String str ) {
        for( int i = 0; i < str.length(); i++ ) {
            if( ( str.charAt(i) >= 65 && str.charAt(i) <= 90 ) ||
                    ( str.charAt(i) >= 97 && str.charAt(i) <= 122 ) )
                continue;
            else
                return false;
        }
        return true;
    }

    private boolean isValidPhone(String phone) {
        if( phone.length() != 10 )
            return false;

        for( int i = 0; i < phone.length(); i++ ) {
            if( ( phone.charAt(i) < 48 || phone.charAt(i) > 57 ) )
                return false;
        }
        return true;
    }

    private boolean isValidZip(String zip) {
        if( zip.length() != 5 )
            return false;

        for( int i = 0; i < zip.length(); i++ ) {
            if( ( zip.charAt(i) < 48 || zip.charAt(i) > 57 ) )
                return false;
        }
        return true;
    }

    private int getNextId() {
        return users.size();
    }
}