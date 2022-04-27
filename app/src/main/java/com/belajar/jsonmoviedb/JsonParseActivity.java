package com.belajar.jsonmoviedb;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonParseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_parse);
        getSupportActionBar().setTitle("JSon");
        String jsonStr = getListData();
        try {
            ArrayList<HashMap<String,String>> userList = new ArrayList<>();
            ListView lv = (ListView) findViewById(R.id.user_list);
            JSONObject jobj = new JSONObject(jsonStr);
            JSONArray jsonArray = jobj.getJSONArray("user");
            for (int i = 0; i < jsonArray.length(); i++){
                HashMap<String, String> user = new HashMap<>();
                JSONObject obj = jsonArray.getJSONObject(i);
                user.put("name", obj.getString("name"));
                user.put("designation", obj.getString("designation"));
                user.put("location", obj.getString("location"));
                userList.add(user);
            }
            ListAdapter adapter = new SimpleAdapter(JsonParseActivity.this,userList,
                    R.layout.list_row, new String[]{"name","designation","location"},
                    new int[]{R.id.name, R.id.designation, R.id.location});
            lv.setAdapter(adapter);
        } catch (JSONException ex){
            Log.e("JsonParser Example","unexpected JSOn exception", ex);
        }
    }
    private String getListData(){
        String jsonStr = "{\"users\" :["+
                "{\"name\":\"Dimas Rizqi Agung Pratama\",\"designation\":\"Leader\",\"location\":\"Jember\"}"+
                "{\"name\":\"Agung Pratama\",\"designation\":\"Vice Leader\",\"location\":\"Tanggul\"}"+
                "{\"name\":\"DOI\",\"designation\":\"Secretary\",\"location\":\"My Heart\"}]}";
        return jsonStr;
    }
}
