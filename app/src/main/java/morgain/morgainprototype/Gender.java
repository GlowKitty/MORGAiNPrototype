package morgain.morgainprototype;

import android.content.Context;
import android.util.Log;

import java.io.Serializable;

public class Gender implements Serializable {
    private transient Context ctx;
    private int gender;
    //private String[] genderStrings;

    public Gender(Context ctx) {//deprecated probably
        //genderStrings = ctx.getResources().getStringArray(R.array.genders);
    }
    public Gender() {
        gender = 0; //set to default; default is male
    }
    public Gender(int gender) {
        this.gender = gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }

    @Override
    public String toString() {
        String[] genderStrings = MyApplication.getAppContext().getResources()
                .getStringArray(R.array.genders); //todo: this is untested, make sure this works
        String str = "";
        if (gender > genderStrings.length) {
            str += gender;
            Log.e("Gender", "int gender out of genderStrings array bounds, value: " + gender,
                    new ArrayIndexOutOfBoundsException());
        } else {
            str += genderStrings[gender];
        }
        return str;
    }
}
