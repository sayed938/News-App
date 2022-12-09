package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class workActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        imageView=findViewById(R.id.back_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(workActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }
}