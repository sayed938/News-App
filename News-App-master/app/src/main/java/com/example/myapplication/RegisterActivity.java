package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class RegisterActivity extends AppCompatActivity {
    Button register;
    EditText username,email,password,confirm_password;
    com.example.registration.DBHelper myDB;
    ImageView imageView;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView btn=findViewById(R.id.alreadyHaveAccount);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        register=findViewById(R.id.btnRegister);
        username=findViewById(R.id.inputUsername);
        email=findViewById(R.id.inputEmail);
        password=findViewById(R.id.inputPassword);
        confirm_password=findViewById(R.id.inputConformPassword);
        imageView=findViewById(R.id.back_button_register);
        myDB=new com.example.registration.DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String my_username=username.getText().toString();
                String my_email=email.getText().toString();
                String my_password=password.getText().toString();
                String my_confirm_password=confirm_password.getText().toString();
                if(my_username.isEmpty()){
                    Toast.makeText(getApplicationContext(), "please enter user name", Toast.LENGTH_LONG).show();
                }else if(my_email.isEmpty()){
                    Toast.makeText(getApplicationContext(), "please enter email", Toast.LENGTH_LONG).show();
                }else if(my_password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "please enter password", Toast.LENGTH_LONG).show();
                }else if(my_confirm_password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "please confirm password", Toast.LENGTH_LONG).show();
                }
                else {
                    if(my_password.equals(my_confirm_password)){
                        Boolean usercheckresult= myDB.checkusername(my_username);
                        if(usercheckresult==false){
                           Boolean reRequest= myDB.insertData(my_username,my_email,my_password);
                            if (reRequest==true){
                                Toast.makeText(getApplicationContext(), "Register successful.", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Register Failed.", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "user already exists. \n please sign In", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Password not matching", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this ,ListActivity.class);
                startActivity(intent);
            }
        });

    }
}
