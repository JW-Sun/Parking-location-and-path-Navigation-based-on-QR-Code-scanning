package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class qidongActivity extends AppCompatActivity {
private ImageView welcomeimg=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qidong);
        welcomeimg=(ImageView)this.findViewById(R.id.welcome_img);
        AlphaAnimation anima=new AlphaAnimation(0.3f,1.0f);
        anima.setDuration(2000);
        welcomeimg.startAnimation(anima);
        anima.setAnimationListener(new AnimationImpl());
    }

    private class AnimationImpl implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            welcomeimg.setBackgroundResource(R.drawable.ting);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            skip();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    private void skip() {
        startActivity(new Intent(this,LoginAndRegisterActivity.class));
        finish();
    }

}
