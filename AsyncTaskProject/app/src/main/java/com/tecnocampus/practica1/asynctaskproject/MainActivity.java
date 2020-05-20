package com.tecnocampus.practica1.asynctaskproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements HttpHelper.HttpHelperResponseHandler {

    private TextView jsonContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonContext = findViewById(R.id.json_content);

        HttpHelper task = new HttpHelper();
        task.delegate = this;
        String url = "https://jsonplaceholder.typicode.com/users";
        task.execute(url);
    }

    @Override
    public void setHttpHelperResponse(String result) {
        jsonContext.setText(getAllNames(result));
    }

    private String getAllNames(String rawJson) {
        try {
            String result = "";
            JSONArray users = new JSONArray(rawJson);
            for (int i = 0; i < users.length(); i++){
                JSONObject currentUser = users.getJSONObject(i);
                if (currentUser.has("name")){
                    result += currentUser.getString("name") + "\n";
                } else {
                    Log.d("CustomTag", "currentUser does not have 'name'.");
                }
            }
            return result;
        } catch (Exception e) {
            Log.d("CustomTag", "Error parsing names " + e.getMessage());
        }
        return "SOMETHING WENT WRONG";
    }
}
