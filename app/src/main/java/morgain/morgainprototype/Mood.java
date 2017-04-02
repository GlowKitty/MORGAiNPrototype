package morgain.morgainprototype;

import java.util.Calendar;
import java.util.Date;

public class Mood {
    private double mood;
    //private Date date;
    private Calendar date;

    public Mood() {
        mood = 5;
        date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 0);
    }

    public void modifyMood(double mod) {
        mood += mod;
    }
    public double getMood() {
        return mood;
    }
}