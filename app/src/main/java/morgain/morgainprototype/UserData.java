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
    private transient Context ctx; //made transient so it isn't serialized
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
        if (today == null) {
            today = new Mood();
        }
        return today;
    }

    public void setToday(Mood m) {
        today = m;
    }
    public void flushMood() {
        moods.add(today);
        today = new Mood();
    }


    public void saveData(UserData ud, Context ctx) {
        try {
            FileOutputStream fos = ctx.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ud);
            oos.close();
            System.out.println("--User Data Saved--");
        } catch (FileNotFoundException e) {
            System.out.println("--User Data Save Failed--");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("--User Data Save Failed--");
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
            System.out.println("--User Data loaded--");
            return ud;
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
        System.out.println("--User Data Load Failed-- Creating new instance...");
        ud = new UserData();
        saveData(ud, ctx);//this might not work
        return ud;
    }
}
