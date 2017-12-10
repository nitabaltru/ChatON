package nitabaltru.tp2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by nitabaltru on 04/12/2017.
 */

public class UserStorage {

    public static final String USER_NAME = "USER_NAME";
    public static final String USER_EMAIL = "USER_EMAIL";

    public static void saveUserInfo(Context context, String name, String email) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("USER_NAME", name);
        editor.putString("USER_EMAIL", email);
        editor.apply();
    }


    public static User getUser(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String name = sharedPreferences.getString(USER_NAME, "noName");
        String email = sharedPreferences.getString(USER_EMAIL, "noEmail");
        return new User(name, email);
    }
}
