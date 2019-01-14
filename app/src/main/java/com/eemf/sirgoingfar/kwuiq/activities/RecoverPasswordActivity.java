package com.eemf.sirgoingfar.kwuiq.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.eemf.sirgoingfar.kwuiq.R;
import com.eemf.sirgoingfar.kwuiq.fragments.RecoverPasswordStep1Fragment;

public class RecoverPasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);

        startFragment(RecoverPasswordStep1Fragment.newInstance(), true);
    }

}
