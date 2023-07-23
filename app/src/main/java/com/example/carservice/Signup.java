package com.example.carservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText repass;
    DBHelper DB;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repass = (EditText) findViewById(R.id.confirm_password);
        DB = new DBHelper(this);
        button = (Button) findViewById(R.id.signup_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String confirmpass = repass.getText().toString();
                if(user.equals("")|| pass.equals("")|| confirmpass.equals("") )
                    Toast.makeText(Signup.this,"Please enter all details",Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(confirmpass)) {
                        Boolean checkuser = DB.checusername(user);
                        if (checkuser == false) {
                            boolean insert = DB.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(Signup.this, "Data Saved", Toast.LENGTH_SHORT).show();
                                Intent signup1 = new Intent(Signup.this, MainActivity.class);
                                startActivity(signup1);
                            } else {
                                Toast.makeText(Signup.this, "Signup fails", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(Signup.this, "User already exist", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Signup.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}