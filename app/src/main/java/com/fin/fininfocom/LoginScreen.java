package com.fin.fininfocom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fin.fininfocom.utils.InternetConnection;
import com.fin.fininfocom.utils.MySharedPreferences;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginScreen extends AppCompatActivity {
    EditText user_name;
    TextInputEditText user_password;
    TextView sign_in;
    ProgressBar progress_bar;
    String userName, password;
    MySharedPreferences mySharedPreferences ;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        getSupportActionBar().hide();
        if (!InternetConnection.isNetworkAvailable(this)){
            InternetConnection.networkDialog(this);
            return;
        }
        user_name = findViewById(R.id.user_name);
        user_password = findViewById(R.id.user_password);
        sign_in = findViewById(R.id.sign_in);
        progress_bar = findViewById(R.id.progress_bar);
        mySharedPreferences = new MySharedPreferences(LoginScreen.this);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress_bar.setVisibility(View.VISIBLE);
                userName = user_name.getText().toString();
                password = user_password.getText().toString();
                if (userName.equals("")){
                    Toast.makeText(LoginScreen.this, "Please enter User Name", Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                if (userName.length()<10){
                    Toast.makeText(LoginScreen.this, "Username must be 10 characters", Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                if (!isValidPassword(password)){
                    Toast.makeText(LoginScreen.this, "Password must be 7 Characters with 1UpperCase Alphabet and 1SpecialCharacter and Numeric", Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                progress_bar.setVisibility(View.GONE);
                mySharedPreferences.setUserName(userName);
                mySharedPreferences.setLoginStatus(true);
                Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{7,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }
}