package com.alfin.jolycat;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText, confirmPasswordEditText, phoneNumberEditText;
    private CheckBox cbRules;

    private ArrayList<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.etUsernameRegister);
        passwordEditText = findViewById(R.id.etPasswordRegister);
        confirmPasswordEditText = findViewById(R.id.etConfirmPassword);
        phoneNumberEditText = findViewById(R.id.etPhoneNumber);
        cbRules = findViewById(R.id.cbRules);

        Button registerButton = findViewById(R.id.btnRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        //Textview if have account (login)
        TextView loginTextView = findViewById(R.id.tvLoginNow);

        // Make "Login now" clickable
        String loginText = "Already have an account? Login now";
        SpannableString spannableString = new SpannableString(loginText);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // navigate to Login Activity
                Intent loginIntent = new Intent(Register.this, Login.class);
                startActivity(loginIntent);
            }
        };

        // Set clickable span only to the Login now text part
        spannableString.setSpan(clickableSpan, loginText.indexOf("Login now"), loginText.length(), 0);
        // Set the text and make it clickable
        loginTextView.setText(spannableString);
        loginTextView.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
    }

    private void register() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();
        String phoneNumber = phoneNumberEditText.getText().toString().trim();
        boolean isCheckbox = cbRules.isChecked();

        if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phoneNumber.isEmpty()) {
            showToast("All fields are required!");
            return;
        }

        if(username.length() < 8) {
            showToast("Username must be at least 8 characters long");
            return;
        }

        Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher usernameMatcher = usernamePattern.matcher(username);
        if(!usernameMatcher.matches()) {
            showToast("username must contain only alphanumeric characters");
            return;
        }

        if(password.length() < 5) {
            showToast("Password must be at least 5 characters long");
            return;
        }

        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&*+=]).*$");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if(!passwordMatcher.matches()) {
            showToast("Password must contain at least 1 letter, 1 number, and 1 symbol!");
            return;
        }

        if(!confirmPassword.equals(password)) {
            showToast("Password and confirm password must be equals!");
            return;
        }

        if(phoneNumber.length() < 8 || phoneNumber.length() > 20) {
            showToast("Phone number must be between 8 and 20 characters long");
            return;
        }

        if(!(phoneNumber.startsWith("0") || phoneNumber.startsWith("+"))) {
            showToast("Phone number must start with '0' or '+'");
            return;
        }

        Pattern phoneNumberPattern = Pattern.compile("^[0-9]+$");
        Matcher phoneNumberMatcher = phoneNumberPattern.matcher(phoneNumber.substring(1));
        if(!phoneNumberMatcher.matches()) {
            showToast("Phone number must only contain digits after the first character");
            return;
        }

        if(!isCheckbox) {
            showToast("Please agree to JolyCat's Terms of Service and Privacy Policy!");
            return;
        }

        //if success all validations
        String userID = UUID.randomUUID().toString();
        User newUser = new User(userID, username, password, phoneNumber);
        users.add(newUser); //store users data to array
        showToast("Registration succesful. User ID: " + userID);

        Intent successRegister = new Intent(Register.this, Login.class);
        successRegister.putExtra("users", users);
        startActivity(successRegister);
    }

    //function to make simple make toast
    private  void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}

