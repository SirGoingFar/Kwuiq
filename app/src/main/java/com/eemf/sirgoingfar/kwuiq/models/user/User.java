package com.eemf.sirgoingfar.kwuiq.models.user;

import android.support.annotation.NonNull;

public class User {

    private String surname;
    private String otherName;
    private String phoneNumber;
    private String email;
    private String password;
    private String profilePicture;

    private User(Builder builder) {
        this.surname = builder.getSurname();
        this.otherName = builder.getOtherName();
        this.phoneNumber = builder.getPhoneNumber();
        this.email = builder.getEmail();
        this.password = builder.getPassword();
        this.profilePicture = builder.getProfilePicture();
    }

    public String getSurname() {
        return surname;
    }

    public String getOtherName() {
        return otherName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public static class Builder {
        private String surname;
        private String otherName;
        private String phoneNumber;
        private String email;
        private String password;
        private String profilePicture;

        public Builder setSurname(@NonNull String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setOtherName(@NonNull String otherName) {
            this.otherName = otherName;
            return this;
        }

        public Builder setPhoneNumber(@NonNull String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setEmail(@NonNull String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(@NonNull String password) {
            this.password = password;
            return this;
        }

        public Builder setProfilePicture(@NonNull String profilePicture) {
            this.profilePicture = profilePicture;
            return this;
        }

        private String getSurname() {
            return surname;
        }

        private String getOtherName() {
            return otherName;
        }

        private String getPhoneNumber() {
            return phoneNumber;
        }

        private String getEmail() {
            return email;
        }

        private String getPassword() {
            return password;
        }

        private String getProfilePicture() {
            return profilePicture;
        }

        public User build(){
            return new User(this);
        }
    }
}
