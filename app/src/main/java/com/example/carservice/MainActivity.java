package com.example.carservice;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;
    DBHelper DB;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        loginButton=findViewById(R.id.loginButton);
        DB = new DBHelper(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(MainActivity.this,"Signin Fails", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(MainActivity.this,"Sign in Successful", Toast.LENGTH_SHORT).show();
                        Intent login = new Intent(MainActivity.this,Home.class);
                        startActivity(login);
                    }else
                    {
                        Toast.makeText(MainActivity.this,"Invalid ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        textView = (TextView)findViewById(R.id.signupText);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(MainActivity.this,Signup.class);
                startActivity(signup);
            }
        });

    }
}