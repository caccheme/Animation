package com.example.animation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.faceIcon);
    }

    public void onButtonClick(View v) {
        //change x and y size of object simultaneously
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageView,
                "scaleX", 1f, 2f)
                .setDuration(1000);
        animatorX.start();

        ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageView,
                "scaleY", 1f, 2f)
                .setDuration(1000);
        animatorY.start();

    }

}