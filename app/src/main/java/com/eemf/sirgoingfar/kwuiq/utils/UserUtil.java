package com.eemf.sirgoingfar.kwuiq.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.TextView;

import com.eemf.sirgoingfar.kwuiq.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUtil {

    public final static String VALID_EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public final static String VALID_PHONE_NUMBER_PATTERN = "^([0]|[234])\\d{10}";

    public static boolean isValidEmail(@NonNull String email) {

        if (!TextUtils.isEmpty(email)) {
            final Pattern VALID_EMAIL_ADDRESS_REGEX =
                    Pattern.compile(VALID_EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            return matcher.find();
        }

        return false;
    }

    public static boolean isValidPassword(@NonNull String password) {
        if (!TextUtils.isEmpty(password)) {
            return passwordContainsLowerCase(password) &&
                    passwordContainsUpperCase(password) &&
                    passwordContainsNumericCharacter(password) &&
                    passwordContainsSpecialCharacter(password) &&
                    passwordContainsMinimumCharacters(password);
        }

        return false;
    }

    public static String resolvePasswordErrorMessage(@NonNull Context context, @NonNull String passwordText) {

        String errorMessage = "";

        //is not empty
        if (TextUtils.isEmpty(passwordText))
            errorMessage = context.getString(R.string.password_field_cannot_be_empty);

        //contains lowercase characters (at least 1)
        else if (!passwordContainsLowerCase(passwordText))
            errorMessage = context.getString(R.string.password_must_contain_at_least_one_lowercase_character);

        //contains uppercase characters (at least 1)
        else if (!passwordContainsUpperCase(passwordText))
            errorMessage = context.getString(R.string.password_must_contain_at_least_one_uppercase_character);

        //contains numeric characters (at least 1)
        else if (!passwordContainsNumericCharacter(passwordText))
            errorMessage = context.getString(R.string.password_must_contain_at_least_one_numeric_character);

        //contains special character (at least 1)
        else if (!passwordContainsSpecialCharacter(passwordText))
            errorMessage = context.getString(R.string.password_must_contain_at_least_one_special_character);

        //minimum of 8 characters
        else if (!passwordContainsMinimumCharacters(passwordText))
            errorMessage = context.getString(R.string.password_must_have_at_least_8_characters);


        return errorMessage;
    }

    private static boolean passwordContainsNumericCharacter(@NonNull String passwordText) {

        char[] passwordCharacters = passwordText.toCharArray();

        for (char character : passwordCharacters) {

            if (Character.isDigit(character)) {
                return true;
            }
        }

        return false;
    }

    private static boolean passwordContainsSpecialCharacter(@NonNull String passwordText) {
        char[] passwordCharacters = passwordText.toCharArray();

        for (char character : passwordCharacters) {

            if (!Character.isLetterOrDigit(character) && !Character.isSpaceChar(character)) {
                return true;
            }
        }

        return false;
    }

    private static boolean passwordContainsUpperCase(@NonNull String passwordText) {

        char[] passwordCharacters = passwordText.toCharArray();

        for (char character : passwordCharacters) {

            if (Character.isUpperCase(character)) {
                return true;
            }
        }

        return false;
    }

    private static boolean passwordContainsLowerCase(@NonNull String passwordText) {

        char[] passwordCharacters = passwordText.toCharArray();

        for (char character : passwordCharacters) {

            if (Character.isLowerCase(character)) {
                return true;
            }
        }

        return false;
    }

    private static boolean passwordContainsMinimumCharacters(@NonNull String passwordText) {
        return passwordText.length() >= Constants.STANDARD_PASSWORD_LENGTH;
    }

    public static boolean isValidName(@NonNull String name) {
        return !TextUtils.isEmpty(name);
    }

    public static boolean isValidPhoneNumber(@NonNull String phoneNumber) {

        if(TextUtils.isEmpty(phoneNumber))
            return false;

        if(phoneNumber.startsWith("+"))
            phoneNumber = phoneNumber.substring(1, phoneNumber.length() - 1);

        return Pattern.compile(VALID_PHONE_NUMBER_PATTERN, Pattern.CASE_INSENSITIVE)
                .matcher(phoneNumber).find();
    }
}
