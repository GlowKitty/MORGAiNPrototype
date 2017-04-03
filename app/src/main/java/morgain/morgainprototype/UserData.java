package morgain.morgainprototype;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class UserData implements Serializable {
    private static Context ctx;
    private final String FILENAME = "MORGAiN_user_data";

    private int age;
    private String firstName;
    private String lastName;
    private String userName;
    private int gender;
    private boolean firstOpen = true;
    private Mood today;
    private ArrayList<Mood> moods = new ArrayList<Mood>();

    public UserData() {//DEPRECATED DO NOT USE
        if (ctx == null) {
            ctx = MyApplication.getAppContext();
        }
        firstName = "";
    }

    public UserData(Context ctx) {
        this.ctx = ctx;
        firstName = "";
    }

    public void setGender(int gender) {
        this.gender = gender;
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

    public Mood getMood() {
        return moods.get(moods.size() - 1);
    }
    public Mood getMood(int i) {
        return moods.get(i);
    }
    public ArrayList<Mood> getMoods() {
        return moods;
    }
    public Mood getTodayMood() {
        return today;
    }

    public void setToday(Mood m) {
        today = m;
    }
    public void flushTodayMood() {
        moods.add(today);
        today = new Mood();
    }

    public void saveData() {
        saveData(this, ctx);
    }
    public void saveData(UserData ud, Context ctx) {
        try {
            FileOutputStream fos = ctx.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ud);
            oos.close();
            System.out.println("--User Data Saved--");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public UserData loadData() {
        return loadData(ctx);
    }
    public UserData loadData(Context ctx) {
        UserData ud;
        /*while (ctx == null) {
            ctx = MyApplication.getAppContext();
        }*/
        try {
            FileInputStream fis = ctx.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ud = (UserData) ois.readObject();
            ois.close();
            System.out.println("--User Data loaded--");
            return ud;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
