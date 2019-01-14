package com.eemf.sirgoingfar.kwuiq.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eemf.sirgoingfar.kwuiq.R;
import com.eemf.sirgoingfar.kwuiq.activities.RecoverPasswordActivity;
import com.eemf.sirgoingfar.kwuiq.activities.RegisterActivity;

public class RecoverPasswordStep1Fragment extends BaseFragment {

    private RecoverPasswordActivity mContext;

    public static RecoverPasswordStep1Fragment newInstance() {
        Bundle args = new Bundle();
        RecoverPasswordStep1Fragment fragment = new RecoverPasswordStep1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof RecoverPasswordActivity)
            mContext = (RecoverPasswordActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recover_password_step1, container, false);

        TextView txt = view.findViewById(R.id.tv_sign_up);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });

        TextView txt2 = view.findViewById(R.id.tv_forgotten_password);
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForgottenPasswordActivity();
            }
        });

        return view;
    }

    void openRegisterActivity(){
        Intent openRegisterActivity = new Intent(mContext, RegisterActivity.class);
        mContext.activityStackClearFlagSetter(openRegisterActivity);
        startActivity(openRegisterActivity);
    }

    void openForgottenPasswordActivity(){
        Intent openForgottenPasswordActivity = new Intent(mContext, RecoverPasswordActivity.class);
        mContext.activityStackClearFlagSetter(openForgottenPasswordActivity);
        startActivity(openForgottenPasswordActivity);
    }
}
