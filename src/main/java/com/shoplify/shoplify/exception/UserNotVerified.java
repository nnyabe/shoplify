package com.shoplify.shoplify.exception;

public class UserNotVerified extends Exception {
    private boolean newEmailSent;;
    public UserNotVerified(boolean newEmailSent) {
        this.newEmailSent = newEmailSent;
    }
    public UserNotVerified() {
        super();
    }

    public UserNotVerified(String message) {
        super(message);
    }

    public boolean isNewEmailSent(){
        return newEmailSent;
    }
}
