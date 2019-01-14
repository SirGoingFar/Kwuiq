package com.eemf.sirgoingfar.kwuiq.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eemf.sirgoingfar.kwuiq.R;

import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView txt = findViewById(R.id.tv_sign_in);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });
    }

    void openLoginActivity(){
        Intent openLoginActivity = new Intent(this, LoginActivity.class);
        activityStackClearFlagSetter(openLoginActivity);
        startActivity(openLoginActivity);
    }
}
