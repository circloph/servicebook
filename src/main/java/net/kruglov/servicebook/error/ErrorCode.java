package net.kruglov.servicebook.error;

public enum ErrorCode {

    INVALID_REVIEW_ID("You can only change your review", "reviewId"),
    REVIEW_ALREADY_EXISTS("You can write only one review", "review");


    private String message;
    private String field;

    ErrorCode(String message, String field) {
        this.message = message;
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }
    
}
