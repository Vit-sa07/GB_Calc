package com.example.gb_calculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity implements Constants {

    public static final String EXPRESSION = "EXPRESSION";
    private static StringBuffer expression = new StringBuffer();
    private TextView indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme());
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.button_1);
        Button btn2 = findViewById(R.id.button_2);
        Button btn3 = findViewById(R.id.button_3);
        Button btn4 = findViewById(R.id.button_4);
        Button btn5 = findViewById(R.id.button_5);
        Button btn6 = findViewById(R.id.button_6);
        Button btn7 = findViewById(R.id.button_7);
        Button btn8 = findViewById(R.id.button_8);
        Button btn9 = findViewById(R.id.button_9);
        Button btn0 = findViewById(R.id.button_0);
        Button btnPoint = findViewById(R.id.button_point);
        Button sumBtn = findViewById(R.id.button_sum);
        Button subBtn = findViewById(R.id.button_sub);
        Button multiplicationBtn = findViewById(R.id.button_multiplication);
        Button divisionBtn = findViewById(R.id.button_division);
        Button acBtn = findViewById(R.id.button_ac);
        Button resultBtn = findViewById(R.id.button_result);
        indicator = findViewById(R.id.indicator);

        if (savedInstanceState != null && savedInstanceState.containsKey(EXPRESSION)) {
            expression = (StringBuffer) savedInstanceState.getSerializable(EXPRESSION);
            indicator.setText(String.valueOf(expression));
        }

        btn1.setOnClickListener(view -> clickOnTheNumberButton(btn1));

        btn2.setOnClickListener(view -> clickOnTheNumberButton(btn2));

        btn3.setOnClickListener(view -> clickOnTheNumberButton(btn3));

        btn4.setOnClickListener(view -> clickOnTheNumberButton(btn4));

        btn5.setOnClickListener(view -> clickOnTheNumberButton(btn5));

        btn6.setOnClickListener(view -> clickOnTheNumberButton(btn6));

        btn7.setOnClickListener(view -> clickOnTheNumberButton(btn7));

        btn8.setOnClickListener(view -> clickOnTheNumberButton(btn8));

        btn9.setOnClickListener(view -> clickOnTheNumberButton(btn9));

        btn0.setOnClickListener(view -> clickOnTheNumberButton(btn0));

        btnPoint.setOnClickListener(view -> clickOnTheNumberButton(btnPoint));

        sumBtn.setOnClickListener(view -> {
            expression.append("+");
            indicator.setText(String.valueOf(expression));
        });

        subBtn.setOnClickListener(view -> {
            expression.append("-");
            indicator.setText(String.valueOf(expression));
        });

        multiplicationBtn.setOnClickListener(view -> {
            expression.append("*");
            indicator.setText(String.valueOf(expression));
        });

        divisionBtn.setOnClickListener(view -> {
            expression.append("/");
            indicator.setText(String.valueOf(expression));
        });

        acBtn.setOnClickListener(view -> {
            expression = new StringBuffer();
            indicator.setText(String.valueOf(expression));
        });

        resultBtn.setOnClickListener(view -> {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
            String resultExpression = null;
            try {
                resultExpression = (String.valueOf(engine.eval(expression.toString())));
            } catch (ScriptException e) {
                Log.d("Calculator", " ScriptEngine error: " + e.getMessage());
            }
            indicator.setText(resultExpression);
            expression = new StringBuffer();
        });
    }

    private void clickOnTheNumberButton(Button button) {
        expression.append(button.getText());
        indicator.setText(String.valueOf(expression));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(EXPRESSION, expression);
    }

    private int getAppTheme() {
        return codeStyleToRStyleId(getCodeStyle(FIRST_THEME));
    }

    private int getCodeStyle(int defaultCodeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NAME_SHARED_PREFS, MODE_PRIVATE);
        return sharedPref.getInt(APP_THEME, defaultCodeStyle);
    }

    private int codeStyleToRStyleId(int codeStyle) {
        switch (codeStyle) {
            case SECOND_THEME:
                return R.style.Theme_Gb_calculatorDark;
            case FIRST_THEME:
            default:
                return R.style.Theme_Gb_calculatorLight;
        }
    }
}