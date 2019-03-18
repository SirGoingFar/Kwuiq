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
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.CreateCustomerResponse;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.FailureResponse;
import com.eemf.sirgoingfar.kwuiq.api_communications.responses.SuccessResponse;
import com.eemf.sirgoingfar.kwuiq.custom.AbsViewHolder;
import com.eemf.sirgoingfar.kwuiq.models.user.UserData;
import com.eemf.sirgoingfar.kwuiq.utils.Constants;
import com.eemf.sirgoingfar.kwuiq.utils.UserUtil;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends BaseActivity implements NetworkCallback {

    class RegisterActivityViewHolder extends AbsViewHolder {

        private CircleImageView mDisplayPicture;
        private AutoCompleteTextView mSurname;
        private AutoCompleteTextView mOtherName;
        private AutoCompleteTextView mPhoneNumber;
        private AutoCompleteTextView mEmail;
        private AutoCompleteTextView mPassword;
        private AutoCompleteTextView mConfirmPassword;
        private CheckBox mPasswordVisibilityToggler;
        private CheckBox mConfirmPasswordVisibilityToggler;
        private Button mRegisterButton;
        private TextView mSignInButton;

        //Action Views
        private View[] viewsArray = {mSurname, mOtherName, mPhoneNumber, mEmail, mPassword, mConfirmPassword, mRegisterButton, mSignInButton};

        protected RegisterActivityViewHolder(@NonNull Context context, @NonNull View containerView) {
            super(context, containerView);
        }

        @Override
        protected void init(View containerView) {
            //initialize views
            mDisplayPicture = (CircleImageView) findViewById(R.id.civ_display_picture);
            mSurname = (AutoCompleteTextView) findViewById(R.id.atv_surname);
            mOtherName = (AutoCompleteTextView) findViewById(R.id.atv_other_names);
            mPhoneNumber = (AutoCompleteTextView) findViewById(R.id.atv_phone_number);
            mEmail = (AutoCompleteTextView) findViewById(R.id.atv_email);
            mPassword = (AutoCompleteTextView) findViewById(R.id.atv_password);
            mConfirmPassword = (AutoCompleteTextView) findViewById(R.id.atv_confirm_password);
            mRegisterButton = (Button) findViewById(R.id.btn_register);
            mSignInButton = (TextView) findViewById(R.id.tv_sign_in);
            mPasswordVisibilityToggler = (CheckBox) findViewById(R.id.cb_password_visibility_toggler);
            mConfirmPasswordVisibilityToggler = (CheckBox) findViewById(R.id.cb_confirm_password_visibility_toggler);

            //add listeners to views
            applyListenerToViews();
        }

        private void applyListenerToViews() {
            mPasswordVisibilityToggler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (TextUtils.isEmpty(mPassword.getText().toString().trim()))
                        return;

                    //handle toggle
                    mPassword.setInputType(isChecked ? InputType.TYPE_TEXT_VARIATION_PASSWORD :
                            InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                    mPassword.setSelection(mPassword.getText().toString().length());

                    //change toggle icon
                    if (isChecked)
                        mPasswordVisibilityToggler.setButtonDrawable(R.drawable.ic_visibility_off);
                    else
                        mPasswordVisibilityToggler.setButtonDrawable(R.drawable.ic_visibility);
                }

            });

            mConfirmPasswordVisibilityToggler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (TextUtils.isEmpty(mConfirmPassword.getText().toString().trim()))
                        return;

                    //handle toggle
                    mConfirmPassword.setInputType(isChecked ? InputType.TYPE_TEXT_VARIATION_PASSWORD :
                            InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                    mConfirmPassword.setSelection(mConfirmPassword.getText().toString().length());

                    //change toggle icon
                    if (isChecked)
                        mConfirmPasswordVisibilityToggler.setButtonDrawable(R.drawable.ic_visibility_off);
                    else
                        mConfirmPasswordVisibilityToggler.setButtonDrawable(R.drawable.ic_visibility);
                }

            });

            mRegisterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleViewsEnable(false);
                    validateCustomerInputAndCreateCustomer();
                }
            });

            mSignInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openLoginActivity();
                }
            });
        }

        private void toggleViewsEnable(boolean isEnable) {
            toggleActionViewsEnable(Arrays.asList(viewsArray), isEnable);
        }

        void openLoginActivity() {
            Intent openLoginActivity = new Intent(mContext, LoginActivity.class);
            activityStackClearFlagSetter(openLoginActivity);
            startActivity(openLoginActivity);
        }

        private AutoCompleteTextView getmSurname() {
            return mSurname;
        }

        private AutoCompleteTextView getmOtherName() {
            return mOtherName;
        }

        private AutoCompleteTextView getmPhoneNumber() {
            return mPhoneNumber;
        }

        private AutoCompleteTextView getmEmail() {
            return mEmail;
        }

        private AutoCompleteTextView getmPassword() {
            return mPassword;
        }

        private AutoCompleteTextView getmConfirmPassword() {
            return mConfirmPassword;
        }

        private void clearPasswordFields() {
            mPassword.clearComposingText();
            mConfirmPassword.clearComposingText();
        }
    }

    private RegisterActivityViewHolder viewHolder;
    private String verifiedEmail;
    private String verifiedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        viewHolder = new RegisterActivityViewHolder(this, findViewById(android.R.id.content).getRootView());
    }

    public void validateCustomerInputAndCreateCustomer() {

        AutoCompleteTextView surnameField = viewHolder.getmSurname();
        AutoCompleteTextView otherNameField = viewHolder.getmOtherName();
        AutoCompleteTextView phoneNumberField = viewHolder.getmPhoneNumber();
        AutoCompleteTextView emailField = viewHolder.getmEmail();
        AutoCompleteTextView passwordField = viewHolder.getmPassword();
        AutoCompleteTextView confirmPasswordField = viewHolder.getmConfirmPassword();


        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String surname = surnameField.getText().toString().trim();
        String otherName = otherNameField.getText().toString().trim();
        String phoneNumber = phoneNumberField.getText().toString().trim();
        String confirmPassword = confirmPasswordField.getText().toString().trim();

        //validate surname
        if (!UserUtil.isValidName(surname)) {
            viewHolder.setErrorMessage(surnameField, getString(R.string.name_cannot_be_empty));
            viewHolder.toggleViewsEnable(true);
            return;
        }

        //validate other names
        if (!UserUtil.isValidName(otherName)) {
            viewHolder.setErrorMessage(otherNameField, getString(R.string.other_name_field_cannot_be_blank));
            viewHolder.toggleViewsEnable(true);
            return;
        }

        //validate phone number
        if (!UserUtil.isValidPhoneNumber(phoneNumber)) {
            String errorMessage;
            if (TextUtils.isEmpty(phoneNumber))
                errorMessage = getString(R.string.phone_number_field_cannot_be_blank);
            else if (phoneNumber.length() < Constants.MIN_NUMBER_OF_PHONE_NUMBER_DIGIT)
                errorMessage = getString(R.string.incomplete_phone_number);
            else
                errorMessage = getString(R.string.phone_number_is_invalid);

            viewHolder.setErrorMessage(phoneNumberField, errorMessage);
            viewHolder.toggleViewsEnable(true);
            return;
        }

        //validate email
        if (!UserUtil.isValidEmail(email)) {
            String errorMessage;
            if (TextUtils.isEmpty(email))
                errorMessage = getString(R.string.email_field_cannot_be_empty);
            else
                errorMessage = getString(R.string.email_address_is_not_valid);

            viewHolder.setErrorMessage(emailField, errorMessage);
            viewHolder.toggleViewsEnable(true);
            return;
        }

        verifiedEmail = email;

        //validate password
        if (!UserUtil.isValidPassword(password)) {
            viewHolder.setErrorMessage(passwordField, UserUtil.resolvePasswordErrorMessage(this, password));
            viewHolder.toggleViewsEnable(true);
            return;
        }

        //validate if password and confirm password match
        if (TextUtils.isEmpty(confirmPassword)) {
            viewHolder.setErrorMessage(confirmPasswordField, getString(R.string.confirm_password_field_cannot_be_blank));
            viewHolder.clearPasswordFields();
            viewHolder.toggleViewsEnable(true);
            return;
        } else if (!TextUtils.equals(password, confirmPassword)) {
            viewHolder.setErrorMessage(confirmPasswordField, getString(R.string.password_and_password_field_do_not_match));
            viewHolder.clearPasswordFields();
            viewHolder.toggleViewsEnable(true);
            return;
        }

        verifiedPassword = password;

        //create user object
        UserData user = new UserData.Builder()
                .setSurname(surname)
                .setOtherName(otherName)
                .setEmail(verifiedEmail)
                .setPhoneNumber(phoneNumber)
                .setPassword(verifiedPassword)
                .build();

        //make API call
        makeCreateCustomerRequestCall(user);
    }

    private void makeCreateCustomerRequestCall(@NonNull UserData user) {
        viewHolder.toggleViewsEnable(false);
        UserAuthController controller = new UserAuthController(this, this, true, false);
        controller.createCustomer(user);
    }

    @Override
    public void onNetworkSuccess(SuccessResponse response) {

        if (response instanceof CreateCustomerResponse.Success) {
            CreateCustomerResponse.Success createCustomerSuccessResponse = (CreateCustomerResponse.Success) response;
            setUserSessionAndOpenDashboard(verifiedEmail, verifiedPassword, createCustomerSuccessResponse.getUser());
        }
    }

    @Override
    public void onNetworkFailure(FailureResponse response) {

        //Todo: General Error Handling ought to be done
        if(isActivityStarted()){
            viewHolder.toggleViewsEnable(true);
            Toast.makeText(this, response.getErrorMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
