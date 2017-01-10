package morgain.morgainprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Tutorial extends AppCompatActivity {
    private static int[] spriteSequence = {

    };
    private static String[] dialogSequence = {

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        TextView t = (TextView)  findViewById(R.id.textView2);
        ImageView v = (ImageView) findViewById(R.id.imageView2);
        SpriteChat sc = new SpriteChat(t, v, spriteSequence, dialogSequence);
    }
}
