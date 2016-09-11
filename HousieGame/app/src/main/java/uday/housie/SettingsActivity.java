package uday.housie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.CompoundButton;

public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    SwitchCompat settings_switch;
    SessonManager sm;
    private static final String TAG = "SettingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initdata();
    }



    public void initdata(){
        sm = new SessonManager(SettingsActivity.this);

        Log.e(TAG, "initdata: "+sm.getPermStoreData(AppStrings.Session.KEY_SWITCH) );

        settings_switch = (SwitchCompat) findViewById(R.id.settings_switch);
        settings_switch.setOnCheckedChangeListener(this);
        if(sm.getPermStoreData(AppStrings.Session.KEY_SWITCH).equals("1")){
            settings_switch.setChecked(true);
        }else {
            settings_switch.setChecked(false);
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.settings_switch:
                if(b){
                    sm.setPermStoreData(AppStrings.Session.KEY_SWITCH,"1");
                }else {
                    sm.setPermStoreData(AppStrings.Session.KEY_SWITCH,"0");
                }
                break;
        }
    }
}
