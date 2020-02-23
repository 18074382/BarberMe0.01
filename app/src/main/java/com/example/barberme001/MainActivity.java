package com.example.barberme001;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity  {

    public static final String EXTRA_MESSAGE = "com.example.barberme001.MESSAGE";


    EditText etEmail, etPassword;
    TextView tvRegister;
    Button btnLogin;

    final String url_Login = "https://barberme.000webhostapp.com/login_user.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail =  (EditText) findViewById(R.id.editText1);
        etPassword = (EditText) findViewById(R.id.editText2);
        btnLogin = (Button) findViewById(R.id.login);
        tvRegister = (TextView) findViewById(R.id.register);



        tvRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, SignUp.class);
                startActivity(i);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = etEmail.getText().toString();
                String Password = etPassword.getText().toString();

                new loginUser().execute(Email, Password);
            }
        });

    }
    public class loginUser extends AsyncTask<String ,Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String Email = strings[0];
            String Password = strings[1];

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("user_id", Email)
                    .add("user_password", Password)
                    .build();

            Request request = new Request.Builder()
                    .url(url_Login)
                    .post(formBody)
                    .build();
            Response response = null;
            try{
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful()){
                    String result = response.body().string();
                    if(result.equalsIgnoreCase("login")){
                        Intent i = new Intent(MainActivity.this, DashboardActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this,
                                "Email or Password mismatches!", Toast.LENGTH_SHORT).show();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

    public void getStarted(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}
