package com.eemf.sirgoingfar.kwuiq.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.eemf.sirgoingfar.kwuiq.R;
import com.eemf.sirgoingfar.kwuiq.api_communications.NetworkCallback;
import com.eemf.sirgoingfar.kwuiq.api_communications.controllers.UserAuthController;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.CustomerLoginResponse;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.FailureResponse;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.SuccessResponse;
import com.eemf.sirgoingfar.kwuiq.custom.AbsViewHolder;
import com.eemf.sirgoingfar.kwuiq.models.user.UserData;
import com.eemf.sirgoingfar.kwuiq.utils.UserUtil;

import java.util.Arrays;

public class LoginActivity extends BaseActivity implements NetworkCallback{

    private class LoginViewHolder extends AbsViewHolder {

        //Views
        private AutoCompleteTextView mEmailField;
        private AutoCompleteTextView mPasswordField;
        private Button mSigninBtn;
        private TextView mSignupBtn;
        private TextView mForgotPasswordBtn;
        private CheckBox mPasswordVisibility;

        //Action Views
        private View[] viewsArray = {mSigninBtn, mSignupBtn, mForgotPasswordBtn, mPasswordField, mEmailField};

        LoginViewHolder(@NonNull Context context, @NonNull View containerView) {
            super(context, containerView);
        }

        @Override
        protected void init(View containerView) {

            //instantiate views
            mEmailField = (AutoCompleteTextView) findViewById(R.id.atv_email);
            mPasswordField = (AutoCompleteTextView) findViewById(R.id.atv_password);
            mSigninBtn = (Button) findViewById(R.id.btn_sign_in);
            mSignupBtn = (TextView) findViewById(R.id.tv_sign_up);
            mForgotPasswordBtn = (TextView) findViewById(R.id.tv_forgotten_password);
            mPasswordVisibility = (CheckBox) findViewById(R.id.cb_password_visibility_toggler);

            //apply click listeners
            applyListenersToViews();

            //password visibility toggle
            mPasswordVisibility.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (TextUtils.isEmpty(mPasswordField.getText().toString().trim()))
                        return;

                    //handle toggle
                    mPasswordField.setInputType(isChecked ? InputType.TYPE_TEXT_VARIATION_PASSWORD :
                            InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                    mPasswordField.setSelection(mPasswordField.getText().toString().length());

                    //change toggle icon
                    if (isChecked)
                        mPasswordVisibility.setButtonDrawable(R.drawable.ic_visibility_off);
                    else
                        mPasswordVisibility.setButtonDrawable(R.drawable.ic_visibility);
                }

            });
        }

        void applyListenersToViews() {
            //apply listener to Sign in button
            mSigninBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toggleActionViewsEnable(Arrays.asList(viewsArray), false);
                    validateLogin();
                }
            });

            //apply listener to Sign Up button
            mSignupBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openRegisterActivity();
                }
            });

            //apply listener to Forgot Password button
            mForgotPasswordBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openForgottenPasswordActivity();
                }
            });
        }

        void openRegisterActivity() {
            Intent openRegisterActivity = new Intent(mContext, RegisterActivity.class);
            startActivity(openRegisterActivity);
        }

        void openForgottenPasswordActivity() {
            Intent openForgottenPasswordActivity = new Intent(mContext, RecoverPasswordActivity.class);
            startActivity(openForgottenPasswordActivity);
        }

        AutoCompleteTextView getmEmailField() {
            return mEmailField;
        }

        AutoCompleteTextView getmPasswordField() {
            return mPasswordField;
        }

        public void toggleViewsEnable(boolean isEnable) {
            toggleActionViewsEnable(Arrays.asList(viewsArray), isEnable);
        }
    }

    private LoginViewHolder viewHolder;

    private String verifiedEmail;
    private String verifiedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewHolder = new LoginViewHolder(this, findViewById(android.R.id.content).getRootView());
    }

    void validateLogin() {

        AutoCompleteTextView emailField = viewHolder.getmEmailField();
        AutoCompleteTextView passwordField = viewHolder.getmPasswordField();

        //disable views
        viewHolder.toggleViewsEnable(false);

        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        //check email validity
        if (!UserUtil.isValidEmail(email)) {

            //resolve error message
            String errorMessage;
            if (TextUtils.isEmpty(emailField.getText().toString().trim()))
                errorMessage = this.getString(R.string.email_field_cannot_be_empty);
            else
                errorMessage = this.getString(R.string.email_invalidity_response_text);

            viewHolder.setErrorMessage(emailField, errorMessage);
            viewHolder.toggleViewsEnable(true);
            return;
        }

        verifiedEmail = email;

        //check password validity
        if (!isValidPassword(password)) {

            //resolve error message
            String errorMessage = UserUtil.resolvePasswordErrorMessage(this, passwordField.getText().toString().trim());

            viewHolder.setErrorMessage(passwordField, errorMessage);
            viewHolder.toggleViewsEnable(true);
            return;
        }

        verifiedPassword = password;

        //validate user at the server level
        validateAndSignUserIn(email, password);
    }

    void validateAndSignUserIn(@NonNull String verifiedEmail, @NonNull String verifiedPassword) {
        UserAuthController controller = new UserAuthController(this, this, true, false);
        controller.customerSignIn(verifiedEmail, verifiedPassword);
    }

    private boolean isValidPassword(@NonNull String password) {
        return !TextUtils.isEmpty(password);
    }

    @Override
    public void onNetworkSuccess(SuccessResponse response) {

        if (response instanceof CustomerLoginResponse.Success) {

            CustomerLoginResponse.Success loginResponse = (CustomerLoginResponse.Success) response;
            UserData data = loginResponse.getUser();

            //if the data is NULL, the customer is not authenticated
            if(data == null)
                return;

            setIsCustomerVerified(true);
            setUserSessionAndOpenDashboard(verifiedEmail, verifiedPassword, data);
        }
    }

    @Override
    public void onNetworkFailure(FailureResponse response) {
        //Todo: General Error Handling ought to be done
        if (isActivityStarted()) {
            viewHolder.toggleViewsEnable(true);
            Toast.makeText(this, response.getErrorMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
