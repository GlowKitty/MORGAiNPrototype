package morgain.morgainprototype;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
        MorgainFace.OnFragmentInteractionListener, GenderDropdown.OnFragmentInteractionListener {
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
            R.drawable.questioning};/*,
            R.drawable.smilingresting,
            R.drawable.smilingresting,
            R.drawable.serious,
            R.drawable.smilingresting,//15
            R.drawable.largegrin,
            R.drawable.questioning
    };*/
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
            "*User selects from a dropdown menu*"};/*,
            "I appreciate your honesty! \nGender is important to you humans and you should stick up for what you believe in!",
            "I myself do not have a gender, so refer to me as whatever you'd like to!",
            "This is to reassure that you can have the full experience, without gender confining or influencing opinions of me.",
            "Now, $name, how about we get to know each other!",//15
            "I have a few questions for you, and then, you can ask me a few questions!",
            "Though, if you would like to move right along, dont let me stop you!"
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            MorgainFace mf = MorgainFace.newInstance(spriteSequence, dialogSequence);
            mf.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mf).commit();
            mf.setMainActivity(this);
        }
    }

    public void changeUI() {//make this so it can change dynamically, how to though?
        GenderDropdown gd = GenderDropdown.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_lower, gd).commit();
    }

    @Override
    public void onSpinnerSelect() {
        //TODO: everything
    }

    @Override
    public void onTextTap(TextView t) {
        MorgainFace mf = (MorgainFace) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container_lower);
        if (mf != null) {
            mf.skip();
        }
    }
}