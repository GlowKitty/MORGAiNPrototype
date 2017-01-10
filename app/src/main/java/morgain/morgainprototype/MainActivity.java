package morgain.morgainprototype;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int[] spriteSequence = {
            R.drawable.waving,
            R.drawable.smilingresting,
            R.drawable.largegrin,
            R.drawable.questioning,
            R.drawable.questioning, //5
            R.drawable.largegrin,
            R.drawable.smilingresting,
            R.drawable.largegrin,
            R.drawable.largegrin,
            R.drawable.questioning,//10
            R.drawable.questioning,
            R.drawable.smilingresting,
            R.drawable.smilingresting,
            R.drawable.serious,
            R.drawable.smilingresting,//15
            R.drawable.largegrin,
            R.drawable.questioning
    };
    private static String[] dialogSequence = {
            "Hello! My name is MORGAiN!",
            "I was created as a guide for those in need.",
            "But most importantly, I am here to be a friend!",
            "Now, what is your name?",
            "*User enters name here*", //5
            "What a lovely name you have!",
            "My name is MORGAiN, as I said before!",
            "That stands for Mood Organizer Referrals Guidance Artificial Intelligence Nurturing.",
            "Which are all things I am capable of!",
            "Now I know this might be a sensitive topic, but what gender do you identify as?",//10
            "*User selects from a dropdown menu*",
            "I appreciate your honesty! \nGender is important to you humans and you should stick up for what you believe in!",
            "I myself do not have a gender, so refer to me as whatever you'd like to!",
            "This is to reassure that you can have the full experience, without gender confining or influencing opinions of me.",
            "Now, $name, how about we get to know each other!",//15
            "I have a few questions for you, and then, you can ask me a few questions!",
            "Though, if you would like to move right along, dont let me stop you!"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView  t = (TextView)  findViewById(R.id.textView);
        ImageView v = (ImageView) findViewById(R.id.imageView);
        final Button b1 = (Button) findViewById(R.id.button);
        final Button b2 = (Button) findViewById(R.id.button2);

        SpriteChat ic = new SpriteChat(t, v, spriteSequence, dialogSequence);
        ic.startChat();

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);

                b2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        tutorial();
                    }
                });
            }
        }, ic.getTotalWait());
    }

    public void tutorial() {
        Intent in = new Intent(this, Tutorial.class);
        startActivity(in);
    }
}