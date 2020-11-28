package com.example.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private RelativeLayout canvas;
    private static final String TAG = "AnimationEvents";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.faceIcon);
        canvas = findViewById(R.id.animationCanvas);
    }

    public void onButtonClick(View v) {
        //set the bottom of the animation, so the bottom of the animation
        //lands at the bottom of the screen, don't want off the screen
        int screenHeight = canvas.getHeight();
        int targetY = screenHeight - imageView.getHeight();

        ObjectAnimator animator = ObjectAnimator.ofFloat(
                imageView, "y", 0, targetY)
                .setDuration(3000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animator.setInterpolator(new BounceInterpolator());
        }

        animator.setRepeatCount(2); //means running animation 3 times (original + 2)
        animator.addUpdateListener(animation -> {
            //passing a value animator so can find where you are and current value in animation
            Log.i(TAG, "onAnimationUpdate: " + animation.getAnimatedValue());
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Log.i(TAG, "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Log.i(TAG, "onAnimationEnd: ");
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();

    }

}