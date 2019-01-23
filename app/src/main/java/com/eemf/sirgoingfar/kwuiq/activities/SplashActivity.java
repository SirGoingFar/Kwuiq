package com.eemf.sirgoingfar.kwuiq.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.eemf.sirgoingfar.kwuiq.R;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        showWelcomeText();

        switchScreen();
    }

    private void showWelcomeText() {
        List<String> splashText = new ArrayList<>();
        splashText.add("Data Subscription made easy");
        splashText.add("Bill payment at one click");
        splashText.add("Quick TV Subscription payment");
    }

    /**
     * check if there's a session (valid access token), go to dashboard or Register Activity
     */
    private void switchScreen() {

        //open Register Activity
        final Handler transitionHandler = new Handler();
        transitionHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent openLoginActivity = new Intent(SplashActivity.this, LoginActivity.class);
                activityStackClearFlagSetter(openLoginActivity);
                startActivity(openLoginActivity);
            }
        }, 3000);
    }
}
