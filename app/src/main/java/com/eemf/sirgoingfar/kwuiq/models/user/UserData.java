package com.eemf.sirgoingfar.kwuiq.models.user;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("lastName")
    private String surname;

    @SerializedName("firstName")
    private String otherName;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("emailAddress")
    private String email;

    @SerializedName("profilePicture")
    private String profilePicture;

    @SerializedName("creationDate")
    private String creationDate;

    @SerializedName("customerId")
    private String customerId;

    @SerializedName("enabled")
    private boolean isEnabled;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("roleId")
    private String roleId;

    @SerializedName("roleName")
    private String roleName;

    private String password;

    UserData(Builder builder) {
        this.surname = builder.getSurname();
        this.otherName = builder.getOtherName();
        this.fullName = builder.getFullName();
        this.email = builder.getEmail();
        this.profilePicture = builder.getProfilePicture();
        this.creationDate = builder.getCreationDate();
        this.customerId = builder.getCustomerId();
        this.isEnabled = builder.isEnabled();
        this.phoneNumber = builder.getPhoneNumber();
        this.roleId = builder.getRoleId();
        this.roleName = builder.getRoleName();
        this.password = builder.getPassword();
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

    public String getFullName() {
        return fullName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public static class Builder {
        private String surname;
        private String otherName;
        private String phoneNumber;
        private String email;
        private String password;
        private String profilePicture;
        private String creationDate;
        private String customerId;
        private boolean isEnabled;
        private String fullName;
        private String roleId;
        private String roleName;

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

        public Builder setCreationDate(String creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder setCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setEnabled(boolean enabled) {
            isEnabled = enabled;
            return this;
        }

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setRoleId(String roleId) {
            this.roleId = roleId;
            return this;
        }

        public Builder setRoleName(String roleName) {
            this.roleName = roleName;
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

        private String getCreationDate() {
            return creationDate;
        }

        private String getCustomerId() {
            return customerId;
        }

        private boolean isEnabled() {
            return isEnabled;
        }

        private String getFullName() {
            return fullName;
        }

        private String getRoleId() {
            return roleId;
        }

        private String getRoleName() {
            return roleName;
        }

        public UserData build(){
            return new UserData(this);
        }
    }
}
