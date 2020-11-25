package com.example.animation;

import android.animation.Animator;
import android.os.Bundle;
import android.util.Log;
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
        imageView.animate()
                .scaleX(2)
                .scaleY(2)
                .rotationX(180)
                .rotationY(180)
                .translationX(200)
                .translationY(200)
                .setDuration(2000)
                //can set listener for animations on older devices, small fraction of devices now
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        Log.i("Animation", "OnAnimationStart");
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        Log.i("Animation", "OnAnimationEnd");
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                        Log.i("Animation", "OnAnimationCancel");
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {
                        Log.i("Animation", "OnAnimationRepeat");
                    }
                });
    }

}