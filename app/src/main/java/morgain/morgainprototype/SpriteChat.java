package morgain.morgainprototype;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.widget.*;

public class SpriteChat {
    private TextView t;
    private ImageView v;
    private final int WAITTIME = 500;//750;
    private final int CHARTIME = 15;
    private int totalWait = 0;
    private TypedArray spriteSequence;
    private String[] dialogSequence;
    private Runnable set;
    private Runnable saying;
    private final Handler h;
    private final View.OnClickListener skip = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            skip();
        }
    };
    private MainActivity m;
    private int id = 0;
    private UserData ud;
    private String name = "";

    public SpriteChat(TextView t, ImageView v, TypedArray spriteSequence, String[] dialogSequence) {
        this.t = t;
        this.v = v;
        t.setOnClickListener(skip);
        v.setOnClickListener(skip);
        this.spriteSequence = spriteSequence;
        this.dialogSequence = dialogSequence;
        this.h = new Handler();
        ud = new UserData();
    }

    public void setMainActivity(MainActivity m) {
        this.m = m;
        ud = ud.loadData(m.getApplicationContext());
        if (ud == null) {
            ud = new UserData();
            System.out.println("UserData load failed");
        }
        name = ud.getFirstName();
    }

    public void startChat() {
        int nextTime = 0;
        for (int i = 0; i < dialogSequence.length; i++) {
            dialogSequence[i] = dialogSequence[i].replaceAll("(_un_)", name);
        }

        if (spriteSequence.length() != dialogSequence.length) {
            t.setText("More sprites than dialog, check your arrays");
        } else {
            int sumTime = 0;
            for (int i = 0; i < spriteSequence.length(); i++) {
                nextTime = dialogSequence[i].length() * CHARTIME;
                setSprite(sumTime, spriteSequence.getDrawable(i), dialogSequence[i]);
                sumTime += nextTime + WAITTIME;
            }
            totalWait = sumTime - WAITTIME;
        }
        //m.setTotalWait(totalWait);
        m.changeUI(totalWait, id);

    }

    public void setResources(TypedArray spriteSequence, String[] dialogSequence, int id) {
        this.spriteSequence = spriteSequence;
        this.dialogSequence = dialogSequence;
        this.id = id;
    }

    public int getTotalWait() {
        return totalWait;
    }

    private void setSprite(final int time, final Drawable id, final String say) {
        set = new Runnable() {
            @Override
            public void run() {
                scrollText(say);
                v.setImageDrawable(id);
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

    public void skip() {//fix implementation, its super broke
        h.removeCallbacks(set);
        h.removeCallbacks(saying);
    }
}