package morgain.morgainprototype;

import android.content.Context;
import android.support.v4.app.Fragment;
import java.util.Random;

public class MoodQuestions {
    private final int NUM_QUESTIONS = 5;
    private boolean[] used = new boolean[NUM_QUESTIONS];
    private Random rn = new Random();
    private Context ctx;


    public MoodQuestions(Context ctx) {
        this.ctx = ctx;
        for (int i = 0; i < used.length; i++) {//this is redundant I think; but that is ok
            used[i] = false;
        }
    }

    public Fragment getNextQuestion() {
        int rnd = rn.nextInt(4);
        int all = 0;
        Fragment f;
        f = HomeLowerMenu.newInstance();//in case it gets past the switch which it wont...

        for (boolean b : used) {
            if (b) {
                all++;
            }
        }
        if (all >= used.length) {
            f = HomeLowerMenu.newInstance(); //TODO: make a "finished with all 5 questions" fragment
        }

        switch(rnd) {
            case 0:
                if (used[0]) {
                    f = getNextQuestion();
                } else {
                    used[0] = true;
                    f = MoodColor.newInstance();
                }
                break;
            case 1:
                if (used[0]) {
                    f = getNextQuestion();
                } else {
                    used[0] = true;
                    f = MoodAnimal.newInstance();
                }
                break;
            case 2:
                if (used[0]) {
                    f = getNextQuestion();
                } else {
                    used[0] = true;
                    f = MoodWeather.newInstance();
                }
                break;
            case 3:
                if (used[0]) {
                    f = getNextQuestion();
                } else {
                    used[0] = true;
                    f = MoodTimeOfDay.newInstance();
                }
                break;
            case 4:
                if (used[0]) {
                    f = getNextQuestion();
                } else {
                    used[0] = true;
                    f = MoodScene.newInstance();
                }
                break;
        }
        return f;
    }
}
