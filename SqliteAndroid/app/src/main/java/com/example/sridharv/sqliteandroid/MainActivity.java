package com.example.sridharv.sqliteandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private String arrayVal = "{\n" +
            "    \"name\" : \"Admin\",\n" +
            "    \"age\" : 36,\n" +
            "    \"rights\" : [\n" +
            "        {\"roleName\" : \"admin\", \"roleIds\" : [1,2,3] },\n" +
            "        {\"roleName\" : \"editor\", \"roleIds\" : [4,5,6] },\n" +
            "    ]   {\"roleName\" : \"contributor\", \"roleIds\" : [7,8,9]}\n" +
            "} ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            JSONObject jsonObject = new JSONObject(arrayVal);
            Log.e("name_val", jsonObject.getString("country"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
