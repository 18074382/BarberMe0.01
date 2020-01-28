package com.example.barberme001;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class  SignUp_Login extends AppCompatActivity {

    EditText etName, etEmail, etPassword;
    Button button_register;

    final String url_Register = "https://barberme.000webhostapp.com/register_user.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up__login);

        etName = (EditText) findViewById(R.id.editText_name);
        etEmail = (EditText) findViewById(R.id.editText_email);
        etPassword = (EditText) findViewById(R.id.editText_password);
        button_register = (Button) findViewById(R.id.button_register);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = etName.getText().toString();
                String Email = etEmail.getText().toString();
                String Password = etPassword.getText().toString();
                new RegisterUser().execute(Name, Email, Password);

            }
        });

    }
    public class RegisterUser extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String Name = strings[0];
            String Email = strings[1];
            String Password = strings[2];
            String finalURL = url_Register + "?user_name=" + Name + "&user_id=" + Email + "&user_Password=" + Password;

            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(finalURL)
                    .get()
                    .build();
            Response response = null;

            try{
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful()){
                    String result = response.body().string();
                    if(result.equalsIgnoreCase("User registered successfully")){
                        Toast.makeText(SignUp_Login.this, "Register successful", Toast.LENGTH_LONG).show();
                        Intent i = new Intent (SignUp_Login.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    }else if(result.equalsIgnoreCase("User already exists")){
                        Toast.makeText(SignUp_Login.this, "User already", Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(SignUp_Login.this, "oops! please try again", Toast.LENGTH_LONG).show();

                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }


            return null;
        }
    }


}


