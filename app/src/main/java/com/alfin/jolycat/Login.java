package com.alfin.jolycat;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;

    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.etUsernameLogin);
        passwordEditText = findViewById(R.id.etPasswordLogin);

        users = getIntent().getParcelableExtra("users");

        Button loginButton = findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        //Textview when don't have account (register)
        TextView registerTextView = findViewById(R.id.tvRegisterNow);

        // Make "register now" clickable
        String registerText = "Don't have an account? Register now";
        SpannableString spannableString = new SpannableString(registerText);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // navigate to Register Activity
                Intent registerIntent = new Intent(Login.this, Register.class);
                startActivity(registerIntent);
            }
        };

        // Set clickable span only to the Register now text part
        spannableString.setSpan(clickableSpan, registerText.indexOf("Register now"), registerText.length(), 0);
        // Set the text and make it clickable
        registerTextView.setText(spannableString);
        registerTextView.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
    }

    private  boolean login() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        for(User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                showToast("Success login!");
//                Intent gotoDashboard = new Intent(Login.this, Dashboard.class);
//                startActivity(gotoDashboard);
                return true;
            }
            else {
                showToast("username or password is wrong!");
                return false;
            }
        }

        showToast("username not found!");
//        Intent gotoDashboard = new Intent(Login.this, Dashboard.class);
//        startActivity(gotoDashboard);
        return false;
    }

    //function to make simple make toast
    private  void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}

