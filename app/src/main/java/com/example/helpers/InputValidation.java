package com.example.helpers;

import android.app.Activity;
import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class InputValidation {
    private Context context;


    //constructor
    public InputValidation(Context context) {
        this.context = context;
    }


    //check if input is filled
    public boolean isInputFilled(TextInputEditText text, TextInputLayout layout, String message) {
        String value = text.getText().toString().trim();
        if (value.isEmpty()) {
            text.setError(message);
            hideKeyboardFrom(text);
            return false;
        } else {
            layout.setErrorEnabled(false);
        }
        return true;
    }

    //check for valid email
    public boolean isEmailValid(TextInputEditText text, TextInputLayout layout, String message) {
        String value = text.getText().toString().trim();
        if (value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            text.setError(message);
            hideKeyboardFrom(text);
            return false;
        } else {
            layout.setErrorEnabled(false);
        }
        return true;
    }

    //do two text fields match
    public boolean doTextMatch(TextInputEditText text1, TextInputEditText text2, TextInputLayout layout, String message) {
        String value1 = text1.getText().toString().trim();
        String value2 = text2.getText().toString().trim();

        if(!value1.contentEquals(value2)) {
            layout.setError(message);
            hideKeyboardFrom(text2);
            return false;
        } else {
            layout.setErrorEnabled(false);
        }
        return true;
    }

    private void hideKeyboardFrom(View view) {
        InputMethodManager imm  = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
