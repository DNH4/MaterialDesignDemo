package com.example.hdang.materialdesigndemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by hdang on 2/29/2016.
 */
public class MaterialDesignDemoActivity extends AppCompatActivity {
    private View mBlueFill;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);

        //START share Element transition

        final Button transitionButton = (Button) findViewById(R.id.transition_button);
        final TextView transitionText = (TextView) findViewById(R.id.transition_text);

        Intent i = new Intent(this, TransitionButtonActivity.class);
        transitionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), TransitionButtonActivity.class);
                Pair<View, String> p1 = Pair.create((View)transitionButton,"tButton");
                Pair<View, String> p2 = Pair.create((View)transitionText,"tText");
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(MaterialDesignDemoActivity.this,p1,p2);
                        //makeSceneTransitionAnimation(MaterialDesignDemoActivity.this, (View)transitionButton,"tButton");
                startActivity(i,options.toBundle());
            }
        });
        // FIN -- share element transition

        // START -- Test for Circular reveal
        mBlueFill = this.findViewById(R.id.blue_fill);
        mBlueFill.setVisibility(View.INVISIBLE);
        View view = this.findViewById(R.id.activity_material_design_view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] clickCoords = new int[2];

                //find the location fo click on the screen
                v.getLocationOnScreen(clickCoords);

                //Tweak that location so that it points at the center of the view, not the corner
                clickCoords[0] += v.getWidth()/2;
                clickCoords[1] += v.getHeight()/2;

                if(mBlueFill.getVisibility() == View.INVISIBLE){
                    performRevealAnimation(mBlueFill, clickCoords[0],clickCoords[1]);
                }else{
                    performUnrevealAnimation(mBlueFill, clickCoords[0], clickCoords[1]);
                }


            }
        });
        // END -- TEst for Circular reveal

        Button moreMaterial = (Button) findViewById(R.id.more_activity);
        moreMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),MoreMaterialActivity.class);
                startActivity(i);
            }
        });
    }

    public void performRevealAnimation(View view, int screenCenterX, int screenCenterY){
        // Find the center relative to the view that will be animated
        int[] animatingViewCoords = new int[2];
        view.getLocationOnScreen(animatingViewCoords);

        int centerX = screenCenterX-animatingViewCoords[0];
        int centerY = screenCenterY-animatingViewCoords[1];

        //find the maximum radius
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int maxRadius = size.y;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setVisibility(View.VISIBLE);
            ViewAnimationUtils.createCircularReveal(view, centerX, centerY, 0, maxRadius)
                    .setDuration(1000)
                    .start();

        }
    }

    public void performUnrevealAnimation(View view2, int screenCenterX, int screenCenterY) {
        final View view= view2;


        // Find the center relative to the view that will be animated
        int[] animatingViewCoords = new int[2];
        view.getLocationOnScreen(animatingViewCoords);

        int centerX = screenCenterX - animatingViewCoords[0];
        int centerY = screenCenterY - animatingViewCoords[1];

        //find the maximum radius
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int maxRadius = size.y;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Animator anim= ViewAnimationUtils.createCircularReveal(view, centerX, centerY, maxRadius, 0)
                    .setDuration(1000);

            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setVisibility(View.INVISIBLE);
                }
            });

            anim.start();
        }
    }


}


