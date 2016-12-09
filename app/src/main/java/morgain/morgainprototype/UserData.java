package morgain.morgainprototype;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class UserData {

    public UserData() {

    }

    public void saveData(UserData ud, Context ctx) {
        String FILENAME = "MORGAiN_user_data";
        try {
            FileOutputStream fos = ctx.openFileOutput(FILENAME, Context.MODE_PRIVATE);

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ud);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
