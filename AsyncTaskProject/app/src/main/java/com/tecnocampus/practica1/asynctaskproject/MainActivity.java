package com.tecnocampus.practica1.asynctaskproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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
        jsonContext.setText(result);
    }
}
