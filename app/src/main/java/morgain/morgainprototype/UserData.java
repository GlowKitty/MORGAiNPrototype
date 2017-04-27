package morgain.morgainprototype;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;

import java.util.ArrayList;

public class UserData implements Serializable { //may not need serializable
    private transient Context ctx; //made transient so it isn't serialized
    // also context appears to only be needed to load the gender Strings under the Gender class
    // maybe dont even need context, figure it out later
    private transient Gson gson; //transient so not serialized
    private static final String FILENAME = "MORGAiN_user_data_dev_6.json";
    private static transient File FILE;

    private String name;
    private Gender gender;
    private boolean firstRun = true;
    private int UUID;
    private Mood today;
    private ArrayList<Mood> moods = new ArrayList<>();

    public UserData(Context ctx) {
        gson = new GsonBuilder().create();
        FILE = new File(ctx.getFilesDir(), FILENAME);
        name = "";
        gender = new Gender();
        createUUID();
        Log.i("UserData", "New instance of UserData created, UUID: " + UUID);
    }
    public static UserData instantiate(Context ctx) {
        UserData ud = new UserData(ctx);
        if(FILE.exists()) {
            Log.i("UserData", "User Data found, loading...");
            ud = ud.loadData(ctx);
        } else {
            Log.i("UserData", "User Data not found, using defaults...");
        }
        return ud;
    }

    //maybe?
    public int getUUID() {
        return UUID;
    }
    private void createUUID() {
        this.UUID = (int) (Math.random() * Integer.MAX_VALUE);
    }

    /*|=====================|
      |  First Run Methods  |
      |=====================|*/
    public boolean getFirstRun() {
        return firstRun;
    }
    public void setFirstRun(boolean firstRun) {
        Log.i("UserData", "firstRun set to " + firstRun);
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
        String debug = "";
        for (Mood m : moods) {
            debug += m.getMoodValue() + " ";
        }
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
        Log.i("UserData/Mood", "Mood flushed to array, value: " + today.getMoodValue());
        moods.add(today);
        today = new Mood();
    }

    /*|================|
      |  Data Methods  |
      |================|*/
    public void saveData(UserData ud, Context ctx) {
        if (!FILE.exists()) {
            try {
                FILE.createNewFile();
                Log.i("UserData", "New UserData file created at " + FILE.getPath());
            } catch (IOException e) {
                Log.e("UserData", "Failed to create UserData file");
                e.printStackTrace();
            }
        }
        try {
            gson = new GsonBuilder().create();
            String json = gson.toJson(ud);
            FileOutputStream fos = new FileOutputStream(FILE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.append(json);
            osw.close();
            fos.close();
            Log.i("UserData", "User Data Saved. UUID: " +  UUID);
        } catch (IOException e) {
            Log.e("UserData", "User Data Save Failed");
            e.printStackTrace();
        }
    }
    public UserData loadData(Context ctx) {
        UserData ud;
        gson = new GsonBuilder().create();
        if (FILE.exists()) {
            try {
                FileInputStream fis = new FileInputStream(FILE);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String cLine = "";
                String json = "";
                while ((cLine = br.readLine()) != null) {
                    json += cLine;
                }
                br.close();
                fis.close();
                ud = gson.fromJson(json, UserData.class);
                Log.i("UserData", "User Data Loaded for UUID: " + ud.getUUID());
            } catch(IOException e) {
                Log.e("UserData", "User Data Load Failed - Creating new instance...");
                e.printStackTrace();
                ud = new UserData(ctx);
                saveData(ud, ctx);
            }
        } else {
            ud = new UserData(ctx);
            saveData(ud, ctx);
            Log.e("UserData", "UserData file does not exist, creating file and new instance...");
        }
        return ud;
    }
}
