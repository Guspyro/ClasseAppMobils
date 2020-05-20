package com.tecnocampus.glideproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        // DIFFERENT OPTIONS to show the image
        //loadResourceWithDefaultLibrary();
        loadResourceWithGlideLocal();
        //loadResourceWithGlideURL();
    }

    private void loadResourceWithDefaultLibrary() {
        imageView.setImageResource(R.drawable.lion);
    }

    private void loadResourceWithGlideLocal() {
        Glide.with(this).load(R.drawable.lion).into((imageView));
    }

    private void loadResourceWithGlideURL() { // Needs Internet Permission
        Glide.with(this).load("https://lionsclubs.co/Public/wp-content/uploads/2018/06/xcropped-lions-web.png.pagespeed.ic.pgwrox6ThR.jpg").into(imageView);
    }
}
