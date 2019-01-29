package com.eemf.sirgoingfar.kwuiq.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.eemf.sirgoingfar.kwuiq.R;
import com.eemf.sirgoingfar.kwuiq.activities.RecoverPasswordActivity;

public class RecoverPasswordStep2Fragment extends BaseFragment{


    private RecoverPasswordActivity mContext;

    public static RecoverPasswordStep2Fragment newInstance() {
        Bundle args = new Bundle();
        RecoverPasswordStep2Fragment fragment = new RecoverPasswordStep2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof RecoverPasswordActivity)
            mContext = (RecoverPasswordActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recover_password_step_2, container, false);

        final ProgressBar pb = view.findViewById(R.id.pb_progress_loader);

        Button btn = view.findViewById(R.id.btn_reset_password);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);
                //pop dialog fragment

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //clear backstack
                        //take action
                    }
                }, 3000);
            }
        });


        return view;
    }
}
