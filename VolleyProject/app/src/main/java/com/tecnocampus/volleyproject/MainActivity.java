package com.tecnocampus.volleyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView jsonResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonResult = findViewById(R.id.json_result);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://jsonplaceholder.typicode.com/users";
        fetchData(url, queue);
    }

    private void fetchData(String url, RequestQueue queue) {
        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject firstUser = response.getJSONObject(0);
                            jsonResult.setText(firstUser.toString());
                        } catch (Exception e) {
                            Log.d("CustomTag", "Error parsing json array");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("CustomTag", "Error in request: " + error.getMessage());
                    }
                }
        );
        queue.add(stringRequest);
    }
}
