package com.kingdee.exceptions;

/**
 * RESTful接口通用异常
 * @author coder
 */
public class RestApiException extends RuntimeException {
    private static final long serialVersionUID = -4157087332819752180L;

    public static final int SC_BAD_REQUEST = 400;

    public static final int SC_CONFLICT = 409;

    private final int statusCode;

    public RestApiException() {
        super();
        this.statusCode = SC_BAD_REQUEST;
    }

    public RestApiException(String s) {
        super(s);
        this.statusCode = SC_BAD_REQUEST;
    }

    public RestApiException(int statusCode, String s) {
        super(s);
        this.statusCode = statusCode;
    }

    public RestApiException(int statusCode, String message, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public static RestApiException notFound(String message) {
        return new RestApiException(404, message);
    }

    public static RestApiException conflict(String message) {
        return new RestApiException(SC_CONFLICT, message);
    }

    public static RestApiException conflict(String message, Throwable cause) {
        return new RestApiException(SC_CONFLICT, message, cause);
    }

    public static RestApiException serverError(String message, Throwable cause) {
        return new RestApiException(500, message, cause);
    }
}
