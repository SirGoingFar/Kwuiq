package com.eemf.sirgoingfar.kwuiq.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eemf.sirgoingfar.kwuiq.R;
import com.eemf.sirgoingfar.kwuiq.activities.LoginActivity;
import com.eemf.sirgoingfar.kwuiq.activities.RecoverPasswordActivity;
import com.eemf.sirgoingfar.kwuiq.activities.RegisterActivity;
import com.eemf.sirgoingfar.kwuiq.dialog_fragments.RecoverPasswordCodeVerificationDialogFragment;

public class RecoverPasswordStep1Fragment extends BaseFragment implements RecoverPasswordCodeVerificationDialogFragment.OnVerifyButtonClickListener {

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

        TextView txt = view.findViewById(R.id.tv_sign_in);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });

        TextView txt2 = view.findViewById(R.id.tv_sign_up);
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });

        final ProgressBar pb = view.findViewById(R.id.pb_progress_loader);

        Button btn = view.findViewById(R.id.btn_recover_password);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);
                //pop dialog fragment

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Todo: clear backstack
                        DialogFragment dialogFragment = RecoverPasswordCodeVerificationDialogFragment.newInstance(RecoverPasswordStep1Fragment.this);
                        dialogFragment.show(mContext.getSupportFragmentManager(), RecoverPasswordCodeVerificationDialogFragment.class.getName());
                    }
                }, 3000);
            }
        });

        return view;
    }

    void openRegisterActivity(){
        Intent openRegisterActivity = new Intent(mContext, RegisterActivity.class);
        mContext.activityStackClearFlagSetter(openRegisterActivity);
        startActivity(openRegisterActivity);
    }

    void openLoginActivity(){
        Intent openLoginActivity = new Intent(mContext, LoginActivity.class);
        mContext.activityStackClearFlagSetter(openLoginActivity);
        startActivity(openLoginActivity);
    }

    @Override
    public void onVerifyButtonClick() {
        mContext.startFragment(RecoverPasswordStep2Fragment.newInstance(), true);
    }
}
