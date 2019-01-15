package com.eemf.sirgoingfar.kwuiq.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eemf.sirgoingfar.kwuiq.R;

public class RecoverPasswordStep2Fragment extends BaseFragment{

    public static RecoverPasswordStep2Fragment newInstance() {
        Bundle args = new Bundle();
        RecoverPasswordStep2Fragment fragment = new RecoverPasswordStep2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recover_password_step_2, container, false);
        return view;
    }
}
