package com.example.helpers;

import android.app.Activity;
import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class InputValidation {
    private Context context;


    //constructor
    public InputValidation(Context context) {
        this.context = context;
    }


    //check if input is filled
    public boolean isInputFilled(String text) {
        String value = text.trim();
        if (value.isEmpty()) {
            return false;
        }
        return true;
    }

    //check for valid email
    public boolean isEmailValid(String text) {
        String value = text.trim();
        if (value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            return false;
        }
        return true;
    }

    //do two text fields match
    public boolean doTextMatch(String text1, String text2) {
        String value1 = text1.trim();
        String value2 = text2.trim();

        if(!value1.contentEquals(value2)) {
            return false;
        }
        return true;
    }


}
