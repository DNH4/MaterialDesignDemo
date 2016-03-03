package com.example.hdang.materialdesigndemo;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.Window;

/**
 * Created by hdang on 3/2/2016.
 */
public class TransitionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Specify activity transition
            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            //set an exit transition
            getWindow().setEnterTransition(new Explode()
                    .setDuration(1000)
                    .setStartDelay(1000)
                    .excludeTarget(getWindow().getDecorView().findViewById(R.id.action_bar), false));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
    }
}
