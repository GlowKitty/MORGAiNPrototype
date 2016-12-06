package morgain.morgainprototype;

import android.os.Handler;
import android.widget.*;

public class IntroductoryConversation {
    private static TextView t;
    private static ImageView v;
    public IntroductoryConversation(TextView t, ImageView v) {
        this.t = t;
        this.v = v;
    }

    public static void startConvo() {
        t.setText("Hello! My name is MORGAiN");
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //v.setImageDrawable(R.drawable.smilingresting);
            }
        }, 1000);
    }
}
