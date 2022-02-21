package com.example.gb_calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity implements Constants {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initThemeChooser();
    }

    private void initThemeChooser() {
        initRadioButton(findViewById(R.id.radioButtonFirstTheme),
                FIRST_THEME);
        initRadioButton(findViewById(R.id.radioButtonSecondTheme),
                SECOND_THEME);
    }

    private void initRadioButton(View button, final int codeStyle) {
        button.setOnClickListener(v -> {
            setAppTheme(codeStyle);
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NAME_SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(APP_THEME, codeStyle);
        editor.apply();
    }


}