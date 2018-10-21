/*
 *  ZukPref Extras Settings Module
 *  Made by @andr68rus 2017
 *  Modified by AgustinLorenzo to ZukPref
 */

package com.lineageos.settings.zukpref;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.os.Build;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import android.util.Log;
import android.os.SystemProperties;
import java.io.*;
import android.preference.ListPreference;

public class ZukPrefSettings extends PreferenceActivity implements OnPreferenceChangeListener {
	private static final boolean DEBUG = false;
	private static final String TAG = "ZukPref";
	private static final String ENABLE_BATTERY_MODE = "battery_mode";
	private static final String BATTERY_SYSTEM_PROPERTY = "persist.battery.save";

	private static final String ENABLE_EXP_BATTERY_MODE = "experimental_battery_mode";
	private static final String EXP_BATTERY_SYSTEM_PROPERTY = "persist.experimental.battery.save";

	private static final String ENABLE_SOFTWARE_BUTTONS = "software_buttons";
	private static final String SOFTWARE_BUTTONS_PROPERTY = "qemu.hw.mainkeys";

	private SwitchPreference mBatterySave;
    private SwitchPreference mExpBatterySave;
	private SwitchPreference mSoftwareButtons;

    private Context mContext;
    private SharedPreferences mPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.zuk_settings);
        mContext = getApplicationContext();


        mBatterySave = (SwitchPreference) findPreference(ENABLE_BATTERY_MODE);
        mBatterySave.setChecked(SystemProperties.getBoolean(BATTERY_SYSTEM_PROPERTY, true));
        mBatterySave.setOnPreferenceChangeListener(this);
		
        mExpBatterySave = (SwitchPreference) findPreference(ENABLE_EXP_BATTERY_MODE);
        mExpBatterySave.setChecked(SystemProperties.getBoolean(EXP_BATTERY_SYSTEM_PROPERTY, true));
        mExpBatterySave.setOnPreferenceChangeListener(this);

        mSoftwareButtons = (SwitchPreference) findPreference(ENABLE_SOFTWARE_BUTTONS);
        mSoftwareButtons.setChecked(SystemProperties.getBoolean(SOFTWARE_BUTTONS_PROPERTY, true));
        mSoftwareButtons.setOnPreferenceChangeListener(this);
		
        }
	// Control BatterySave
    private void setBatterySave(boolean value) {
		if(value) {
			SystemProperties.set(BATTERY_SYSTEM_PROPERTY, "1");
		} else {
			SystemProperties.set(BATTERY_SYSTEM_PROPERTY, "0");
		}
    }

    // Control Experimental BatterySave
    private void setExpBatterySave(boolean value) {
		if(value) {
			SystemProperties.set(EXP_BATTERY_SYSTEM_PROPERTY, "1");
		} else {
			SystemProperties.set(EXP_BATTERY_SYSTEM_PROPERTY, "0");
		}
    }
	
    // Control Hardware Buttons
    private void setSoftwareButtons(boolean value) {
		if(value) {
			SystemProperties.set(SOFTWARE_BUTTONS_PROPERTY, "1");
		} else {
			SystemProperties.set(SOFTWARE_BUTTONS_PROPERTY, "0");
		}
    }
	
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final String key = preference.getKey();
        boolean value;
        String strvalue;
		if (ENABLE_BATTERY_MODE.equals(key)) {
			value = (Boolean) newValue;
			mBatterySave.setChecked(value);
			setBatterySave(value);
			return true;
	    } else if (ENABLE_EXP_BATTERY_MODE.equals(key)) {
			value = (Boolean) newValue;
			mExpBatterySave.setChecked(value);
			setExpBatterySave(value);
			return true;
		} else if (ENABLE_SOFTWARE_BUTTONS.equals(key)) {
			value = (Boolean) newValue;
			mSoftwareButtons.setChecked(value);
			setSoftwareButtons(value);
			return true;
		}

      	return false;
    }

}
