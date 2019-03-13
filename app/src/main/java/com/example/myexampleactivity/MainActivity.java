package com.example.myexampleactivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3;
    Button send,upload;
    ImageView imageView1;
    private static final int CAMERA_REQUEST=1100;
    private static final int CAMERA_CODE=100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1=(EditText)findViewById(R.id.edt1);
        e2=(EditText)findViewById(R.id.edt2);
        e3=(EditText)findViewById(R.id.edt3);
        send=(Button)findViewById(R.id.b2);
        upload=(Button)findViewById(R.id.b1);
        imageView1=(ImageView)findViewById(R.id.iv1);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                        requestPermissions(new String[]{Manifest.permission.CAMERA},CAMERA_CODE);
                    }else{
                        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(i,CAMERA_REQUEST);
                    }
                }
            }
        });


         }
    @Override
    protected void onPause() {
        super.onPause();
       Toast.makeText(this,"Activity Paused",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"Activity Resumed",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Activity Stopped", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==CAMERA_CODE){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"camera permission granted",Toast.LENGTH_LONG).show();
                Intent i2=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i2,CAMERA_REQUEST);
            }else {
                Toast.makeText(this,"Camera permission denied",Toast.LENGTH_LONG).show();
            }
    }}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if (requestCode==CAMERA_REQUEST && resultCode== Activity.RESULT_OK){
           Bitmap pic=(Bitmap)data.getExtras().get("data");
           imageView1.setVisibility(View.VISIBLE);
           imageView1.setImageBitmap(pic);
       }
    }
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
    public void sendData(){
        String s1=e1.getText().toString();
        String s2=e2.getText().toString();
        String s3=e3.getText().toString();

        if (s1.isEmpty()||s1.equals("")){
            Toast.makeText(this,"Please provide your username",Toast.LENGTH_LONG).show();
        }else if (s2.isEmpty()||s2.equals("")){
            Toast.makeText(this,"Please provide your email",Toast.LENGTH_LONG).show();
        }
        else if (s3.isEmpty()||s3.equals("")){
            Toast.makeText(this,"Please provide your phone",Toast.LENGTH_LONG).show();
        }else if(!s2.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){
            Toast.makeText(this,"Invalid email format",Toast.LENGTH_LONG).show();

        }else{

            Drawable drawable = imageView1.getDrawable();
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();


            Intent i = new Intent(MainActivity.this, ResultActivity.class);
            i.putExtra("edt1", s1);
            i.putExtra("edt2", s2);
            i.putExtra("edt3", s3);
            i.putExtra("img", b);
            Toast.makeText(this, "hello", Toast.LENGTH_LONG).show();
            startActivity(i);
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Activity Stopped", Toast.LENGTH_LONG).show();

    }
}
