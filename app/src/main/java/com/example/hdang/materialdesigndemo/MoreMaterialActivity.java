package com.example.hdang.materialdesigndemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by hdang on 2/29/2016.
 * Getting FLoating button and animation
 */
public class MoreMaterialActivity extends AppCompatActivity {

    private FloatingActionButton mFab;
    private String mSnackbarMsg;
    boolean mHideFab = false;


    private ViewGroup mRootView;

    ObjectAnimator mAnimator;
    View topView, bottomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Specify activity transition
            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            //set an exit transition
            getWindow().setExitTransition(new Explode()
                    .setDuration(1000)
                    .excludeTarget(getWindow().getDecorView().findViewById(R.id.action_bar),true));
            //getWindow().setEnterTransition(new Explode());

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_material);

        topView = (View) findViewById(R.id.viewA);
        bottomView = findViewById(R.id.viewB);
        mRootView = (ViewGroup) findViewById(R.id.rootView);


        mFab = (FloatingActionButton) findViewById(R.id.floating_action_button);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Snackbar.make(v, "TEST", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() { //show action on the right side **if null doesn't display
                            @Override
                            public void onClick(View v) {
                                Snackbar.make(v,"My Action",Snackbar.LENGTH_SHORT).show();


                            }
                        }).show();*/


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    fabAnimation();//start moving
                }else {
                    //start new activity
                    Intent i = new Intent(getApplicationContext(),TransitionActivity.class);
                    startActivity(i);
                }




            }
        });
    }

    public void fabAnimation(){
        //Animate curve motion
        //*** Snackbar movement will interfere with the animation (Making it drop down to the original Y position

        //fab position
        float fabX = mFab.getX();
        float fabY = mFab.getY();

        //center position end point
        float centerX = topView.getHeight()/2+100;
        float centerY = topView.getWidth()/2;

        //control point
        float cX = centerX;
        float cY = fabY;

        //create Path
        final Path path = new Path();
        path.moveTo(fabX,fabY);
        path.quadTo(cX, cY, centerX, centerY);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //2nd check to suppress error throw by compiler
            mAnimator = ObjectAnimator.ofFloat(mFab,View.X,View.Y,path);
        }
        mAnimator.setDuration(1000);
        mAnimator.start();

        //start activity after
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //start new activity
                Intent i = new Intent(getApplicationContext(), TransitionActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(i, ActivityOptions.makeSceneTransitionAnimation(MoreMaterialActivity.this).toBundle());
                    //startActivity(i);
                }
            }
        });

    }

}
