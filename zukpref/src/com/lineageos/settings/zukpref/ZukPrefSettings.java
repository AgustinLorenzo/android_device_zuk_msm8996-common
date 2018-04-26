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

	private SwitchPreference mEnableQC;
	private SwitchPreference mBatterySave;

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
			
        }
	// Control BatterySave
    private void setBatterySave(boolean value) {
		if(value) {
			SystemProperties.set(BATTERY_SYSTEM_PROPERTY, "1");
		} else {
			SystemProperties.set(BATTERY_SYSTEM_PROPERTY, "0");
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
	}
      	return false;
    }

}
