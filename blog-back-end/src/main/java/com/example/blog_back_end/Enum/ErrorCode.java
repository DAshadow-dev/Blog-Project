package com.example.blog_back_end.Enum;

public enum ErrorCode {
    USER_EXISTED(1001,"User already exists"),
    USER_NOT_EXISTED(1002,"User is not exist in the database"),
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized exception"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;    

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
