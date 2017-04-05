package morgain.morgainprototype;

import android.content.Context;
import android.support.v4.app.Fragment;
import java.util.Random;

public class MoodQuestions {
    private static final int NUM_QUESTIONS = 5;
    private static boolean[] used = new boolean[NUM_QUESTIONS];
    private Random rn = new Random();
    private Context ctx;
    private static int rnd;


    public MoodQuestions(Context ctx) {
        this.ctx = ctx;
        for (int i = 0; i < used.length; i++) {//this is redundant I think; but that is ok
            used[i] = false;
        }
    }

    public Fragment getNextQuestion() throws NullPointerException {
        int all = 0;
        rnd = rn.nextInt(5);
        Fragment f = null;

        for (boolean b : used) {
            if (b) {
                all++;
            }
        }
        if (all >= used.length) {
            return null;
        }

        switch (rnd) {
            case 0:
                if (used[0]) {
                    f = getNextQuestion();
                } else {
                    used[0] = true;
                    f = MoodColor.newInstance();
                }
                break;
            case 1:
                if (used[1]) {
                    f = getNextQuestion();
                } else {
                    used[1] = true;
                    f = MoodAnimal.newInstance();
                }
                break;
            case 2:
                if (used[2]) {
                    f = getNextQuestion();
                } else {
                    used[2] = true;
                    f = MoodWeather.newInstance();
                }
                break;
            case 3:
                if (used[3]) {
                    f = getNextQuestion();
                } else {
                    used[3] = true;
                    f = MoodTimeOfDay.newInstance();
                }
                break;
            case 4:
                if (used[4]) {
                    f = getNextQuestion();
                } else {
                    used[4] = true;
                    f = MoodScene.newInstance();
                }
                break;
        }
        return f;
    }
}
