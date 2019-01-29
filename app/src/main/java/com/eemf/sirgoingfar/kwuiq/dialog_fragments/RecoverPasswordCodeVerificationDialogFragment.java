package com.eemf.sirgoingfar.kwuiq.dialog_fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.eemf.sirgoingfar.kwuiq.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecoverPasswordCodeVerificationDialogFragment extends BaseDialogFragment {

    @BindView(R.id.iv_close)
    ImageView closeBtn;

    @BindView(R.id.btn_verify_code)
    Button verifyBtn;

    private OnVerifyButtonClickListener listener;

    public static RecoverPasswordCodeVerificationDialogFragment newInstance(OnVerifyButtonClickListener listener) {
        Bundle args = new Bundle();
        RecoverPasswordCodeVerificationDialogFragment fragment = new RecoverPasswordCodeVerificationDialogFragment();
        fragment.listener = listener;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_recover_password_code_verification, container, false);
        ButterKnife.bind(this, view);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible())
                    dismiss();
            }
        });

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                listener.onVerifyButtonClick();
            }
        });
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {

            //remove the title pane
            dialogWindow.requestFeature(Window.FEATURE_NO_TITLE);

            //re-scale the window
            DisplayMetrics metric = new DisplayMetrics();
            int newWidth = (int) (metric.widthPixels * 0.85);
            int newHeight = (int) (metric.heightPixels * 0.5);

            dialogWindow.setLayout(newWidth, newHeight);
        }

        //set the content view
        dialog.setContentView(R.layout.dialog_fragment_recover_password_code_verification);

        return dialog;
    }

    public interface OnVerifyButtonClickListener {
        void onVerifyButtonClick();
    }
}
