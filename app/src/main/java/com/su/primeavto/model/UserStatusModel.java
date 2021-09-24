package com.su.primeavto.model;

import com.google.gson.annotations.SerializedName;

public class UserStatusModel {

    @SerializedName("Success")
    private boolean success;
    @SerializedName("IsAccepted")
    private boolean isAccepted;
    @SerializedName("Message")
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
