package com.eemf.sirgoingfar.kwuiq.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eemf.sirgoingfar.kwuiq.R;

import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView txt = findViewById(R.id.tv_sign_up);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });

        TextView txt2 = findViewById(R.id.tv_forgotten_password);
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForgottenPasswordActivity();
            }
        });
    }

    void openRegisterActivity(){
        Intent openRegisterActivity = new Intent(this, RegisterActivity.class);
        startActivity(openRegisterActivity);
    }

    void openForgottenPasswordActivity(){
        Intent openForgottenPasswordActivity = new Intent(this, RecoverPasswordActivity.class);
        startActivity(openForgottenPasswordActivity);
    }

}
