package nitabaltru.tp2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * A class to save the user information
 * Created by nitabaltru on 04/12/2017.
 */

class UserStorage {

    /**
     * the user's name
     */
    private static final String USER_NAME = "USER_NAME";

    /**
     * the user's email
     */
    private static final String USER_EMAIL = "USER_EMAIL";

    /**
     * to set and save the user information
     * @param context the context
     * @param name the user's name
     * @param email the user's email
     */
    static void saveUserInfo(Context context, String name, String email) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("USER_NAME", name);
        editor.putString("USER_EMAIL", email);
        editor.apply();
    }

    /**
     * to get the user information
     * @param context the context
     * @return an instance of the User class
     */
    static User getUser(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String name = sharedPreferences.getString(USER_NAME, "noName");
        String email = sharedPreferences.getString(USER_EMAIL, "noEmail");
        return new User(name, email);
    }
}
