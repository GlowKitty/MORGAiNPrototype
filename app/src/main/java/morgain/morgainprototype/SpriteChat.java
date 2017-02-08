package morgain.morgainprototype;

import android.os.Handler;
import android.view.View;
import android.widget.*;

public class SpriteChat {
    private TextView t;
    private ImageView v;
    private final int WAITTIME = 750;
    private final int CHARTIME = 25;
    private int totalWait = 0;
    private boolean introOver = false;
    private static int[] spriteSequence;
    private static String[] dialogSequence;
    private Runnable set;
    private Runnable saying;
    private final Handler h;
    private final View.OnClickListener skip = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            skip();
        }
    };

    public SpriteChat(TextView t, ImageView v, int[] spriteSequence, String[] dialogSequence) {
        this.t = t;
        this.v = v;
        //t.setOnClickListener(skip);
        //v.setOnClickListener(skip);
        this.spriteSequence = spriteSequence;
        this.dialogSequence = dialogSequence;
        this.h = new Handler();

    }

    public void startChat() {
        if (spriteSequence.length != dialogSequence.length) {
            t.setText("Wow, this is broken!");
        } else {
            int sumTime = 0;
            int nextTime;
            for (int i = 0; i < spriteSequence.length; i++) {
                nextTime = dialogSequence[i].length() * CHARTIME;
                setSprite(sumTime, spriteSequence[i], dialogSequence[i]);
                sumTime += nextTime + WAITTIME;
            }
            totalWait = sumTime;
        }
    }

    private void setSprite(final int time, final int id, final String say) {
        set = new Runnable() {
            @Override
            public void run() {
                scrollText(say);
                v.setImageResource(id);
            }
        };
        h.postDelayed(set, time);
    }
    private void scrollText(final String say) {
        int time = 0;
        String str = "";
        for (char c : say.toCharArray()) {
            str += c;
            sayText(str, time);
            time += CHARTIME;
        }
    }
    private void sayText(final String say, int time) {
        saying = new Runnable() {
            @Override
            public void run() {
                t.setText(say);
            }
        };
        h.postDelayed(saying, time);
    }

    public int getTotalWait() {
        return totalWait;
    }

    public void skip() {
        h.removeCallbacks(set);
        h.removeCallbacks(saying);
    }
}