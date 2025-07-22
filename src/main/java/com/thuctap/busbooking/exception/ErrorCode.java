package com.thuctap.busbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    ROLE_EXISTED(1009, "Role already existed", HttpStatus.BAD_REQUEST),
    LIST_ROLE_NULL(1010, "List role null", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1011,"User not found!",HttpStatus.BAD_REQUEST),
    ACCOUNT_EXIST(1012,"Account already exists!",HttpStatus.BAD_REQUEST),
    INVALID_OTP(1013,"Invalid OTP code",HttpStatus.BAD_REQUEST),
    UPLOAD_FAILED(1014,"Image upload failed",HttpStatus.BAD_REQUEST),
    PHONE_EXIST(1012,"Phone already exists!",HttpStatus.BAD_REQUEST),
    CCCD_EXIST(1012,"CCCD already exists!",HttpStatus.BAD_REQUEST),
    EMAIL_EXIST(1012,"Email already exists!",HttpStatus.BAD_REQUEST),
    PHOTO_UPLOAD_FAILED(1012,"Photo upload failed!",HttpStatus.BAD_REQUEST),
    TICKET_LIST_FAILED(1018,"Error when getting bus ticket list!",HttpStatus.BAD_REQUEST),

    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
