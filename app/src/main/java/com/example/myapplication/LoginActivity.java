package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registration.DBHelper;

public class LoginActivity extends AppCompatActivity {
Button login;
EditText email,password;
com.example.registration.DBHelper myDB;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView btn=findViewById(R.id.textViewSignUp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, com.example.myapplication.RegisterActivity.class));
            }
        });
        login=findViewById(R.id.btnlogin);
        email=findViewById(R.id.inputEmail);
        password=findViewById(R.id.inputPassword);
        imageView=findViewById(R.id.back_button_login);
        myDB=new DBHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myEmail=email.getText().toString();
                String myPassword=password.getText().toString();
                if(myEmail.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_LONG).show();
                } else if (myPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_LONG).show();
                }
                else {
                    Boolean result =myDB.check_email_password(myEmail,myPassword);
                    if(result==true)
                    {
                        Intent intent =new Intent(getApplicationContext(),ListActivity.class);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Invalid Crediantials.", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
