package morgain.morgainprototype;

import android.os.Handler;
import android.widget.*;

public class IntroductoryConversation {
    private TextView t;
    private ImageView v;
    private static int[] spriteSequence = {
            R.drawable.waving, R.drawable.smilingresting, R.drawable.largegrin
    };
    private static String[] dialogeSequence = {
            "Hello! My name is MORGAiN", "I was created as a guide for those in need.",
            "But most importantly, I am here to be a friend!"
    };

    public IntroductoryConversation(TextView t, ImageView v) {
        this.t = t;
        this.v = v;
    }

    public void startConvo() {
        for (int i = 0; i < 3; i++) {
            setSprite(2000 * (i + 1), spriteSequence[i], dialogeSequence[i]);
        }
    }

    private void setSprite(int time, final int id, final String say) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                t.setText(say);
                v.setImageResource(id);
            }
        }, time);
    }
}
