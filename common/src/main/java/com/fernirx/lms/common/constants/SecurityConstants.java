package com.fernirx.lms.common.constants;

public final class SecurityConstants {

    // ========== JWT ==========
    public static final String JWT_ACCESS_TOKEN = "access_token";
    public static final String JWT_REFRESH_TOKEN = "refresh_token";
    public static final String JWT_CLAIMS_TYPE = "type";
    public static final String JWT_CLAIMS_USERNAME = "username";
    public static final String JWT_CLAIMS_AUTHORITIES = "authorities";

    private SecurityConstants() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
    }
}
