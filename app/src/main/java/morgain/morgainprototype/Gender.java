package morgain.morgainprototype;

import android.content.Context;
import android.util.Log;

import java.io.Serializable;

public class Gender implements Serializable {
    private transient Context ctx;
    private int gender;
    private String[] genderStrings;

    public Gender(Context ctx) {
        genderStrings = ctx.getResources().getStringArray(R.array.genders);
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }

    @Override
    public String toString() {
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
