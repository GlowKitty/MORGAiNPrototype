package morgain.morgainprototype;

import android.content.Context;
import android.util.Log;

import java.io.File;
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
    private static final String FILENAME = "MORGAiN_user_data_dev_1";

    private int age;
    private String name;
    //private int gender; //TODO: assign gender
    private Gender gender;
    private boolean firstRun = true;
    private Mood today;
    private ArrayList<Mood> moods = new ArrayList<Mood>();

    public UserData(Context ctx) {
        this.ctx = ctx;
        name = "";
        gender = new Gender(ctx);
        Log.i("UserData", "New instance of UserData created");
    }
    public static UserData instantiate(Context ctx) {
        UserData ud = new UserData(ctx);
        File file = ctx.getFileStreamPath(FILENAME);
        if(file.exists()) {
            Log.i("UserData", "User Data found, loading...");
            ud = ud.loadData(ctx);
        }
        return ud;
    }

    /*|=====================|
      |  First Run Methods  |
      |=====================|*/
    public boolean getFirstRun() {
        return firstRun;
    }
    public void setFirstRun(boolean firstRun) {
        this.firstRun = firstRun;
    }

    /*|================|
      |  Name Methods  |
      |================|*/
    public void setName(String name) {
        this.name = name;
        Log.i("UserData", "Name set to \"" + this.name + "\"");
    }
    public String getName() {
        return name;
    }

    /*|==================|
      |  Gender Methods  |
      |==================|*/
    public void setGender(int gender) {
        this.gender.setGender(gender);
        Log.i("UserData/Gender", "Gender set to " + this.gender.getGender());
    }
    public Gender getGender() {
        return gender;
    }

    /*|================|
      |  Mood Methods  |
      |================|*/
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

    /*|================|
      |  Data Methods  |
      |================|*/
    public void saveData(UserData ud, Context ctx) {
        try {
            FileOutputStream fos = ctx.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ud);
            oos.close();
            fos.close();
            Log.i("UserData", "User Data Saved");
        } catch (IOException e) {
            Log.e("UserData", "User Data Save Failed");
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
            fis.close();
            Log.i("UserData", "User Data Loaded");
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            e.printStackTrace();
            Log.e("UserData", "User Data Load Failed-- Creating new instance...");
            ud = new UserData(ctx);
            saveData(ud, ctx);
        }
        return ud;
    }
}
