package morgain.morgainprototype;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class UserData implements Serializable {
    private final String FILENAME = "MORGAiN_user_data";

    private int age;
    private String firstName;
    private String lastName;
    private String userName;

    public UserData() {

    }
    public UserData(String firstName, String lastName, String userName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.age = age;
    }

    public void setName(String firstName) {
        this.firstName = firstName;
        this.lastName = "";
    }
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public void setName(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getUserName() {
        return userName;
    }

    public void saveData(UserData ud, Context ctx) {
        try {
            FileOutputStream fos = ctx.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ud);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public UserData loadData(Context ctx) {
        UserData ud;
        try {
            FileInputStream fis = ctx.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ud = (UserData) ois.readObject();
            ois.close();
            return ud;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
