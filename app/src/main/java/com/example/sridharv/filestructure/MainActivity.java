package com.example.sridharv.filestructure;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createFolderBtn = (Button) findViewById(R.id.createFolder);
        Button checkFolderBtn = (Button) findViewById(R.id.checkFolder);
        createFolderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Permission check
                if (!hasPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
                    // Permission ask
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 111);
                } else {
                    String isAvailable = checkIfOnedayAvailable("oneday");
                    if (isAvailable != null) {
                        String getChannel = checkIfOnedayAvailable("oneday/channel");
                        String getMember = checkIfOnedayAvailable("oneday/member");
                    }
                }

            }
        });
        checkFolderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File f = new File("/data/user/0/com.example.sridharv.filestructure/app_oneday/channel");
                if (f.isDirectory()) {
                    Toast.makeText(MainActivity.this, "Available", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Not Available", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Function for check permission already granted or not
    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    private String checkIfOnedayAvailable(String folderName) {

        File mydir = MainActivity.this.getDir(folderName, Context.MODE_PRIVATE); //Creating an internal dir;
        if (!mydir.exists()) {
            mydir.mkdirs();
            return mydir.getAbsolutePath().toString();
        } else {
            return mydir.getAbsolutePath().toString();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 111) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String isAvailable = checkIfOnedayAvailable("oneday");
                if (isAvailable != null) {
                    String getChannel = checkIfOnedayAvailable("oneday/channel");
                    String getMember = checkIfOnedayAvailable("oneday/member");
                }
            }
        }
    }
}
