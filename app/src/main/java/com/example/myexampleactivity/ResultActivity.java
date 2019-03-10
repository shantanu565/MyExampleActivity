package com.example.myexampleactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.InterruptibleChannel;

public class ResultActivity extends AppCompatActivity {
    TextView t1,t2,t3;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        Intent i2=getIntent();


        String s1=i2.getStringExtra("edt1");
        String s2=i2.getStringExtra("edt2");
        String s3=i2.getStringExtra("edt3");
        byte[] byteArray = getIntent().getByteArrayExtra("img");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        t1=(TextView)findViewById(R.id.tv21);
        t2=(TextView)findViewById(R.id.tv22);
        t3=(TextView)findViewById(R.id.tv23);
        imageView2=(ImageView)findViewById(R.id.iv21);

        imageView2.setImageBitmap(bmp);
        t1.setText(s1);
        t2.setText(s2);
        t3.setText(s3);

    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"Result Activity Paused",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"Result Activity Resumed",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Result Activity Stopped", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, " Result Activity Stopped", Toast.LENGTH_LONG).show();

    }
}
