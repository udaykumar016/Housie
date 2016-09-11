package uday.housie;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lakki on 09-Jul-16.
 */
public class SessonManager {



    SharedPreferences preferences;

    SharedPreferences.Editor editor;

    private static final String PREFER_NAME = "Game";
    private static final String IS_USER_LOGIN = "login";

    int PRIVATE_MODE = 0;
    Context _context;


    public SessonManager(Context context) {
        preferences = context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        this._context = context;
        editor = preferences.edit();
        editor.commit();

    }

    public void createUserLoginSession() {
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.commit();
    }

    public boolean isUserLoggedIn() {
        return preferences.getBoolean(IS_USER_LOGIN, false);
    }

    public void logoutUser() {

        editor.clear();
        editor.commit();
    }


    public void setPermStoreData(String store_key, String store_id){
        editor.putString(store_key, store_id);
        editor.commit();
    }
    public String  getPermStoreData(String hkeye){
        return preferences.getString(hkeye, "");

    }
    public void clearPermStoreData(String hkey3){
        editor.putString(hkey3, "");
        editor.commit();
    }

}

