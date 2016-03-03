package com.example.hdang.materialdesigndemo;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Slide;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by hdang on 2/29/2016.
 */
public class TransitionButtonActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_button);
        Button transitionButton = (Button) findViewById(R.id.transition_button);
        transitionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportFinishAfterTransition();
            }
        });
    }
}
