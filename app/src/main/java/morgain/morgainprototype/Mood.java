package morgain.morgainprototype;

import java.util.Calendar;

public class Mood {
    private double mood;
    //private Date date;
    private Calendar date;

    public Mood() {
        mood = 0;
        date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 0);
    }

    public void modifyMood(double mod) {
        mood += mod;
    }
    public double getMoodValue() {
        return mood;
    }

    public Calendar getDate() {
        return date;
    }

    public boolean isSameDay(Mood m) {
        if (date.equals(m.getDate())) {
            return true;
        }
        return false;
    }
}